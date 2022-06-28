<template>
  <div class="schoolCardPay-container">
    <!-- 校园卡充值title -->
    <van-nav-bar title="校园卡充值" left-arrow :border="false" @click-left="onClickLeft" />
    <div class="schoolCardPay-body">
      <div class="card-view">
        <van-row type="flex" justify="space-between">
          <van-col span="10" style="text-align: start">
            <i
              class="iconfont icon-user"
              style="color: aliceblue; font-size:15px"
            >{{ loginUsername }}</i>
          </van-col>
          <van-col :span="5">
            <span class="card-label">校园卡</span>
          </van-col>
        </van-row>
        <van-row>
          <p style="color: aliceblue">账户余额</p>
        </van-row>
        <van-row class="card-money">
          <h3 style="color: #fff; font-size: 23px">{{ this.balance + '元'}}</h3>
        </van-row>
      </div>
      <van-row style="text-align: start">
        <h3>充值金额</h3>
      </van-row>
      <van-row type="flex">
        <ul class="scp-grid">
          <li
            class="btn-money"
            v-for="(item,index) in buttonItems"
            :key="index"
            :class="{ active:buttonIndex===index}"
            @click="change(index)"
          >{{ item.name + '元' }}</li>
          <span class="btn-money-diy">
            <input
              v-model="inputMoney"
              type="number"
              placeholder="自定义金额"
              style="background-color: #ededed; width: 95px; height: 40px; font-size: 15px; text-align: center;"
            />
          </span>
        </ul>
      </van-row>
      <van-row style="text-align: start">
        <h3>支付方式</h3>
      </van-row>
      <van-row style="text-align: start">
        <van-checkbox v-model="checked" disabled>银行卡支付</van-checkbox>
        <p style="font-size: 12px">*注:该银行卡是与校园卡绑定的卡</p>
      </van-row>
      <van-row style="text-align: start">
        <van-button
          type="info"
          class="btn-pay"
          @click="showDialog"
        >立即充值 {{ this.moneyDetail || this.inputMoney }}元</van-button>
      </van-row>
      <van-action-sheet v-model="show" title="请输入支付密码" class="password-dialog">
        <div class="content">
          <p style="font-size: 17px; margin-top: 20px">校园卡充值</p>
          <h4
            style="font-size: 30px; margin-top: -10px"
          >￥{{ this.moneyDetail || this.inputMoney }} 元</h4>
          <van-password-input
            :value="inputPassword"
            :focused="showKeyboard"
            @focus="showKeyboard = true"
            style="margin-bottom: 30px"
            :gutter="8"
          />
          <!-- 数字键盘 -->
          <van-number-keyboard
            v-model="inputPassword"
            :show="showKeyboard"
            @blur="showKeyboard = false"
          />
          <van-row class="confrim-button">
            <van-button
              type="info"
              style="width: 100%; height: 60px; font-size: 18px; border-radius: 0"
              @click="ShchoolCardPay"
            >确定</van-button>
          </van-row>
        </div>
      </van-action-sheet>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SchoolCardPay',
  data () {
    return {
      checked: 'true',
      show: false,
      account: '',
      inputPassword: '',
      loginUsername: '',
      buttonIndex: 7,
      moneyDetail: '',
      showKeyboard: true,
      balance: '',
      inputMoney: '',
      buttonItems: [
        { name: '10' },
        { name: '20' },
        { name: '50' },
        { name: '100' },
        { name: '200' },
      ]
    }
  },
  created () {
    this.loginUsername = localStorage.getItem('username')
    this.balance = localStorage.getItem('balance')
  },
  methods: {
    onClickLeft () {
      this.$router.push('electricity')
    },
    change: function (index) {
      this.buttonIndex = index;
      this.moneyDetail = this.buttonItems[index].name
      if (this.buttonItems[index].name === '') {
        this.moneyDetail = this.inputMoney
      }
    },
    showDialog () {
      this.show = !this.show
    },
    async ShchoolCardPay () {
      this.account = localStorage.getItem('el-account')
      let Base64 = require('js-base64').Base64
      var sendPassword = Base64.encode(this.inputPassword)
      this.show = false
      const isPay = this.$toast.loading({
        duration: 0,
        message: '正在支付...',
      })
      await this.axios({
        method: 'post',
        url: 'https://www.mzkt.xyz/smart-school/electricity/recharge',
        params: {
          account: this.account,
          tranamt: this.moneyDetail || this.inputMoney,
          password: sendPassword
        }
      }).then(res => {
        if (res.data.code === '200') {
          isPay.clear()
          this.$router.push('paySucess')
          localStorage.setItem('moneys', this.inputMoney)
          localStorage.setItem('money', this.moneyDetail)
        }
      }).catch(err => {
        console.log(err)
      })
    },
  }
}
</script>

<style lang="less" scoped>
.schoolCardPay-container {
  height: 100vh;
  background-color: #fff;
}

.schoolCardPay-body {
  padding: 0 20px;
}

.money-chose {
  width: 100%;
  height: 50px;
  text-align: center;
  line-height: 50px;
  border-radius: 5px;
  background-color: rgb(241, 241, 241);
  color: rgb(189, 189, 189);
  border: 0;
  font-weight: 600;
  font-size: 16px;
}

.card-view {
  height: 19vh;
  background: linear-gradient(to right, #3da2ff, #5d76db);
  margin: 10px 0;
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
  margin-top: -22px;
}

.van-field {
  width: 60%;
  background-color: rgb(243, 243, 243);
  border-radius: 5px;
  border: 0;
  margin-top: 20px;
}

.btn-pay {
  width: 100%;
  background: linear-gradient(to right, #3da2ff, #5d76db);
  height: 47px;
  margin-top: 20px;
  border-radius: 5px;
  font-size: 17px;
}

.scp-grid {
  display: grid;
  grid: 65px / auto auto auto;
}

.btn-money {
  display: block;
  width: 105px;
  height: 50px;
  border: 0;
  background: #ededed;
  border-radius: 5px;
  line-height: 50px;
  margin-right: 12px;
  color: #b4b4b4;
}

.btn-money-diy {
  display: block;
  width: 105px;
  height: 50px;
  border: 0;
  background: #ededed;
  border-radius: 5px;
  line-height: 50px;
  margin-right: 12px;
}

.btn-money:checked {
  background: #3da2ff;
}

input::-webkit-input-placeholder {
  /* WebKit browsers */
  color: #b4b4b4;
}

.active {
  background: #3da2ff;
  color: #fff;
}

.password-dialog {
  height: 60%;
  background-color: #f7f8fa;
}

.confrim-button {
  width: 100%;
  position: fixed;
  bottom: 0;
}
</style>
