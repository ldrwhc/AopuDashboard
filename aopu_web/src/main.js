import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/global.css'
import axios from "axios";
import VueRouter from "vue-router";
import router from "./router";



Vue.config.productionTip = false
// Vue.prototype.$httpUrl = 'http://121.41.227.153:8089'
Vue.prototype.$httpUrl = 'http://localhost:8089'
Vue.prototype.$axios= axios

Vue.use(ElementUI,{size:'small'});
Vue.use(VueRouter)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
