<template>
  <div class="eleInvest-box">
    <!-- 头部 -->
    <div class="eleInvest-header">
      <span style="display:flex; width: 7px; height:30px; background:#409EFF; margin-top:65px"></span>
      <h4 style="margin-left:20px;font-size:19px">电费充值</h4>
    </div>
    <!-- 查询 -->
    <div class="eleInvest-container">
      <!-- 宿舍号 -->
      <div class="dorm-num">
        <h3 style="font-size: 17px">宿舍号：</h3>
        <el-input placeholder="请输入宿舍号" v-model="roomNum" clearable style="width:250px"></el-input>
      </div>
      <!-- 电量 -->
      <div class="energy" style="margin-top:20px">
        <h3 style="font-size: 17px">当前电量</h3>
        <div class="energy-box">
          <span>{{ this.roomEnergy }}</span>
          <el-button class="search" type="primary" @click="FoundEnergy()">查询</el-button>
        </div>
      </div>
      <!-- 支付 -->
      <div class="eleInvest-amount" style="margin-top:40px">
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
            :value="item.sPassword"
          ></el-option>
        </el-select>
      </div>
    </div>
    <el-row class="btn-pay">
      <el-button
        type="primary"
        style="width:300px; margin-top:60px; height: 40px; font-size: 14px"
        @click="eleInvest()"
      >立即支付</el-button>
    </el-row>
  </div>
</template>
<script>
export default {
  name: 'ElectricityInvest',
  data () {
    return {
      roomNum: '',
      roomEnergy: '',
      buttonIndex: 8,
      inputMoney: '',
      options: [{
        value: '选项1',
        label: '校园卡'
      }],
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
    getLoginAccount () {
      this.loginAccount = localStorage.getItem('el-account')
    },
    // 余电查询
    async FoundEnergy () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/queryBettery',
        params: {
          roomNumber: this.roomNum
        }
      }).then(res => {
        if (res.data.code === '200') {
          this.roomEnergy = res.data.msg
        }
      })
    },
    // 充值
    async eleInvest () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/buyElectricity',
        params: {
          account: this.loginAccount,
          tran: this.changeValue || this.inputMoney,
          roomID: this.roomNum
        }
      }).then(res => {
        this.$message.success('充值成功')
      })
    },
    onSubmit (index) {
      this.buttonIndex = index
      this.changeValue = this.buttonValue[index].value
      console.log('value值' + this.changeValue)
    }
  }
}
</script>

<style>
.eleInvest-header {
  display: flex;
  margin-top: -70px;
}

.eleInvest-container {
  width: 100%;
  line-height: 40px;
  margin-top: -40px;
  text-align: start;
}

.energy-box {
  border: 0.5px solid #dcdfe6;
  position: relative;
  width: 250px;
  height: 32px;
  border-radius: 4px;
  font-size: 14px;
  text-align: center;
  line-height: 32px;
}

.eleInvest-amount .el-button {
  width: 80px;
  height: 30px;
  font-size: 5px;
  margin-right: 10px;
}

.search {
  float: right;
  position: absolut;
  border-radius: 3px;
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
  background-color: #ecf5ff;
  outline: none;
  text-align: center;
}

.pay-diy input::-webkit-input-placeholder {
  color: #409eff;
  text-align: center;
}
</style>
