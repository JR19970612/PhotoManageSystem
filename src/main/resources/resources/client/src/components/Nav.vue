<template>
    <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
    >
        <el-menu-item index="0" style="margin-left:30px;">
            <router-link to="/photos"><img src="src/assets/img/icon.png" height="100%" alt="LOGO"></router-link>
        </el-menu-item>
        <el-menu-item index="1">
            <router-link to="/photos">照片</router-link>
        </el-menu-item>
        <el-menu-item index="2">
            <router-link to="/albums">相册</router-link>
        </el-menu-item>
        <el-menu-item index="3">
            <router-link to="/upPhoto">临时上传到相册</router-link>
        </el-menu-item>
        <el-menu-item index="4" style="float:right">
            <el-button type="text" @click="dialogFormVisible = true">管理员登陆</el-button>
            <el-dialog title="管理员登陆" :visible.sync="dialogFormVisible">
                <el-form :model="form">
                    <el-form-item >
                        <el-input id="name" v-model="form.name" placeholder="请输入帐号">
                            <template slot="prepend">帐号</template>
                        </el-input>
                    </el-form-item>
                    <el-form-item >
                        <el-input id="password" v-model="form.password" type="password" placeholder="请输入密码">
                            <template slot="prepend">密码</template>
                        </el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="login()">确 定</el-button>
                </div>
            </el-dialog>
        </el-menu-item>
    </el-menu>
</template>

<script>
    import {mapState, mapGetters, mapActions} from "vuex";

    export default {
        data() {
            return {
                activeIndex: "1",
                dialogFormVisible: false,
                form: {
                    name: '',
                    password: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                },
            };
        }, computed: mapGetters(["URL"]),
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            login: function () {
                let successCallback = response => {
                    console.log("服务器请求登陆接口");
                    let result = response.data;
                    console.log("result:", result);
                    // todo 成功就進入管理員的頁面

                };
                let errorCallback = response => {
                    // 失敗就提示錯誤信息，不關閉頁面
                    console.log("服务器请求出错了");
                };
                this.$http
                    .post(this.URL.loginUrl+"&personName="+this.form.name+"&personPassword="+this.form.password)
                    .then(successCallback, errorCallback);
            }
        },
        watch: {
            $route(to, from) {
                console.log("nav监听路由信息：", to);
                if (to.path == "/albums") {
                    this.activeIndex = "2";
                } else if (to.path == "/photos") {
                    this.activeIndex = "1";
                }
                // /searchPhotos/:keyWord
                // /searchAlbums/:keyWord
            }
        }
    };
</script>
<style>
    .el-menu-item {
        font-size: 18px;
    }
    a {
        text-decoration: none;
    }
</style>
