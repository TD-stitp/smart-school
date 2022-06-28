<template>
  <div class="box-card">
    <el-row
      style="line-height: 50px; text-align: start; padding: 10px 15px; margin-bottom: 10px"
      type="flex"
      align="center"
      justify="space-between"
    >
      <el-row type="flex" align="center">
        <h3 style="font-size: 17px">历史消息</h3>
        <el-badge
          :value="historyList.length"
          class="item"
          style="margin-left: 5px; margin-top: 4px"
        ></el-badge>
      </el-row>
      <el-row type="flex" align="center">
        <span>
          <el-button @click="toMysubscribe()">
            返回
            <i class="el-icon-right el-icon--right"></i>
          </el-button>
        </span>
      </el-row>
    </el-row>
    <div class="message-box" style="margin-top: -25px">
      <el-row class="history-container" v-for="item in historyList" :key="item.index">
        <p style="line-height: 40px; color: rgb(168, 168, 168); font-size: 13px">{{ item.date }}</p>
        <div class="message-item">{{ item.message }}</div>
      </el-row>
      <span v-if="historyList.length == '0'" style="display:block; margin-top: 50px">
        <img src="../assets/images/icon-historyMessage.svg" alt="" srcset="" width="300px" height="300px">
        <p style="line-height: 30px; margin-top: -30px">暂无历史记录</p>
      </span>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      historyList: [],
      channelCode: ''
    }
  },
  created () {
    this.getParams()
  },
  mounted () {
    this.getChannelMessage()
  },
  methods: {
    toMysubscribe () {
      this.$router.push('/home/subscribe/mySubscribe')
    },
    getParams () {
      this.channelCode = this.$route.query.code
    },
    async getChannelMessage () {
      const loading = this.$loading({
        lock: true,
        text: '数据加载中...',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      await this.axios({
        methods: 'get',
        url: 'https://www.mzkt.xyz/smart-school/message/getMessages',
        params: {
          code: this.channelCode
        }
      }).then(res => {
        if (res.data.code === '200') {
          loading.close()
          this.historyList = res.data.messages
        }
      }).catch(err => {
        console.log(err)
      })
    }
  },
}
</script>

<style lang="less" scoped>
.box-card {
  border-radius: 5px;
  background-color: #fff;
  margin-top: 15px;
  height: 720px;
}

.history-item {
  background-color: #fff;
}

.message-item {
  width: 70%;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  line-height: 32px;
  padding: 5px 15px;
  margin: 0 auto;
  margin-bottom: 10px;
  text-align: start;
}

.message-box {
  overflow-y: auto;
}
</style>
