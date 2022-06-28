import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login.vue'
import ChangePassword from '@/views/ChangePassword.vue'
import QueryResult from '@/views/QueryResults.vue'
import TestMessage from '@/views/TestMessage.vue'
import SchoolTimetable from '@/views/SchoolTimetable.vue'
import Home from '@/views/Home.vue'
import ElectricityLogin from '@/views/ELectricityLogin.vue'
import Electricity from '@/views/Electricity.vue'
import SchoolCardPay from '@/components/SchoolCardPay.vue'
import ElectricCharge from '@/components/ElectricCharge.vue'
import CardLoss from '@/components/CardLoss.vue'
import ElChangePassword from '@/components/ElChangePassword.vue'
import PaySucess from '@/components/PaySucess.vue'
import Subscription from '@/views/Subscription.vue'
import SubscriptAdmin from '@/views/SubscriptionAdmin.vue'
import ChannalMessage from '@/components/ChannalMessage.vue'
import ChannelAdmin from '@/components/ChannelAdmin.vue'
import Refresh from '@/views/Refresh.vue'
import MyChannelMessage from '@/components/MyChannelMessage.vue'
import AdminMessage from '@/components/AdminMessage.vue'
import Apply from '@/components/Apply.vue'
import startPage from '@/views/startView.vue'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter)

const routes = [{
    path: '/',
    redirect: '/startView'
  },
  {
    path: '/startView',
    component: startPage
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/changePassword',
    component: ChangePassword
  },
  {
    path: '/queryResult',
    component: QueryResult
  },
  {
    path: '/testMessage',
    component: TestMessage
  },
  {
    path: '/lesson',
    component: SchoolTimetable
  },
  {
    path: '/electricity',
    component: Electricity
  },
  {
    path: '/schoolCardPay',
    component: SchoolCardPay
  },
  {
    path: '/electricCharge',
    component: ElectricCharge
  },
  {
    path: '/cardLoss',
    component: CardLoss
  },
  {
    path: '/elChangePssword',
    component: ElChangePassword
  },
  {
    path: '/paySucess',
    component: PaySucess
  },
  {
    path: '/subscription',
    component: Subscription
  },
  {
    path: '/subscriptionAdmin',
    component: SubscriptAdmin,
  },
  {
    path: '/channalMessage',
    component: ChannalMessage
  },
  {
    path: '/channelAdmin',
    component: ChannelAdmin
  },
  {
    path: '/refresh',
    component: Refresh
  },
  {
    path: '/myChannelMessage',
    component: MyChannelMessage
  },
  {
    path: '/adminMessage',
    component: AdminMessage
  },
  {
    path: '/apply',
    component: Apply
  },
  {
    path: '/electricityLogin',
    component: ElectricityLogin
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach(function (to, from, next) {
  if (to.path === '/home') {
    const token = localStorage.getItem('loginToken')
    if (token) {
      next()
    } else {
      next('/login')
    }
  } else {
    next()
  }
  if (to.path === '/electricity') {
    const token = localStorage.getItem('eleToken')
    if (token) {
      next()
    } else {
      next('/electricityLogin')
    }
  } else {
    next()
  }
})

export default router
