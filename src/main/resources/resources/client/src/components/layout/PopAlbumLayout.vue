<template>
  <div
    style="width: 100vw;height: 100vh;background: rgba(0,0,0,0.63);position: fixed;top: 0;z-index: 1;"
    v-show="showAlbum"
  >
    <div
      style="border-radius: 5px;margin:50px;border:1px #c0c0c0 solid;height: 75vh;background: #fff;padding: 40px;"
    >
      <div>
        <i class="el-icon-close btn_close" @click="setShowAlbum"></i>
        <h3
          style="border-left:4px solid #00BCD4;padding-left:10px;"
        >相册名：{{popPhotoAlbumInfo.albumName}}</h3>
        <h5 style="color:#8e8e8e;">创建时间：{{this.$covertDate(popPhotoAlbumInfo.albumCreatetime)}}</h5>
      </div>
      <hr>
      <div style="padding:10px;width: 100%;height: 80%;overflow-y:auto; overflow-x:hidden;  ">
        <el-row class="row-bg" :gutter="20">
          <el-col
            :xs="12"
            :sm="12"
            :md="4"
            :lg="4"
            :xl="4"
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
              <ImageCard v-bind:photo="item"></ImageCard>
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
        }
      ]
    };
  },
  components: {
    ImageCard
  },
  computed: mapGetters([
    // 需要用的数据
    "showAlbum",
    "popAlbumId",
    "allAlbums",
    "popPhotoAlbumInfo"
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
      return this.$store.dispatch("setComments", photoId);
    },
    setPopPhotoAlbumInfo(albumId) {
      return this.$store.dispatch("setPopPhotoAlbumInfo", albumId);
    }
    // getPhotos: function() {
    //   // 获取首页的所有图片
    //   var successCallback = response => {
    //     console.log("服务器请求成功了getPhotos");
    //     this.photos = response.data.data;
    //   };
    //   var errorCallback = response => {
    //     console.log("服务器请求出错了");
    //   };
    //   this.$http
    //     .get("http://127.0.0.1:8080/photo")
    //     .then(successCallback, errorCallback);
    // },
    // covertDate: function(value) {
    //   var date = new Date(value);
    //   var fmt = "yyyy-MM-dd hh:mm:ss";

    //   var o = {
    //     "M+": date.getMonth() + 1, //月份
    //     "d+": date.getDate(), //日
    //     "h+": date.getHours(), //小时
    //     "m+": date.getMinutes(), //分
    //     "s+": date.getSeconds(), //秒
    //     "q+": Math.floor((date.getMonth() + 3) / 3), //季度
    //     S: date.getMilliseconds() //毫秒
    //   };
    //   if (/(y+)/.test(fmt))
    //     fmt = fmt.replace(
    //       RegExp.$1,
    //       (date.getFullYear() + "").substr(4 - RegExp.$1.length)
    //     );
    //   for (var k in o)
    //     if (new RegExp("(" + k + ")").test(fmt))
    //       fmt = fmt.replace(
    //         RegExp.$1,
    //         RegExp.$1.length == 1
    //           ? o[k]
    //           : ("00" + o[k]).substr(("" + o[k]).length)
    //       );
    //   return fmt;
    //   // return this.dateFtt(fmt, date); //直接调用公共JS里面的时间类处理的办法
    // },
  },
  watch: {
    popAlbumId: function(popAlbumId) {
      console.log("PopAlbumLayout监听到当前的popAlbumId改变", popAlbumId);
      var successCallback = response => {
        console.log("服务器请求成功了");
        console.log(response.data);
        this.photos = response.data.data[0].photos;
      };
      var errorCallback = response => {
        console.log("服务器请求出错了");
      };
      this.$http
        .get("http://127.0.0.1:8080/album/AlbumId?params=" + popAlbumId)
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
  margin-right: 50px;
}
</style>
