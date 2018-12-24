<template>
    <div>
        <el-row>
            <el-col :span="8">
                <el-input id="name" v-model="name" placeholder="请输入帐号">
                    <template slot="prepend">帐号</template>
                </el-input>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="8">
                <el-input id="password" v-model="password" type="password" placeholder="请输入密码">
                    <template slot="prepend">密码</template>
                </el-input>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="8">
                <el-button id="login" v-on:click="login" style="width:100%" type="primary">登录</el-button>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import {mapState, mapGetters, mapActions} from "vuex";

    export default {
        data() {
            return {
                dialogFormVisible: false,
                form: {
                    name: '',
                    region: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                },
            };
        },
        methods: {
            login: function (event) {
//-----------------------------------------------
                let successCallback = response => {
                    console.log("服务器请求登陆接口");
                    let result = response.data;
                    console.log("result:", result);

                };
                let errorCallback = response => {
                    console.log("服务器请求出错了");
                };
                this.$http
                    .post(this.URL.loginUrl)
                    .then(successCallback, errorCallback);
            }
        },
        computed: mapGetters(["URL"]),
        components: {},
        mounted() {
        },
        watch: {}
    };
</script>

<style>
</style>
