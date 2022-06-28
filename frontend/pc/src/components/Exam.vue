<template>
  <div class="exam-container">
    <!-- 头部内容 -->
    <div class="exam-header">
      <h3 style="display:flex; margin-top:-60px; font-size:18px">考试信息</h3>
      <el-button size="mini" style="display:flex; margin-top:-95px; float:right" @click="gotolink">
        返回
        <i class="el-icon-arrow-right el-icon--right"></i>
      </el-button>
    </div>
    <div class="exam-box">
      <vue-scroll :ops="ops">
        <el-table :v-for="examData" :data="examData" class="box-container" stripe>
          <el-table-column type="index" label="序号" width="100" align="center"></el-table-column>
          <el-table-column prop="kch" label="课程号" align="center"></el-table-column>
          <el-table-column prop="kcmc" label="考试科目" width="300" align="center"></el-table-column>
          <el-table-column prop="kssj" label="考试时间" align="center"></el-table-column>
          <el-table-column prop="cdmc" label="考试地点" align="center"></el-table-column>
          <el-table-column prop="ksmc" label="考试名称" align="center"></el-table-column>
          <el-table-column prop="xf" label="学分" align="center"></el-table-column>
        </el-table>
      </vue-scroll>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Exam',
  data () {
    return {
      examData: [],
      RequestData: {
        examData: []
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
      value: new Date()
    }
  },
  mounted () {
    setTimeout(() => {
      this.RequestData.examData = localStorage.getItem('examData')
      this.examData = JSON.parse(this.RequestData.examData)
    }, 200)
  },
  methods: {
    // 返回
    gotolink () {
      this.$router.push('index')
    },
    // 序号
    indexMethod (index) {
      return index + 1;
    }
  }
}
</script>

<style lang="less" scoped>
.exam-box {
  height: 745px;
  margin-top: -30px;
  background-color: #ffffff;
  border-radius: 5px;
}

.box-container {
  line-height: 25px !important;
  color: black;
}

.el-table {
  width: 98%;
  font-size: 14px;
  margin: 0 15px;
}
</style>
