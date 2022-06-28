import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import '@/assets/css/mainc.css'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/images/icon/Aside/iconfont.css'
import '@/assets/images/icon/Index/iconfont.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import vuescroll from 'vuescroll'
import 'vuescroll/dist/vuescroll.css'

Vue.use(ElementUI, { size: 'small', zIndex: 3000 })
Vue.use(ElementUI)
Vue.config.productionTip = false
Vue.use(VueAxios, axios)
axios.defaults.withCredentials = true
Vue.use(vuescroll)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
