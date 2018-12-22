<template>
  <div style="margin-top: 40px;display:flex; justify-content: center;">
    <div style="width:40%" @keyup.enter="getSearchPhotos">
      <el-input placeholder="请输入内容" v-model="search_content" class="input-with-select">
        <el-select v-model="select" slot="prepend" placeholder="请选择" style="z-index:0">
          <el-option label="图片名" value="photoName"></el-option>
          <el-option label="相册名" value="AlbumName"></el-option>
          <el-option label="相片编号" value="3"></el-option>
        </el-select>
        <el-button
          slot="append"
          class="el-icon-search"
          @click="getSearchPhotos"
        ></el-button>
      </el-input>
    </div>
  </div>
</template>
<style>
.el-select .el-input {
  width: 130px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
.el-input-group__append {
  color: #fff;
  background: rgba(81, 136, 152, 0.85);
}
</style>
<script>
import { mapState, mapGetters, mapActions } from "vuex";
export default {
  data() {
    return {
      search_content: "",
      select: "photoName"
    };
  },
  methods: {
    getSearchPhotos() {
      if (this.select == "photoName") {
        this.$router.push('/searchPhotos');
        return this.$store.dispatch(
          "getSearchPhotosByPhotoName",
          this.search_content
        );
      } else {
        this.$router.push('/searchAlbums');
        return this.$store.dispatch(
          "getSearchPhotosByAlbumName",
          this.search_content
        );
      }
    },
    // getSearchPhotosByAlbumName(search_content) {
    //   return this.$store.dispatch("getSearchPhotosByAlbumName", search_content);
    // },
    addFlag(val) {
      return "'" + val + "'";
    }
  }
};
</script>