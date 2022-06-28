<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {

    }
  },
  mounted () {
    window.addEventListener('beforeunload', this.removeItem())
  },
  beforeDestroy () {
    this.loginOut()
  },
  destroyed () {
    window.removeEventListener('beforeunload', this.removeItem())
  },
  methods: {
    async loginOut () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/education/logout'
      }).then(res => {
        console.log(res.data)
        let flag = false
        localStorage.setItem('loginFlag', flag)
      }).catch(err => {
        console.log(err)
      })
    },
    removeItem () {
      var storage = window.localStorage
      storage.removeItem('eleToken')
      storage.setItem('loginFlag', false)
    }
  },
}
</script>

<style lang="less" scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
</style>
