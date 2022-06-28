<template>
  <div class="card-view">
    <div class="top-container">
      <div class="scord-main-container">
        <span class="scord-main">
          <h4>{{ leassonName }}</h4>
        </span>
        <span class="scord-number">
          <h5
            style="margin-left: 25px; color: green"
            v-if="score >= '60' && score !== '不及格'"
          >成绩: {{ score }}</h5>
          <h5 style="margin-left: 25px; color: red" v-if="score < '60'">成绩: {{ score }}</h5>
        </span>
      </div>
    </div>
    <div class="bottom-container">
      <div class="scord-detail" style="text-align: start; flex: 1">
        <p>学分: {{ credit }}</p>
        <p>成绩绩点: {{ point }}</p>
      </div>
      <div class="scord-state">
        <i class="iconfont icon-pass" v-if="score >= '60' && score !== '不及格'"></i>
        <i class="iconfont icon-fail" v-if="score < '60'"></i>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Score',
  props: {
    leassonName: {
      type: String,
      default: ''
    },
    leassonCode: {
      type: String,
      default: ''
    },
    score: {
      type: String,
      default: ''
    },
    credit: {
      type: String,
      default: ''
    },
    point: {
      type: String,
      default: ''
    }
  }
}
</script>

<style lang="less" scoped>
.card-view {
  display: flex;
  flex-direction: column;
  border: 1px solid rgb(243, 243, 243);
  margin: 8px;
}

.top-container {
  flex: 1;
  border-bottom: 1px solid rgb(243, 243, 243);
}

.scord-main-container {
  display: flex;
  margin-top: -10px;
  margin-bottom: -10px;
}

.scord-main {
  flex: 1;
  display: flex;
  text-align: start;
  font-size: 17px;
}

.scord-number {
  flex: 0.6;
  text-align: end;
  font-size: 19px;
}

.bottom-container {
  flex: 1;
  display: flex;
  font-size: 15px;
  line-height: 17px;
  color: rgb(31, 94, 119);
  align-items: center;
  margin-bottom: -5px;
}
</style>
