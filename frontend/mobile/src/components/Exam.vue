<template>
    <div class="message-box">
      <i class="iconfont icon-exam"></i>
      <div class="message-container-item">
        <h4>{{ examName }}</h4>
        <p>{{ examTime }}</p>
        <p>{{ examPlace }}</p>
      </div>
    </div>
</template>

<script>
export default {
  name: 'Exam',
  props: {
    examName: {
      type: String,
      default: ''
    },
    examTime: {
      type: String,
      default: ''
    },
    examPlace: {
      type: String,
      default: ''
    }
  }
}
</script>

<style lang="less" scoped>
.message-box {
  display: flex;
  background-color: #fff;
  margin: 8px 6px;
  align-items: center;
  padding-right: 15px;
  border-radius: 8px;
}

.message-box i {
  width: 35px;
  height: 35px;
  margin-left: 12px;
}

.message-container-item {
  margin-left: 12px;
  text-align: start;
}

.message-container-item h4 {
  margin-top: 12px;
}

.message-container-item p {
  margin-top: -10px;
  font-size: 14px;
  color: rgb(110, 110, 110);
}
</style>
