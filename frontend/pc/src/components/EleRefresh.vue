<template>
  <div></div>
</template>

<script>
export default {
  name: 'EleRefresh',
  data () {
    return {

    }
  },
  created () {
    this.openFullScreen2()
  },
  methods: {
    openFullScreen2 () {
      const loading = this.$loading({
        lock: true,
        text: '加载中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      setTimeout(() => {
        loading.close();
        this.$router.replace({
          path: '/home/electricityHome/cardLoss' ,
          name: 'Cardloss'
        })
      }, 500)
    }
  }
}
</script>

<style lang="less" scoped>
</style>
