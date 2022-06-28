<template>
  <el-container>
    <el-header>
      <Header></Header>
    </el-header>
    <el-container>
      <el-aside width="281px">
        <Nav></Nav>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src
import Nav from '@/components/Nav'
import Header from '@/components/Header.vue'
export default {
  name: 'Home',
  components: { Nav, Header }
}
</script>

<style lang="less" scoped>
.el-header {
  color: #333;
  text-align: center;
  line-height: 60px;
  border-bottom: 1px solid rgb(230, 230, 230);
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
}

.el-main {
  background-color: #f1f1f1;
  color: #333;
  text-align: center;
  line-height: 160px;
  height: calc(100vh - 60px);
}
</style>
