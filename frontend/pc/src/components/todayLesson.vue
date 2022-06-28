<template>
  <div>
    <el-row style="text-align: center;">
      <el-row type="flex" align="center">
        <el-col :span="4" style="text-align: start">
          <el-tag
            effect="dark"
            class="label"
            style="text-align: center"
          >{{ this.weeks[this.nowWeek]}}</el-tag>
        </el-col>
        <el-col :span="16">
          <h3 style="margin-top: 5px">今日课表</h3>
        </el-col>
        <el-col :span="4"></el-col>
      </el-row>
      <!-- 今日课表内容 -->
      <el-empty description="暂无数据" v-if="this.nowWeek == '6' || this.nowWeek == '0'"></el-empty>
      <div class="todayLesson-item" v-for="item in todayLessonData" :key="item.id">
        <div v-for="items in item" :key="items.index">
          <div v-if="items.kcmc && items.xqj === nowWeek">
            <div class="todayLesson-container">
              <!-- 左侧内容 -->
              <div class="left-container" style="flex: 1">
                <el-row style="margin-bottom: 12px">
                  <h4 style="font-size: 17px">{{ items.kcmc }}</h4>
                </el-row>
                <el-row type="flex" align="center" style="text-align: start">
                  <el-col :span="2">
                    <i class="iconfont icon-location"></i>
                  </el-col>
                  <el-col :span="10">
                    <el-row type="flex" align="center">
                      <p
                        style="font-size: 14px; color: #8A8A8A; margin-left: -15px"
                      >{{ items.cdmc }}</p>
                      <el-divider direction="vertical" class="divider"></el-divider>
                      <p style="font-size: 14px; color: #8A8A8A;">教师: {{ items.xm }}</p>
                    </el-row>
                  </el-col>
                </el-row>
              </div>
              <!-- 右侧内容 -->
              <div class="right-container">
                <span v-if="items.jcs === '1' & items.row === '2'">
                  <p>{{ jcsList[0].time }}</p>
                </span>
                <span v-if="items.jcs === '1' & items.row === '3'">
                  <p>{{ jcsList[1].time }}</p>
                </span>
                <span v-if="items.jcs === '1' & items.row === '4'">
                  <p>{{ jcsList[2].time }}</p>
                </span>
                <span v-if="items.jcs === '1' & items.row === '5'">
                  <p>{{ jcsList[3].time }}</p>
                </span>
                <span v-if="items.jcs === '3' & items.row === '2'">
                  <p>{{ jcsList[4].time }}</p>
                </span>
                <span v-if="items.jcs === '3' & items.row === '3'">
                  <p>{{ jcsList[5].time }}</p>
                </span>
                <span v-if="items.jcs === '4' & items.row === '2'">
                  <p>{{ jcsList[6].time }}</p>
                </span>
                <span v-if="items.jcs === '6' & items.row === '2'">
                  <p>{{ jcsList[7].time }}</p>
                </span>
                <span v-if="items.jcs === '6' & items.row === '4'">
                  <p>{{ jcsList[8].time }}</p>
                </span>
                <span v-if="items.jcs === '8' & items.row === '2'">
                  <p>{{ jcsList[9].time }}</p>
                </span>
                <span v-if="items.jcs === '10' & items.row === '2'">
                  <p>{{ jcsList[10].time }}</p>
                </span>
                <span v-if="items.jcs === '10' & items.row === '3'">
                  <p>{{ jcsList[11].time }}</p>
                </span>
              </div>
            </div>
            <!-- 分割线 -->
            <el-row style="margin-top: -20px">
              <el-divider class="divider-h"></el-divider>
            </el-row>
          </div>
        </div>
      </div>
    </el-row>
  </div>
</template>

<script>
import lessonTime from '../util/lessonTime.json'
export default {
  name: 'todayLesson',
  data () {
    return {
      todayLessonData: [],
      nowWeek: '',
      jcsList: [],
      year: '',
      semester: '',
      weeks: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
      requestData: {
        lessonData: []
      }
    }
  },
  created () {
    this.getWeekDate()
    this.jcsList = lessonTime
  },
  mounted () {
    this.getLessonData()
  },
  methods: {
    getWeekDate () {
      let myDate = new Date()
      let wk = myDate.getDay()
      let weeks = ['0', '1', '2', '3', '4', '5', '6']
      let week = weeks[wk]
      this.nowWeek = week
    },
    async getLessonData () {
      this.year = localStorage.getItem('yearData')
      this.semester = localStorage.getItem('semesterData')
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/education/queryClasses',
        params: {
          year: JSON.parse(this.year),
          semester: JSON.parse(this.semester)
        }
      }).then(res => {
        this.todayLessonData = res.data.kbList
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.todayLesson-item {
  text-align: start;
  margin-top: 25px;
}

.todayLesson-container {
  display: flex;
  align-items: center;
}

.right-container p {
  color: #009ffb;
}

.divider-h {
  margin-top: 30px;
  background-color: rgb(245, 245, 245);
}
</style>
