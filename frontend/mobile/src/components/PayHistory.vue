<template>
  <div class="pay-history-container">
    <van-empty description="暂无查询记录" image="search" v-if="this.count == '0'" />
    <div class="pay-history-item" v-for="item in this.balance" :key="item.id">
      <van-row type="flex" align="center" style="text-align: start">
        <van-col :span="2">
          <i class="iconfont icon-jiaoyi"></i>
        </van-col>
        <van-col :span="12" style="margin-left: 8px">
          <h5 style="font-size: 16px; margin-top: 10px;">{{ item.MERCNAME }}</h5>
          <p style="margin-top: -18px; font-size: 14px">{{ item.OCCTIME }}</p>
        </van-col>
        <van-col :span="8" style="text-align: end">
          <h5 style="font-size: 17px;">{{ item.TRANAMT }}</h5>
        </van-col>
      </van-row>
    </div>
  </div>
</template>

<script>
import moment from 'moment'
export default {
  name: 'PayHistory',
  data () {
    return {
      balance: [],
      account: '',
      username: '',
      nowDate: '',
      endDate: '',
      count: '',
      search: {
        date: [],
        beginDate: '',
        endDate: ''
      },
    }
  },
  created () {
    this.username = localStorage.getItem('username')
    this.account = localStorage.getItem('el-account')
    this.search.beginDate = this.handleTimeOld(new Date())
    this.search.endDate = this.handleTimeNew(new Date())
    this.search.date.push(this.search.beginDate)
    this.search.date.push(this.search.endDate)
  },
  mounted () {
    this.getPayHistory()
  },
  methods: {
    formatNumber (number) {
      return String(number)[1] ? String(number) : `0${number}`
    },
    handleTimeOld (time, split) {
      let date = new Date(time)
      let year = date.getFullYear()
      let month = date.getMonth() + 1
      split = '-'
      return [year, month, 1].map(num => this.formatNumber(num)).join(split)
    },
    handleTimeNew (time) {
      let date = new Date(time)
      let year = date.getFullYear()
      let month = (date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : ('0' + (date.getMonth() + 1))
      let day = date.getDate() > 10 ? date.getDate() : ('0' + date.getDate())
      return `${year}-${month}-${day}`
    },
    async getPayHistory () {
      this.startDate = this.search.date[0]
      this.endDate = this.search.date[1]
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/queryFlow',
        params: {
          startDate: this.startDate,
          endDate: this.endDate,
          account: this.account
        }
      }).then(res => {
        this.balance = res.data.rows
        this.count = res.data.total
      })
    },
  }
}
</script>

<style lang="less" scoped>
.pay-history-container {
  background-color: #fff;
}
.pay-history-item {
  padding: 0 15px;
  background-color: #fff;
}

.pay-history-item p {
  color: rgb(168, 168, 168);
}
</style>
