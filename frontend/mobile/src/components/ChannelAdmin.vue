<template>
  <div class="channelAdmin-container">
    <van-nav-bar title="频道管理" left-arrow @click-left="onClickLeft"></van-nav-bar>
    <div class="channelAdmin-body">
      <van-row
        type="flex"
        align="center"
        justify="space-between"
        class="channal-cell"
        @click="showDialog()"
      >
        <van-col>
          <h5 style="font-size: 15px">频道名称</h5>
        </van-col>
        <van-col>
          <van-row type="flex" align="center">
            <p
              style="font-size: 15px; margin-right: 10px; color: rgb(146, 146, 146)"
              v-if="this.ischange == false"
            >{{ channelNames }}</p>
            <p
              style="font-size: 15px; margin-right: 10px; color: rgb(146, 146, 146)"
              v-if="this.ischange == true"
            >{{ afterChange }}</p>
            <van-icon name="arrow" />
          </van-row>
        </van-col>
      </van-row>
      <van-row align="center" class="channal-cell" type="flex" justify="space-between">
        <h5 style="font-size: 15px">频道成员</h5>
        <p style="font-size: 15px; color: #4b9ef8">共 {{ this.Number }} 人</p>
      </van-row>
      <van-row style="background-color: #fff; padding: 0 15px">
        <van-empty description="暂无成员数据" image="search" v-if="this.channelMember.length == '0'" />
        <div class="channelUser-items" v-for="item in channelMember" :key="item.index">
          <van-icon name="manager" size="20px" color="rgb(153, 152, 152)" />
          <p style="margin-left: 10px">{{ item.id }}</p>
        </div>
      </van-row>
      <van-row style="margin-top: 25px">
        <van-button type="danger" style="width: 100%" @click="deleteChannelDialog()">解散频道</van-button>
      </van-row>
    </div>
    <!-- 修改频道名称 -->
    <van-dialog
      v-model="show"
      title="修改频道名称"
      confirm-button-color="#4b9ef8"
      confirm-button-text="确定"
      showCancelButton
      @confirm="beforeClose"
    >
      <van-row type="flex" justify="center" style="margin: 35px 0">
        <van-field
          v-model="channelName"
          clearable
          type="text"
          placeholder="请输入频道名称"
          style="width: 90%;"
        ></van-field>
      </van-row>
    </van-dialog>
  </div>
</template>

<script>
export default {
  name: 'ChannelAdmin',
  data () {
    return {
      show: false,
      channelName: '',
      channelNames: '',
      channelCode: '',
      Number: '',
      ischange: false,
      channelMember: [],
      getChannelName: '',
      afterChange: ''
    }
  },
  created () {
    this.getParams()
  },
  mounted () {
    this.getSubscribers()
    this.getSubscribersNumber()
  },
  methods: {
    onClickLeft () {
      this.$router.push('subscriptionAdmin')
    },
    getParams () {
      this.channelNames = this.$route.query.name
      this.channelCode = this.$route.query.code
    },
    beforeClose: function () {
      this.changeChannelName()
    },
    showDialog () {
      this.show = true
    },
    // 解散频道
    deleteChannelDialog () {
      this.$dialog.confirm({
        message: '确定解散该频道？'
      })
        .then(() => {
          // on confirm
          this.deleteChannel()
        })
        .catch(() => {
          // on cancel
        })
    },
    async deleteChannel () {
      const loading = this.$toast.loading({
        duration: 0,
        message: '加载中...',
      })
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/channel/deleteChannel',
        params: {
          code: this.channelCode
        }
      }).then(res => {
        if (res.data.code === '200') {
          loading.clear()
          this.$toast.success({
            message: '解散成功'
          })
          setTimeout(() => {
            this.$router.push('subscriptionAdmin')
          }, 2300)
        }
      }).catch(err => {
        console.log(err)
      })
    },
    async getSubscribers () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/subscriber/getSubscribers',
        params: {
          code: this.channelCode
        }
      }).then(res => {
        console.log(res.data)
        this.channelMember = res.data.subscribers
      }).catch(err => {
        console.log(err)
      })
    },
    async getSubscribersNumber () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/subscriber/getSubscribersNumber',
        params: {
          code: this.channelCode
        }
      }).then(res => {
        console.log(res.data)
        this.Number = res.data.num
      }).catch(err => {
        console.log(err)
      })
    },
    async changeChannelName () {
      var formData = new FormData()
      formData.append('channelName', this.channelName)
      const loading = this.$toast.loading({
        message: '加载中...',
      })
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/channel/setChannelName',
        params: {
          code: this.channelCode,
          name: formData.get('channelName')
        }
      }).then(res => {
        console.log(res.data)
        if (res.data.code === '200') {
          loading.clear()
          this.ischange = true
          this.afterChange = this.channelName
          this.$toast.success({
            message: "修改成功"
          })
        }
      }).catch(err => {
        console.log(err)
      })
    }
  },
}
</script>
 
<style lang="less" scoped>
.channal-cell {
  background-color: #fff;
  padding: 0 15px;
  margin-bottom: 6px;
}

.channelUser-items {
  display: flex;
  align-items: center;
  border-bottom: 1px solid rgb(238, 238, 238);
}

.channelUser-items:last-child {
  border-bottom: 0;
}

.van-field {
  background-color: rgb(243, 243, 243);
  border-radius: 2px;
  border: 0;
  height: 42px;
}
</style>
