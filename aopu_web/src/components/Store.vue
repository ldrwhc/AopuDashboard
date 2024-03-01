<template>
  <div>
    <el-breadcrumb separator="/" style="margin-bottom: 10px">
      <el-breadcrumb-item :to="{ path: '/Home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>门店信息</el-breadcrumb-item>
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
      <el-table-column prop="openTime" label="开业日期" sortable width="93">
      </el-table-column>
      <el-table-column prop="segmentation" label="市场分类">
      </el-table-column>
      <el-table-column prop="shopGuide" label="导购"
                       :filters="filterShopGuide()"
                       :filter-method="filterTag"
                       filter-placement="bottom-end">
      </el-table-column>
      <el-table-column prop="storeName" label="门店名称">
      </el-table-column>
      <el-table-column prop="storeClass" label="门店类别" sortable>
<!--        <template slot-scope="scope">-->
<!--          <el-tag-->
<!--              :type="scope.row.storeClass === '0' ? 'primary' : (scope.row.storeClass === '1' ? 'success' : 'danger')"-->
<!--              disable-transitions>{{scope.row.storeClass === '0' ? '类1' : (scope.row.storeClass === '1' ? '类2' : '类3')}}</el-tag>-->
<!--        </template>-->
      </el-table-column>
      <el-table-column prop="telephone" label="电话">
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
        :total="total"
        >
    </el-pagination>
    <el-divider></el-divider>

<!--    弹出添加表单-->
    <el-dialog title="添加|修改门店信息" :visible.sync="dialogFormVisible" class="demo-ruleForm">
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

        <el-form-item label="开业日期" :label-width="formLabelWidth"
                      prop="openTime"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <div class="block">
            <el-date-picker
                style="position: initial;width : 100%;height : 100%;"
                v-model="form.openTime"
                type="date"
                placeholder="选择日期">
            </el-date-picker>
          </div>
        </el-form-item>

        <el-form-item label="市场分类" :label-width="formLabelWidth"
                      prop="segmentation"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.segmentation" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="导购" :label-width="formLabelWidth"
                      prop="shopGuide"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.shopGuide" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="门店名称" :label-width="formLabelWidth"
                      prop="storeName"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.storeName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="门店类别" :label-width="formLabelWidth"
                      prop="storeClass"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.storeClass" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="面积" :label-width="formLabelWidth"
                      prop="area"
           :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model.number="form.area" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="电话" :label-width="formLabelWidth"
                      prop="telephone"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.telephone" autocomplete="off"></el-input>
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
        <el-table-column prop="province" label="省" width="70">
        </el-table-column>
        <el-table-column prop="city" label="市" width="70">
        </el-table-column>
        <el-table-column prop="corporateName" label="公司名称">
        </el-table-column>
        <el-table-column prop="openTime" label="开业日期">
        </el-table-column>
        <el-table-column prop="segmentation" label="市场分类">
        </el-table-column>
        <el-table-column prop="shopGuide" label="导购">
        </el-table-column>
        <el-table-column prop="storeName" label="门店名称">
        </el-table-column>
        <el-table-column prop="storeClass" label="门店类别" sortable>
        </el-table-column>
        <el-table-column prop="telephone" label="电话">
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="exportExcel">确定保存</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
import axios from 'axios';
import {
  pcTextArr,
} from "element-china-area-data";
import htmlToExcel from "@/utils/htmlToExcel";

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
        storeName: '',
        storeClass: '',
        area: '',
        segmentation: '',
        openTime: '',
        shopGuide: '',
        telephone:''
      },
      formLabelWidth: '80px',
      searchState : 0,
      searchName:'',
      tableData: [],
      pageSize:20,
      pageNum:1,
      total:0,
      pcTextArr,
      selectedOptions: [],
    }
  },
  methods:{
    handleSelectionChange(val) {
      this.selectData = val;
    },
    _filterChange() {
      this.total = this.$refs.table.tableData.length;
      console.log(this.total);
    },
    filterTag(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },
    updateStore(row){
      console.log(row)
      let t = []
      t.push(row.province)
      t.push(row.city)
      this.form.id = row.id
      this.form.provinceAndCity = t
      this.form.corporateName = row.corporateName
      this.form.storeName = row.storeName
      this.form.storeClass = row.storeClass
      this.form.area = row.area
      this.form.segmentation = row.segmentation
      this.form.openTime = row.openTime
      this.form.shopGuide = row.shopGuide
      this.form.telephone = row.telephone

      this.dialogFormVisible = true;
    },
    deleteStore(id){
      this.$confirm('确认删除？')
          .then(_ => {
            console.log(id)
            this.$axios({
              url: this.$httpUrl + '/delete/deleteStore',
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
              url:this.$httpUrl + '/add/addStore',
              method:'post',
              headers: {
                token: localStorage.getItem("token")
              },
              data:{
                "id":this.form.id,
                "province":this.form.provinceAndCity[0],
                "city":this.form.provinceAndCity[1],
                "corporateName":this.form.corporateName,
                "storeName":this.form.storeName,
                "storeClass":this.form.storeClass,
                "area":this.form.area,
                "segmentation":this.form.segmentation,
                "openTime":this.form.openTime,
                "shopGuide":this.form.shopGuide,
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
        url:this.$httpUrl+"/find/findAllStore",
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
          localStorage.setItem("store",JSON.stringify(res.data.data.store))
          this.total = res.data.data.storeLength
          this.loadData()

        }

      })
    },
    loadData(){
      this.tableData = JSON.parse(localStorage.getItem("store"));
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
      if (this.searchName === ''){
        alert("请输入内容");
        return;
      }
      this.searchState = 1;
      this.$axios({
        url:this.$httpUrl+"/find/findAllStore",
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
          localStorage.setItem("store",JSON.stringify(res.data.data.store))
          this.total = res.data.data.storeLength
          this.loadData()
        }
      })

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
    getRowKeys(row){
      return row.id;
    }
  },
  computed: {
    filterShopGuide(){
      return function (){
        let obj = [];
        this.tableData.forEach(function(shop) {
          if (!obj.some(function(item) {
            return item.text === shop.shopGuide;
          })) {
            obj.push({
              text:shop.shopGuide,
              value:shop.shopGuide
            });
          }
        })
        return obj;
      }
    },

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