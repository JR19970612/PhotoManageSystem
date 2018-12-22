<template>
  <div>
    <div class="photo_box">
      <!-- todo bind use 双引号 -->
      <div ref="ele" 
      class="photo"
      v-bind:style="{backgroundImage:'url(' + addFlag(photo.photoThumUrl) + ')',height:imageCardWidth}"
       ></div>
       <!-- v-bind:style="{backgroundImage:'url(' + photo.photoThumUrl + ')',height: imageCardWidth}" -->
      <!-- <img class="photo" :src="photo.photoOriginalUrl" > -->
    </div>
    <div style="position:relative;width:100%">
      <div class="text_content">
        <div class="photo_name">{{photo.photoName}}</div>
        <div class="photo_date">{{photo.photoCreatetime}}</div>
      </div>
    </div>
  </div>
</template>
 
<script>
import { mapState, mapGetters, mapActions } from "vuex";

export default {
  props: {
    photo: Object
  },
  data() {
    return {
      imageCardHeight: 0,
      imageCardWidth: 0,
    };
  },
  computed: mapGetters([
    // 需要用的数据
    "showPhoto"
  ]),
  methods:{
    setShowPhoto(val) {
      return this.$store.dispatch("setShowPhoto", val);
    },
    addFlag(val){
      return "\'"+val+"\'";
    }
  },
  mounted() {
    var width = window.getComputedStyle(this.$refs.ele).width; // ？px
    this.imageCardWidth = width;
    console.log("imageCardWidth:" + width);
  }
};
</script>
<style scoped>
.image {
  width: 100%;
  display: block;
}

.clearfix:after {
  clear: both;
}

.photo {
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
}

.photo_box {
  /* height: 250px; */
  overflow: hidden;
}
.text_content {
  text-align: center;
  position: relative;
  width: 100%;
  height: 50px;
  bottom: 0;
  background: rgba(255, 255, 255, 0.6);
}
.text_label {
  padding: 5px 20px;
  font-weight: 600;
}

.photo_name {
  margin-top: 10px;
}
.photo_date {
  font-size: 12px;
  color: #ccc;
}
</style>
