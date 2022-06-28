<template>
  <div class="SchoolInvest-box">
    <div class="ScInvest-header">
      <!-- 头部 -->
      <span style="display:flex; width: 7px; height: 30px; background: #409EFF; margin-top: 65px"></span>
      <h4 style="margin-left:20px; font-size:19px">校园卡充值</h4>
    </div>
    <div class="ScInvest-container">
      <!-- 金额 -->
      <div class="Invest-amount" style="margin-top: -45px">
        <h3 style="font-size: 17px">支付金额:</h3>
        <el-row v-model="changeValue" style="margin-top: 5px" type="flex">
          <span
            v-for="(items, index) in buttonValue"
            :key="index"
            class="btn-checked"
            :class="buttonIndex === index ? 'active' : ''"
            @click="onSubmit(index)"
          >{{ items.value }} 元</span>
          <span class="pay-diy">
            <input type="text" placeholder="自定义金额" v-model="inputMoney" />
          </span>
        </el-row>
      </div>
      <!-- 支付方式 -->
      <div class="Invest-method" style="margin-top:20px">
        <h3 style="font-size: 17px">支付方式：</h3>
        <el-select v-model="value" placeholder="请选择支付方式">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.roomNum"
          ></el-option>
        </el-select>
      </div>
      <!-- 查询密码 -->
      <div class="Invest-chaxun" style="margin-top:20px">
        <h3 style="font-size: 17px">查询密码：</h3>
        <el-input
          type="password"
          placeholder="请输入查询密码"
          v-model="elePassword"
          show-password
          clearable
          style="width:215px"
        ></el-input>
      </div>
    </div>
    <el-row class="btn-pay">
      <el-button type="primary" style="width:300px; margin-top:60px" @click="deposit()">立即支付</el-button>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Inquire',
  data () {
    return {
      options: [{
        value: '1',
        label: '中国银行'
      }],
      elePassword: '',
      buttonIndex: 8,
      inputMoney: '',
      value: '',
      // 金额按钮value
      changeValue: '',
      // 金额数据
      buttonValue: [
        { value: '10' },
        { value: '20' },
        { value: '30' },
        { value: '50' },
        { value: '100' },
        { value: '200' },
        { value: '300' }
      ]
    }
  },
  created () {
    this.getLoginAccount()
  },
  methods: {
    // 获取用户名
    getLoginAccount () {
      this.loginAccount = localStorage.getItem('el-account')
    },
    async deposit () {
      var Base64 = require('js-base64').Base64
      var inputpassword = Base64.encode(this.elePassword);
      await this.axios({
        method: 'post',
        url: 'https://www.mzkt.xyz/smart-school/electricity/recharge',
        params: {
          account: this.loginAccount,
          password: inputpassword,
          tranamt: this.changeValue || this.inputMoney
        }
      }).then(res => {
        if (res.data.code === '200') {
          this.$router.push('/home/refresh')
          setTimeout(() => {
            this.$alert('充值成功', '提示', {
              confirmButtonText: '确定'
            })
          }, 1500)
        } else if (res.data.code === '202') {
          this.$alert('充值失败', '提示', {
            confirmButtonText: '确定'
          })
        }
      })
    },
    // 充值数额
    onSubmit (index) {
      this.buttonIndex = index
      this.changeValue = this.buttonValue[index].value
      console.log('value值' + this.changeValue)
    }
  }
}
</script>

<style lang="less" scoped>
.ScInvest-header {
  display: flex;
  margin-top: -70px;
}

.ScInvest-container {
  width: 100%;
  line-height: 40px;
  text-align: start;
}

.Invest-amount .el-button {
  width: 80px;
  height: 30px;
  margin-right: 10px;
}

.btn-pay .el-button {
  height: 40px;
  font-size: 14px;
}

.btn-checked {
  display: block;
  width: 105px;
  height: 45px;
  line-height: 45px;
  background-color: #ecf5ff;
  margin-right: 15px;
  text-align: center;
  border-radius: 5px;
  border: 1px solid #b3d8ff;
  color: #409eff;
  transition: 0.2s;
  cursor: pointer;
}

.btn-checked:hover {
  background-color: #409eff;
  color: #ffffff;
  cursor: pointer;
}

.active {
  background-color: #409eff;
  color: #fff;
}

.pay-diy {
  display: block;
  width: 105px;
  height: 45px;
  line-height: 45px;
  background-color: #ecf5ff;
  margin-right: 15px;
  text-align: center;
  border-radius: 5px;
  border: 1px solid #b3d8ff;
  color: #409eff;
  transition: 0.2s;
  cursor: pointer;
}

.pay-diy input {
  width: 95px;
  height: 40px;
  border: 0;
  font-size: 15px;
  text-align: center;
  background-color: #ecf5ff;
  outline: none;
}

.pay-diy input::-webkit-input-placeholder {
  color: #409eff;
  text-align: center;
}
</style>
