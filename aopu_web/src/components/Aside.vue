<template>
<!--菜单-->
  <el-menu
      style="height: 100%;"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      default-active="/Home"
      :collapse="isCollapse"
      :collapse-transition="false"
      router
  >
    <el-menu-item index="/Home">
      <i class="el-icon-s-home"></i>
      <span slot="title">首页</span>
    </el-menu-item>
    <el-menu-item index="/Store">
      <i class="el-icon-s-flag"></i>
      <span slot="title">门店信息</span>
    </el-menu-item>
    <el-menu-item index="/Agent">
      <i class="el-icon-house"></i>
      <span slot="title">代理商信息</span>
    </el-menu-item>
    <el-menu-item index="/Salesman">
      <i class="el-icon-user"></i>
      <span slot="title">业务员信息</span>
    </el-menu-item>
    <el-menu-item index="/Sale">
      <i class="el-icon-notebook-2"></i>
      <span slot="title">门店销售统计表</span>
    </el-menu-item>

    <el-menu-item index="/Purchasing">
      <i class="el-icon-box"></i>
      <span slot="title">代理商进货数据</span>
    </el-menu-item>
    <el-menu-item index="/Inventory">
      <i class="el-icon-goods"></i>
      <span slot="title">产品库</span>
    </el-menu-item>
    <el-menu-item index="/Article">
      <i class="el-icon-edit-outline"></i>
      <span slot="title">备忘录</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse:Boolean
  }
}
</script>

<style scoped>

</style>