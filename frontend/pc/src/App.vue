<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
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
    removeItem () {
      var storage = window.localStorage
      storage.removeItem('loginFlag')
    },
    async loginOut () {
      await this.axios({
        method: 'get',
        url: 'https://www.mzkt.xyz/smart-school/education/logout'
      }).then(res => {
        console.log(res.data)
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
