<template>
  <div></div>
</template>

<script>
export default {
  name: 'Refresh',
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
        background: 'rgba(0, 0, 0, 0.7)'
      })
      setTimeout(() => {
        loading.close();
        this.$router.go(-1)
      }, 400)
    }
  }
}
</script>

<style lang="less" scoped>

</style>
