<template>
  <div class="login-container">
    <div class="login-body">
      <div class="login-title">
        <h2>登录</h2>
        <div class="second-title">
          <p>欢迎使用</p>
          <p>校园教学综合信息服务平台</p>
        </div>
      </div>
      <div class="login-input">
        <div class="filed-input">
          <van-field
            v-model="username"
            type="number"
            placeholder="请输入用户名"
            autofocus
            autosize
            clearable
            size="large"
          />
        </div>
        <!-- 输入密码 -->
        <div class="filed-input">
          <van-field
            v-model="password"
            type="password"
            placeholder="请输入密码"
            autosize
            size="large"
            clearable
          />
        </div>
      </div>
      <div class="login-button">
        <van-button
          type="info"
          color="linear-gradient(to right, #3DA2FF, #5D76DB)"
          @click="login"
        >登 录</van-button>
      </div>
      <img src="../assets/images/logins.svg" alt srcset width="75%" style="margin-top: 55px" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      username: '',
      password: '',
      admin: '',
      loginFlag: false
    }
  },
  // created () {
  //   this.loginFlag = localStorage.gettItem('loginFlag')
  // },
  // mounted () {
  //   console.log(this.loginFlag);
  //   let username = localStorage.getItem('username')
  //   if (username) {
  //     this.username = localStorage.getItem('username')
  //     this.password = localStorage.getItem('password')
  //     if (this.loginFlag === true) {
  //       this.$router.push('home')
  //     } else if (this.loginFlag === false) {
  //       // 开屏动画
  //       this.login()
  //     }
  //   }
  // },
  methods: {
    jump () {
      this.$router.push('home')
    },
    toChangePassword () {
      this.$router.push('changePassword')
    },
    // 登录
    async login () {
      var formData = new FormData()
      formData.append('username', this.username)
      formData.append('password', this.password)
      if (formData.get('username') !== '' && formData.get('password') !== '') {
        const isLoginToast = this.$toast.loading({
          duration: 0,
          message: '正在登录...'
        })
        await this.axios({
          methods: 'get',
          url: 'https://www.mzkt.xyz:443/smart-school/education/login',
          params: {
            username: formData.get('username'),
            password: formData.get('password')
          }
        })
          .then(res => {
            // 判断code是否为'200','200'代表登录成功
            if (res.data.code === '200') {
              localStorage.setItem('username', formData.get('username'))
              localStorage.setItem('password', formData.get('password'))
              isLoginToast.clear()
              this.isLogin()
              this.admin = res.data.flag
              localStorage.setItem('admin', this.admin)
              localStorage.setItem('loginToken', res.data.token)
              this.loginFlag = true
              localStorage.setItem('loginFlag', true)
              this.$toast.success({
                message: '登录成功'
              })
              setTimeout(() => {
                this.$router.push('home')
              }, 800)
              // 跳转到主页
            } else if (res.data.code === '202') {
              isLoginToast.clear()
              this.$toast.fail({
                message: '账号或密码错误',
                position: top
              })
            } else if (res.data.code === '404') {
              isLoginToast.clear()
              this.$dialog.alert({
                message: '登录发生错误，请重试 !',
                confirmButtonColor: '#4B9EF8'
              }).then(() => {
                this.loginOut()
              })
            }
          })
          .catch(err => {
            isLoginToast.clear()
            this.$dialog.alert({
              message: '网络错误，请稍后重试 !',
              confirmButtonColor: '#4B9EF8'
            })
            console.log(err)
            localStorage.removeItem('token')
          })
      } else {
        this.$toast.fail({
          message: '输入不能为空',
          position: top
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
    },
    async loginOut () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/education/logout'
      }).then(res => {
        this.$router.push('login')
        console.log(res.data)
        let flag = false
        localStorage.setItem('loginFlag', flag)
      }).catch(err => {
        console.log(err)
      })
    },
  }
}
</script>

<style lang="less" scoped>
.login-container {
  height: 100vh;
  background-color: #fff;
}

.login-body {
  padding: 0 8px;
}

.login-title {
  display: flex;
  padding-top: 12px;
  margin: 0 10px;
  flex-direction: column;
  align-items: flex-start;
}

.login-title h2 {
  font-family: Arial, Helvetica, sans-serif;
  font-size: 27px;
}

.login-title p {
  margin-top: -10px;
  color: rgb(122, 122, 122);
  font-size: 16px;
}

.second-title {
  display: flex;
}

.second-title p:nth-child(2) {
  color: #3da2ff;
}

.van-field {
  width: 95%;
  margin-left: 10px;
  margin-bottom: 25px;
  margin-right: 10px;
  background-color: rgb(243, 243, 243);
  border-radius: 5px;
  border: 0;
}

.login-button button {
  width: 95%;
  height: 47px;
  margin-top: 20px;
  border-radius: 5px;
  font-size: 17px;
}
</style>
