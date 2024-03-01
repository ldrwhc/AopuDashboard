<template>
  <div>
    <el-breadcrumb separator="/" style="margin-bottom: 10px">
      <el-breadcrumb-item :to="{ path: '/Home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>业务员信息</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row type="flex" class="row-bg" style="padding-bottom: 5px;font-size: small">
      <el-input
          placeholder="输入搜索名称..."
          style="width: 200px; margin-right: 10px"
          suffix-icon="el-icon-search"
          v-model:value="searchName"
          @keyup.enter.native="searchByName"></el-input>
      <el-button type="success" style="margin-right: 10px" @click="searchByName" icon="el-icon-search">按名称查询</el-button>
      <el-cascader
          :options="pcTextArr"
          v-model="selectedOptions">
      </el-cascader>
      <el-button type="primary" style="margin-left: 10px;" @click="findByProvinceAndCity" icon="el-icon-search">按省/市查询</el-button>
      <el-button type="danger" style="margin-left: 10px;" @click="resetContain" icon="el-icon-delete">重置</el-button>

      <el-button type="primary" icon="el-icon-plus" @click="dialogFormVisible = true">添加</el-button>
      <el-button type="primary" style="margin-left:10px;float:right;" @click="exportExcelSelect" icon="el-icon-download">导出Excel</el-button>
      <el-upload action="http://localhost:8089/upload/uploadStore"
                 :on-success="successUpload">
        <el-button type="primary" style="margin-left: 10px;font-size: 12px" icon="el-icon-upload2">点击上传</el-button>
      </el-upload>

    </el-row>
    <el-table :data="tableData"
              :default-sort = "{prop: 'date', order: 'descending'}"
              :header-cell-style="{background:'pink',color:'#555'}"
              border
              @selection-change="handleSelectionChange"
              show-summary
              ref="table"
              @filter-change="_filterChange"
              :row-key="getRowKeys"
    >
      <el-table-column type="selection" width="49" :reserve-selection="true">
      </el-table-column>
      <el-table-column prop="province" label="省" width="70">
      </el-table-column>
      <el-table-column prop="city" label="市" width="70">
      </el-table-column>
      <el-table-column prop="corporateName" label="公司名称">
      </el-table-column>
      <el-table-column prop="name" label="姓名" sortable >
      </el-table-column>
      <el-table-column prop="telephone" label="联系方式">
      </el-table-column>
      <el-table-column prop="age" label="年龄">
      </el-table-column>
      <el-table-column prop="education" label="学历">
      </el-table-column>
      <el-table-column prop="joinDate" label="入司时间">
      </el-table-column>
      <el-table-column prop="manageArea" label="管理区域" sortable>
      </el-table-column>

      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="updateStore(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="deleteStore(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[20, 50, 100, 200]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>

<!--    弹出添加表单-->
    <el-dialog title="添加|修改业务员信息" :visible.sync="dialogFormVisible" class="demo-ruleForm">
      <el-form :model="form">

        <el-form-item label="省|市" :label-width="formLabelWidth"
                      prop="provinceAndCity"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-cascader
              style="position: initial;width : 100%;height : 100%;"
              :options="pcTextArr"
              v-model="form.provinceAndCity">
          </el-cascader>
        </el-form-item>

        <el-form-item label="公司名称" :label-width="formLabelWidth" hide-required-asterisk=false
                      prop="corporateName"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.corporateName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="加入日期" :label-width="formLabelWidth"
                      prop="joinDate"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <div class="block">
            <el-date-picker
                style="position: initial;width : 100%;height : 100%;"
                v-model="form.joinDate"
                type="date"
                placeholder="选择日期">
            </el-date-picker>
          </div>
        </el-form-item>

        <el-form-item label="姓名" :label-width="formLabelWidth"
                      prop="name"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="联系方式" :label-width="formLabelWidth"
                      prop="telephone"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.telephone" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="年龄" :label-width="formLabelWidth"
                      prop="age"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.age" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="学历" :label-width="formLabelWidth"
                      prop="education"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.education" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="管理区域" :label-width="formLabelWidth"
                      prop="manageArea"
           :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model.number="form.manageArea" autocomplete="off"></el-input>
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleClose">确 定</el-button>
      </div>
    </el-dialog>

    <!--   导出预览 -->
    <el-dialog title="表格保存预览" width="70%" :visible.sync="selectWindow">
      <el-table :data="selectData" id="selectTable" height="380px">
        <el-table-column prop="province" label="省">
        </el-table-column>
        <el-table-column prop="city" label="市">
        </el-table-column>
        <el-table-column prop="corporateName" label="公司名称">
        </el-table-column>
        <el-table-column prop="name" label="姓名" >
        </el-table-column>
        <el-table-column prop="telephone" label="联系方式">
        </el-table-column>
        <el-table-column prop="age" label="年龄">
        </el-table-column>
        <el-table-column prop="education" label="学历">
        </el-table-column>
        <el-table-column prop="joinDate" label="入司时间">
        </el-table-column>
        <el-table-column prop="manageArea" label="管理区域">
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="exportExcel">确定保存</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>
import htmlToExcel from '@/utils/htmlToExcel'
import axios from 'axios';
import {
  pcTextArr,
} from "element-china-area-data";

export default {
  name: "Store",
  data() {
    return {
      selectData:[],
      selectWindow:false,
      dialogFormVisible: false,
      form: {
        id:'',
        provinceAndCity: [],
        corporateName: '',
        name: '',
        telephone: '',
        age: '',
        education: '',
        joinDate: '',
        manageArea: '',
      },
      formLabelWidth: '80px',
      searchState : 0,
      searchName:'',
      tableData: [],
      pageSize:50,
      pageNum:1,
      total:0,
      pcTextArr,
      selectedOptions: [],
    }
  },
  methods:{
    successUpload(res){
      console.log(res)
      if (res.code === 200) {
        this.$message({
          message: res.msg,
          type: 'success'
        });
      }else {
        this.$message({
          message: '警告哦，这是一条警告消息' + res.msg,
          type: 'warning'
        });
      }
    },
    filterTag(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },
    _filterChange() {
      this.total = this.$refs.table.tableData.length;
      console.log(this.total);
    },
    exportExcel() {
      htmlToExcel.getExcel('#selectTable','导出的自定义标题')
    },
    exportExcelSelect(){
      if (this.selectData.length < 1){
        this.$message.error('请选择要导出的内容！');
        return false;
      }
      this.selectWindow = true;
    },
    handleSelectionChange(val) {
      this.selectData = val;
    },

    updateStore(row){
      console.log(row)
      let t = []
      t.push(row.province)
      t.push(row.city)
      this.form.id = row.id
      this.form.provinceAndCity = t
      this.form.corporateName = row.corporateName
      this.form.name = row.name
      this.form.age = row.age
      this.form.education = row.education
      this.form.joinDate = row.joinDate
      this.form.telephone = row.telephone
      this.form.manageArea = row.manageArea

      this.dialogFormVisible = true;
    },
    deleteStore(id){
      this.$confirm('确认删除？')
          .then(_ => {
            console.log(id)
            this.$axios({
              url: this.$httpUrl + '/delete/deleteSalesman',
              method: 'get',
              headers: {
                token: localStorage.getItem("token")
              },
              params: {
                "id":id
              }
            }).then((res)=> {
              if (res.data.code === 200) {
                this.$message({
                  message: '删除成功',
                  type: 'success'
                });
                //刷新页面
                if (this.searchState === 1){
                  console.log("刷新")
                  this.searchByName();
                }else{
                  console.log("刷新")
                  this.findByProvinceAndCity()
                }
              }else {
                this.$message({
                  message: '警告哦，这是一条警告消息' + res.data.msg,
                  type: 'warning'
                });
              }
            })
          }).catch(_=>{

      })
    },
    handleClose() {
      this.$confirm('确认提交？')
          .then(_ => {
            console.log("1111")
            this.$axios({
              url:this.$httpUrl + '/add/addSalesman',
              method:'post',
              headers: {
                token: localStorage.getItem("token")
              },
              data:{
                "id":this.form.id,
                "province":this.form.provinceAndCity[0],
                "city":this.form.provinceAndCity[1],
                "corporateName":this.form.corporateName,
                "name":this.form.name,
                "age":this.form.age,
                "education":this.form.education,
                "joinDate":this.form.joinDate,
                "manageArea":this.form.manageArea,
                "telephone":this.form.telephone
              }
            }).then((res)=> {
              console.log("添加成功")
              if (res.data.code === 200) {
                this.$message({
                  message: '恭喜你，这是一条成功消息',
                  type: 'success'
                });

                //刷新页面
                if (this.searchState === 1){
                  console.log("刷新")
                  this.searchByName();
                }else{
                  console.log("刷新")
                  this.findByProvinceAndCity()
                }
              }else {
                this.$message({
                  message: '警告哦，这是一条警告消息' + res.data.msg,
                  type: 'warning'
                });
              }
              this.dialogFormVisible = false;
            }).catch(_=>{

            })
          })
          .catch(_ => {

          });
    },
    findByProvinceAndCity(){
      if (this.selectedOptions.toString() === ''){
        alert("请输入内容");
        return;
      }
      this.searchState = 2;
      //去除末尾的省市区
      // this.searchData()
      console.log(this.selectedOptions)
      this.$axios( {
        url:this.$httpUrl+"/find/findAllSalesman",
        method: 'post',
        headers: {
          token: localStorage.getItem("token")
        },
        params:{
          "province":this.selectedOptions[0],
          "city":this.selectedOptions[1]
        },
        data:{
          "pageNum":this.pageNum,
          "pageSize":this.pageSize
        }
      }).then((res)=>{
        if(res.data.code === 200){
          // 成功，把值赋值给this.books
          // console.log(res.data.data.store)
          localStorage.setItem("salesman",JSON.stringify(res.data.data.salesman))
          this.total = res.data.data.salesmanLength
          this.loadData()

        }

      })
    },
    loadData(){
      this.tableData = JSON.parse(localStorage.getItem("salesman"));
    },
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`);
      this.pageSize = val;
      console.log("每页 ${val} 条"+val)
      if (this.searchState === 1){
        this.searchByName();
      }else{
        this.findByProvinceAndCity()
      }
    },
    resetContain(){
      this.searchName = ''
      this.selectedOptions = []
    }
    ,
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`);
      this.pageNum = val;
      console.log("当前页: ${val}" + val)
      if (this.searchState === 1){
        this.searchByName();
      }else{
        this.findByProvinceAndCity()
      }
    },

    searchByName(){
      // if (this.searchName === ''){
      //   alert("请输入内容");
      //   return;
      // }
      this.searchState = 1;
      this.$axios({
        url:this.$httpUrl+"/find/findAllSalesman",
        method: 'post',
        headers: {
          token: localStorage.getItem("token")
        },
        params:{
          "storeName":this.searchName,
        },
        data:{
          "pageNum":this.pageNum,
          "pageSize":this.pageSize
        }
      }).then((res)=>{
        console.log(res.data.data)
        // 判断code是否为200
        if(res.data.code === 200){
          // 成功，把值赋值给this.books
          // console.log(res.data.data.store)
          localStorage.setItem("salesman",JSON.stringify(res.data.data.salesman))
          this.total = res.data.data.storeLength
          this.loadData()
        }
      })

    }
  },
  computed: {

  },
  beforeMount() {
    // this.loadData()
  },
  updated() {

  }
}
</script>

<style scoped>

</style>