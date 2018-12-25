<template>
    <div
            class="container"
    >
        <!--style="width: 100%"-->
        <!--v-loading="isNULL"-->
        <!--element-loading-text="拼命加载中"-->
        <!--element-loading-spinner="el-icon-loading"-->
            <div class="el-icon-warning"></div>
            <div style="margin-top:45px">暂时没有您需要的数据，你可以浏览其他页面~</div>
    </div>
</template>


<script>
    export default {
        props: {
            isNULL: Boolean
        },
        data() {
            return {};
        }
    };
</script>

<style scoped>

    .container {
        z-index: 0;
        height: 300px;
        background-color: rgb(255, 255, 255);
        display: flex;
        align-items: center;
        flex-direction: column;
        justify-content: center;
    }

    .el-icon-warning {
        font-size: 200px;
        color: rgb(117, 117, 117);
    }
</style>
