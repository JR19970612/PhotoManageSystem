<template>
  <div
    style="width: 100vw;height: 100vh;background: rgba(0,0,0,0.63);position: fixed;top: 0;z-index: 1;"
    v-show="showAlbum"
  >
    <div style="margin:50px;border:1px #c0c0c0 solid;height: 75vh;background: #fff;padding: 40px;">
      <div>
        <i class="el-icon-close btn_close" @click="setShowAlbum"></i>
        <h3>相册名字</h3>
        <h5 style="color:#8e8e8e;">2018年12月4日</h5>
      </div>
      <hr>
      <div
        style="border: 1px #ffb2b2 solid;width: 100%;height: 80%;overflow-y:auto; overflow-x:hidden;  "
      >
        <el-row class="row-bg" :gutter="20">
          <el-col
            :xs="12"
            :sm="12"
            :md="6"
            :lg="6"
            :xl="6"
            v-for="(item,index) in photos"
            :key="item.photoId"
          >
          <div
           @click="
          setShowPhoto();
          setComments(item.photoId);
          setPopPhotoAlbumInfo(item.albumId);
          setPopPhotos({photos,index});
          "
          >
            <ImageCard v-bind:photo="item" ></ImageCard>
          </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

import ImageCard from "../ImageCard.vue";

export default {
  data() {
    return {
      photos: [
        {
          photoId: 1,
          photoName: "图片C",
          photoDesc: 25,
          photoCreatetime: "2018年12月2日",
          albumId: 2,
          albumName: "sad",
          photoURL:
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3819531008,942434957&fm=200&gp=0.jpg"
        },
        {
          photoId: 2,
          photoName: "图片D",
          photoDesc: 25,
          photoCreatetime: "2018年12月12日",
          albumId: 2,
          albumName: "sad",
          photoURL:
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1059770508,159053842&fm=26&gp=0.jpg"
        },
        {
          photoId: 3,
          photoName: "图片D",
          photoDesc: 25,
          photoCreatetime: "2018年12月23日",
          albumId: 2,
          albumName: "sad",
          photoURL:
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4134070514,2065750022&fm=26&gp=0.jpg"
        },
        {
          photoId: 4,
          photoName: "图片D",
          photoDesc: 25,
          photoCreatetime: "2018年12月22日",
          albumId: 2,
          albumName: "sad",
          photoURL:
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1162058567,2289747742&fm=26&gp=0.jpg"
        },
        {
          photoId: 5,
          photoName: "图片D",
          photoDesc: 25,
          photoCreatetime: "2018年12月21日",
          albumId: 2,
          albumName: "sad",
          photoURL:
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3257286882,1013914695&fm=26&gp=0.jpg"
        },
        {
          photoId: 6,
          photoName: "图片D",
          photoDesc: 25,
          photoCreatetime: "2018年12月2日",
          albumId: 2,
          albumName: "sad",
          photoURL:
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1318618513,3987037995&fm=26&gp=0.jpg"
        },
        {
          photoId: 7,
          photoName: "图片D",
          photoDesc: 25,
          photoCreatetime: "2018年12月2日",
          albumId: 2,
          albumName: "sad",
          photoURL:
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1162058567,2289747742&fm=26&gp=0.jpg"
        }
      ],
    };
  },
  components: {
    ImageCard
  },
  computed: mapGetters([
    // 需要用的数据
    "showAlbum",
    "popAlbumId"
  ]),
  methods: {
    setShowPhoto(val) {
      return this.$store.dispatch("setShowPhoto", val);
    },
    setShowAlbum(val) {
      return this.$store.dispatch("setShowAlbum", val);
    },
    setPopAlbumId(val) {
      return this.$store.dispatch("setPopAlbumId", val);
    },
    setPopPhotos(val) {
      return this.$store.dispatch("setPopPhotos", val);
    },
    setShowPhoto(val) {
      return this.$store.dispatch("setShowPhoto", val);
    },
    setComments(photoId) {
      // 获取点击图片的评论信息
      var successCallback = response => {
        console.log("服务器请求成功了setComments");
        return this.$store.dispatch(
          "setComments",
          response.data.data[0].comments
        );
      };
      var errorCallback = response => {
        console.log("服务器请求出错了");
      };
      this.$http
        .get("http://127.0.0.1:8080/photo/photoId?params=" + photoId)
        .then(successCallback, errorCallback);
    },
    setPopPhotoAlbumInfo(albumId) {
      // 获取点击图片的相册信息
      var successCallback = response => {
        console.log("服务器请求成功了setPopPhotoAlbumInfo");
        return this.$store.dispatch(
          "setPopPhotoAlbumInfo",
          response.data.data[0]
        );
      };
      var errorCallback = response => {
        console.log("服务器请求出错了");
      };
      this.$http
        .get("http://127.0.0.1:8080/album/AlbumId?params=" + albumId)
        .then(successCallback, errorCallback);
    },
    getPhotos: function() {
      // 获取首页的所有图片
      var successCallback = response => {
        console.log("服务器请求成功了getPhotos");
        this.photos = response.data.data;
      };
      var errorCallback = response => {
        console.log("服务器请求出错了");
      };
      this.$http
        .get("http://127.0.0.1:8080/photo")
        .then(successCallback, errorCallback);
    }
  },
  watch: {
    popAlbumId: function(popAlbumId) {
      console.log("当前的popAlbumId", popAlbumId);
      var successCallback = response => {
        console.log("服务器请求成功了");
        console.log(response.data);
        this.photos = response.data.data[0].photos;
      };
      var errorCallback = response => {
        console.log("服务器请求出错了");
      };
      this.$http
        .get("http://127.0.0.1:8080/album/AlbumId?params="+popAlbumId)
        .then(successCallback, errorCallback);
    }
  }
};
</script>

<style scoped>
.btn_close {
  font-size: 18pt;
  float: right;
  color: #000000;
  margin-right: 80px;
}
</style>
