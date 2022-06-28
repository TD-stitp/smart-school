<template>
  <div></div>
</template>

<script>
export default {
  name: 'Refresh',
  created () {
    const isLoginToast = this.$toast.loading({
      duration: 0,
      message: '正在加载...'
    })
    setTimeout(() => {
      isLoginToast.clear()
      this.$router.push('subscriptionAdmin')
    },1000)
  }
}
</script>

<style lang="less" scoped>
</style>
