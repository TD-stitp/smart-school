// var cacheName = 'js13kPWA-v1';
// var appShellFiles = [
//   '/src',
//   '/public/index.html',
//   '/src/main.js',
//   '/src/assets/css/common.css',
//   '/src/assets/images/icon/iconfont.css',
//   '/src/assets/images/icon/iconfont.ttf',
//   '/src/assets/images/icon/iconfont.woff',
//   '/src/assets/images/icon/iconfont.woff2',
//   '/src/assets/images/logins.svg',
//   '/src/assets/images/user.svg',
//   '/public/favicon.ico',
//   '/public/img/icons/logo-192x192.png',
//   '/public/img/icons/icon-logo-512x512.png',
//   '/src/assets/images/icon-card.svg',
//   '/src/assets/images/icon-ele-charge.svg',
//   '/src/assets/images/icon-electric.svg',
//   '/src/assets/images/icon-lesson.svg',
//   '/src/assets/images/icon-loss.svg',
//   '/src/assets/images/icon-noLoss.svg',
//   '/src/assets/images/icon-none-lesson.svg',
//   '/src/assets/images/icon-password.svg',
//   '/src/assets/images/icon-scord.svg',
//   '/src/assets/images/icon-test.svg',
//   '/src/assets/images/isLoss.svg',
//   '/src/assets/images/lesson.svg',
//   '/src/assets/images/teacher.svg',
//   '/src/assets/images/banner01.jpg',
//   '/src/assets/images/banner02.jpg',
//   '/src/assets/images/banner03.jpg'
// ]

self.addEventListener('install', function (event) {
  event.waitUntil(
    // 缓存指定文件
    caches.open('v1').then(function (cache) {
      return cache.addAll([
        '/src',
        '/public/index.html',
        '/src/main.js',
        '/src/assets/css/common.css',
        '/src/assets/images/icon/iconfont.css',
        '/src/assets/images/icon/iconfont.ttf',
        '/src/assets/images/icon/iconfont.woff',
        '/src/assets/images/icon/iconfont.woff2',
        '/src/assets/images/logins.svg',
        '/src/assets/images/user.svg',
        '/public/favicon.ico',
        '/public/img/icons/logo-192x192.png',
        '/public/img/icons/icon-logo-512x512.png',
        '/src/assets/images/icon-card.svg',
        '/src/assets/images/icon-ele-charge.svg',
        '/src/assets/images/icon-electric.svg',
        '/src/assets/images/icon-lesson.svg',
        '/src/assets/images/icon-loss.svg',
        '/src/assets/images/icon-noLoss.svg',
        '/src/assets/images/icon-none-lesson.svg',
        '/src/assets/images/icon-password.svg',
        '/src/assets/images/icon-scord.svg',
        '/src/assets/images/icon-test.svg',
        '/src/assets/images/isLoss.svg',
        '/src/assets/images/lesson.svg',
        '/src/assets/images/teacher.svg',
        '/src/assets/images/banner01.jpg',
        '/src/assets/images/banner02.jpg',
        '/src/assets/images/banner03.jpg',
        '/src/assets/images/logo-white.svg',
        '/src/assets/images/logo.svg'
      ])
    })
  )
})

self.addEventListener('fetch', function (e) {
  console.log('[Service Worker] Fetched resource ' + e.request.url);
})
