<template>
  <el-container style="height: 100vh; border: 1px solid #eee">
    <el-aside :width="aside_width" style="height: 100vh; background-color: rgb(238, 241, 246) ; margin-left: -1px;margin-top: -1px" scroll="no">
      <Aside :isCollapse="isCollapse"></Aside>
    </el-aside>

    <el-container style="height: 100%;">
      <el-header style="text-align: right; font-size: 12px; border-bottom: rgba(168,168,168,0.3) 1px solid">
        <Header @doCollapse="doCollapse"></Header>
      </el-header>

      <el-main  style="padding : 10px">
        <router-view/>
      </el-main>


    </el-container>
  </el-container>
</template>

<script>
import Aside from "./Aside"
import Header from "@/components/Header";
import Main from "@/components/Store";
import Login from "@/components/Login";
export default {
  components:{
    Login,

    Header,
    Aside
  },
  data(){
    return{
      isCollapse:false,
      aside_width:'200px',
    }
  },
  methods:{
    doCollapse(){
      this.isCollapse = !this.isCollapse
      if (!this.isCollapse){  // 展开
        this.aside_width = '200px'
      }else { // 收起
        this.aside_width = '64px'
      }
    }
  }

};
</script>

<style scoped>
.el-header {
  /*background-color: #B3C0D1;*/
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>