<template>
  <div class="payResult">
    <div class="payResult-body">
      <van-nav-bar title="支付结果" left-arrow :border="false" @click-left="onClickLeft" />
      <van-row style="margin-top: 45px">
        <i class="iconfont icon-sucess"></i>
        <h3>支付成功</h3>
        <h3 style="font-size: 30px; margin-top: 25px">￥{{ money || moneys }}元</h3>
      </van-row>
      <van-row style="margin-top: 25px">
        <van-button type="primary" @click="returnHome()">返回</van-button>
      </van-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PaySucess',
  data () {
    return {
      money: '',
      moneys: ''
    }
  },
  mounted () {
    this.moneys = localStorage.getItem('moneys')
    this.money = localStorage.getItem('money')
  },
  methods: {
    onClickLeft () {
      this.$router.push('electricity')
    },
    returnHome () {
      this.$router.push('electricity')
    }
  }
}
</script>

<style lang="less" scoped>
.payResult {
  height: 100vh;
  background-color: #fff;
}

.van-button {
  width: 110px;
}
</style>
