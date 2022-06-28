import Vue from 'vue'
import App from './App.vue'
import router from './router'
import '@/assets/css/common.css'
import axios from 'axios'
import VueCookies from 'vue-cookies'
import '@/assets/images/icon/iconfont.css'

// 导入所需组件
import {
  Button,
  NavBar,
  Swipe,
  SwipeItem,
  Sticky,
  SwipeCell,
  Icon,
  Tab,
  Tabs,
  Col,
  Row,
  Cell,
  CellGroup,
  Field,
  Toast,
  Dialog,
  DropdownMenu,
  DropdownItem,
  Empty,
  Lazyload,
  Collapse,
  CollapseItem,
  Divider,
  Notify,
  Checkbox,
  CheckboxGroup,
  Popover,
  PasswordInput,
  NumberKeyboard,
  ActionSheet,
  Tag,
} from 'vant'

import './registerServiceWorker'


Vue.prototype.axios = axios
axios.defaults.withCredentials = true
Vue.prototype.$cookies = VueCookies

Vue.use(Button)
Vue.use(NavBar)
Vue.use(Swipe)
Vue.use(SwipeItem)
Vue.use(Sticky)
Vue.use(Icon)
Vue.use(Tab)
Vue.use(Tabs)
Vue.use(Col)
Vue.use(Row)
Vue.use(Cell)
Vue.use(CellGroup)
Vue.use(Field)
Vue.use(Toast)
Vue.use(DropdownMenu)
Vue.use(DropdownItem)
Vue.use(Dialog)
Vue.use(Empty)
Vue.use(Lazyload)
Vue.use(Collapse)
Vue.use(CollapseItem)
Vue.use(Divider)
Vue.use(Notify)
Vue.use(Checkbox)
Vue.use(CheckboxGroup)
Vue.use(Popover)
Vue.use(PasswordInput)
Vue.use(NumberKeyboard)
Vue.use(ActionSheet)
Vue.use(Tag)
Vue.use(SwipeCell)
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
