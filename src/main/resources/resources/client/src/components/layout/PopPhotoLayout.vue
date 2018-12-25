<template>
  <div>
    <!-- <transition name="el-zoom-in-top"> -->
    <transition name="el-zoom-in-top">

    <div id="app" class="container" v-show="showPhoto" >
      <div style="display: flex; justify-content:center; height:100vh; align-items: center;"
      >
        <img style="height: 70%;" v-bind:src="popPhotos[popPhotoIndex].photoOriginalUrl">
      </div>
      <img
        @click="setShowPhoto"
        src="src/assets/img/close.png"
        style="width: 40px;height: 40px;position: absolute;right: 50px;top: 75px;"
      >
      <!-- setComments(popPhotos[popPhotoIndex].photoId);
      setPopPhotoAlbumInfo(popPhotos[popPhotoIndex].albumId);-->
      <img
        @click="
        incPopPhotoIndex(); 
        getComments(popPhotos[popPhotoIndex].photoId);
        getPopPhotoAlbumInfo(popPhotos[popPhotoIndex].albumId);
        "
        src="src/assets/img/right.png"
        style="width: 40px;height: 40px;position: absolute;right: 50px;top: 50vh;"
      >
      <h1>{{popPhotoIndex}}</h1>
      <img
        @click="decPopPhotoIndex();
        getComments(popPhotos[popPhotoIndex].photoId);
        getPopPhotoAlbumInfo(popPhotos[popPhotoIndex].albumId);"
        src="src/assets/img/left.png"
        style="width: 40px;height: 40px;position: absolute;left: 50px;top: 50vh;"
      >
    </div>
    </transition>

    <transition name="el-zoom-in-top">
      <CommentLayout
        v-show="showPhoto"
        v-bind:photoName="popPhotos[popPhotoIndex].photoName"
        v-bind:photoCreatetime="popPhotos[popPhotoIndex].photoCreatetime"
      ></CommentLayout>
    </transition>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
import CommentLayout from "./CommentLayout.vue";
export default {
  data() {
    return {
      photos: []
    };
  },
  components: {
    CommentLayout
  },
  computed: mapGetters([
    // 需要用的数据
    "showAlbum",
    "showPhoto",
    "popPhotos",
    "allPhotos",
    "popPhotoIndex"
  ]),
  // methods: mapActions([
  //   // 需要动用的外部方法
  //   "setShowPhoto",
  //   "setShowAlbum",
  // ]),
  methods: {
    setShowPhoto(val) {
      return this.$store.dispatch("setShowPhoto", val);
    },
    setShowAlbum(val) {
      return this.$store.dispatch("setShowAlbum", val);
    },
    incPopPhotoIndex(val) {
      return this.$store.dispatch("incPopPhotoIndex", val);
    },
    decPopPhotoIndex(val) {
      return this.$store.dispatch("decPopPhotoIndex", val);
    },
    getComments(photoId) {
      return this.$store.dispatch("getComments", photoId);
    },
    getPopPhotoAlbumInfo(albumId) {
      // 获取点击图片的相册信息
      return this.$store.dispatch("getPopPhotoAlbumInfo", albumId);
    }
  }
};
</script>

<style scoped>
.fade-enter-active {
  animation: bounce-in 0.5s;
}
.fade-leave-active {
  animation: bounce-in 0.5s reverse;
}
@keyframes bounce-in {
  0% {
    transform: scale(0);
  }
  50% {
    transform: scale(1.2);
  }
  100% {
    transform: scale(1);
  }
}
.container {
  width: 80vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.85);
  position: fixed;
  top: 0;
  z-index: 1;
}
</style>
