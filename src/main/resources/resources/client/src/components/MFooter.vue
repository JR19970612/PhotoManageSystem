<template>

    <footer class="mgt50 footer">
        <div class="footer_box1">
            <div style="display: flex;
    align-items: center;">
                <img src="src/assets/img/shan.png" alt="" width="72">
                <span>访问我们的官网</span>
                <span> <a href="#">©大尖山集团 </a>  使用系统前<a href="#">必读</a> <a href="#">意见反馈</a></span>
            </div>
            <div>
                友情链接：
                <span> <a href="https://google.com">Google</a></span>
                <span> <a href="https://youtube.com">Youtube</a></span>
                <span> <a href="https://onedrive.com">OneDrive</a></span>
                <div class="mgt10" style="color: #1a1a1a;font-size: 12px">粤ICP证567567号 粤公网安备567567567567号</div>
            </div>
        </div>
    </footer>

</template>

<script>
    export default {}
</script>

<style scoped>
    .footer {
        background: #8daac0;

    }

    .footer_box1 {
        height: 200px;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
    }

    footer div span {
        font-size: 14px;
    }
    a:visited{
        color: #7f2f2f;;
    }
    a{
        color: #7f2f2f;;
    }
</style>
