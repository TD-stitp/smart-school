<template>
  <el-container>
    <el-aside width="30%">
      <!-- 左侧区域 -->
      <div class="left-container">
        <div class="title">
          <p style=" color: #fff;">轻量，简洁，高效，快速的</p>
          <p style=" color: #fff;">校园教学综合信息服务平台</p>
        </div>
        <img
          src="../assets/images/bgc-login.svg"
          alt
          width="70%"
          height="250px"
          style="margin-top: 15px"
        />
      </div>
    </el-aside>
    <!-- 右侧区域 -->
    <el-main>
      <div class="right-container">
        <div class="right-title" style="line-height: 20px; margin-bottom: 5px">
          <h2>Hello</h2>
          <p style="margin-top: 5px">
            欢迎使用
            <span style="color: #439AF8">校园教学综合信息服务平台</span>
          </p>
        </div>
        <el-form :model="ruleForm" status-icon ref="ruleForm" class="demo-ruleForm">
          <el-form-item
            style="margin-bottom: -1px"
            prop="username"
            :rules="[
                { required: true,message:'用户名不能为空' }
              ]"
          >
            <el-input
              type="username"
              v-model="ruleForm.username"
              prefix-icon="el-icon-user"
              placeholder="请输入学号"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item
            prop="password"
            :rules="[
                {required: true,message:'密码不能为空'}
              ]"
          >
            <el-input
              type="password"
              v-model="ruleForm.password"
              prefix-icon="el-icon-lock"
              placeholder="请输入密码"
              show-password
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              @click="submitForm('ruleForm')"
              style=" width:320px; font-size: 14px"
            >登 录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      ruleForm: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    async login () {
      const loading = this.$loading({
        lock: true,
        text: '登录中...',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/education/login',
        params: {
          username: this.ruleForm.username,
          password: this.ruleForm.password
        }
      }).then(res => {
        const code = res.data.code
        if (code === '200') {
          localStorage.setItem('saveUsername', this.ruleForm.username)
          localStorage.setItem('savePassword', this.ruleForm.password)
          localStorage.setItem('adminFlag', res.data.flag)
          //创建登录token
          localStorage.setItem('eaToken', res.data.token)
          this.isLogin()
          setTimeout(() => {
            loading.close()
            this.$router.push('home')
            this.$message({
              message: '欢迎使用',
              type: 'success'
            });
          }, 1200)

        } else if (code === '202') {
          loading.close()
          this.$alert('账号或密码错误', '提示', {
            confirmButtonText: '确定'
          })
        } else if (code === '404') {
          loading.close()
          this.$alert('请勿重复登录！', '提示', {
            confirmButtonText: '确定'
          })
        }
      })
    },
    async isLogin () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/education/toLogin'
      }).then(res => {
        console.log(res)
      }).catch(err => {
        console.log(err)
      })
    },
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.login()
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  }
}
</script>

<style lang="less" scoped>
.el-aside {
  background-color: #567bff;
  color: #fff;
  text-align: center;
  .left-container {
    height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }
  .title p {
    padding-bottom: 15px;
    font-size: 18px;
  }
}

.el-main ::v-deep .el-input {
  width: 320px;
  margin-bottom: 3px;
}

.el-main ::v-deep .el-input__inner {
  height: 40px;
  line-height: 41px;
}

.el-main ::v-deep .el-input__icon {
  line-height: 40px;
  font-size: 14px;
}

.el-main {
  display: flex;
  height: 100vh;
  align-items: center;
  justify-content: center;
  .right-container {
    text-align: start;
    vertical-align: middle;
  }
}

.el-button {
  height: 40px;
}
</style>
