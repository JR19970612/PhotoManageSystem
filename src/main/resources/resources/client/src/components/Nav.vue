<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
  >
    <el-menu-item index="0"  style="margin-left:30px;">
      <router-link to="/photos">      <img src="/src/assets/img/icon.png" height="100%" alt="LOGO"></router-link>
    </el-menu-item>
    <el-menu-item index="1" >
      <router-link to="/photos">照片</router-link>
    </el-menu-item>
    <el-menu-item index="2">
      <router-link to="/albums">相册</router-link>
    </el-menu-item>
      <el-menu-item index="3">
      <router-link to="/upPhoto">临时上传到相册</router-link>
    </el-menu-item>
    <el-menu-item index="4" style="float:right">
      <a href="http://127.0.0.1:8080/Admind" target="_blank">后台管理</a>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: "1"
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  watch: {
    $route(to, from) {
      console.log("nav监听路由信息：", to);
      if (to.path == "/albums") {
        this.activeIndex = "2";
      } else if (to.path == "/photos") {
        this.activeIndex = "1";
      }
      // /searchPhotos/:keyWord
      // /searchAlbums/:keyWord
    }
  }
};
</script>
<style >
.el-menu-item {
  font-size: 18px;
}
a {
  text-decoration: none;
}
</style>
