<template>
  <div class="electricity">
    <van-nav-bar title="电力系统" left-arrow @click-left="onClickLeft" :border="false" />
    <!-- 账户余额 -->
    <div class="card-view">
      <van-row type="flex" justify="space-between" align="center">
        <van-col span="10" style="text-align: start">
          <i class="iconfont icon-user" style="color: aliceblue; font-size:15px"> {{ loginUsername }}</i>
        </van-col>
        <van-col :span="5">
          <span class="card-label">校园卡</span>
        </van-col>
      </van-row>
      <van-row>
        <p style="color: aliceblue">账户余额</p>
      </van-row>
      <van-row class="card-money">{{ this.balance }} 元</van-row>
    </div>
    <!-- 一卡通功能区 -->
    <van-row type="flex" class="ele-mainFunction">
      <van-col :span="6">
        <span @click="toSchoolCardPay">
          <img src="../assets/images/icon-card.svg" alt srcset width="47px" height="47px" />
          <p>校园卡充值</p>
        </span>
      </van-col>
      <van-col :span="6">
        <span @click="toElectricCharge">
          <img src="../assets/images/icon-ele-charge.svg" alt srcset width="47px" height="47px" />
          <p>电费充值</p>
        </span>
      </van-col>
      <van-col :span="6">
        <span @click="toCardLoss">
          <img src="../assets/images/icon-loss.svg" alt srcset width="47px" height="47px" />
          <p>校园卡挂失</p>
        </span>
      </van-col>
      <van-col :span="6">
        <span @click="toElChangePassword">
          <img src="../assets/images/icon-password.svg" alt srcset width="47px" height="47px" />
          <p>修改密码</p>
        </span>
      </van-col>
    </van-row>
    <!-- 流水查询 -->
    <van-row type="flex" class="ele-pay-history">
      <van-col :span="6" style="border-left: 5px solid #4b9ef8">流水查询</van-col>
    </van-row>
    <van-row>
      <PayHistory></PayHistory>
    </van-row>
  </div>
</template>

<script>
import PayHistory from '@/components/PayHistory.vue'
export default {
  name: 'Electricity',
  components: { PayHistory },
  data() {
    return {
      balance: '',
      loginUsername: ''
    }
  },
  created () {
    this.loginUsername = localStorage.getItem('username')
    this.getBalance()
  },
  methods: {
    onClickLeft () {
      this.$router.push('home')
    },
    toSchoolCardPay () {
      this.$router.push('schoolCardPay')
    },
    toElectricCharge () {
      this.$router.push('electricCharge')
    },
    toCardLoss () {
      this.$router.push('cardLoss')
    },
    toElChangePassword () {
      this.$router.push('elChangePssword')
    },
    // 账户余额
    async getBalance () {
      var elAccount = localStorage.getItem('el-account')
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/queryBalance',
        params: {
          account: elAccount
        }
      }).then(res => {
        this.balance = res.data.balance
        localStorage.setItem('balance', res.data.balance)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.electricity {
  height: 100vh;
  background-color: #fff;
}

.card-view {
  height: 19vh;
  background: linear-gradient(to right, #3da2ff, #5d76db);
  margin: 8px 20px;
}

.card-label {
  display: block;
  background-color: rgb(225, 243, 255);
  text-align: center;
  border-radius: 30px;
  font-size: 13px;
  padding: 1px;
}

.card-money {
  font-size: 21px;
  color: aliceblue;
  font-weight: 600;
}

.ele-mainFunction {
  margin-top: 20px;
}

.ele-mainFunction p {
  font-size: 12px;
  margin-top: 3px;
}

.ele-pay-history {
  margin: 20px 20px;
}
</style>
