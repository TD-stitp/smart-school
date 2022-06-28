<template>
  <div class="cardLoss-container">
    <van-nav-bar title="校园卡挂失" left-arrow @click-left="onClickLeft" :border="false" />
    <div class="carfLoss-body">
      <van-row style="margin-top: 20px">
        <img
          src="../assets/images/icon-noLoss.svg"
          alt
          srcset
          width="90px"
          height="90px"
          v-if="this.flag === false"
        />
        <img
          src="../assets/images/isLoss.svg"
          alt
          srcset
          width="90px"
          height="90px"
          v-if="this.flag === true"
        />
        <van-row type="flex" justify="center">
          <p>当前状态：</p>
          <p style="color: #2595e8; font-weight: 600" v-if="this.flag === false">未挂失</p>
          <p style="color: #18a05e; font-weight: 600" v-if="this.flag === true">已挂失</p>
        </van-row>
      </van-row>
      <van-row>
        <van-field
          v-model="password"
          type="password"
          placeholder="请输入一卡通密码"
          autosize
          clearable
          left-icon="wap-home"
        >
          <template v-slot:left-icon>
            <i class="iconfont icon-password-o"></i>
          </template>
        </van-field>
      </van-row>
      <!-- 挂失按钮 -->
      <van-row style="text-align: start; margin-top: 20px" v-if="this.flag === false">
        <van-button class="btn-loss" @click="Lost()">立即挂失</van-button>
      </van-row>
      <!-- 解挂按钮 -->
      <van-row style="text-align: start; margin-top: 20px" v-if="this.flag === true">
        <van-button class="btn-loss" @click="Lost()">解挂</van-button>
      </van-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CardLoss',
  data () {
    return {
      account: '',
      password: '',
      flag: ''
    }
  },
  mounted () {
    this.queryLostState()
  },
  methods: {
    onClickLeft () {
      this.$router.push('electricity')
    },
    // 判断校园卡挂失状态
    async queryLostState () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/queryLostFlag'
      }).then(res => {
        this.flag = res.data.flag
      }).catch(err => {
        console.log(err)
      })
    },
    // 挂失请求
    async Lost () {
      this.account = localStorage.getItem('el-account')
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/lostCard',
        params: {
          account: this.account,
          password: this.password,
          flag: this.flag
        }
      }).then(res => {
        this.queryLostState()
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.van-field {
  width: 100%;
  background-color: rgb(241, 241, 241);
  border-radius: 5px;
  border: 0;
  margin-top: 15px;
}

.cardLoss-container {
  background-color: #fff;
  height: 100vh;
}

.carfLoss-body {
  padding: 0 15px;
}

.btn-loss {
  width: 100%;
  background: linear-gradient(to right, #3da2ff, #5d76db);
  height: 47px;
  margin-top: 20px;
  border-radius: 5px;
  font-size: 17px;
  margin: 20px 0;
  border: 0;
  color: #fff;
}
</style>
