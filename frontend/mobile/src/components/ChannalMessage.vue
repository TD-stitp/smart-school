<template>
  <div class="channalMessage-container">
    <van-nav-bar left-arrow @click-left="onClickLeft">
      <template #right>
        <span style="margin-right: 20px" @click="toHistoryMessage()">
          <van-icon name="clock-o" size="23px" color="rgb(107, 107, 107)" />
        </span>
        <span @click="toChannelAdmin()">
          <van-icon name="setting-o" size="23px" color="rgb(107, 107, 107)" />
        </span>
      </template>
      <template #title>
        <p>{{ channelName }}</p>
      </template>
    </van-nav-bar>
    <div class="channalMessage-body">
      <van-field
        v-model="userInputData"
        autosize
        type="textarea"
        maxlength="500"
        clearable
        rows="6"
        placeholder="说点什么吧..."
        show-word-limit
      />
      <van-row>
        <van-button round type="info" size="small" @click="addData()">发送</van-button>
      </van-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ChannalMessage',
  data () {
    return {
      channelName: '',
      channelCode: '',
      userInputData: '',
      messageList: []
    }
  },
  created () {
    this.getParams()
  },
  methods: {
    onClickLeft () {
      this.$router.push('subscriptionAdmin')
    },
    getParams () {
      this.channelName = this.$route.query.name
      this.channelCode = this.$route.query.code
    },
    toChannelAdmin () {
      this.$router.push({ path: 'channelAdmin', query: { name: this.channelName, code: this.channelCode } })
    },
    addData () {
      this.sendMessage()
    },
    getData () {
      this.messageList = localStorage.getItem('channelMessage')
    },
    toHistoryMessage () {
      this.$router.push({ path: 'adminMessage', query: { name: this.channelName, code: this.channelCode } })
    },
    async sendMessage () {
      const loading = this.$toast.loading({
        duration: 0,
        message: '发送中...',
      })
      var formData = new FormData()
      formData.append('messages', this.userInputData)
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/message/sendMessage',
        params: {
          code: this.channelCode,
          message: formData.get('messages')
        }
      }).then(res => {
        if (res.data.code === '200') {
          loading.clear()
          this.$toast.success({
            message: '发送成功'
          })
        }
      }).catch(err => {
        console.log(err)
      })
    },
  },
}
</script>

<style lang="less" scoped>
.admin-input {
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 55px;
  background-color: rgb(255, 255, 255);
  border-top: 1px solid rgb(235, 234, 234);
}

.channalMessage-container {
  height: 100vh;
  background-color: #fff;
}

table {
  width: 100%;
}

.message-item {
  float: right;
  margin-right: 10px;
  margin-top: 15px;
  margin-bottom: 5px;
  max-width: 70%;
  background-color: rgb(127, 168, 255);
  border-radius: 8px;
  color: #ffffff;
  padding-left: 8px;
  padding-right: 8px;
  padding-bottom: 5px;
  padding-top: 5px;
  max-lines: 100;
  text-align: start;
}

.van-button {
  width: 68px;
  margin-right: 20px;
  margin-top: 20px;
  float: right;
}

.message-item p {
  word-break: break-all;
}
</style>
