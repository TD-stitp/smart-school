<template>
  <div class="card-box">
    <div class="card-header">
      <!-- 头部 -->
      <span style="display:flex; width: 7px; height: 30px; background: #409EFF; margin-top: 65px"></span>
      <h4 style="margin-left:20px;font-size:19px">校园卡挂失</h4>
    </div>
    <div class="card-container">
      <!-- 主体头部 -->
      <div class="card-false" v-if="this.flag === false" style="margin-top:-30px">
        <img src="../assets/images/loss-0.svg" alt style="width: 70px; heigth: 70px" />
        <p style="margin-top:-120px">
          当前状态：
          <span style="color:rgb(9, 142, 75); font-weight: 600; font-size: 17px">未挂失</span>
        </p>
      </div>
      <div class="flag-true" style="margin-top:-30px" v-if="this.flag === true">
        <img src="../assets/images/loss-1.svg" alt style="width:80px; heigth:80px" />
        <p style="margin-top:-120px">
          当前状态：
          <span style="color:rgb(255, 128, 0); font-weight: 600; font-size: 17px">已挂失</span>
        </p>
      </div>
    </div>
    <!-- 输入框 -->
    <div class="card-input">
      <el-form
        :model="ruleForm"
        status-icon
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item
          label="一卡通账号："
          prop="username"
          style="width:370px"
          :rules="[{ requires: true,message: '账号不能为空' }]"
        >
          <el-input
            type="eleusername"
            v-model="ruleForm.eleusername"
            autocomplete="off"
            placeholder="请输入一卡通账号"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="查询密码：" prop="password" style="width:370px">
          <el-input
            type="password"
            v-model="ruleForm.elepassword"
            autocomplete="off"
            placeholder="请输入查询密码"
            clearable
            :rules="[{ required: true, message:'密码不能为空' }]"
          ></el-input>
        </el-form-item>
      </el-form>
    </div>
    <!-- 解挂 -->
    <div class="findcard" style="margin-top: -20px">
      <div class="true-footer" v-if="this.flag === false">
        <el-button
          type="danger"
          style="width: 350px; height: 40px; font-size: 14px; margin-left: 25px;"
          @click="LossorFind('ruleForm')"
        >立即挂失</el-button>
      </div>
      <div class="true-footer" v-if="this.flag === true">
        <el-button
          type="success"
          style="width:350px; height: 40px; font-size: 14px; margin-left: 25px;"
          @click="LossorFind('ruleForm')"
        >立即解挂</el-button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'CardLoss',
  data () {
    return {
      ruleForm: {
        eleusername: '',
        elepassword: ''
      },
      flag: '',
    }
  },
  mounted () {
    this.judgeFlag()
    this.LossorFind()
  },
  methods: {
    // flag判断挂失or解挂
    async judgeFlag () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/queryLostFlag',
      }).then(res => {
        this.flag = res.data.flag
        console.log('flag判断值' + res.data.flag)
      }).catch(err => {
        console.log(err)
      })
    },
    // 请求挂失or解挂
    async LossorFind () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/lostCard',
        params: {
          account: this.ruleForm.eleusername,
          password: this.ruleForm.elepassword,
          flag: this.flag
        }
      }).then(res => {
        console.log(res)
        if (res.data.code === '200') {
          this.$alert('操作成功', '提示', {
            confirmButtonText: '确定',
            type: 'success'
          })
          this.judgeFlag()
        }
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style>
.card-header {
  display: flex;
  margin-top: -70px;
}

.el-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: -20px;
}
</style>