<template>
  <div class="test-message-container">
    <van-nav-bar title="考试信息" left-arrow :border="false" @click-left="onClickLeft" />
    <van-dropdown-menu active-color="#1989fa">
      <van-dropdown-item v-model="value1" :options="option1" @change="selectedAcademic" />
      <van-dropdown-item v-model="value2" :options="option2" @change="selectedSemester" />
    </van-dropdown-menu>
    <Exam
      v-for="item in examList"
      :key="item.id"
      :examName="item.kcmc"
      :examTime="item.kssj"
      :examPlace="item.cdmc"
    ></Exam>
     <van-empty description="暂无考试信息" v-if="this.examList.length == '0'"/>
  </div>
</template>

<script>
import Exam from '../components/Exam.vue'
export default {
  name: 'TestMessage',
  components: { Exam },
  data () {
    return {
      examList: [],
      value1: 2,
      value2: 1,
      year: '',
      semester: '',
      option1: [
        { text: '请选择学年', value: 0 },
        { text: '2020-2021', value: 1 },
        { text: '2021-2022', value: 2 },
        { text: '2022-2023', value: 3 },
        { text: '2023-2024', value: 4 }
      ],
      option2: [
        { text: '请选择学期', value: 0 },
        { text: '1', value: 1 },
        { text: '2', value: 2 }
      ]
    }
  },
  created () {
    this.year = '2021'
    this.semester = '1'
  },
  mounted () {
    this.geExamData()
  },
  methods: {
    onClickLeft () {
      this.$router.push('home')
    },
    // 选择学年
    selectedAcademic () {
      switch (this.value1) {
        case 1:
          this.year = '2020'
          break
        case 2:
          this.year = '2021'
          break
        case 3:
          this.year = '2022'
          break
        case 4:
          this.year = '2023'
          break
      }
    },
    // 选择学期
    selectedSemester () {
      switch (this.value2) {
        case 1:
          this.semester = '1'
          break
        case 2:
          this.semester = '2'
      }
      this.geExamData()
    },
    async geExamData () {
      await this.axios({
        methods: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/education/queryExamInfo',
        params: {
          year: this.year,
          semester: this.semester
        }
      }).then(res => {
        this.$delete(res.data, 0)
        this.examList = res.data
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.test-message-container {
  width: 100%;
  height: 100vh;
  background-color: #f8f8f8;
}
</style>
