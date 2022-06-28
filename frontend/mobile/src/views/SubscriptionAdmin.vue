<template>
  <div class="subscriptionAdmin-container">
    <van-nav-bar title="我创建的频道" left-arrow @click-left="onClickLeft" :border="false">
      <template #right>
        <span @click="openShow()">
          <van-icon name="add-o" size="22px" color="rgb(107, 107, 107)" />
        </span>
      </template>
    </van-nav-bar>
    <div class="subscriptionAdmin-body">
      <van-empty description="暂无数据" v-if="this.channelList.length == '0'" />
      <div
        class="subscription-item"
        v-for="item in this.channelList"
        :key="item.index"
        @click="toChannelMessage(item.name, item.code, item.index)"
      >
        <van-row type="flex" align="center" justify="space-between">
          <h4 style="font-size: 17px">{{ item.name }}</h4>
          <p style="font-size: 14px; color: #4b9ef8">{{ item.date }}</p>
        </van-row>
        <van-row style="text-align: start; margin-top:-10px">
          <p style="font-size: 15px; color: rgb(148, 148, 148);">订阅码：{{ item.code }}</p>
        </van-row>
      </div>
    </div>
    <van-dialog
      v-model="show"
      title="创建频道"
      confirm-button-color="#4b9ef8"
      confirm-button-text="创建"
      showCancelButton
      @confirm="beforeClose"
    >
      <van-row type="flex" align="center" justify="center" style="padding-top: 20px">
        <p>频道名称:</p>
        <van-field
          v-model="channalName"
          clearable
          type="text"
          placeholder="请输入频道名称"
          style="width: 65%; margin-left: 10px"
        ></van-field>
      </van-row>
      <van-row
        type="flex"
        align="center"
        style="text-align: end; padding-left: 25px; padding-bottom: 10px"
      >
        <van-col :span="5">
          <p>订阅码:</p>
        </van-col>
        <van-col :span="6">
          <h4 style="margin-top: 25px">{{ this.channelCode }}</h4>
        </van-col>
        <van-col :span="11" style="margin-left: 7px">
          <van-button type="primary" size="small" @click="createChannel()">生成订阅码</van-button>
        </van-col>
      </van-row>
    </van-dialog>
  </div>
</template>

<script>
export default {
  name: 'SubscriptionAdmin',
  data () {
    return {
      show: false,
      channelCode: '',
      channalName: '',
      channelList: [],
      createName: '',
      pushName: '',
      pushCode: '',
      channelListData: [],
      createdStatue: ''
    }
  },
  created () {
    this.getChannelList()
  },
  methods: {
    openShow () {
      this.show = true
    },
    onClickLeft () {
      this.$router.push('home')
    },
    toChannelMessage (channelName, channelCode, isSelected) {
      this.$router.push({ path: 'channalMessage', query: { name: channelName, code: channelCode, index: isSelected } })
    },
    beforeClose: function () {
      if (this.createdStatue === '创建成功') {
        this.$router.push('refresh')
        setTimeout(() => {
          this.$toast.success({
            message: "创建成功"
          })
        }, 2200)
      }
    },
    async createChannel () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/channel/createChannel',
        params: {
          name: this.channalName
        }
      }).then(res => {
        localStorage.setItem('channelCode', res.data.code)
        this.channelCode = res.data.code
        this.createdStatue = res.data.msg
      }).catch(err => {
        console.log(err)
      })
    },
    async getChannelList () {
      const loading = this.$toast.loading({
        duration: 0,
        message: '正在加载...',
      })
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/channel/getChannels',
      }).then(res => {
        if (res.data.code === '200') {
          loading.clear()
          this.channelList = res.data.channels
          for (let index = 0; index < this.channelList.length; index++) {
            this.pushName = this.channelList[index].name
            this.pushCode = this.channelList[index].code
            this.channelListData.push({ name: this.pushName, code: this.pushCode })
          }
        }
      }).catch(err => {
        console.log(err)
      })
    },
  }
}
</script>

<style lang="less" scoped>
.van-field {
  background-color: rgb(243, 243, 243);
  border-radius: 2px;
  border: 0;
  height: 42px;
}

.subscriptionAdmin-body {
  padding: 10px 10px;
}

.subscription-item {
  background-color: #fff;
  border-radius: 5px;
  padding: 0 10px;
  margin-bottom: 10px;
}
</style>
