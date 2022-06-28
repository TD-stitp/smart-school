<template>
  <div>
    <el-row
      style="text-align: start; margin: -60px 0"
      type="flex"
      align="center"
      justify="space-between"
    >
      <h3>我的课程表</h3>
    </el-row>
    <el-container style="margin-top: 10px">
      <el-main>
        <table>
          <thead>
            <tr>
              <th>节次</th>
              <th
                v-for="(week, index) in weeks"
                :key="index"
              >{{'周' + digital2Chinese(index+1, 'week')}}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(sectionName, section) in classTableTime" :key="section">
              <td style="background: #f5f5f5">
                <p style="font-weight: 600">{{'第' + digital2Chinese(section + 1) + '节'}}</p>
                <p style="margin-top: 6px">{{ sectionName }}</p>
              </td>
              <td v-for="(items, index) in Data[section]" :key="index" :rowspan="items.row">
                <span v-if="items.name" :style="{ background: items.bgc }">
                  <h5
                    style="font-size: 17px; padding-top: 10px; line-height: 25px"
                    :style="{ color: items.color }"
                  >{{ items.name }}</h5>
                  <p
                    style="line-height: 30px; font-size: 14px"
                    :style="{ color: items.color }"
                  >{{ items.teacher }}</p>
                  <p
                    style="line-height: 30px; font-size: 14px"
                    :style="{ color: items.color }"
                  >{{ items.location }}</p>
                </span>
              </td>
            </tr>
          </tbody>
        </table>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import todayLesson from './todayLesson.vue'
import lessonData1 from '@/data/lessonData1.json'
import lessonData2 from '@/data/lessonData2.json'
import lessonData3 from '@/data//lessonData3.json'
import lessonData4 from '@/data/lessonData4.json'
export default {
  components: { todayLesson },
  name: 'Lesson',
  data () {
    return {
      weeks: ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'],
      classTableTime: ['7:50-8:35', '8:40-9:25', '9:40-10:25', '10:30-11:15', '11:20-12:05', '14:00-14:45', '14:50-15:35', '15:45-16:30', '16:35-17:25', '18:30-19:15', '19:25-20:05', '20:10-20:55'],
      Data: [],
      year: '',
      semester: '',
      finally: {
        year: '',
        semester: ''
      }
    }
  },
  created () {
    this.year = localStorage.getItem('yearData')
    this.semester = localStorage.getItem('semesterData')
  },
  mounted () {
    this.finally.year = JSON.parse(this.year)
    this.finally.semester = JSON.parse(this.semester)
    if (this.finally.year === "2021" && this.finally.semester === "2") {
      this.Data = lessonData1
    } else if (this.finally.year === "2021" && this.finally.semester === "1") {
      this.Data = lessonData2
    } else if (this.finally.year === "2020" && this.finally.semester === "2") {
      this.Data = lessonData3
    } else if (this.finally.year === "2020" && this.finally.semester === "1") {
      this.Data = lessonData4
    }
  },
  methods: {
    digital2Chinese (num, identifier) {
      const character = ['零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二']
      return identifier === 'week' && (num === 0 || num === 7) ? '日' : character[num]
    }
  },
}
</script>

<style lang="less" scoped>
.el-main {
  
  background-color: #fff;
  border-radius: 5px;
  line-height: 30px;
  overflow-y: auto;
  padding: 7px;
}

table {
  width: 100%;
  height: 82vh;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  margin-top: 1px;
}

table thead th {
  color: #909399;
  font-size: 17px;
  background-color: #f5f5f5;
}

tbody {
  td {
    color: #5e5e5e;
    line-height: 14px;
  }
}
th,
td {
  width: 62px;
  padding: 0 2px;
  font-size: 13px;
}

td {
  width: 62px;
  height: 50px;
  padding: 2px 1px;
  font-size: 13px;
}

td span {
  height: 100%;
  display: block;
  border-radius: 5px;
  padding: 2px 7px;
}
</style>
