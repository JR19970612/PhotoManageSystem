<template>
  <div class="container" v-loading="loading">
    <div class="el-icon-warning"></div>
    <div style="margin-top:45px">暂时没有您需要的数据，你可以浏览其他页面~</div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      loading: false
    };
  }
};
</script>

<style scoped>
.container {
  height: 300px;
  background-color: rgb(255, 255, 255);
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
}
.el-icon-warning {
  font-size: 200px;
  color: rgb(255, 110, 110);
}
</style>
