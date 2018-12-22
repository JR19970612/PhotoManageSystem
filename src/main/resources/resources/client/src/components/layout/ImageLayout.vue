<template>
  <div style="margin-top:40px;">
    <div style="
      margin: 0 auto;
  padding: 20px 20px;
  width: 80%;
  ">
      <i class="el-icon-caret-right"></i>
      <span style="font-size:24px;">Photos</span>
    </div>
    <div class="container" v-show="allPhotos.length>0">
      <el-row class="row-bg" :gutter="20">
        <el-col
          :xs="12"
          :sm="12"
          :md="6"
          :lg="6"
          :xl="4"
          v-for="(item,index) in allPhotos"
          :key="item.photoId"
        >
          <div
            @click="
          setShowPhoto();
          getComments(item.photoId);
          getPopPhotoAlbumInfo(item.albumId);
          setPopPhotos({photos:allPhotos,index});
          "
          >
            <ImageCard v-bind:photo="item" class="image_card_hover"></ImageCard>
          </div>
        </el-col>
      </el-row>
      <div class="mgt30" style="text-align: center">
        <el-button round style="padding:10px 100px;" @click="addPagePhotos()">查看更多</el-button>
      </div>
    </div>
    <div>
      <PopPhotoLayout></PopPhotoLayout>
      <NullLayout v-if="allPhotos.length==0" :isNULL="allPhotos.length==0"></NullLayout>
    </div>
    <!-- <div>
      <NullLayout v-show="allPhotos.length==0"></NullLayout>
    </div>-->
  </div>
</template>
 
<style scoped>
.image_card_hover:hover {
  cursor: pointer;
}
.el-col {
  /* margin-bottom: 20px; */
}
.container {
  background-color: white;
  margin-top: 100px;
  margin: 0 auto;
  padding: 15px 15px;
  width: 80%;
  border-radius: 4px;
  box-shadow: #eaeaea 1px 1px 1px;
  border: 1px #eaeaea solid;
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
  bottom: 0;
  background: rgba(255, 255, 255, 0.6);
}
</style>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
import ImageCard from "../ImageCard.vue";
import PopPhotoLayout from "./PopPhotoLayout.vue";
import Classify from "../Classify.vue";
import NullLayout from "./NullLayout.vue";

export default {
  data() {
    return {
      pageCount: 12
    };
  },
  components: {
    ImageCard,
    PopPhotoLayout,
    Classify,
    NullLayout
  },
  mounted() {
    this.getPagePhotos();
  },
  computed: mapGetters([
    // 需要用的数据
    "showAlbum",
    "showPhoto",
    "popPhotos",
    "allPhotos",
    "popPhotoIndex"
  ]),
  methods: {
    setPopPhotos(val) {
      return this.$store.dispatch("setPopPhotos", val);
    },
    addPagePhotos() {
      return this.$store.dispatch("addPagePhotos", {
        pageCount: this.pageCount
      });
    },
    getPagePhotos() {
      return this.$store.dispatch("getPagePhotos", {
        pageCount: this.pageCount
      });
    },
    setShowPhoto(val) {
      return this.$store.dispatch("setShowPhoto", val);
    },
    getComments(photoId) {
      return this.$store.dispatch("getComments", photoId);
    },
    getPopPhotoAlbumInfo(albumId) {
      return this.$store.dispatch("getPopPhotoAlbumInfo", albumId);
    },
    getAllPhotos(albumId) {
      // 获取首页的所有图片
      return this.$store.dispatch("getAllPhotos");
    }
  }
};
</script>