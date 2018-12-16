<template>
  <div style="margin-top:40px;">
    <PopAlbumLayout></PopAlbumLayout>
    <PopPhotoLayout></PopPhotoLayout>
    <div v-if="allAlbums.length!=0">
      <div class="title_border">
        <i class="el-icon-caret-right"></i>
        <span style="font-size:24px;">Albums</span>
          <div v-for="album in allAlbums" :key="album.albumId">
            <AlbumCard @click="setShowAlbum();setPopAlbumId(album.albumId)" v-bind:album="album"></AlbumCard>
          </div>
      </div>

      <div class="mgt30" style="text-align: center">
        <el-button round style="padding:10px 100px;">查看更多</el-button>
      </div>
    </div>

    <NullLayout v-if="allAlbums.length==0"></NullLayout>
  </div>
</template>
 
<script>
import AlbumCard from "../AlbumCard.vue";
import PopAlbumLayout from "./PopAlbumLayout.vue";
import PopPhotoLayout from "./PopPhotoLayout.vue";
import NullLayout from "./NullLayout.vue";
import { mapState, mapGetters, mapActions } from "vuex";

export default {
  components: {
    AlbumCard,
    PopAlbumLayout,
    PopPhotoLayout,
    NullLayout
  },
  computed: mapGetters([
    // 需要用的数据
    "setPopAlbumId",
    "allAlbums"
  ]),
  methods: {
    getAllAlbums() {
      return this.$store.dispatch("getAllAlbums");
    }
  },
  mounted() {
    this.getAllAlbums();
  },
  data() {
    return {
      // todo 获取这个相册的photos
    };
  }
};
</script>
<style scoped>
.el-col {
  margin-bottom: 20px;
}
.title_border {
  padding: 20px 0;
  margin: 0 auto;
  width: 80%;
}

.container {
  background-color: white;
  margin-top: 20px;
  border: 1px #eaeaea solid;
  padding: 20px 50px;
  border-radius: 4px;
  box-shadow: #eaeaea 1px 1px 1px;
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
.album_name {
  font-size: 20px;
  color: rgb(88, 73, 58);
}
.album_time {
  font-size: 12px;
  color: rgb(137, 137, 137);
}

.album_desc {
  color: rgb(137, 137, 137);
  font-size: 12px;
}
</style>
