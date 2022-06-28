<template>
  <div class="result-contaqiner">
    <van-nav-bar title="查询成绩" left-arrow :border="false" @click-left="onClickLeft" />
    <van-dropdown-menu active-color="#1989fa">
      <van-dropdown-item v-model="value1" :options="option1" @change="selectedAcademic" />
      <van-dropdown-item v-model="value2" :options="option2" @change="selectedSemester" />
    </van-dropdown-menu>
    <div class="score-container">
      <Score
        v-for="item in scoreList"
        :key="item.id"
        :leassonName="item.kcmc"
        :leassonCode="item.kch"
        :credit="item.xf"
        :score="item.cj"
        :point="item.jd"
      ></Score>
    </div>
    <van-empty description="暂无成绩信息" v-if="this.scoreList.length == '0'"/>
  </div>
</template>

<script>
import Score from '@/components/Scord.vue'
export default {
  name: 'QueryResult',
  components: { Score },
  data () {
    return {
      year: '',
      semester: '',
      value1: 3,
      value2: 1,
      option1: [
        { text: '请选择学年', value: 0 },
        { text: '2019-2020', value: 1 },
        { text: '2020-2021', value: 2 },
        { text: '2021-2022', value: 3 },
        { text: '2023-2024', value: 4 },
        { text: '2024-2025', value: 5 }
      ],
      option2: [
        { text: '请选择学期', value: 0 },
        { text: '1', value: 1 },
        { text: '2', value: 2 }
      ],
      scoreList: []
    }
  },
  created () {
    this.year = '2021'
    this.semester = '1'
    this.getScore()
  },
  methods: {
    onClickLeft () {
      this.$router.push('home')
    },
    // 选择学年
    selectedAcademic () {
      switch (this.value1) {
        case 1:
          this.year = '2019'
          break
        case 2:
          this.year = '2020'
          break
        case 3:
          this.year = '2021'
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
      this.getScore()
    },
    async getScore () {
      var formData = new FormData()
      formData.append('year', this.year)
      formData.append('semester', this.semester)
      await this.axios({
        methods: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/education/queryScore',
        params: {
          year: this.year,
          semester: this.semester
        }
      }).then(res => {
        this.scoreList = res.data
        this.$delete(this.scoreList, 0)
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.result-contaqiner {
  width: 100%;
  height: 100vh;
  background-color: #fff;
}
</style>
