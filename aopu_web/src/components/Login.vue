<template>
  <div class="login-container">
    <el-container>
      <el-header>

      </el-header>
      <el-main>
          <div class="title-container">
            <h1 class="title" style="font-size: 50px">欢迎您~！</h1>
          </div>
          <el-tabs v-model="ActiveName" @tab-click="handleClick" style="width: 600px; margin:90px auto auto auto" tab-position="left">
            <el-tab-pane label="登录" name="first" >

                <el-form ref="loginForm"  class="login-container" auto-complete="on">
                  <el-form-item prop="username"  label="用户名" label-width="60px">
                    <el-input ref="username"
                        v-model="user.username"
                        placeholder="用户名"
                        name="username"
                        type="text"
                        auto-complete="on"
                        style="width: 300px"
                    />
                  </el-form-item>

                  <el-form-item prop="password"  label="密码"  label-width="60px">
                    <el-input ref="password"
                        v-model="user.password"
                        placeholder="密码"
                        name="password"
                        auto-complete="on"
                        @keyup.enter.native="handleLogin"
                        style="width: 300px"
                    />
                  </el-form-item>
                  <el-button type="primary" style="width:300px;margin-bottom:30px;margin-left: 60px" @click.native.prevent="handleLogin">登录</el-button>

                </el-form>




            </el-tab-pane>
            <el-tab-pane label="注册" name="second">

              <el-form ref="loginForm"  class="login-container"
                       auto-complete="on"
                       hide-required-asterisk:false
              >
                <el-form-item prop="username" label="用户名" label-width="60px">
                  <el-input ref="username"
                            v-model="register.username"
                            placeholder="用户名"
                            name="username"
                            type="text"
                            auto-complete="on"
                            style="width: 300px"
                            required:true
                  />
                </el-form-item>

                <el-form-item prop="password" label="密码" label-width="60px">
                  <el-input ref="password"
                            v-model="register.password"
                            placeholder="密码"
                            name="password"
                            auto-complete="on"
                            style="width: 300px"
                  />
                </el-form-item>
                <el-form-item prop="nickname" label="昵称" label-width="60px">
                  <el-input ref="nickname"
                            v-model="register.nickname"
                            placeholder="昵称"
                            name="password"
                            auto-complete="on"
                            style="width: 300px"
                  />
                </el-form-item>
                <el-form-item prop="telephone" label="电话" label-width="60px">
                  <el-input ref="telephone"
                            v-model="register.telephone"
                            placeholder="电话"
                            name="password"
                            auto-complete="on"
                            @keyup.enter.native="handleLogin"
                            style="width: 300px"
                  />
                </el-form-item>
                <el-button type="primary" style="width:300px;margin-bottom:30px;margin-left: 60px" @click.native.prevent="handleRegister">注册</el-button>

              </el-form>
            </el-tab-pane>

          </el-tabs>

      </el-main>
      <el-footer>

      </el-footer>
    </el-container>





  </div>
</template>

<script>

export default {
  name: 'Login',
  data() {
    return {
      ActiveName:'first',
      user:{
        username:"",
        password:"",
      },
      register:{
        username:"",
        password:"",
        nickname:'',
        telephone:'',
        corporateName:'',
        storeName:''
      }
    }
  },
  watch: {
  },
  methods: {
    handleLogin(){
      //请求后台登录接口，把接受到的数据展示在页面中
      this.$axios.post(this.$httpUrl + "/user/login",this.user).then((res)=>{
        // 判断code是否为200
        if(res.data.code == 200){
          localStorage.setItem("token",res.data.data.token)
          // 成功，把值赋值给this.books
          this.$message({
            message: '登录成功！',
            type: 'success'
          });
          this.$router.replace("/Index")
        }else{
          this.$message({
            message: '登录失败，原因：' + res.data.msg,
            type: 'warning'
          });
        }
      })
    },
    handleRegister(){
      this.$axios.post(this.$httpUrl +"/user/register",this.register).then((res)=>{
        console.log(res)
        // 判断code是否为200
        if(res.data.code == 200){
          // 成功，把值赋值给this.books
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
      })
      // this.$axios.post(this.$httpUrl +"/user/login",this.register).then((res)=>{
      //   localStorage.setItem("token",res.data.data.token)
      //   // 成功，把值赋值给this.books
      //   this.$message({
      //     message: '登录成功！',
      //     type: 'success'
      //   });
      //   this.$router.replace("/Index")
      // })
    }
  }
}
</script>


<style  scoped>

.title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

</style>
