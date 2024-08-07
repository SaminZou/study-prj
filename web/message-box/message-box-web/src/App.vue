<template>
  <el-container id="app" class="container" v-if="this.showIndex">
    <el-header class="header">
      <div id="logo">Message Box 后台管理</div>
      <div id="userInfo">
        <el-avatar icon="el-icon-user-solid"></el-avatar>
      </div>
    </el-header>
    <el-container class="body-container" direction="horizontal">
      <el-aside width="210px" class="aside">
        <nav-menu></nav-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
  <el-container v-else>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
import NavMenu from "./pages/NavMenu";

export default {
  name: "app",
  components: {
    NavMenu,
  },
  data() {
    return {
      showIndex: true,
    };
  },
  methods: {},
  mounted() {
    this.showIndex = this.$store.getters.getShowIndex;
    if (this.showIndex) {
      console.log("success");
    }
  },
};
</script>

<style>
*,
html,
body {
  padding: 0;
  margin: 0;
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /*隐藏滚动条，当IE下溢出，仍然可以滚动*/
  -ms-overflow-style: none;
  /*火狐下隐藏滚动条*/
  overflow: -moz-scrollbars-none;
}

::-webkit-scrollbar {
  display: none;
}

html,
body {
  color: #8aa4af;
  height: 100%;
}

.container {
  height: 100%;
  width: 100%;
}

.body-container {
  height: 100%;
}

.header {
  background-color: #181f46;
  height: 10%;
}

.aside {
  background-color: #181f46;
  overflow: hidden;
}

#logo {
  float: left;
  line-height: 3.5em;
  width: 200px;
  font-size: 18px;
}

#userInfo {
  float: right;
  text-align: right;
  display: flex;
  align-items: center;
  height: 100%;
}

.sys-search {
  margin-bottom: 10px;
  background-color: #f9f9f9;
  /*margin-top: 10px;*/
  padding: 20px 10px 0;
}

.sys-opt {
  float: right;
  margin-bottom: 10px;
}

.warnLabel .el-form-item__label {
  color: crimson;
  font-weight: bolder;
}

.warnCell .cell {
  color: crimson;
  font-weight: bolder;
}

.search-btn {
  background-color: #181f46 !important;
}

.el-form-item {
  display: inline-block;
  margin-right: 10px;
}
</style>