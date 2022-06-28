<template>
  <div class="el-changePassword">
    <van-nav-bar title="一卡通修改密码" left-arrow @click-left="onClickLeft" :border="false" />
    <div class="el-changePassword-body">
      <van-row>
        <van-field
          v-model="oldPassword"
          type="password"
          placeholder="请输入旧密码"
          autosize
          clearable
          left-icon="wap-home"
        >
          <template v-slot:left-icon>
            <i class="iconfont icon-password-o"></i>
          </template>
        </van-field>
      </van-row>
      <van-row>
        <van-field
          v-model="newPassword"
          type="password"
          placeholder="请输入新密码"
          autosize
          clearable
          left-icon="wap-home"
        >
          <template v-slot:left-icon>
            <i class="iconfont icon-password-o"></i>
          </template>
        </van-field>
      </van-row>
      <van-row style="text-align: start">
        <van-button class="btn-change" @click="getRsaKey()">保存</van-button>
      </van-row>
    </div>
  </div>
</template>

<script>
import { setMaxDigits } from '@/util/bigint.js'
import { encryptedString, RSAKeyPair } from '@/util/RSA.js'
export default {
  name: 'ElChangePassword',
  data () {
    return {
      account: '',
      oldPassword: '',
      newPassword: '',
      changePasswordParams: {
        newPasswordpwd: '',
        oldPasswordpwd: '',
        key: ''
      }
    }
  },
  methods: {
    onClickLeft () {
      this.$router.push('electricity')
    },
    async getRsaKey () {
      var oldPassword = this.oldPassword
      var newPassword = this.newPassword
      var publicKey = ''
      var newpwd = ''
      var oldpwd = ''
      await this.axios({
        methods: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/electricity/getRSAKey'
      }).then(res => {
        setMaxDigits(129)
        var rsaKey = new RSAKeyPair(res.data.Obj.split(',')[0], "", res.data.Obj.split(',')[1])
        // 新密码加密
        newpwd = encryptedString(rsaKey, newPassword)
        // 旧密码加密
        oldpwd = encryptedString(rsaKey, oldPassword)
        publicKey = res.data.Msg
        this.changePasswordParams.newPasswordpwd = newpwd
        this.changePasswordParams.oldPasswordpwd = oldpwd
        this.changePasswordParams.key = publicKey
        this.ElChangePassword()
      }).catch(err => {
        console.log(err)
      })
    },
    // 修改密码
    async ElChangePassword () {
      this.account = localStorage.getItem('el-account')
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/updatePassword',
        params: {
          account: this.account,
          password: this.changePasswordParams.newPasswordpwd,
          oldPassword: this.changePasswordParams.oldPasswordpwd,
          key: this.changePasswordParams.key
        }
      }).then(res => {
        console.log(res.data)
        if (res.data.code === '200') {
          this.$dialog.alert({
            message: '修改密码成功',
          }).then(() => {
            // on close
          });
        } else if (res.data.code === '202') {
          this.$dialog.alert({
            message: '修改失败, 请检查输入是否正确',
          }).then(() => {
            // on close
          });
        }
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.el-changePassword {
  height: 100vh;
  background-color: #fff;
}

.el-changePassword-body {
  padding: 0 15px;
}

.van-field {
  width: 100%;
  background-color: rgb(247, 247, 247);
  border-radius: 5px;
  border: 0;
  margin-top: 20px;
}

.btn-change {
  width: 100%;
  background: linear-gradient(to right, #3da2ff, #5d76db);
  height: 47px;
  margin-top: 30px;
  border-radius: 5px;
  font-size: 17px;
  border: 0;
  color: #fff;
}
</style>
