<template>
    <div>
        <div class="container">
            <el-upload
                    class="upload-demo"
                    drag
                    :before-upload="beforeUpload"
                    :action="URL.upPhoto"
                    :data="uploadData"
                    multiple
                    name="photo"
                    ref="newUpload"
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
                <div class="el-upload__tip" slot="tip">请注意您只能上传 png  jpg 格式的视频文件</div>
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
    import {mapState, mapGetters, mapActions} from "vuex";
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
                value: "",
                uploadData: {
                    photoName: "test",
                    photoDesc: "test",
                    albumId: 1
                }
            }
        },
        computed: mapGetters(["URL"]),
        methods: {
            beforeUpload(file) {
                console.log(file);
//                let data = new FormData();
//                data.append("photo", file);
//                data.append("photoName", file.name);
//                data.append("photoDesc", "测试数据");
//                data.append("albumId", this.value);
//                // console.log(data)
//                this.send(data).then(res => {
//                    console.log("res:" + res);
//                });
                console.log("this.$refs.newUpload1", this.$refs.newUpload);
                this.$refs.newUpload.data.photoName=file.name;
                console.log("this.$refs.newUpload2", this.$refs.newUpload);
                return true;
            },
            newSubmitForm() {
                this.$refs.newUpload.submit();
            },
            newhandleChange() {
            },
            newhandlesuccess() {
            },
            send(data) {
                return axios({
                    method: "post",
                    url: this.URL.getPagePhotosUrl,
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
