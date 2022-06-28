<template>
  <div class="start-view-container">
    <el-header>
      <el-row type="flex" align="center" class="header-container">
        <img
          src="../assets/images/icon-logo.svg"
          alt
          srcset
          width="35px"
          height="35px"
          style="margin-top: 11px"
        />
        <h5 style="margin-left: 12px; font-size: 18px; color: rgb(102, 101, 101)">校园教学综合信息服务平台</h5>
      </el-row>
    </el-header>
    <div class="start-view-body">
      <span style="text-align: start; margin-right: 7%; margin-top: -7%">
        <h1>全新界面设计</h1>
        <p style="font-size: 22px; margin-top: 15px; color: #818181">简洁，高效，快速</p>
        <p style="font-size: 22px; margin-top: 15px; color: #818181">整体界面焕然一新，更清爽，更高效</p>
      </span>
      <img src="../assets/images/icon-start.svg" alt srcset width="27%" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'startView',
  data () {
    return {
      autoUsername: '',
      autoPassword: ''
    }
  },
  mounted () {
    let username = localStorage.getItem('saveUsername')
    if (username) {
      this.login()
    }
    localStorage.getItem
    setTimeout(() => {
      this.$router.push('home')
    }, 1600)
  },
  methods: {
    async login () {
      this.autoUsername = localStorage.getItem('saveUsername')
      this.autoPassword = localStorage.getItem('savePassword')
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/education/login',
        params: {
          username: this.autoUsername,
          password: this.autoPassword
        }
      }).then(res => {
        const code = res.data.code
        if (code === '200') {
          localStorage.setItem('adminFlag', res.data.flag)
          //创建登录token
          localStorage.setItem('eaToken', res.data.token)
          this.isLogin()
        } else if (code === '202') {
          console.log('error')
        } else if (code === '404') {
          console.log('error')
        }
      })
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
  },
}
</script>

<style lang="less" scoped>
.start-view-container {
  height: 100vh;
  background-color: #fff;
}
.start-view-body {
  height: calc(100vh - 60px);
  display: flex;
  justify-content: center;
  align-items: center;
}

.el-header {
  text-align: start;
  line-height: 60px;
}
</style>
