<template>
  <div style="display: flex;line-height: 60px">
    <div >
      <i class="el-icon-s-fold" style="font-size: 20px;margin-top: 5px" @click="collapse"></i>
    </div>
    <div style="flex:1;text-align: center;font-size: 34px">
      <span>欢迎来到管理系统</span>
    </div>
    <el-dropdown>
      <span>{{username}}</span>
      <i class="el-icon-arrow-down" style="margin-left: 15px"></i>
      <el-dropdown-menu slot="dropdown">
<!--        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>-->
        <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

  </div>
</template>

<script>

export default {
  name: "Header",
  data(){
    return{
      username:''
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
          console.log("res.data.data"+res.data.data)
          localStorage.setItem("user",JSON.stringify(res.data.data))
        }
      })
    },
    toUser(){
      console.log("to-user")
      this.$router.push('/Home')
    },
    logout(){
      console.log("log-out")
      this.$confirm('确定要退出？','提示',{
        confirmButtonText:'确定',
        type : 'warning',
        center: true,  //居中
      }).then(_=>{
        localStorage.removeItem("token")
        this.$router.push("/")
        this.$message({
          message: '退出成功',
          type: 'success'
        });
      }).catch(_=>{
        this.$message('取消退出');
      })
    },
    collapse(){
      this.$emit('doCollapse')
    }
  },
  computed:{

  },
  mounted() {
    this.username = JSON.parse(localStorage.getItem("user")).username
  },
  created() {
    this.$router.push("/Home")

  },
  beforeMount() {
    this.getUsername()
  }
}
</script>

<style scoped>

</style>