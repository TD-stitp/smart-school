<template>
  <div class="result-container">
    <div class="result-header">
      <h3 style="display:flex; margin-top:-60px; font-size:18px;">我的成绩</h3>
      <el-button size="mini" style="display:flex; margin-top:-95px; float:right" @click="gotolink">
        返回
        <i class="el-icon-arrow-right el-icon--right"></i>
      </el-button>
    </div>
    <div class="scord-box">
      <!-- 成绩表格 -->
      <vue-scroll :ops="ops">
        <el-table :v-for="scordData" :data="scordData" class="box-container" stripe>
          <el-table-column type="index" label="序号" align="center"></el-table-column>
          <el-table-column prop="kch" label="课程号" align="center"></el-table-column>
          <el-table-column prop="kcmc" label="考试科目" align="center"></el-table-column>
          <el-table-column prop="tjrxm" label="教师" align="center"></el-table-column>
          <el-table-column prop="xf" label="学分" align="center"></el-table-column>
          <el-table-column prop="cj" label="成绩" align="center"></el-table-column>
          <el-table-column prop="xfjd" label="成绩绩点" align="center"></el-table-column>
          <el-table-column
            label="状态"
            width="150"
            align="center"
            v-if="scordData.cj <3.0 || scordData.cj == '不及格'"
          >
            <el-tag type="danger" effect="dark">不合格</el-tag>
          </el-table-column>
          <el-table-column label="状态" width="150" align="center" v-else>
            <el-tag type="success" effect="dark">合格</el-tag>
          </el-table-column>
        </el-table>
      </vue-scroll>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Result',
  data () {
    return {
      scordData: [],
      RequestData: {
        scordData: []
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
      this.RequestData.scordData = localStorage.getItem('scordData')
      this.scordData = JSON.parse(this.RequestData.scordData)
    }, 200)
  },
  methods: {
    gotolink () {
      this.$router.push('index')
    },
    indexMethod (index) {
      return index + 1;
    }
  }
}
</script>

<style lang="less" scoped>
.scord-box {
  height: 745px;
  margin-top: -30px;
  background-color: #ffffff;
  border-radius: 5px;
}

.box-container {
  line-height: 20px !important;
  color: black;
}

.el-table {
  width: 98%;
  font-size: 14px;
  margin: 0 15px;
}
</style>
