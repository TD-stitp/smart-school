<template>
  <div class="change-password-container">
    <van-nav-bar title="修改密码" left-arrow :border="false" @click-left="onClickLeft"/>
    <div class="change-password-box">
      <van-field v-model="oldPassword" type="password" label="原始密码:" placeholder="请输入原始密码" />
      <van-field v-model="newPassword" type="password" label="新密码:" placeholder="请输入新密码" />
      <van-field v-model="newAgainPassword" type="password" label="确认密码:" placeholder="请再次输入新密码" />
      <van-button
        type="info"
        class="btn-change"
        color="linear-gradient(to right, #3DA2FF, #5D76DB)"
        @click="changePassword()"
      >完成</van-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ChangePassword',
  data () {
    return {
      oldPassword: '',
      newPassword: '',
      newAgainPassword: ''
    }
  },
  methods: {
     onClickLeft () {
      this.$router.push('home')
    },
    async changePassword () {
      var formData = new FormData()
      formData.append('oldPassword', this.oldPassword)
      formData.append('newPassword', this.newPassword)
      formData.append('newAgainPassword', this.newAgainPassword)
      await this.axios({
        methods: 'post',
        url: 'https://www.mzkt.xyz:443/smart-school/education/updatePassword',
        params: {
          oldPassword: formData.get('oldPassword'),
          newPassword: formData.get('newPassword'),
          newAgain: formData.get('newAgainPassword')
        }
      }).then(res => {
        this.$router.push('home')
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.change-password-container {
  width: 100%;
  height: 100vh;
  background-color: #fff;
}

.change-password-box {
  margin-top: 30px;
  padding: 0 5px;
}

.van-field {
  margin-top: 15px;
}

.change-password-box .btn-change {
  width: 95%;
  margin-top: 50px;
}

.van-button {
  font-size: 16px;
  border-radius: 5px;
}
</style>
