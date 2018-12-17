<template>
  <div class="comment_layout">
    <div class="photo_info">
      <div>
        <div>
          <div>
            <label class="photo_name">{{popPhotos[popPhotoIndex].photoName}}</label>
            <label class="belong_album">来自: {{popPhotoAlbumInfo.albumName}}</label>
          </div>
          <div>
            <label class="date">图片创建时间：{{popPhotos[popPhotoIndex].photoCreateTime}}</label>
          </div>
        </div>
      </div>
      <div class="photo_desc">This thing in future will be a lot.</div>
      <div class="comments" style="overflow:auto">
        <div class="comment_user_item" v-for="item in comments" :key="item.commentId">
          <div class="user_img">
            <img
              src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544634253339&di=bb79e3a83e0153119719b691f793f0ce&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3D97eab9fad139b6004d9b07b3dc60191c%2Fb21c8701a18b87d6a55b1106030828381f30fd8a.jpg"
              alt
              height="40"
              width="40"
            >
          </div>
          <div class="comment_content">
            <label class="comment_user">{{item.person.personName}}</label>
            <label class="date">{{item.commentTime}}</label>
            <label class="content">{{item.commentContent}}</label>
          </div>
        </div>
      </div>
    </div>

    <div>
      <div>
        <img src="/src/assets/img/dz1.png" alt="点赞" width="24" height="24">
      </div>
      <el-input v-model="input" placeholder="我要对图片进行评论"></el-input>
      <el-button type="primary" style="margin-top:10px; float:right" @click="sendComment();">发表</el-button>
    </div>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
export default {
  props: {
    photoName: String
  },
  data() {
    return {
      input: ""
    };
  },
  computed: mapGetters([
    // 需要用的数据
    // 这个图片的评论的信息
    "comments",
    //这个图片的相册详细信息，
    //也包含了这个相册中的所有图片的信息
    "popPhotoAlbumInfo",
    "showPhoto",
    "popPhotos",
    "popPhotoIndex"
  ]),
  methods: {
    sendComment() {
      if (this.input == "") {
        alert("请输入评论内容在尝试发送！");
      } else {
        this.$store.dispatch("sendComment", {
          personId: 1,
          photoId: this.popPhotos[this.popPhotoIndex].photoId,
          commentContent: this.input
        });
        this.input = "";
      }
    },
    getComments(val) {
      return this.$store.dispatch(
        "getComments",
        this.popPhotos[this.popPhotoIndex].photoId
      );
    }
  }
};
</script>

<style scoped>
.photo_desc {
  padding: 10px 10px;
  margin: 10px 0;
  /* border-top: 1px #ccc solid; */
  /* border-bottom: 1px #ccc solid; */
  border-left: 5px rgb(109, 192, 178, 55) solid;
}
.comment_layout {
  padding: 10px;
  /* margin-top: 300px; */
  float: right;
  width: 20vw;
  height: 100vh;
  position: fixed;
  right: 0;
  justify-content: center;
  display: flex;
  align-items: center;
  flex-direction: column;
  background: white;
  position: fixed;
  top: 0;
  z-index: 1;
}
.photo_name {
  display: block;
  font-size: 16px;
  font-weight: 600;
}
.photo_info {
  background-color: white;
  height: 40vh;
  width: 100%;
}
.belong_album {
  color: #555;
  font-size: 10pt;
}
.date {
  color: #555;
  font-size: 8px;
}
.comments {
  height: 150px;
  overflow: scroll;
}
.comment_user_item {
  padding: 10px;
  border-bottom: 1px #ddd solid;
  display: flex;
}
.comment_user {
  font-size: 8px;
  font-weight: 600;
}
.comment_content {
  margin-left: 10px;
}
.comment_content label {
  display: block;
}
.comment_content .content {
  margin-top: 6px;
}
</style>
