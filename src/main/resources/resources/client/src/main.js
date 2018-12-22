import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css' //该样式文件需要单独引入
import '../src/assets/public.css' //该样式文件需要单独引入
import App from './App.vue'
import VueRouter from 'vue-router'
import routerConfig from './router.config.js'
// import axios from 'axios'
import store from './store.js' //导入store对象
import VueResource from 'vue-resource'
/*使用VueResource插件*/
Vue.use(VueResource);

// 2. 使用VueRouter
Vue.use(VueRouter);

//创建路由实例
const router = new VueRouter(routerConfig);
Vue.prototype.$notify = ElementUI.Notification;

Vue.prototype.$covertDate = function (value) {
  var date = new Date(value);
  var fmt = "yyyy-MM-dd hh:mm:ss";

  var o = {
    "M+": date.getMonth() + 1, //月份
    "d+": date.getDate(), //日
    "h+": date.getHours(), //小时
    "m+": date.getMinutes(), //分
    "s+": date.getSeconds(), //秒
    "q+": Math.floor((date.getMonth() + 3) / 3), //季度
    S: date.getMilliseconds() //毫秒
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(
      RegExp.$1,
      (date.getFullYear() + "").substr(4 - RegExp.$1.length)
    );
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt))
      fmt = fmt.replace(
        RegExp.$1,
        RegExp.$1.length == 1
          ? o[k]
          : ("00" + o[k]).substr(("" + o[k]).length)
      );
  return fmt;
  // return this.dateFtt(fmt, date); //直接调用公共JS里面的时间类处理的办法
}

// Vue.prototype.$http=axios;
Vue.use(ElementUI);

new Vue({
  el: '#app',
  render: h => h(App),
  router,
  VueResource,//通过import引入，然后在这里调用
  store,
})
