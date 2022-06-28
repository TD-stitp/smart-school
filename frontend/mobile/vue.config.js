module.exports = {
  publicPath: './',
  pwa: {
    workboxOptions: {
      // https://developers.google.com/web/tools/workbox/modules/workbox-webpack-plugin
      skipWaiting: true,
      clientsClaim: true,
      importWorkboxFrom: 'local',
      importsDirectory: 'js',
      navigateFallback: '/',
      navigateFallbackBlacklist: [/\/api\//]
    },
    iconPaths: {
      favicon32: 'logo.svg',
      favicon16: 'logo.svg',
      appleTouchIcon: 'logo.svg',
      maskIcon: 'logo.svg',
      msTileImage: 'logo.svg'
    }
  }
}
