importScripts("/precache-manifest.b1712460f12ba99c4bf36750bd0fec8f.js", "/workbox-v4.3.1/workbox-sw.js");
workbox.setConfig({modulePathPrefix: "/workbox-v4.3.1"});
// @ts-nocheck
self.__precacheManifest = [].concat(self.__precacheManifest || [])
workbox.precaching.suppressWarnings()
workbox.precaching.precacheAndRoute(self.__precacheManifest, {})

workbox.routing.registerRoute(
    /\.(?:png|gif|jpg|jpeg|svg)$/,
    workbox.strategies.staleWhileRevalidate(0),
)

workbox.routing.registerRoute(
    new RegExp('https://reqres.in'),
    workbox.strategies.networkFirst(),
)

