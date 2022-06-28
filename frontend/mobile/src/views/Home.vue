<template>
  <div class="home-container">
    <div class="header-container">
      <van-nav-bar :safe-area-inset-top="true" :border="false">
        <template #left>
          <img src="../assets/images/icon-user.svg" alt srcset width="35px" height="35px" />
          <p style="margin-left: 5px">{{ loginUsername }}</p>
        </template>
      </van-nav-bar>
      <!-- 订阅系统入口 -->
      <span style="margin-right: 10px" @click="toSubscription()">
        <van-icon name="envelop-o" size="22px" color="rgb(107, 107, 107)" />
      </span>
      <van-popover
        v-model="showPopover"
        theme="dark"
        trigger="click"
        :actions="actions"
        placement="bottom-end"
        @select="onSelect"
      >
        <template #reference>
          <van-icon name="add-o" size="22px" color="rgb(107, 107, 107)" />
        </template>
      </van-popover>
    </div>
    <!-- 轮播区域 -->
    <div class="swiper-container">
      <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
        <van-swipe-item>
          <img src="@/assets/images/banner01.jpg" alt srcset />
        </van-swipe-item>
        <van-swipe-item>
          <img src="@/assets/images/banner02.jpg" alt srcset />
        </van-swipe-item>
        <van-swipe-item>
          <img src="@/assets/images/banner03.jpg" alt srcset />
        </van-swipe-item>
      </van-swipe>
    </div>
    <!-- 主要功能区域 -->
    <div class="card-view">
      <ul class="ul-major-function">
        <li class="li-major-function" @click="toSelectedLeasson">
          <img src="../assets/images/icon-lesson.svg" alt srcset />
          <p class="p-major-function">课程表</p>
        </li>
        <li class="li-major-function" @click="toResult">
          <img src="../assets/images/icon-scord.svg" alt srcset />
          <p class="p-major-function">查询成绩</p>
        </li>
        <li class="li-major-function" @click="toTestMessage">
          <img src="../assets/images/icon-test.svg" alt />
          <p class="p-major-function">考试信息</p>
        </li>
        <li class="li-major-function" @click="toElectricity">
          <img src="../assets/images/icon-electric.svg" alt srcset class="img-major-function" />
          <p class="p-major-function">电力系统</p>
        </li>
      </ul>
    </div>
    <!-- 今日课表区域 -->
    <div class="today-leasson">
      <TodayLeasson></TodayLeasson>
    </div>
    <!-- 通知区域 -->
    <div class="information-container">
      <div class="card-view">
        <div class="leasson-title">
          <span>通知</span>
        </div>
        <HomeMessage></HomeMessage>
      </div>
    </div>
    <!-- 电力系统登录对话框 -->
  </div>
</template>

<script>
import TodayLeasson from '@/components/TodayLeasson.vue'
import HomeMessage from '@/components/HomeMessage.vue'
export default {
  name: 'Home',
  components: { TodayLeasson, HomeMessage },
  data () {
    return {
      active: 2,
      show: false,
      admin: '',
      showPopover: false,
      loginUsername: '',
      actions: [{ text: '修改密码', icon: 'edit' }, { text: '退出登录', icon: 'close' }],
      ValidateCodeTime: '10:00',
      messageList: [],
      VerificationCodeImgUrl: '',
      EleloginParams: {
        username: '',
        inputPassword: '',
        inputVerificationCode: '',
        key: '',
        loginNpwd: ''
      },
      electricitlLoginToken: ''
    }
  },
  created () {
    this.loginUsername = localStorage.getItem('username')
    this.getAdmin()
    console.log(this.electricitlLoginToken)
  },
  methods: {
    toSelectedLeasson () {
      this.$router.push('lesson')
    },
    getAdmin () {
      this.admin = localStorage.getItem('admin')
    },
    toResult () {
      this.$router.push('queryResult')
    },
    toTestMessage () {
      this.$router.push('testMessage')
    },
    toSubscription () {
      if (this.admin === '1') {
        this.$router.push('subscriptionAdmin')
      } else {
        this.$router.push('subscription')
      }
    },
    onCancle () {
      this.show = true
    },
    onSelect (actions) {
      if (actions.text === '修改密码') {
        this.$router.push('changePassword')
      } else if (actions.text === '退出登录') {
        this.exitLogin()
      }
    },
    // 退出登录
    exitLogin () {
      this.$dialog.confirm({
        message: '退出登录？',
        confirmButtonColor: '#40a3f6'
      })
        .then(() => {
          // on confirm
          this.loginOut()
        })
        .catch(() => {
          // on cancel
        })
    },
    async loginOut () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/education/logout'
      }).then(res => {
        console.log(res)
        this.$toast.success({
          message: '退出成功'
        })
        localStorage.removeItem('el-account')
        localStorage.removeItem('channelCode')
        localStorage.removeItem('channelList')
        localStorage.removeItem('balance')
        localStorage.removeItem('lessonData')
        localStorage.removeItem('fullData')
        localStorage.removeItem('semester')
        localStorage.removeItem('semesterData')
        localStorage.removeItem('yearData')
        localStorage.removeItem('lesson')
        localStorage.removeItem('admin')
        localStorage.removeItem('year')
        localStorage.removeItem('username')
        localStorage.removeItem('password')
        localStorage.removeItem('money')
        localStorage.removeItem('channelMessage')
        localStorage.removeItem('coursesData')
        localStorage.removeItem('channelName')
        localStorage.removeItem('loginToken')
        localStorage.removeItem('eleToken')
        this.$router.push('login')
      })
    },
    toElectricity () {
      this.$router.push('/electricity')
    },
  }
}
</script>

<style lang="less" scoped>
.header-container {
  display: flex;
  align-items: center;
  background-color: #fff;
}
.header-container .van-nav-bar {
  flex: 1;
}
.header-container span {
  flex: 0.09;
  margin-right: 5px;
  cursor: pointer;
}
.title {
  font-family: 'Times New Roman', Times, serif;
}
// 轮播样式
.swiper-container {
  margin: 8px 5px;
}

.my-swipe {
  width: 100%;
  height: 170px;
  line-height: 150px;
  text-align: center;
  border-radius: 8px;
}

.my-swipe img {
  display: block;
  width: 100%;
  height: 100%;
}

//主要功能样式
.ul-major-function {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 8px;
}

.li-major-function {
  flex: 1;
}

.li-major-function img {
  width: 40px;
  height: 40px;
}

.li-major-function p {
  margin-top: 1px;
}

.p-major-function {
  font-size: 12px;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  margin-top: -2px;
}

//今日课表
.today-leasson {
  margin-top: 8px;
}

.information-container {
  margin-top: 8px;
}

// 通知
.leasson-title {
  display: flex;
  align-items: center;
  padding: 5px;
}

.leasson-title span {
  flex: 1;
  border-left: 4px solid rgb(6, 167, 6);
  padding-left: 6px;
  margin-left: 2px;
}

.leasson-title span:nth-child(1) {
  text-align: start;
  font-size: 15px;
  font-weight: 600;
  font-family: monospace;
}

.van-field {
  width: 90%;
  margin-top: 15px;
  margin-bottom: 12px;
  background-color: rgb(243, 243, 243);
  border-radius: 2px;
  border: 0;
}

.van-field:nth-child(3) {
  margin-bottom: 30px;
}

.code-style {
  width: 91px;
  height: 43px;
  margin-left: 5px;
}
</style>
