<template>
  <div class="today-leasson-container">
    <div class="card-view">
      <van-row type="flex" justify="space-between" class="lesson-title" align="center">
        <span>今日课表</span>
        <van-tag
          type="primary"
          size="large"
          style="text-align: end"
          color="#4b9ef8"
        >周{{ this.weeks[this.nowWeek] }}</van-tag>
      </van-row>
      <van-row></van-row>
      <div class="none-lesson" v-if="this.nowWeek == '6' || this.nowWeek == '0'" >
        <img src="../assets/images/icon-none-lesson.svg" alt="" srcset="" width="155px" height="155px">
        <p style="font-size: 19px; color: #808080; font-weight: 600; margin-top: -10px">今天没课啦~</p>
        <p style="font-size: 14px; color: #b8b8b8">记得要好好休息呀</p>
      </div>
      <div v-for="item in classTableData.courses" :key="item.id">
        <div v-for="items in item" :key="items.index">
          <div class="lesson-items-container" v-if="items.kcmc && items.xqj === nowWeek">
            <div class="lesson-item">
              <div class="lesson-message">
                <h3>{{ items.kcmc }}</h3>
                <div class="lesson-detail">
                  <!-- 课程节数 -->
                  <span v-if="items.jcs === '1' & items.row === '2'">
                    <p>第{{ jcsList[0].jcs }}节</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '3'">
                    <p>第{{ jcsList[1].jcs }}节</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '4'">
                    <p>第{{ jcsList[2].jcs }}节</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '5'">
                    <p>第{{ jcsList[3].jcs }}节</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '2'">
                    <p>第{{ jcsList[4].jcs }}节</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '3'">
                    <p>第{{ jcsList[5].jcs }}节</p>
                  </span>
                  <span v-if="items.jcs === '4' & items.row === '2'">
                    <p>第{{ jcsList[6].jcs }}节</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '2'">
                    <p>第{{ jcsList[7].jcs }}节</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '4'">
                    <p>第{{ jcsList[8].jcs }}节</p>
                  </span>
                  <span v-if="items.jcs === '8' & items.row === '2'">
                    <p>第{{ jcsList[9].jcs }}节</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '2'">
                    <p>第{{ jcsList[10].jcs }}节</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '3'">
                    <p>第{{ jcsList[11].jcs }}节</p>
                  </span>
                  <p>@{{ items.cdmc }}</p>
                  <p>{{ items.xm }}</p>
                </div>
              </div>
              <!-- 课程时间 -->
              <div class="lesson-time">
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
            <van-divider></van-divider>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import lessonTime from '@/data/lessonTime.json'
export default {
  name: 'TodayLeasson',
  data () {
    return {
      year: '',
      semester: '',
      timer: null,
      nowWeek: [],
      nowDate: '',
      classTableData: {
        courses: []
      },
      jcsList: [],
      // 将数字转换成汉字
      weeks: ['日', '一', '二', '三', '四', '五', '六']
    }
  },
  created () {
    // 访问本地课程数据
    this.setNowTimes()
    this.jcsList = lessonTime
    this.year = localStorage.getItem('year')
    this.semester = localStorage.getItem('semester')
    this.classTableData.courses = JSON.parse(localStorage.getItem('coursesData'))
  },
  mounted () {
    this.getCourseData()
  },
  methods: {
    // 请课表数据
    async getCourseData () {
      await this.axios({
        methods: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/education/queryClasses',
        params: {
          year: '2021',
          semester: '2'
        }
      }).then(res => {
        // 获取课程数据
        this.classTableData.courses = res.data.kbList
      }).catch(err => {
        console.log(err)
      })
    },
    // 获取当前日期是星期数
    setNowTimes () {
      var myDate = new Date()
      // console.log(myDate)
      var wk = myDate.getDay()
      var yy = String(myDate.getFullYear())
      var mm = myDate.getMonth() + 1
      var dd = String(myDate.getDate() < 10 ? '0' + myDate.getDate() : myDate.getDate())
      var weeks = ['0', '1', '2', '3', '4', '5', '6']
      var week = weeks[wk]
      this.nowDate = yy + '-' + mm + '-' + dd
      this.nowWeek = week
    }
  }
}
</script>

<style lang="less" scoped>
.card-view {
  padding-bottom: 15px;
}

.tag {
  display: inline-block;
  width: 200px;
  height: 30px;
  background-color: #4b9ef8;
}

.lesson-title {
  padding: 8px;
  margin-bottom: 5px;
}

.lesson-title span:nth-child(1) {
  text-align: start;
  font-size: 15px;
  font-weight: 600;
  font-family: monospace;
  border-left: 4px solid #4b9ef8;
  padding-left: 6px;
}

.lesson-item {
  display: flex;
  width: 100%;
  align-items: center;
}

.lesson-message {
  flex: 0.7;
  text-align: start;
  padding-left: 13px;
}

.lesson-message h3 {
  font-size: 15px;
  margin-top: 2px;
}

.lesson-message p {
  margin-right: 12px;
  color: rgb(110, 110, 110);
  font-size: 14px;
}

.lesson-detail {
  display: flex;
  align-items: center;
  margin-top: -22px;
  margin-bottom: -13px;
}

.lesson-time {
  flex: 0.3;
  text-align: end;
  color: #4b9ef8;
  font-size: 16px;
  padding-right: 5px;
}

.van-divider {
  margin-left: 12px;
  margin-right: 5px;
}

.none-lesson {
  align-items: center;
  justify-content: center;
  border-radius: 5px;
}

</style>
