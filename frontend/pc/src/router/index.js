import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '@/views/Login.vue'
import Nav from '@/components/Nav.vue'
import Index from '@/components/Index.vue'
import Lesson from '@/components/Lesson.vue'
import Exam from '@/components/Exam.vue'
import Result from '@/components/Result.vue'
import Subscribe from '@/components/Subscribe.vue'
import Electricity from '@/components/Electricity.vue'
import Refresh from '@/components/Refresh.vue'
import ElectricityHome from '@/components/ElectricityHome.vue'
import Inquire from '@/components/Inquire.vue'
import SchoolInvest from '@/components/SchoolInvest.vue'
import ElectricityInvest from '@/components/ElectricityInvest.vue'
import CardLoss from '@/components/CardLoss.vue'
import Revise from '@/components/Revise.vue'
import EleRefresh from '@/components/EleRefresh.vue'
import SchRefresh from '@/components/SchRefresh.vue'
import Mysubscribe from '@/components/MySubscribe.vue'
import ChannelAdmin from '@/components/ChannelAdmin.vue'
import ChannelDetail from '@/components/ChannelDetail.vue'
import StartView from '@/views/startView.vue'
import UserHistoryMessage from '@/components/UserHistoryMessage.vue'
import NotFound from '@/views/404.vue'

Vue.use(VueRouter)

const routes = [{
    path: '/',
    redirect: '/startView'
  },
  {
    path: '/startView',
    name: 'StartView',
    component: StartView
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/404',
    name: '404',
    component: NotFound
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    redirect: '/home/index',
    meta: {
      mainCurrentRoute: '/home'
    },
    children: [{
        path: 'index',
        name: 'Index',
        component: Index
      },
      {
        path: 'lesson',
        name: 'Lesson',
        component: Lesson
      },
      {
        path: 'result',
        name: 'Result',
        component: Result
      },
      {
        path: 'exam',
        name: 'Exam',
        component: Exam
      },
      {
        path: 'refresh',
        name: 'Refresh',
        component: Refresh
      },
      {
        path: 'subscribe',
        name: 'Subscribe',
        component: Subscribe,
        redirect: '/home/subscribe/mySubscribe',
        children: [{
            path: 'mySubscribe',
            name: 'MySubscribe',
            component: Mysubscribe,
          },
          {
            path: 'channelAdmin',
            name: 'ChannelAdmin',
            component: ChannelAdmin,
          },
          {
            path: 'channelDetail',
            name: 'ChannelDetail',
            component: ChannelDetail,
          },
          {
            path: 'userHistoryMessage',
            name: 'UserHistoryMessage',
            component: UserHistoryMessage
          }
        ]
      },
      {
        path: 'electricity',
        name: 'Electricity',
        component: Electricity,
      },
      {
        path: 'electricityHome',
        name: 'ElectricityHome',
        component: ElectricityHome,
        redirect: '/home/electricityHome/inquire',
        children: [{
            path: 'inquire',
            name: 'Inquire',
            component: Inquire,
          },
          {
            path: 'schoolInvest',
            name: 'SchoolInvest',
            component: SchoolInvest,
          },
          {
            path: 'electricityInvest',
            name: 'ElectricityInvest',
            component: ElectricityInvest,
          },
          {
            path: 'cardLoss',
            name: 'Cardloss',
            component: CardLoss,
          },
          {
            path: 'revise',
            name: 'Revise',
            component: Revise,
          },
          {
            path: 'eleRefresh',
            name: 'EleRefresh',
            component: EleRefresh
          },
          {
            path: 'shcRefresh',
            name: 'SchRefresh',
            component: SchRefresh
          }
        ]
      },
    ]
  },
  {
    path: '/nav',
    name: 'Nav',
    component: Nav
  },
  {
    path: '*',
    redirect: '/404',
    name: 'notFound',
    hidden: true
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach(function (to, from, next) {
  if (to.path === '/home/index') {
    const token = localStorage.getItem('eaToken')
    if (token) {
      next()
    } else {
      next('/login')
    }
  } else {
    next()
  }
  if (to.path === '/home/electricityHome/inquire') {
    const token = localStorage.getItem('eleToken')
    if (token) {
      next()
    } else {
      next('/home/electricity')
    }
  } else {
    next()
  }
})

export default router
