<template>
  <div class="electricCharge">
    <van-nav-bar title="电费充值" left-arrow @click-left="onClickLeft" :border="false" />
    <div class="electricCharge-body">
      <van-row>
        <van-field
          v-model="dormitoryNumber"
          type="number"
          placeholder="请输入宿舍号"
          autosize
          clearable
          left-icon="wap-home"
        />
      </van-row>
      <van-row type="flex" class="cell" justify="space-between" align="center">
        <van-col style="padding-left: 15px">{{ this.queryResult }}</van-col>
        <van-col>
          <van-button
            type="info"
            style="border-bottom-right-radius: 5px; border-top-right-radius: 5px;"
            @click="queryElectric"
          >查询电量</van-button>
        </van-col>
      </van-row>
      <van-row style="text-align: start; margin-left: 10px">
        <h4>充值金额</h4>
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
        <van-button class="btn-pay" @click="payElectric">立即充值 {{ this.moneyDetail || this.inputMoney }} 元</van-button>
      </van-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ElectricCharge',
  data () {
    return {
      dormitoryNumber: '',
      account: '',
      queryResult: '',
      inputMoney: '',
      buttonIndex: 10,
      moneyDetail: '',
      buttonItems: [
        { name: '10' },
        { name: '20' },
        { name: '50' },
        { name: '100' },
        { name: '200' },
      ]
    }
  },
  methods: {
    onClickLeft () {
      this.$router.push('electricity')
    },
    change: function (index) {
      this.buttonIndex = index;
      this.moneyDetail = this.buttonItems[index].name
    },
    async queryElectric () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/queryBettery',
        params: {
          roomNumber: this.dormitoryNumber
        }
      }).then(res => {
        this.queryResult = res.data.msg
      }).catch(err => {
        console.log(err)
      })
    },
    async payElectric () {
      this.account = localStorage.getItem('el-account')
      const isPay = this.$toast.loading({
        duration: 0,
        message: '正在支付...'
      })
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/buyElectricity',
        params: {
          account: this.account,
          tran: this.moneyDetail || this.inputMoney,
          roomID: this.dormitoryNumber
        }
      }).then(res => {
        if (res.data.code === '200') {
          isPay.clear()
          this.$dialog.confirm({
            message: '充值成功',
            confirmButtonColor: '#40a3f6'
          })
            .then(() => {
              // on confirm
            })
        } else if (res.data.code === '202') {
          this.$dialog.confirm({
            message: '充值失败, 请稍后重试',
            confirmButtonColor: '#40a3f6'
          })
            .then(() => {
              // on confirm
            })
        }
        console.log(res)
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.electricCharge {
  height: 100vh;
  background-color: #ffffff;
}

.electricCharge-body {
  padding: 0 15px;
}

.van-field {
  width: 100%;
  background-color: rgb(247, 247, 247);
  border-radius: 5px;
  border: 0;
  margin-top: 15px;
}

.cell {
  height: 45px;
  background-color: rgb(247, 247, 247);
  border-radius: 5px;
  margin-top: 15px;
}

.scp-grid {
  display: grid;
  grid: 65px / auto auto auto;
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

.money-chose:focus {
  background-color: #3da2ff;
  color: #fff;
}

.btn-pay {
  width: 100%;
  background: linear-gradient(to right, #3da2ff, #5d76db);
  height: 47px;
  margin-top: 40px;
  border-radius: 5px;
  font-size: 17px;
  border: 0;
  color: #fff;
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

.active {
  background: #3da2ff;
  color: #fff;
}

input::-webkit-input-placeholder {
  /* WebKit browsers */
  color: #b4b4b4;
}
</style>
