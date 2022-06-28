<template>
  <div class="electricity-container">
    <div class="electrictity-login">
      <el-row>
        <h3 style="margin-top: -25px">电力系统登录</h3>
      </el-row>
      <el-row class="ele-form">
        <el-form :model="ruelForm" ref="ruelForm">
          <el-form-item
            prop="password"
            :rules="[
          {required: true,message:'密码不能为空'}]"
            style="width: 305px; margin-bottom: 5px"
          >
            <el-input
              style="padding-bottom: 5px"
              type="password"
              v-model="ruelForm.password"
              prefix-icon="el-icon-lock"
              placeholder="请输入密码"
              show-password
            ></el-input>
          </el-form-item>
          <el-row type="flex" align="center" justify="center">
            <el-form-item
              prop="code"
              :rules="[
             {required: true,message:'验证码不能为空'}]"
              style="width: 200px"
            >
              <el-input
                style="width: 205px; padding-bottom: 5px"
                type="code"
                v-model="ruelForm.code"
                prefix-icon="el-icon-key"
                placeholder="请输入验证码"
              ></el-input>
            </el-form-item>
            <img
              :src="this.verificationCodeImgUrl"
              alt
              srcset
              width="95px"
              height="31px"
              style="border: 1px solid #ccc"
              @click="RefreshCode()"
            />
          </el-row>
        </el-form>
        <el-row style="margin-top: -30px">
          <el-button type="primary" style="width: 95%" @click="getRsaKey('ruelForm')">登 录</el-button>
        </el-row>
      </el-row>
    </div>
  </div>
</template>

<script>
import { setMaxDigits } from '../util/bigint.js'
import { encryptedString, RSAKeyPair } from '../util/RSA.js'

export default {
  name: 'ElectricityLogin',
  data () {
    return {
      verificationCodeImgUrl: '',
      EleloginParams: {
        loginNpwd: '',
        code: '',
        key: ''
      },
      ruelForm: {
        password: '',
        code: ''
      },
      //判断是否登录的标志
      eleLoginFlag: false
    }
  },
  created () {
    this.getVerificationCode()
  },
  // 获取验证码
  methods: {
    async getVerificationCode () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/electricity/getValidateCode',
        responseType: 'arraybuffer',
        params: {
          time: '8'
        }
      }).then(res => {
        //将返回的数据转为base64
        var imgBase = window.btoa(new Uint8Array(res.data).reduce((data, byte) =>
          data + String.fromCharCode(byte), ''))
        this.verificationCodeImgUrl = 'data:image/png;base64,' + imgBase
      }).catch(err => {
        console.log(err)
      })
    },
    // 获取RsaKey加密
    async getRsaKey () {
      var password = this.ruelForm.password
      var publicKey = ''
      var npwd = ''
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/electricity/getRSAKey'
      }).then(res => {
        setMaxDigits(129)
        var rsaKey = new RSAKeyPair(res.data.Obj.split(',')[0], "", res.data.Obj.split(',')[1])
        npwd = encryptedString(rsaKey, password)
        publicKey = res.data.Msg
        this.EleloginParams.loginNpwd = npwd
        this.EleloginParams.key = publicKey
        // 调用电力系统登录
        this.ElectricityLogin()
      }).catch(err => {
        console.log(err)
      })
    },
    // 电力系统登录
    async ElectricityLogin () {
      const loading = this.$loading({
        lock: true,
        text: '登录中...',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      await this.axios({
        method: 'post',
        url: 'https://www.mzkt.xyz:443/smart-school/electricity/login',
        params: {
          code: this.ruelForm.code,
          key: this.EleloginParams.key,
          password: this.EleloginParams.loginNpwd,
          username: Math.round(Math.random() * 100)
        }
      }).then(res => {
        // 存储res.data中的account，该参数作用后续请求的参数
        localStorage.setItem('el-account', res.data.account)
        if (res.data.code === '200') {
          localStorage.setItem('eleToken', 'userLoginEle')
          setTimeout(() => {
            loading.close()
            this.$router.push('electricityHome')
            this.$message({
              message: '登录成功',
              type: 'success'
            });
          }, 1200)
        } else {
          this.$alert('密码错误或验证码错误', '提示', {
            confirmButtonText: '确定'
          })
        }
      }).catch(err => {
        console.log(err)
      })
    },
    RefreshCode () {
      this.getVerificationCode()
    }
  }
}
</script>

<style lang="less" scoped>
.electricity-container {
  height: 100%;
  display: grid;
  align-items: center;
  justify-items: center;
}

.electrictity-login {
  width: 500px;
  height: 470px;
  border-radius: 10px;
  box-shadow: 0px 0px 28px rgba(0, 0, 0, 0.18);
  background-color: #fff;
}

.ele-form {
  width: 65%;
  margin: 0 auto;
  margin-top: -40px;
}

.el-form {
  text-align: center;
}

.el-form img {
  margin-top: 31px;
  margin-left: 10px;
}

.el-input:nth-child(1) {
  width: 305px;
}

.el-button {
  height: 40px;
  font-size: 15px;
  border-radius: 5px;
  margin-top: -60px;
}

.ele-form ::v-deep .el-input__inner {
  height: 35px;
}

.ele-form ::v-deep .el-input__icon {
  line-height: 35px;
  font-size: 14px;
}
</style>
