<template>
  <div class="home-message-container">
    <div class="home-message-items" v-for="(item, index) in messageData" :key="index">
      <van-row type="flex" align="center">
        <span>
          <i class="iconfont icon-inform"></i>
        </span>
        <p>{{ item.xxbt | ellipsis }}</p>
      </van-row>
      <span>
        <p style="font-size: 12px; color: #ed8800" @click="showDialog(item.xxbt, index)">查看详情</p>
      </span>
    </div>
    <van-row style="padding: 0 15px" v-if="this.messageData.length == '0'">
      <van-divider>暂无通知数据</van-divider>
    </van-row>
    <van-dialog
      v-model="show"
      title="通知详情"
      confirm-button-color="#4b9ef8"
      confirm-button-text="确定"
      style="padding: 0 12px; text-align: start; line-height: 25px"
    >
      <p style="font-size: 15px">{{ dialogmessage }}</p>
    </van-dialog>
  </div>
</template>

<script>
export default {
  name: 'HomeMessage',
  data () {
    return {
      messageData: [],
      dialogmessage: '',
      id: '',
      show: false
    }
  },
  created () {
    this.getMessage()
  },
  filters: {
    // 限制通知字数显示
    ellipsis: function (value) {
      if (!value) return ''
      if (value.length > 18) {
        return value.slice(0, 18) + '...'
      }
      return value
    }
  },
  methods: {
    showDialog (message, index) {
      this.show = true
      this.dialogmessage = message
    },
    // 获取通知数据
    async getMessage () {
      await this.axios({
        methods: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/education/queryMessage',
        params: {
          flag: '0'
        }
      }).then(res => {
        this.$delete(res.data, 0)
        this.messageData = res.data
      }).catch(err => {
        console.log(err)
      })
    },
  }
}
</script>

<style lang="less" scoped>
.home-message-items {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: -5px;
  padding-left: 5px;
}

.home-message-items img {
  width: 20px;
  height: 20px;
  margin-top: 4px;
}

.home-message-items p {
  margin-left: 10px;
  font-size: 14px;
}
</style>
