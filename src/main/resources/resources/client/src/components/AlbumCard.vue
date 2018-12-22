<template>
  <div class="container">
    <!-- <i style="float:left;font-size:60px;color: rgb(145, 201, 246);" class="el-icon-picture"></i> -->
    <div style="float:left;margin-left:20px">
      <div
        class="album_name"
        @click="setShowAlbum();setPopAlbumId(album.albumId);getPopPhotoAlbumInfo(album.albumId);"
      >- {{album.albumName}}</div>
      <div class="album_time">{{this.$covertDate(album.albumCreatetime)}}</div>
      <div class="album_desc">{{album.albumDesc}}</div>
    </div>
    <div style="clear:both"></div>
    <el-row class="row-bg mgt10" :gutter="10">
      <!-- 显示六张缩略图照片 -->
      <el-col
        :xs="12"
        :sm="12"
        :md="4"
        :lg="4"
        :xl="4"
        v-for="(photo,index) in album.photos"
        v-bind:key="photo.photoId"
      >
        <div class="photo_box" ref="ele2" v-if="index < 6">
          <div
            @click="
            setShowPhoto();
            setPopPhotos({photos: photos,index});
            getComments(photo.photoId);
            getPopPhotoAlbumInfo(photo.albumId);
            "
            class="photo"
            v-bind:style="{backgroundImage:'url(' + addFlag(photo.photoThumUrl) + ')'}"
          ></div>
          <!-- <img class="photo" :src="photo.photoURL"> -->
        </div>
      </el-col>
    </el-row>
  </div>
</template>
 
<script>
import { mapState, mapGetters, mapActions } from "vuex";

export default {
  props: {
    album: Object
  },
  data() {
    return {
      photos: this.album.photos
    };
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
    setPopPhotos(val) {
      return this.$store.dispatch("setPopPhotos", val);
    },
    setPopAlbumId(val) {
      return this.$store.dispatch("setPopAlbumId", val);
    },
    getComments(photoId) {
      return this.$store.dispatch("getComments", photoId);
    },
    getPopPhotoAlbumInfo(albumId) {
      return this.$store.dispatch("getPopPhotoAlbumInfo", albumId);
    },
    addFlag(val) {
      return "'" + val + "'";
    },
  }
  // mapActions([
  //   // 需要动用的外部方法
  //   "setShowPhoto",
  //   "setShowAlbum",
  //   "setPopPhotos"
  // ]),
  // addFlag(val){
  //     return "\'"+val+"\'";
  //   }
};
</script>

<style scoped>
.container {
  background-color: white;
  margin-top: 20px;
  border: 1px #eaeaea solid;
  padding: 20px 50px;
  border-radius: 4px;
  box-shadow: #eaeaea 1px 1px 1px;
}
.photo {
  height: 200px;
  background-repeat: no-repeat;
  background-position: center;
  margin-top: 10px;
  background-size: cover;
}
.photo:hover {
  cursor: pointer;
}
.album_name {
  font-size: 20px;
  color: rgb(88, 73, 58);
}
.album_name:hover {
  cursor: pointer;
  color: rgb(191, 156, 121);
}
.album_time {
  font-size: 12px;
  color: rgb(137, 137, 137);
}

.album_desc {
  color: rgb(137, 137, 137);
  font-size: 12px;
}
/* img :hover {
  cursor: pointer;
}
.photo {
  display: block;
  height: 100%;
}

.photo_box {
  height: 250px;
  overflow: hidden;
}
.text_content {
  position: absolute;
  width: 100%;
  height: 50px;
  background: rgba(255, 255, 255, 0.6);
}
.text_content .name {
  font-weight: 600;
}
.text_content .desc {
  font-size: 10pt;
  color: rgb(54, 54, 54);
}
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
} */
</style>
