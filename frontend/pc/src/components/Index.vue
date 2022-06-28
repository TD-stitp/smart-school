<template>
  <div>
    <el-row :gutter="12">
      <el-col :span="8">
        <div class="box-card">
          <todayLesson></todayLesson>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="box-card">
          <div class="box-container">
            <h3>考试信息</h3>
          </div>
          <!-- 首页考试信息 -->
          <vue-scroll :ops="ops" style="height: 37vh">
            <div
              class="exam-item"
              v-for="item in examData"
              :key="item.id"
              style="margin-left: 10px; margin-right: 10px"
            >
              <el-row type="flex">
                <h4 style="font-size: 17px; margin:10px 0">{{ item.kcmc }}</h4>
              </el-row>
              <el-row type="flex" align="center" style="text-align: start; margin-top: 5px">
                <el-col :span="2">
                  <i class="iconfont icon-location"></i>
                </el-col>
                <el-col :span="6">
                  <el-row type="flex" align="center">
                    <p style="font-size: 14px; color: #8A8A8A; margin-top: 1px">{{ item.cdmc }}</p>
                    <el-divider direction="vertical" class="divider" style="margin-left: 10px"></el-divider>
                  </el-row>
                </el-col>
                <el-col :span="2">
                  <i class="iconfont icon-clock"></i>
                </el-col>
                <el-col>
                  <p style="font-size: 14px; color: #8A8A8A;">{{ item.kssj }}</p>
                </el-col>
              </el-row>
              <el-row style="margin-top: -20px">
                <el-divider class="divider-h"></el-divider>
              </el-row>
            </div>
            <!-- 空状态 -->
            <div class="exam-empty" v-if="examData.length == 0">
              <el-empty description="暂无数据" v-if="examData.length == 0"></el-empty>
            </div>
          </vue-scroll>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="box-card">
          <div class="box-container">
            <h3>我的成绩</h3>
          </div>
          <!-- 首页成绩信息 -->
          <vue-scroll :ops="ops" style="height: 37vh">
            <div
              class="scord-item"
              v-for="item in scordData"
              :key="item.id"
              style="line-height: 30px; margin-top: 15px"
            >
              <el-row type="flex">
                <el-col :span="4" v-if="item.jd < 3.0">
                  <i class="iconfont icon-fail" style="margin-left: 10px"></i>
                </el-col>
                <el-col :span="4" v-else>
                  <i class="iconfont icon-pass" style="margin-left: 10px"></i>
                </el-col>
                <el-col>
                  <p style="font-size: 15px; text-align:left; margin-left: 20px;">{{ item.kcmc }}</p>
                </el-col>
                <el-col v-if="item.jd < 3.0 || item.jd == '不及格'">
                  <p
                    style="font-size: 16px; color: rgb(218, 55, 55); text-align: left; margin-left: 110px"
                  >成绩：{{ item.cj }}</p>
                </el-col>
                <el-col v-else>
                  <p
                    style="font-size: 16px; color: rgb(62, 165, 86); text-align: left; margin-left: 110px"
                  >成绩：{{ item.cj }}</p>
                </el-col>
              </el-row>
            </div>
            <!-- 空状态 -->
            <div class="scord-empty" v-if="scordData.length === 0">
              <el-empty description="暂无数据" v-if="scordData.length === 0"></el-empty>
            </div>
          </vue-scroll>
        </div>
      </el-col>
    </el-row>
    <!-- 首页通知信息 -->
    <el-row :gutter="12" style="margin-top: 12px">
      <el-col :span="12">
        <div class="box-card">
          <div class="box-container">
            <h3>
              通知信息
              <el-badge
                class="item"
                :value="informationData.length"
                style="margin-top: 5px; margin-left: 2px"
              ></el-badge>
            </h3>
          </div>
          <vue-scroll :ops="ops" style="height: 37vh">
            <div
              class="information-item"
              v-for="(item, index) in informationData "
              :key="index"
              style="line-height: 35px; text-align: left;"
            >
              <el-row type="flex" align="center" justify="space-between">
                <el-row type="flex" align="center">
                  <i class="iconfont icon-inform" style="margin-left: 5px"></i>
                  <p style="margin-left: 10px; font-size: 15px">{{ item.xxbt | ellipsis }}</p>
                </el-row>
                <p
                  style="font-size: 14px; color: #ed8800; cursor: pointer;"
                  @click="showDialog(item.xxbt, index)"
                >查看详情</p>
              </el-row>
            </div>
          </vue-scroll>
          <!-- 空状态 -->
          <div class="information-empty" v-if="informationData.lenght === 0">
            <el-empty description="暂无数据" v-if="informationData.length === 0"></el-empty>
          </div>
        </div>
      </el-col>
      <!-- 首页日历 -->
      <el-col :span="12">
        <div class="box-card">
          <el-calendar v-model="value">
            <template slot="dateCell " slot-scope="{ data }">
              <div :class="data.isSelected ? 'is-selected' : ''">{{ data.day.split("-")[2] }}</div>
            </template>
          </el-calendar>
        </div>
      </el-col>
    </el-row>
    <el-dialog title="通知详情" :visible.sync="messageShow" class="ApplyChannelAdmin">
      <p>{{ message }}</p>
      <div slot="footer" class="dialog-footer" style="text-align: center">
        <el-button
          type="primary"
          @click="handleClose()"
          style="width: 100%; height: 40px; font-size: 14px; margin-bottom: 10px"
        >确 认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import todayLesson from "@/components/todayLesson.vue";
