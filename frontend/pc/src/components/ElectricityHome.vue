<template>
  <div class="eleNav-container">
    <el-container>
      <el-aside width="320px">
        <el-row type="flex" style="padding-left: 10px; margin-top: 20px" align="center">
          <el-col style="text-align: start" :span="11">
            <img src="../assets/images/icon-user.svg" alt srcset width="75px" height="75px" />
          </el-col>
          <el-col style="line-height: 45px; text-align: start">
            <p style="font-size: 200">学号: {{ this.loginUsername }}</p>
            <p style="font-size: 200">一卡通账号: {{ this.loginAccount }}</p>
          </el-col>
        </el-row>
        <el-divider></el-divider>
        <el-row
          type="flex"
          style="padding: 0 15px; height: 130px; line-height: 30px; "
          align="center"
        >
          <span style="display: block">
            <img src="../assets/images/icon-card.svg" alt srcset width="50px" height="50px" />
          </span>
          <span style="display: block; margin-left: 25px; margin-top: 11px">
            <h3 style="font-size: 20px">账户余额: {{ Mybalance }} 元</h3>
          </span>
        </el-row>
        <!-- 左侧导航栏 -->
        <el-row class="tac">
          <el-col :span="12" style="width: 100%; margin-top: -40px; text-align: start;">
            <el-menu
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
              :default-active="activeIndex"
              router
            >
              <el-menu-item index="/home/electricityHome/inquire">
                <span slot="title">流水查询</span>
              </el-menu-item>
              <el-menu-item index="/home/electricityHome/schoolInvest">
                <span slot="title">校园卡充值</span>
              </el-menu-item>
              <el-menu-item index="/home/electricityHome/electricityInvest">
                <span slot="title">电费充值</span>
              </el-menu-item>
              <el-menu-item index="/home/electricityHome/cardLoss">
                <span slot="title">校园卡挂失</span>
              </el-menu-item>
              <el-menu-item index="/home/electricityHome/revise">
                <span slot="title">修改查询密码</span>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'ElectricityHome',
  data () {
    return {
      Mybalance: '',
      activeIndex: '/home/electricityHome/inquire'
    }
  },
  created () {
    this.getLoginUsername(),
      this.getLoginAccount(),
      this.getBalance()
  },
  watch: {
    // 监听路由变化
    '$route.path': {
      handler (routePath) {
        this.initMenuActive(routePath)
      },
      immediate: true
    }
  },
  methods: {
    initMenuActive (routePath) {
      if (routePath == '/home/electricityHome/inquire') {
        this.activeIndex = '/home/electricityHome/inquire'
      } else if (routePath == '/home/electricityHome/schoolInvest') {
        this.activeIndex = '/home/electricityHome/schoolInvest'
      } else if (routePath == '/home/electricityHome/electricityInvest') {
        this.activeIndex = '/home/electricityHome/electricityInvest'
      } else if (routePath == '/home/electricityHome/cardLoss') {
        this.activeIndex = '/home/electricityHome/cardLoss'
      } else if (routePath == '/home/electricityHome/revise') {
        this.activeIndex = '/home/electricityHome/revise'
      } else {
        this.activeIndex = routePath
      }
    },
    // 学号
    getLoginUsername () {
      this.loginUsername = localStorage.getItem('saveUsername')
    },
    // 一卡通卡号
    getLoginAccount () {
      this.loginAccount = localStorage.getItem('el-account')
    },
    // 余额
    async getBalance () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/queryBalance',
        params: {
          account: this.loginAccount,
        }
      }).then(res => {
        console.log(res)
        if (res.data.code === '200') {
          this.Mybalance = res.data.balance
        }
      })
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    }
  }
}
</script>

<style lang="less" scoped>
.el-aside {
  height: 89vh;
  background-color: #fff;
  border-radius: 5px;
}

.el-main {
  background-color: #fff;
  border-radius: 5px;
  margin-left: 15px;
}

.el-divider {
  margin-top: -35px;
  background-color: rgb(240, 240, 240);
}

.el-menu {
  border-right: 0;
}

.el-menu-item.is-active {
  background-color: rgb(236, 245, 255);
  color: #409eff;
  border-right: 4px solid #409eff;
}
</style>
