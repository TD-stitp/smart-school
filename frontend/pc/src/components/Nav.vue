<template>
  <div class="left-menu">
    <el-row class="tac">
      <el-col :span="12">
        <el-menu
          :default-active="defaultActive"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          router
          active-text-color="#409eff"
        >
          <el-menu-item :index="item.path" v-for="item in asideMenu" :key="item.path">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.label }}</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data () {
    return {
      activeIndex: '/home/index',
      asideMenu: [
        {
          path: '/home/index',
          name: 'index',
          label: '首页',
          icon: 'iconfont el-icon-home'
        },
        {
          path: '/home/lesson',
          name: 'lesson',
          label: '课程表',
          icon: 'iconfont el-icon-lesson'
        },
        {
          path: '/home/result',
          name: 'result',
          label: '我的成绩',
          icon: 'iconfont el-icon-score'
        },
        {
          path: '/home/exam',
          name: 'exam',
          label: '考试信息',
          icon: 'iconfont el-icon-exam'
        },
        {
          path: '/home/subscribe',
          name: 'subscribe',
          label: '我的订阅',
          icon: 'iconfont el-icon-message'
        },
        {
          path: '/home/electricityHome',
          name: 'electricity',
          label: '电力系统',
          icon: 'iconfont el-icon-lightning'
        }
      ],
      defaultActive: '/home/index',
    }
  },
  watch: {
    $route () {
      this.setCurrentRoute()
    },
  },
  created () {
    this.setCurrentRoute()
  },
  methods: {
    setCurrentRoute () {
      this.defaultActive = this.$route.path
      if (this.$route.path === '/home/subscribe/mySubscribe') {
        this.defaultActive = '/home/subscribe'
      } else if (this.$route.path === '/home/subscribe/channelAdmin') {
        this.defaultActive = '/home/subscribe'
      } else if (this.$route.path === '/home/subscribe/channelDetail') {
        this.defaultActive = '/home/subscribe'
      } else if (this.$route.path === '/home/subscribe/userHistoryMessage') {
        this.defaultActive = '/home/subscribe'
      } else if (this.$route.path === '/home/electricity') {
        this.defaultActive = '/home/electricityHome'
      } else if (this.$route.path === '/home/electricityHome/inquire') {
        this.defaultActive = '/home/electricityHome'
      } else if (this.$route.path === '/home/electricityHome/schoolInvest') {
        this.defaultActive = '/home/electricityHome'
      } else if (this.$route.path === '/home/electricityHome/electricityInvest') {
        this.defaultActive = '/home/electricityHome'
      } else if (this.$route.path === '/home/electricityHome/cardLoss') {
        this.defaultActive = '/home/electricityHome'
      } else if (this.$route.path === '/home/electricityHome/revise') {
        this.defaultActive = '/home/electricityHome'
      }
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
  }
}
</script>

<style lang="less" scoped>
.left-menu {
  height: 100%;
  background-color: #fff;
}

.el-col {
  width: 100%;
  text-align: start;
}

a {
  outline: none;
  text-decoration: none;
}

.el-menu {
  height: calc(100vh - 60px);
}

.el-menu-item span {
  margin-left: 10px;
}

.el-submenu span {
  margin-left: 8px;
}

.el-menu-item.is-active {
  color: #409eff;
  border-right: 4px solid #409eff;
  background-color: #ecf5ff;
}
</style>