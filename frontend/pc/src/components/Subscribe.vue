<template>
  <div class="subscribe-container">
    <div class="box-card" style="height: 60px; line-height: 60px">
      <el-row type="flex" align="center" justify="space-between" style="padding: 0 15px;">
        <el-col>
          <el-row type="flex">
            <span class="subscribe-title"></span>
            <h3 style="margin-left: 10px; font-size: 18px">我的订阅</h3>
          </el-row>
        </el-col>
        
      </el-row>
    </div>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Subscribe',
  data () {
    return {
      subscriptionCode: '',
      show: false
    }
  },
}
</script>

<style lang="less" scoped>
.box-card {
  border-radius: 5px;
  background-color: #fff;
}

.subscribe-item {
  line-height: 25px;
  padding: 0 15px;
}

.subscribe-title {
  display: block;
  height: 27px;
  border-left: 7px solid rgb(31, 154, 255);
  margin-top: 17px;
}

.divider-h {
  margin-top: 15px;
  background-color: rgb(236, 236, 236);
}

</style>
