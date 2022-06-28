<template>
  <div class="revise-box">
    <div class="revise-header">
      <!-- 头部 -->
      <span style="display: flex; width: 7px; height: 30px; background: #409EFF;margin-top: 65px"></span>
      <h4 style="margin-left:20px;font-size:19px">修改查询密码</h4>
    </div>
    <!-- 修改框 -->
    <div class="revise-container">
      <el-form
        :model="ruleForm"
        status-icon
        ref="ruleForm"
        label-width="120px"
        class="demo-ruleForm"
      >
        <el-form-item label="一卡通账号：" prop="eleUsername">
          <el-input
            type="eleUsername"
            v-model="ruleForm.eleUsername"
            autocomplete="off"
            placeholder="请输入一卡通账号"
            clearable
            :rules="[{ required:true,message:'账号不能为空' }]"
          ></el-input>
        </el-form-item>
        <el-form-item label="旧查询密码：" prop="eleOldpassword">
          <el-input
            type="password"
            v-model="ruleForm.elePassword"
            autocomplete="off"
            placeholder="请输入旧查询密码"
            clearable
            :rules="[{ required:true, message:'旧查询密码不能为空' }]"
          ></el-input>
        </el-form-item>
        <el-form-item label="新查询密码：" prop="eleNewpassword">
          <el-input
            type="password"
            v-model="ruleForm.eleNewpassword"
            placeholder="请输入新查询密码"
            clearable
            :rules="[{ required:true, message: '新密码不能为空' }]"
          ></el-input>
        </el-form-item>
      </el-form>
      <el-button
        type="primary"
        style=" width:380px; margin-top:40px; height: 40px; font-size: 14px; margin-left: 25px"
        @click="getRsaKey('ruleForm')"
      >完 成</el-button>
    </div>
  </div>
</template>

<script>
import { setMaxDigits } from '../util/bigint.js'
import { encryptedString, RSAKeyPair } from '../util/RSA.js'
export default {
  name: 'Revise',
  data () {
    return {
      EleloginParams: {
        loginNpwd: '',
        changeNpwd: '',
        code: '',
        key: ''
      },
      ruleForm: {
        eleUsername: '',
        elePassword: '',
        eleNewpassword: ''
      },
    }
  },
  methods: {
    // 获取RsaKey加密
    async getRsaKey () {
      var oldPassword = this.ruleForm.elePassword
      var password = this.ruleForm.eleNewpassword
      var publicKey = ''
      var oldnpwd = ''
      var newnpwd = ''
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/electricity/getRSAKey'
      }).then(res => {
        setMaxDigits(129)
        var rsaKey = new RSAKeyPair(res.data.Obj.split(',')[0], "", res.data.Obj.split(',')[1])
        oldnpwd = encryptedString(rsaKey, oldPassword)
        newnpwd = encryptedString(rsaKey, password)
        publicKey = res.data.Msg
        this.EleloginParams.loginNpwd = oldnpwd
        this.EleloginParams.changeNpwd = newnpwd
        this.EleloginParams.key = publicKey
        console.log(this.EleloginParams.loginNpwd)
        console.log(this.EleloginParams.changeNpwd)
        console.log(this.EleloginParams.key)
        // 调用电力系统登录
        this.changeElepassword()
      }).catch(err => {
        console.log(err)
      })
    },
    //  修改密码
    async changeElepassword () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/updatePassword',
        params: {
          account: this.ruleForm.eleUsername,
          oldPassword: this.EleloginParams.loginNpwd,
          password: this.EleloginParams.changeNpwd,
          key: this.EleloginParams.key
        }
      }).then(res => {
        console.log(res.data.code)
        if (res.data.code === '200') {
          this.$message.success('修改成功')
        } else {
          this.$alert('一卡通账号或原密码错误', '提示', {
            confirmButtonText: '确定',
            type: 'waring'
          })
        }
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style>
.revise-header {
  display: flex;
  margin-top: -70px;
}

.revise-container {
  width: 100%;
  line-height: 40px;
  text-align: center;
}

.el-form-item {
  width: 400px;
  margin-top: 30px;
}
</style>