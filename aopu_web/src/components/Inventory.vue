<template>
  <div>
    <el-breadcrumb separator="/" style="margin-bottom: 10px">
      <el-breadcrumb-item :to="{ path: '/Home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>产品库</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row type="flex" class="row-bg" style="padding-bottom: 5px;font-size: small">
      <el-input
          placeholder="输入搜索名称..."
          style="width: 200px; margin-right: 10px"
          suffix-icon="el-icon-search"
          v-model:value="searchName"
          @keyup.enter.native="searchByName"></el-input>
      <el-button type="success" @click="searchByName" icon="el-icon-search">按名称查询</el-button>
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
      <el-table-column prop="category" label="品类" >
      </el-table-column>
      <el-table-column prop="goodName" label="商品名称" sortable>
      </el-table-column>
      <el-table-column prop="factoryPrice" label="出厂价" width="70">
      </el-table-column>
      <el-table-column prop="transactionPrice" label="成交价"  width="70">
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
    <el-dialog title="添加|修改产品" :visible.sync="dialogFormVisible" class="demo-ruleForm">
      <el-form :model="form">

        <el-form-item label="品类" :label-width="formLabelWidth" hide-required-asterisk=false
                      prop="category"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.category" autocomplete="off"></el-input>
        </el-form-item>


        <el-form-item label="商品名称" :label-width="formLabelWidth"
                      prop="goodName"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.goodName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="出厂价" :label-width="formLabelWidth"
                      prop="factoryPrice"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.factoryPrice" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="成交价" :label-width="formLabelWidth"
                      prop="transactionPrice"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.transactionPrice" autocomplete="off"></el-input>
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
        <el-table-column prop="category" label="品类" >
        </el-table-column>
        <el-table-column prop="goodName" label="商品名称">
        </el-table-column>
        <el-table-column prop="factoryPrice" label="出厂价" >
        </el-table-column>
        <el-table-column prop="transactionPrice" label="成交价" >
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
        category: '',
        goodName: '',
        factoryPrice: '',
        transactionPrice: '',
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

      this.form.category = row.category
      this.form.goodName = row.goodName
      this.form.factoryPrice = row.factoryPrice
      this.form.transactionPrice = row.transactionPrice

      this.dialogFormVisible = true;
    },
    deleteStore(id){
      this.$confirm('确认删除？')
          .then(_ => {
            console.log(id)
            this.$axios({
              url: this.$httpUrl + '/delete/deleteInventory',
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
                this.searchByName();
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
              url:this.$httpUrl + '/add/addInventory',
              method:'post',
              headers: {
                token: localStorage.getItem("token")
              },
              data:{
                "id":this.form.id,

                "category":this.form.category,
                "factoryPrice":this.form.factoryPrice,
                "goodName":this.form.goodName,
                "transactionPrice":this.form.transactionPrice,

              }
            }).then((res)=> {
              console.log("添加成功")
              if (res.data.code === 200) {
                this.$message({
                  message: '恭喜你，这是一条成功消息',
                  type: 'success'
                });
                this.searchByName();
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
    loadData(){
      this.tableData = JSON.parse(localStorage.getItem("inventory"));
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
        url:this.$httpUrl+"/find/findAllInventory",
        method: 'post',
        headers: {
          token: localStorage.getItem("token")
        },
        params:{
          "inventoryName":this.searchName,
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
          localStorage.setItem("inventory",JSON.stringify(res.data.data.inventory))
          this.total = res.data.data.inventoryLength
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