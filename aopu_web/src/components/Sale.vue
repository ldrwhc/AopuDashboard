<template>
  <div>
    <el-breadcrumb separator="/" style="margin-bottom: 10px">
      <el-breadcrumb-item :to="{ path: '/Home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>门店销售统计表</el-breadcrumb-item>
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

      <el-button type="primary" icon="el-icon-plus" style="margin-right: 10px" @click="dialogFormVisible = true">添加</el-button>



    </el-row>
    <el-table :data="tableData"
              :default-sort = "{prop: 'date', order: 'descending'}"
              :header-cell-style="{background:'pink',color:'#555'}"
              border
              show-summary
              ref="table"
              @filter-change="_filterChange"
              @selection-change="handleSelectionChange"
              :row-key="getRowKeys"
    >
      <el-table-column type="selection" width="49" :reserve-selection="true">
      </el-table-column>
      <el-table-column prop="province" label="省" width="70">
      </el-table-column>
      <el-table-column prop="city" label="市" width="70">
      </el-table-column>
      <el-table-column prop="agentName" label="代理商公司名称"
                       :filters="filterAgentName()"
                       :filter-method="filterTag"
                       filter-placement="bottom-end">
      </el-table-column>
      <el-table-column prop="storeName" label="门店名称"
                       :filters="filterStoreName()"
                       :filter-method="filterTag"
                       filter-placement="bottom-end"
      >
      </el-table-column>
      <el-table-column prop="salesman" label="业务员"
                       :filters="filterData()"
                       :filter-method="filterTag"
                       filter-placement="bottom-end"
                       width="70"
      >
      </el-table-column>
      <el-table-column prop="price" label="销售金额">
      </el-table-column>
      <el-table-column prop="goodName" label="商品名称">
      </el-table-column>
      <el-table-column prop="amount" label="数量" sortable>
      </el-table-column>
      <el-table-column prop="writeDate" label="填写日期">
      </el-table-column>
      <el-table-column prop="orderImgUrl" label="订单图片">
        <template slot-scope="scope">
          <el-image
              style="width: 100px; height: 100px"
              :src="scope.row.orderImgUrl"></el-image>
        </template>

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
    <el-dialog title="添加|修改门店销售统计信息" :visible.sync="dialogFormVisible" class="demo-ruleForm">
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

        <el-form-item label="代理商公司名称" :label-width="formLabelWidth" hide-required-asterisk=false
                      prop="agentName"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.agentName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="填写日期" :label-width="formLabelWidth"
                      prop="writeDate"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <div class="block">
            <el-date-picker
                style="position: initial;width : 100%;height : 100%;"
                v-model="form.writeDate"
                type="date"
                placeholder="选择日期">
            </el-date-picker>
          </div>
        </el-form-item>

        <el-form-item label="门店名称" :label-width="formLabelWidth"
                      prop="storeName"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.storeName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="业务员" :label-width="formLabelWidth"
                      prop="salesman"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.salesman" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="销售金额" :label-width="formLabelWidth"
                      prop="price"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.price" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="商品名称" :label-width="formLabelWidth"
                      prop="goodName"
                      :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model="form.goodName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="数量" :label-width="formLabelWidth"
                      prop="amount"
           :rules="[
             { required: true, message: '不能为空'},
           ]"
        >
          <el-input v-model.number="form.amount" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="订单图片" :label-width="formLabelWidth"
                      prop="orderImgUrl"
        >
          <template slot-scope="scope">
            <el-upload
                class="upload-demo"
                action="http://localhost:8089/file/upload"
                :on-change="handleChange"
                :on-success="submitUpload"
                :data={id:form.id}
                :limit=1
                accept="fileTypes"
                :auto-upload="true"
                list-type="picture"
                :before-upload="beforeAvatarUpload">
              <el-button slot="trigger" type="primary" >选取文件（只能上传jpg/png文件，且不超过500kb）</el-button>
            </el-upload>
          </template>

        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleClose">确 定</el-button>
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
  name: "Sale",
  data() {
    return {
      selectData:[],
      selectWindow:false,
      dialogFormVisible: false,
      form: {
        id:'',
        provinceAndCity: [],
        agentName: '',
        storeName: '',
        salesman: '',
        price: '',
        goodName: '',
        amount: '',
        writeDate: '',
        orderImgUrl:''
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
      fileTypes :["image/apng", "image/bmp", "image/gif", "image/jpeg", "image/png", "image/svg+xml",]
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

    filterTag(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 1;

      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isLt2M;
    },
    handleChange(file, fileLists) {
      console.log(file.name);
    },
    submitUpload(res) {
      if (res.code === 200){
        this.form.orderImgUrl = 'http://localhost:8089/images/' + res.msg
        this.$message.success("图片"+res.msg+"上传成功")
      }else{
        this.$message.warning("上传失败"+res.msg)
      }

    },
    updateStore(row){
      console.log(row)
      let t = []
      t.push(row.province)
      t.push(row.city)
      this.form.id = row.id
      this.form.provinceAndCity = t
      this.form.agentName = row.agentName
      this.form.storeName = row.storeName
      this.form.salesman = row.salesman
      this.form.price = row.price
      this.form.goodName = row.goodName
      this.form.amount = row.amount
      this.form.writeDate = row.writeDate
      this.form.orderImgUrl = row.orderImgUrl

      this.dialogFormVisible = true;
    },
    deleteStore(id){
      this.$confirm('确认删除？')
          .then(_ => {
            console.log(id)
            this.$axios({
              url: this.$httpUrl + '/delete/deleteSale',
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
              url:this.$httpUrl + '/add/addSale',
              method:'post',
              headers: {
                token: localStorage.getItem("token")
              },
              data:{
                "id":this.form.id,
                "province":this.form.provinceAndCity[0],
                "city":this.form.provinceAndCity[1],
                "agentName":this.form.agentName,
                "storeName":this.form.storeName,
                "salesman":this.form.salesman,
                "price":this.form.price,
                "goodName":this.form.goodName,
                "amount":this.form.amount,
                "writeDate":this.form.writeDate,
                "orderImgUrl":this.form.orderImgUrl
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
        url:this.$httpUrl+"/find/findAllSale",
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
          localStorage.setItem("sale",JSON.stringify(res.data.data.sale))
          this.total = res.data.data.saleLength
          this.loadData()

        }

      })
    },
    loadData(){
      this.tableData = JSON.parse(localStorage.getItem("sale"));
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
        url:this.$httpUrl+"/find/findAllSale",
        method: 'post',
        headers: {
          token: localStorage.getItem("token")
        },
        params:{
          "saleName":this.searchName,
        },
        data:{
          "pageNum":this.pageNum,
          "pageSize":this.pageSize
        }
      }).then((res)=>{
        // console.log(res.data.data)
        // 判断code是否为200
        if(res.data.code === 200){
          // 成功，把值赋值给this.books
          // console.log(res.data.data.store)
          localStorage.setItem("sale",JSON.stringify(res.data.data.sale))
          this.total = res.data.data.saleLength
          this.loadData()
        }
      })

    },
    _filterChange() {
      this.total = this.$refs.table.tableData.length;
      console.log(this.total);
    },
  },
  computed: {
    filterDate(){
      return function (){
        let obj = [];
        this.tableData.forEach(function(sale) {
          if (!obj.some(function(item) {
            return item.text === sale.date;
          })) {
            obj.push({
              text:sale.date,
              value:sale.date
            });
          }
        })
        return obj;
      }
    },
    filterData(){
      return function (){
        let obj = [];
        this.tableData.forEach(function(sale) {
          if (!obj.some(function(item) {
            return item.text === sale.salesman;
          })) {
            obj.push({
              text:sale.salesman,
              value:sale.salesman
            });
          }
        })
        return obj;
      }
    },
    filterStoreName(){
      return function () {
        let obj = [];
        //找到对应的数据 并添加到obj
        this.tableData.forEach(function (sale) {
          if (!obj.some(function (item) {
            //这行要改
            return item.text === sale.storeName;
          })) {
            obj.push({
              //这行要改
              text: sale.storeName,
              //这行要改
              value: sale.storeName
            });
          }
        })
        return obj;
      }
    },

    filterAgentName(){
      return function (){
        let obj = [];
        //找到对应的数据 并添加到obj
        this.tableData.forEach(function(sale) {
          if (!obj.some(function(item) {
            return item.text === sale.agentName;
          })) {
            obj.push({
              text:sale.agentName,
              value:sale.agentName
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