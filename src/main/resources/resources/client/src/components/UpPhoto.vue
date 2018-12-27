<template>
  <div>
    <div class="container" v-loading="loading">
      <el-upload
        class="upload-demo"
        drag
        action="123"
        :before-upload="beforeUpload"
        multiple
        ref="newupload"
        :auto-upload="false"
        accept=".jpg, .png"
        :on-change="newhandleChange"
        :on-success="newhandlesuccess"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">请注意您只能上传.mp4 .flv .mov格式的视频文件</div>
      </el-upload>
      <el-select v-model="value" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
      <div>
        <el-button type="primary" @click="newSubmitForm()" class="yes-btn">确 定</el-button>
        <el-button @click="resetForm('newform')">重 置</el-button>
      </div>
    </div>
  </div>
</template>
 
<script>
import axios from "axios";
export default {
  data() {
    return {
      param: new FormData(),
      form: {},
      count: 0,
      fileList: [],
      dialogVisible: false,
      dialogImageUrl: "",
      options: [
        {
          value: "1",
          label: "相册ID=1"
        },
        {
          value: "2",
          label: "相册ID=2"
        },
        {
          value: "3",
          label: "相册ID=3"
        }
      ],
      value: ""
    };
  },
  methods: {
    beforeUpload(file) {
      console.log(file);
      let fd = new FormData();
      fd.append("photo", file);
      fd.append("photoName", file.name);
      fd.append("photoDesc", "asddsa");
      fd.append("AlbumId", this.value);
      // console.log(fd)
      this.newVideo(fd).then(res => {
        console.log(res);
      });
      return true;
    },
    newSubmitForm() {
      this.$refs.newupload.submit();
    },
    newhandleChange() {},
    newhandlesuccess() {},
    newVideo(data) {
      return axios({
        method: "post",
        url: "http://127.0.0.1:8080/gdpi/photo",
        timeout: 20000,
        data: data
      });
    }
  }
};
</script>
 
<style scoped>
.container {
  margin: 30px;
  height: 300px;
  background-color: rgb(255, 255, 255);
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
}
</style>
