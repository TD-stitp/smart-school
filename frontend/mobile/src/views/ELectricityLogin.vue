<template>
  <div class="electricityLogin-container">
    <van-nav-bar title="电力系统" left-arrow :border="false" @click-left="onClickLeft" />
    <van-row style="margin-top: 30px" type="flex" align="center" justify="center">
      <i class="iconfont icon-bulb"></i>
      <h4 style="font-size: 20px; color: #fafafa; margin-left: 10px">您好，请登录</h4>
    </van-row>
    <van-row style="margin-top: 35px">
      <div class="filed-input">
        <van-field
          v-model="EleloginParams.inputPassword"
          type="password"
          placeholder="请输入查询密码"
          autosize
          size="large"
        />
      </div>
      <!-- 输入密码 -->
      <div class="filed-input" style="display: flex">
        <van-field
          v-model="EleloginParams.inputVerificationCode"
          type="password"
          placeholder="请输入验证码"
          autosize
          size="large"
          style="width: 61%; color: #ffffff"
        />
        <span @click="getCode()">
          <img :src="this.VerificationCodeImgUrl" alt srcset width="120px;" height="47px" />
        </span>
      </div>
    </van-row>
    <van-row>
      <van-button type="default" @click="getRsaKey()">登 录</van-button>
    </van-row>
  </div>
</template>

<script>
import { setMaxDigits } from '@/util/bigint.js'
import { encryptedString, RSAKeyPair } from '@/util/RSA.js'
export default {
  name: 'ELectricityLogin',
  data () {
    return {
      VerificationCodeImgUrl: '',
      EleloginParams: {
        username: '',
        inputPassword: '',
        inputVerificationCode: '',
        key: '',
        loginNpwd: ''
      },
    }
  },
  created () {
    this.getVerificationCode()
  },
  methods: {
    onClickLeft () {
      this.$router.push('/home')
    },
    // 获取电力系统RsaKey加密
    async getRsaKey () {
      var password = this.EleloginParams.inputPassword
      var publicKey = ''
      var npwd = ''
      await this.axios({
        methods: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/electricity/getRSAKey'
      }).then(res => {
        setMaxDigits(129)
        var rsaKey = new RSAKeyPair(res.data.Obj.split(',')[0], "", res.data.Obj.split(',')[1])
        npwd = encryptedString(rsaKey, password)
        publicKey = res.data.Msg
        this.EleloginParams.loginNpwd = npwd
        this.EleloginParams.key = publicKey
        this.ElectricityLogin()
      }).catch(err => {
        console.log(err)
      })
    },
    //获取电力系验证码
    async getVerificationCode () {
      await this.axios({
        methods: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/electricity/getValidateCode',
        responseType: 'arraybuffer',
        params: {
          time: '8'
        }
      }).then(res => {
        //将返回数据转为为base64
        var imgBase = window.btoa(new Uint8Array(res.data).reduce((data, byte) => data + String.fromCharCode(byte), ''))
        this.VerificationCodeImgUrl = 'data:image/png;base64,' + imgBase
      }).catch(err => {
        console.log(err)
      })
    },
    //刷新验证码
    RefreshCode () {
      this.getVerificationCode()
    },
    //电力系统登录
    async ElectricityLogin () {
      if (this.EleloginParams.inputVerificationCode !== '' && this.EleloginParams.loginNpwd !== '') {
        const isLoginToast = this.$toast.loading({
          duration: 0,
          message: '正在登录...'
        })
        await this.axios({
          methods: 'post',
          url: 'https://www.mzkt.xyz:443/smart-school/electricity/login',
          params: {
            code: this.EleloginParams.inputVerificationCode,
            key: this.EleloginParams.key,
            username: Math.round(Math.random() * 100),
            password: this.EleloginParams.loginNpwd
          }
        }).then(res => {
          localStorage.setItem('el-account', res.data.account)
          if (res.data.code === '200') {
            isLoginToast.clear()
            this.$toast.success({
              message: '登录成功'
            })
            setTimeout(() => {
              this.$router.push('electricity')
            }, 400)
            localStorage.setItem('eleToken', 'userLogin')
            this.electricitlLoginToken = localStorage.getItem('eleToken')
          } else if (res.data.code === '202') {
            this.$dialog.alert({
              message: '密码或验证码错误',
              theme: 'round-button',
            }).then(() => {
              // on close
              isLoginToast.clear()
              this.showDialog()
            })
          }
        }).catch(err => {
          console.log(err)
        })
      } else {
        this.$toast.fail({
          message: '输入不能为空',
          position: top
        })
      }
    },
    getCode () {
      this.getVerificationCode()
    }
  },
}
</script>

<style lang="less" scoped>
.electricityLogin-container {
  height: 100vh;
  background: #64a0f8;
}

.van-nav-bar {
  background-color: #64a0f8;
  color: #ffffff;
}

/deep/ .van-nav-bar__title {
  color: #ffffff;
}

/deep/ .van-nav-bar__left i {
  color: #ffffff;
}

.van-field {
  width: 95%;
  margin-left: 10px;
  margin-bottom: 30px;
  margin-right: 10px;
  background-color: #78b1fe;
  border-radius: 5px;
  border: 0;
}

/deep/ .van-field__control {
  color: #ffffff;
}

/deep/.van-field__control::-webkit-input-placeholder {
  color: #e7e6e6;
}

/deep/.van-cell::after {
  border-bottom: 0;
}

.van-button {
  width: 95%;
  border-radius: 20px;
  border: 0;
  color: #408cff;
  margin-top: 25px;
  font-size: 15px;
}
</style>
