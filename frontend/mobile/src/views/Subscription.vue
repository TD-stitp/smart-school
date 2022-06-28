<template>
  <div class="subscription-container">
    <van-nav-bar title="我的订阅" left-arrow @click-left="onClickLeft" :border="false">
      <template #right>
        <van-popover
          v-model="showPopover"
          theme="dark"
          trigger="click"
          :actions="actions"
          placement="bottom-end"
          @select="onSelect"
        >
          <template #reference>
            <van-icon name="add-o" size="22px" color="rgb(107, 107, 107)" />
          </template>
        </van-popover>
      </template>
    </van-nav-bar>
    <div class="subscription-body">
      <van-empty description="暂无订阅数据" v-if="this.mySubscriptionList.length == '0'" />
      <div
        class="subscription-items"
        v-for="item in mySubscriptionList"
        :key="item.index"
        @click="toChannelMessage(item.name, item.code, item.index)"
      >
        <van-row type="flex" align="center" justify="space-between">
          <h4>{{ item.name }}</h4>
          <p style="font-size: 14px; color: #4b9ef8">订阅码: {{ item.code }}</p>
        </van-row>
        <van-row style="text-align: start; margin-top:-10px">
          <p style="font-size: 14px; color: rgb(148, 148, 148);">{{ item.message }}</p>
        </van-row>
      </div>
    </div>
    <van-dialog
      v-model="show"
      title="订阅频道"
      confirm-button-color="#4b9ef8"
      confirm-button-text="订阅"
      showCancelButton
      @confirm="beforeClose"
    >
      <van-row type="flex" align="center" justify="center" style="margin: 30px 10px">
        <p>订阅码:</p>
        <van-field
          v-model="subscriptionCode"
          clearable
          type="text"
          placeholder="请输入订阅码"
          style="width: 70%; margin-left: 10px"
        ></van-field>
      </van-row>
    </van-dialog>
  </div>
</template>

<script>
export default {
  name: 'Subscription',
  data () {
    return {
      show: false,
      subscriptionCode: '',
      showPopover: false,
      mySubscriptionList: [],
      channelCode: '',
      channalName: '',
      actions: [{ text: '订阅频道', icon: 'certificate' }, { text: '申请权限', icon: 'manager-o' }],
    }
  },
  created () {
    this.getChannelFollows()
  },
  methods: {
    onClickLeft () {
      this.$router.push('home')
    },
    openShow () {
      this.show = true
    },
    onSelect (actions) {
      if (actions.text === '订阅频道') {
        this.show = true
      } else if (actions.text === '申请权限') {
        this.$router.push('apply')
      }
    },
    toChannelMessage (channelName, channelCode, isSelected) {
      this.$router.push({ path: 'myChannelMessage', query: { name: channelName, code: channelCode, index: isSelected } })
    },
    beforeClose: function () {
      this.addFollow()
    },
    async addFollow () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/follow/addFollow',
        params: {
          code: this.subscriptionCode
        }
      }).then(res => {
        if (res.data.code === '200') {
          this.$toast.success({
            message: '订阅成功'
          })
          this.getChannelFollows()
        } else if (res.data.code === '404') {
          this.$dialog.alert({
            message: '订阅失败，请检查订阅码输入是否正确'
          })
            .then(() => {
              this.subscriptionCode = ''
            })
            .catch(() => {
              this.subscriptionCode = ''
            })
        }
      }).catch(err => {
        console.log(err)
      })
    },
    async getChannelFollows () {
      const loading = this.$toast.loading({
        duration: 0,
        message: '正在加载...',
      })
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/follow/getFollows',
      }).then(res => {
        if (res.data.code === '200') {
          loading.clear()
          this.mySubscriptionList = res.data.follows
        }
      }).catch(err => {
        console.log(err)
      })
    }
  },
}
</script>

<style lang="less" scoped>
.subscription-container {
  height: 100vh;
}

.subscription-body {
  padding: 10px 10px;
}

.subscription-items {
  background-color: #fff;
  border-radius: 5px;
  padding: 0 10px;
  margin-bottom: 10px;
}

.van-field {
  background-color: rgb(243, 243, 243);
  border-radius: 2px;
  border: 0;
  height: 42px;
}
</style>
