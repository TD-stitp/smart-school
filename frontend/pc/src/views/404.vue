<template>
  <div class="notFound-container">
    <img src="../assets/images/not-found.svg" alt srcset width="78%" height="550px" />
    <el-row type="flex" align="center" justify="center" style="margin-top: 15px">
      <h5>很抱歉，您访问的页面不存在......</h5>
      <el-button type="primary" @click="toHome()">返回首页</el-button>
    </el-row>
  </div>
</template>

<script>
export default {
  methods: {
    toHome () {
      this.$router.push('home')
    }
  },
}
</script>

<style lang="less" scoped>
.notFound-container {
  height: 100vh;
}

img {
  margin-top: 20px;
}

h5 {
  font-size: 25px;
  color: #5b5f63;
  font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB',
    'Microsoft YaHei', '微软雅黑', Arial, sans-serif;
}

.el-button {
  margin-left: 25px;
  height: 35px;
  font-size: 13px;
  font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB',
    'Microsoft YaHei', '微软雅黑', Arial, sans-serif;
}
</style>