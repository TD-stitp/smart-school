<template>
  <div class="inquire-box">
    <div class="inquire-header">
      <el-row type="flex" align="center">
        <span style="display:flex; width: 7px; height:30px; background:#409EFF;margin-top:65px"></span>
        <span style="margin-left:20px;font-size:19px; font-weight: 600">流水查询</span>
      </el-row>
      <!-- 时间查询 -->
      <div class="inquire-block">
        <el-date-picker
          v-model="valuedate"
          type="daterange"
          align="right"
          unlink-panels
          range-separator="至"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          :picker-options="pickerOptions"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
        ></el-date-picker>
        <el-button type="primary" style="margin-left:8px;" @click="flowInform('rows')">查询</el-button>
      </div>
    </div>
    <!-- 水费表格 -->
    <vue-scroll :ops="ops" style="overflow-y: auto; height: 680px">
      <div class="inquire-container">
        <el-table :v-for="rows" :data="rows" style="width: 99%" class="box-container" stripe>
          <el-table-column type="index" label="序号" align="center"></el-table-column>
          <el-table-column prop="OCCTIME" label="交易时间" align="center"></el-table-column>
          <el-table-column prop="MERCNAME" label="交易地点" align="center"></el-table-column>
          <el-table-column prop="POSCODE" label="POS" align="center"></el-table-column>
          <el-table-column prop="TRANAMT" label="交易金额" align="center"></el-table-column>
          <el-table-column prop="TRANNAME" label="交易类型" align="center"></el-table-column>
          <el-table-column prop="CARDBAL" label="账户余额" align="center"></el-table-column>
        </el-table>
      </div>
    </vue-scroll>
  </div>
</template>

<script>
export default {
  name: 'Inquire',
  data () {
    return {
      // 时间选择
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick (picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick (picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick (picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      // 滚动条
      ops: {
        bar: {
          hoverStyle: true,
          onlyShowBarScroll: false,
          background: "#8A8A8A",
          opacity: 0.3
        }
      },
      value: new Date(),
      valuedate: [],
      pickdata: {
        startTime: '',
        endTime: ''
      },
      search: {
        date: [],
        beginDate: '',
        endDate: ''
      },
      rows: [],
    }
  },
  created () {
    this.getLoginAccount()
    this.search.beginDate = this.handleTimeOld(new Date())
    this.search.endDate = this.handleTimeNew(new Date())
    this.search.date.push(this.search.beginDate)
    this.search.date.push(this.search.endDate)
    this.valuedate[0] = this.search.date[0]
    this.valuedate[1] = this.search.date[1]
  },

  mounted () {
    this.flowInform()
  },
  methods: {
    // 用户名
    getLoginAccount () {
      this.loginAccount = localStorage.getItem('el-account')
    },
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
    // 流水信息
    async flowInform () {
      this.pickdata.startTime = this.valuedate[0]
      this.pickdata.endTime = this.valuedate[1]
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/electricity/queryFlow',
        params: {
          account: this.loginAccount,
          startDate: this.pickdata.startTime,
          endDate: this.pickdata.endTime
        }
      }).then(res => {
        if (res.data.code === '200') {
          console.log('流水信息')
          console.log(res.data.rows)
          this.rows = res.data.rows
        }
      }).catch(err => {
        console.log(err)
      })
    },
    // 序号
    indexMethod (index) {
      return index + 1;
    }
  }
}
</script>

<style lang="less" scoped>
.inquire-header {
  display: flex;
  justify-content: space-between;
  margin-top: -65px;
}

.box-container {
  line-height: 20px !important;
  color: black;
  height: 100%;
}

.el-table {
  font-size: 14px;
  font-family: Arial, Helvetica, sans-serif;
}
</style>