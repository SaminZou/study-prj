<template>
  <el-menu
    class="menu"
    background-color="#181F46"
    text-color="#8aa4af"
    active-text-color="#ffffff"
    :unique-opened="false"
    :default-active="$route.path"
    router
  >
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-setting"></i>
        <span>系统配置</span>
      </template>
      <el-menu-item index="/messageconfig/list">消息推送配置</el-menu-item>
      <el-menu-item index="/tool/push">消息推送</el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "NavMenu",
  methods: {},
};
</script>

<style scoped>
.el-menu {
  border-right: none;
}
</style>