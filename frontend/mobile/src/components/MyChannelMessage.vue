<template>
  <div class="channalMessage-container">
    <van-nav-bar left-arrow @click-left="onClickLeft" :border="false">
      <template #right>
        <span @click="openShow()">
          <van-icon name="delete-o" size="23px" color="rgb(107, 107, 107)" />
        </span>
      </template>
      <template #title>
        <p>{{ channelNames }}</p>
      </template>
    </van-nav-bar>
    <div class="channalMessage-body">
      <div class="message-container">
        <table>
          <tbody>
            <tr v-for="item in messageList" :key="item.index">
              <td>
                <p style="font-size: 13px; margin-bottom: -5px; color: #ccc">{{ item.date }}</p>
                <div class="message-item">
                  <p>{{ item.message }}</p>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ChannalMessage',
  data () {
    return {
      channelNames: '',
      channelCode: '',
      messageList: [],
    }
  },
  created () {
    this.channelName = localStorage.getItem('channelName')
    this.getParams()
  },
  mounted () {
    this.getChannelMessage()
  },
  methods: {
    onClickLeft () {
      this.$router.push('subscription')
    },
    toChannelAdmin () {
      this.$router.push('channelAdmin')
    },
    openShow () {
      this.$dialog.confirm({
        message: '确定取消该频道订阅？'
      })
        .then(() => {
          // on confirm
          this.cancelSubscription()
        })
        .catch(() => {
          // on cancel
        })
    },
    beforeClose: function () {
      this.cancelSubscription()
    },
    getParams () {
      this.channelNames = this.$route.query.name
      this.channelCode = this.$route.query.code
    },
    async getChannelMessage () {
      const loading = this.$toast.loading({
        duration: 0,
        message: '正在加载...',
      })
      await this.axios({
        methods: 'get',
        url: 'https://www.mzkt.xyz/smart-school/message/getMessages',
        params: {
          code: this.channelCode
        }
      }).then(res => {
        if (res.data.code === '200') {
          loading.clear()
          this.messageList = res.data.messages
        }
      }).catch(err => {
        console.log(err)
      })
    },
    async cancelSubscription () {
      const loading = this.$toast.loading({
        duration: 0,
        message: '加载中...',
      })
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/follow/cancelFollow',
        params: {
          code: this.channelCode
        }
      }).then(res => {
        if (res.data.code === '200') {
          loading.clear()
          this.$toast.success({
            message: '取消成功'
          })
          this.$router.push('subscription')
        }
      }).catch(err => {
        console.log(err)
      })
    }
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

table {
  width: 100%;
}

.van-field {
  background-color: rgb(243, 243, 243);
  border-radius: 2px;
  border: 0;
  height: 42px;
}

.message-item {
  float: right;
  margin-right: 10px;
  margin-top: 15px;
  margin-bottom: 5px;
  max-width: 70%;
  background-color: rgb(127, 168, 255);
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  border-end-start-radius: 10px;
  color: #ffffff;
  padding-left: 8px;
  padding-right: 8px;
  max-lines: 100;
  text-align: start;
}

.message-item p {
  word-break: break-all;
  margin-top: 8px;
  margin-bottom: 8px;
  font-size: 15px;
}
</style>
