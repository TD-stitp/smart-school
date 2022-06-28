<template>
  <div class="channelDetail-container">
    <el-container>
      <el-aside width="300px">
        <el-row type="flex" align="center" justify="space-between">
          <h3 style="margin-top: -45px; margin-left: 15px">频道成员</h3>
          <el-tag style="margin-top: 22px; margin-right: 15px;">{{ this.memberCount }} 人</el-tag>
        </el-row>
        <div class="member-container">
          <div class="member-item" v-for="item in member" :key="item.index">
            <i class="iconfont icon-member" style="font-size: 23px"></i>
            <p style="margin-left: 10px">{{ item.id }}</p>
          </div>
        </div>
        <el-empty description="暂无成员信息" v-if="this.member.length == '0' "></el-empty>
      </el-aside>
      <el-main>
        <el-row type="flex" justify="space-between">
          <h3 style="margin-top: -65px;">频道消息</h3>
          <el-row type="flex">
            <el-button
              icon="el-icon-chat-line-round"
              type="success"
              style="margin-right: 10px"
              @click="openShow()"
            >发消息</el-button>
            <el-button @click="toChannelAdmin()">
              返回
              <i class="el-icon-right el-icon--right"></i>
            </el-button>
          </el-row>
        </el-row>
        <div class="message-box" style="margin-top: -45px">
          <div
            class="message-container"
            style="text-align: center"
            v-for="item in message"
            :key="item.index"
          >
            <p style="line-height: 40px; color: rgb(168, 168, 168); font-size: 13px">{{ item.date }}</p>
            <div class="message-item">{{ item.message }}</div>
          </div>
          <el-empty description="暂无频道信息" v-if="this.message.length == '0' "></el-empty>
          <!-- 发送消息对话框 -->
          <el-dialog title="请输入消息内容" :visible.sync="show">
            <div slot="footer" class="dialog-footer">
              <el-input
                type="textarea"
                placeholder="说点什么吧..."
                v-model="userInputData"
                show-word-limit
                :autosize="{ minRows: 4, maxRows: 15}"
                style="margin-bottom: 30px"
              ></el-input>
              <el-button
                type="primary"
                @click="sendMessage()"
                style="width: 100%; height: 40px; font-size: 14px"
              >发 送</el-button>
            </div>
          </el-dialog>
        </div>
        <!-- 发送消息对话框 -->
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'ChannelDetail',
  data () {
    return {
      member: [],
      memberCount: '',
      message: [],
      channelCode: '',
      dialogTableVisible: false,
      dialogFormVisible: false,
      show: false,
      userInputData: ''
    }
  },
  created () {
    this.channelCode = this.$route.query.code
    console.log(this.channelCode);
    this.getChannelMember()
    this.getChannalMessage()
  },
  methods: {
    toChannelAdmin () {
      this.$router.push('/home/subscribe/channelAdmin')
    },
    openShow () {
      this.show = true
    },
    async getChannelMember () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/subscriber/getSubscribers',
        params: {
          code: this.channelCode
        }
      }).then(res => {
        this.memberCount = res.data.total
        this.member = res.data.subscribers
      }).catch(err => {
        console.log(err)
      })
    },
    async getChannalMessage () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/message/getMessages',
        params: {
          code: this.channelCode
        }
      }).then(res => {
        this.message = res.data.messages
      }).catch(err => {
        console.log(err)
      })
    },
    async sendMessage () {
      const loading = this.$loading({
        lock: true,
        text: '请稍后...',
      })
      this.show = false
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/message/sendMessage',
        params: {
          code: this.channelCode,
          message: this.userInputData
        }
      }).then(res => {
        loading.close()
        if (res.data.code === '200') {
          this.$message({
            message: '发送成功',
            type: 'success'
          })
          this.getChannalMessage()
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
  height: 78vh;
  overflow-y: auto;
}

.el-aside {
  background-color: #fff;
  height: 700px;
  margin-top: 15px;
  border-radius: 5px;
  margin-right: 15px;
  text-align: start;
}

.el-main {
  background-color: #fff;
  height: 700px;
  margin-top: 15px;
  border-radius: 5px;
  text-align: start;
}

.member-container {
  margin-top: -50px;
}

.member-item {
  display: flex;
  padding: 0 15px;
  border-bottom: 1px solid rgb(235, 235, 235);
  line-height: 55px;
}

.el-tag {
  width: 60px;
  text-align: center;
}

.message-item {
  box-shadow: 0px 0px 22px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  line-height: 45px;
  padding: 0 15px;
  margin-bottom: 5px;
  text-align: start;
}

.el-button {
  height: 30px;
}

/deep/.el-dialog__header {
  line-height: 0;
}

/deep/.el-dialog__body {
  padding: 5px 0px;
  color: #606266;
  font-size: 14px;
  word-break: break-all;
  margin-left: -30px;
}

/deep/.el-dialog__footer {
  line-height: 0;
}

/deep/.el-dialog {
  width: 590px;
}
</style>
