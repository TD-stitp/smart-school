<template>
  <div class="start-container">
    <van-row class="bg-logo">
      <img src="../assets/images/logo.svg" alt srcset width="180px" height="180px" />
    </van-row>
    <div class="bg-logo-bottom">
      <img src="../assets/images/logo-white.svg" alt srcset width="42px" height="42px" />
      <span class="bg-bottom-text">
        <p>校园教学综合信息服务平台</p>
        <p style="color: #aacefd">Campus information platform</p>
      </span>
    </div>
  </div>
</template>

<script>

export default {
  name: 'startPage',
  mounted () {
    let username = localStorage.getItem('username')
    if (username) {
      this.login()
    }
    setTimeout(() => {
      this.$router.replace('home')
    }, 1600)
  },
  methods: {
    async login () {
      let autoLoginUsername = localStorage.getItem('username')
      let autoLoginPassword = localStorage.getItem('password')
      if (autoLoginPassword !== '' && autoLoginUsername !== '') {
        await this.axios({
          methods: 'get',
          url: 'https://www.mzkt.xyz:443/smart-school/education/login',
          params: {
            username: autoLoginUsername,
            password: autoLoginPassword
          }
        })
          .then(res => {
            // 判断code是否为'200','200'代表登录成功
            if (res.data.code === '200') {
              this.isLogin()
              this.admin = res.data.flag
              localStorage.setItem('admin', this.admin)
              localStorage.setItem('loginToken', res.data.token)
              this.loginFlag = true
              localStorage.setItem('loginFlag', true)
            } else if (res.data.code === '404') {
              isLoginToast.clear()
              this.loginOut()
            }
          })
          .catch(err => {
            isLoginToast.clear()
            console.log(err)
            localStorage.removeItem('token')
          })
      }
    },
    async isLogin () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/education/toLogin'
      }).then(res => {
        console.log(res)
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.start-container {
  height: 100vh;
  background-color: #6692fb;
}

.bg-logo {
  position: relative;
  top: 20%;
}

.bg-logo-bottom {
  display: flex;
  width: 100%;
  position: absolute;
  bottom: 0;
  align-items: center;
  justify-content: center;
  margin-bottom: 40%;
}

.bg-bottom-text {
  color: #ffffff;
  line-height: 12px;
  font-size: 14px;
  margin-left: 15px;
  text-align: start;
}

.bg-logo-bottom img {
  position: relative;
}

h4 {
  line-height: 30px;
  margin-top: -100px;
}
</style>