export default {
  name: 'Index',
  components: { todayLesson },
  data () {
    return {
      examData: [],
      informationData: [],
      scordData: [],
      messageShow: false,
      message: '',
      RequestData: {
        examData: [],
        informationData: [],
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
      this.RequestData.examData = localStorage.getItem('examData')
      this.examData = JSON.parse(this.RequestData.examData)
      this.RequestData.informationData = localStorage.getItem('informationData')
      this.informationData = JSON.parse(this.RequestData.informationData)
      this.RequestData.scordData = localStorage.getItem('scordData')
      this.scordData = JSON.parse(this.RequestData.scordData)
    }, 580)
  },
  filters: {
    // 限制通知字数显示
    ellipsis: function (value) {
      if (!value) return ''
      if (value.length > 45) {
        return value.slice(0, 45) + '...'
      }
      return value
    }
  },
  methods: {
    showDialog (msg, index) {
      this.messageShow = true
      this.message = msg
    },
    handleClose () {
      this.messageShow = false
    }
  },
}
</script>

<style lang="less" scoped>
.box-card {
  height: 40vh;
  background-color: #fff;
  border-radius: 5px;
  line-height: 15px;
  position: relative;
  padding: 15px;

  .box-container {
    position: relative;
    line-height: 30px;
  }
}

.weeks {
  display: inline-block;
  width: 55px;
  height: 25px;
  background-color: #deefff;
  border-radius: 30px;
}

.box-card /deep/ .el-calendar {
  font-size: 14px;
  .next {
    border: none;
  }
  td {
    border: none;
  }
  .el-calendar-day {
    height: 28px !important;
    text-align: center;
    border: none;
  }
  .el-calendar__header {
    justify-content: space-between;
  }
  .is-selected {
    background-color: #1d8dd8;
    color: #fff;
  }
}

.divider-h {
  margin-top: 30px;
  background-color: rgb(245, 245, 245);
}

/deep/.el-dialog__header {
  line-height: 0;
}

/deep/.el-dialog__body {
  padding: 10px 15px;
  color: #606266;
  font-size: 15px;
  word-break: break-all;
  margin-left: 5px;
}

/deep/.el-dialog__footer {
  line-height: 0;
}

/deep/.el-dialog {
  width: 630px;
  text-align: start;
  line-height: 40px;
  padding: 0 20px;
}
</style>