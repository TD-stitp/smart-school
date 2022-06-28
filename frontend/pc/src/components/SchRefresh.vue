<template>
  <div></div>
</template>

<script>
export default {
  name: 'SchRefresh',
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
        text: '请稍后...',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      setTimeout(() => {
        loading.close();
        this.$router.replace({
          path: '/home/electricityHome/schoolInvest',
          name: 'SchoolInvest'
        })
      }, 1000)
    }
  }
}
</script>

<style lang="less" scoped>
</style>
