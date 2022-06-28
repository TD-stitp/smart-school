<template>
  <el-container>
    <div class="nav-container">
      <el-aside class="nav-box">
        <!-- 头像信息 -->
        <div class="nav-header">
          <div class="user-pic" style="display:flex">
            <img src="../assets/images/icon-user.svg" 
            alt 
            style="width: 70px; height: 70px; margin-left:10px; margin-top: 30px">
            <div class="user-inform" style="margin-top:-40px; margin-left:10px">
              <p>学号： {{ this.loginUsername }}</p>
              <p>一卡通账号： {{ this.loginAccount }}</p>
            </div>
          </div>
        </div>
        <el-divider></el-divider>
        <el-row class="tac">
          <el-col :span="12">
            <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
              router>
                <!-- 余额显示 -->
                <el-menu-item style="margin-top:-20px">
                  <img src="../assets/images/icon-card.svg" alt style="width:40px; height:40px">
                  <span style="margin-left: 20px">账户余额： {{ this.Mybalance }}元</span>
                </el-menu-item>
                <!-- 流水查询 -->
                <el-menu-item index="1" :route="{ path: '/home/eleNav/inquire' }">
                  <span slot="title">流水查询</span>
                </el-menu-item>
                <!-- 校园卡充值 -->
                <el-menu-item index="2" :route="{ path: '/home/eleNav/schoolInvest' }">
                  <span slot="title">校园卡充值</span>
                </el-menu-item>
                <!-- 电费充值 -->
                <el-menu-item index="3" :route="{ path: '/home/eleNav/inquire' }">
                  <span slot="title">电费充值</span>
                </el-menu-item>
                <!-- 校园卡挂失 -->
                <el-menu-item index="4" :route="{ path: '/home/eleNav/lostCard' }">
                  <span slot="title">校园卡挂失</span>
                </el-menu-item>
                <!-- 修改密码 -->
                <el-menu-item index="5" :route="{ path: '/home/eleNav/revise' }">
                  <span slot="title">修改密码</span>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-row>
      </el-aside>
    </div>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'EleNav',
  data () {
    return {
      Mybalance:''
    }
  },
  created () {
    this.getLoginUsername (),
    this.getLoginAccount (),
    this.getBalance ()
  },
  methods: {
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
      await this.axios ({
        method: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/electricity/queryBalance',
        params: {
          account: this.loginAccount,
        }
      }).then(res => {
        if (res.data.code === '200') {
          this.Mybalance = res.data.balance
        }
      })
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style lang="less" scoped>
.el-container{
  height: 100%;

  .nav-container{
    width: 300px;
    height: 100%;
    margin-top: -5px;
    margin-left: -5px;
    background-color: #fff;
    border-radius: 5px;

    .nav-header{
      height: 100px;
    }
  }
}
  .el-col{
    width: 100%;
    text-align: start;
  }
</style>