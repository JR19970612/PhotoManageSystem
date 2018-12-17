<template>
  <div id="app">
    <ImageLayout></ImageLayout>
  </div>
</template>

<script>
import ImageLayout from "./ImageLayout.vue";

export default {
  name: "app",
  data() {
    return {
      msg: "欢迎来到"
    };
  },
  components: {
    ImageLayout,
  }
};
</script>

<style >

body{
  margin: 0;
}

</style>
