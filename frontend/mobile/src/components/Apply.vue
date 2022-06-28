<template>
  <div class="apply-container">
    <van-nav-bar :safe-area-inset-top="true" left-arrow @click-left="onClickLeft" title="申请频道管理者"></van-nav-bar>
    <div class="apply-body">
      <van-field
        v-model="username"
        type="text"
        placeholder="请输入姓名"
        autofocus
        required
        label="您的姓名："
        autosize
        clearable
        size="large"
        style="margin-top: 35px"
      />
      <van-field
        v-model="departmentName"
        type="text"
        placeholder="请填写部门名称 (如校队, 社团)"
        required
        label="所属部门："
        autofocus
        autosize
        clearable
        size="large"
      />
      <van-field
        class="reason"
        v-model="applyReason"
        autosize
        type="textarea"
        maxlength="100"
        clearable
        required
        label="申请理由："
        rows="4"
        placeholder="请填写申请理由..."
        show-word-limit
      />
      <van-row>
        <van-button type="info" style="width: 90%" @click="applyAdmin()">申 请</van-button>
      </van-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Apply',
  data () {
    return {
      username: '',
      departmentName: '',
      applyReason: ''
    }
  },
  methods: {
    onClickLeft () {
      this.$router.push('subscription')
    },
    async applyAdmin () {
      var formData = new FormData()
      formData.append('username', this.username)
      formData.append('departmentName', this.departmentName)
      formData.append('reason', this.applyReason)
      if (formData.get('username') !== '' && formData.get('departmentName') !== '' && formData.get('reason') !== '') {
        const isLoginToast = this.$toast.loading({
          duration: 0,
          message: '请稍后...'
        })
        await this.axios({
          method: 'get',
          url: 'https://www.mzkt.xyz/smart-school/channel/apply',
          params: {
            name: formData.get('username'),
            department: formData.get('departmentName'),
            reason: formData.get('reason')
          }
        }).then(res => {
          if (res.data.code === '200') {
            isLoginToast.clear()
            setTimeout(() => {
              this.$dialog.confirm({
                message: '申请成功，等待管理员审核',
                confirmButtonColor: '#40a3f6'
              })
                .then(() => {

                })
                .catch(() => {

                })
            }, 200)
          } else if (res.data.code === '202') {
            isLoginToast.clear()
            setTimeout(() => {
              this.$dialog.confirm({
                message: '申请失败，请联系管理员',
                confirmButtonColor: '#40a3f6'
              })
                .then(() => {

                })
                .catch(() => {

                })
            }, 200)
          }
        })
      } else {
        this.$toast.fail({
          message: '输入不能为空',
          position: top
        })
      }
    }
  }
}
</script>

<style lang="less" scoped>
.apply-container {
  height: 100vh;
  background-color: #fff;
}

.apply-body {
  margin-top: 15px;
}

.van-field {
  width: 95%;
  margin-left: 10px;
  margin-bottom: 25px;
  margin-right: 10px;
  font-size: 16px;
}

.van-steps {
  padding: 0 20px;
  margin-top: 30px;
  text-align: start;
}

.van-step {
  height: 65px;
}

.reason {
  font-size: 16px;
}
</style>
