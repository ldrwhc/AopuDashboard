<template>
  <div>
    <el-descriptions class="margin-top" title="用户信息" :column="3" size='medium' border>

      <template slot="extra">
          <el-button size="small" type="success" @click="updateStore">编辑</el-button>
      </template>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          用户名
        </template>
        {{user.username}}
      </el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          昵称
        </template>
        {{user.nickname}}
      </el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-mobile-phone"></i>
          公司名称
        </template>
        {{user.corporateName}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-location-outline"></i>
          门店名称
        </template>
        {{user.storeName}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          电话
        </template>
        {{user.telephone}}
      </el-descriptions-item>

    </el-descriptions>
    <el-dialog title="修改用户信息" :visible.sync="dialogFormVisible" class="demo-ruleForm">
      <el-form :model="form">
        <el-form-item label="公司名称" label-width="100px" hide-required-asterisk=false>
          <el-input v-model="form.corporateName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="代理商名称" label-width="100px" hide-required-asterisk=false>
          <el-input v-model="form.storeName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户名" label-width="100px" hide-required-asterisk=false :disabled=false>
          <el-input v-model="form.username" autocomplete="off" :disabled=true></el-input>
        </el-form-item>
        <el-form-item label="昵称" label-width="100px" hide-required-asterisk=false>
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" label-width="100px" hide-required-asterisk=false>
          <el-input v-model="form.telephone" autocomplete="off"></el-input>
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
export default {
  name: "Home",
  data(){
    return{
      dialogFormVisible: false,
      user:{
        id:'',
        corporateName:'',
        storeName:'',
        telephone:'',
        username:'',
        nickname:''
      },
      form: {
        id:'',
        corporateName:'',
        storeName:'',
        telephone:'',
        username:'',
        nickname:''
      },
    }
  },
  methods:{
    getUsername(){
      this.$axios({
        url:this.$httpUrl + '/user/userinfo',
        methods: "get",
        headers: {
          token: localStorage.getItem("token")
        }
      }).then((res)=>{
        if (res.data.code === 200){
          console.log(res.data.data)
          this.user =  res.data.data
        }
      })
    },
    updateUser(){
      this.form.corporateName = this.user.corporateName
      this.form.storeName = this.user.storeName
      this.form.telephone = this.user.telephone
      this.form.username = this.user.username
      this.form.nickname = this.user.nickname

      console.log("form" + this.form)
    },
    updateStore(){
      this.updateUser()
      this.dialogFormVisible = true;
    },
    handleClose() {
      console.log("form" + this.form)
      this.form.id = this.user.id
      this.$axios({
        url:this.$httpUrl + '/user/update',
        method:'post',
        headers: {
          token: localStorage.getItem("token")
        },
        data:{
          "id":this.form.id,
          "corporateName":this.form.corporateName,
          "storeName":this.form.storeName,
          "telephone":this.form.telephone,
          "username":this.form.username,
          "nickname":this.form.nickname
        }
      }).then((res)=>{
        if (res.data.code === 200){
          this.$message({
            message: res.data.msg,
            type: 'success'
          });
        }else{
          this.$message({
            message: res.data.msg,
            type: 'warning'
          });
        }
      }).catch((res)=>{

      })

      this.dialogFormVisible = false;
      location.reload()
    }
  },
  beforeMount() {
    this.getUsername()

  },
  mounted() {
  }
}
</script>

<style scoped>

</style>