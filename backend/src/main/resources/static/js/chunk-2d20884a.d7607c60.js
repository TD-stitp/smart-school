(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d20884a"],{a4ab:function(e,t,o){"use strict";o.r(t);var n=o("7a23"),r={class:"yandex-maps-page"},a={class:"row"},i={class:"flex md12 xs12"};function s(e,t,o,s,c,l){var u=Object(n["resolveComponent"])("yandex-map"),p=Object(n["resolveComponent"])("va-card");return Object(n["openBlock"])(),Object(n["createElementBlock"])("div",r,[Object(n["createElementVNode"])("div",a,[Object(n["createElementVNode"])("div",i,[Object(n["createVNode"])(p,{class:"yandex-maps-page__widget",title:"Yandex Maps"},{default:Object(n["withCtx"])((function(){return[Object(n["createVNode"])(u,{"use-object-manager:":!0,coords:[55.2,38.8],zoom:8,style:{width:"100%",height:"65vh"},behaviors:["default"],controls:["trafficControl","zoomControl","geolocationControl","fullscreenControl","searchControl"],placemarks:c.placemarks,"map-type":"hybrid"},null,8,["coords","placemarks"])]})),_:1})])])])}function c(e){return(c="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function l(e,t){for(var o=0;o<t.length;o++){var n=t[o];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}function u(e,t,o){return t in e?Object.defineProperty(e,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):e[t]=o,e}function p(e){return function(e){if(Array.isArray(e)){for(var t=0,o=new Array(e.length);t<e.length;t++)o[t]=e[t];return o}}(e)||function(e){if(Symbol.iterator in Object(e)||"[object Arguments]"===Object.prototype.toString.call(e))return Array.from(e)}(e)||function(){throw new TypeError("Invalid attempt to spread non-iterable instance")}()}function d(e,t){var o=t.options,n=t.callbacks,r=t.map,a=t.useObjectManager,i=t.objectManagerClusterize,s={},c=[];if(e.forEach((function(e){e.clusterName?s[e.clusterName]=s[e.clusterName]?[].concat(p(s[e.clusterName]),[e]):[e]:c.push(e)})),Object.keys(s).forEach((function(e){var t=o[e]||{},c=n[e]||{},l=t.layout||"\n      <div>{{ properties.balloonContentHeader }}</div>\n      <div>{{ properties.balloonContentBody }}</div>\n      <div>{{ properties.balloonContentFooter }}</div>\n    ";t.clusterBalloonItemContentLayout=ymaps.templateLayoutFactory.createClass(l);var u=t.clusterBalloonLayout||t.clusterLayout;delete t.clusterBalloonLayout;var p=u?ymaps.templateLayoutFactory.createClass(u):t.clusterBalloonContentLayout||"cluster#balloonTwoColumns";t.clusterBalloonContentLayout=p;var d=t.clusterIconContentLayout;if(t.clusterIconContentLayout=d&&ymaps.templateLayoutFactory.createClass(d),a){var m=new ymaps.ObjectManager(Object.assign({clusterize:i},t));Object.keys(c).forEach((function(e){m.clusters.events.add(e,c[e])})),m.add(s[e]),r.geoObjects.add(m)}else{var f=new ymaps.Clusterer(t);Object.keys(c).forEach((function(e){f.events.add(e,c[e])})),t.createCluster&&(f.createCluster=t.createCluster),f.add(s[e]),r.geoObjects.add(f)}})),c.length){var l=a?new ymaps.ObjectManager({clusterize:!1}):new ymaps.GeoObjectCollection;c.forEach((function(e){return l.add(e)})),r.geoObjects.add(l)}}function m(e){return e.charAt(0).toUpperCase()+e.slice(1)}function f(e){return(e.icon.color||"blue")+(e.icon.glyph?m(e.icon.glyph):e.icon.content?"Stretchy":"")}function h(e){return e.map((function(e){return Array.isArray(e)?h(e):+e}))}function y(e,t){var o=[];return function e(t,n){if(t===n)return!0;if(t instanceof Date&&n instanceof Date)return+t==+n;if("object"!==c(t)||"object"!==c(n))return!1;if(function(e,t){for(var n=o.length;n--;)if(!(o[n][0]!==e&&o[n][0]!==t||o[n][1]!==t&&o[n][1]!==e))return!0;return!1}(t,n))return!0;o.push([t,n]);var r=Object.keys(t),a=r.length;if(Object.keys(n).length!==a)return!1;for(;a--;)if(!e(t[r[a]],n[r[a]]))return!1;return!0}(e,t)}var b=new(function(){function e(){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,e),this.events={},this.ymapReady=!1,this.scriptIsNotAttached=!0}var t,o,n;return t=e,(o=[{key:"$on",value:function(e,t){var o=this;return this.events[e]||(this.events[e]=[]),this.events[e].push(t),function(){o.events[e]=o.events[e].filter((function(e){return t!==e}))}}},{key:"$emit",value:function(e,t){var o=this.events[e];o&&o.forEach((function(e){return e(t)}))}}])&&l(t.prototype,o),n&&l(t,n),e}()),k=["fullscreenControl","geolocationControl","routeEditor","rulerControl","searchControl","trafficControl","typeSelector","zoomControl","routeButtonControl","routePanelControl"];function g(e){return 0===e.filter((function(e){return![].concat(k,["default"]).includes(e)})).length}function v(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return new Promise((function(t,o){if(window.ymaps)return t();if(document.getElementById("vue-yandex-maps"))b.$on("scriptIsLoaded",t);else{var n=document.createElement("SCRIPT"),r=e.apiKey,a=void 0===r?"":r,i=e.lang,s=void 0===i?"ru_RU":i,c=e.version,l=void 0===c?"2.1":c,u=e.coordorder,p=void 0===u?"latlong":u,d=e.debug,m=void 0!==d&&d,f=e.enterprise,h=void 0!==f&&f,y=m?"debug":"release",k="lang=".concat(s).concat(a&&"&apikey=".concat(a),"&mode=").concat(y,"&coordorder=").concat(p),g="https://".concat(h?"enterprise.":"","api-maps.yandex.ru/").concat(l,"/?").concat(k);n.setAttribute("src",g),n.setAttribute("async",""),n.setAttribute("defer",""),n.setAttribute("id","vue-yandex-maps"),document.head.appendChild(n),b.scriptIsNotAttached=!1,n.onload=function(){ymaps.ready((function(){b.ymapReady=!0,b.$emit("scriptIsLoaded"),t()}))},n.onerror=o}}))}var O=b,C=["actionend","balloonclose","balloonopen","click","contextmenu","dblclick","destroy","hintclose","hintopen","optionschange","sizechange","typechange"],j={pluginOptions:{},provide:function(){var e,t,o=this,n=[],r=[];return{useObjectManager:this.useObjectManager,addMarker:this.addMarker,deleteMarker:function(t){o.myMap.geoObjects&&(n.push(t),e&&clearTimeout(e),e=setTimeout((function(){o.deleteMarkers(n),n=[]}),0))},compareValues:function(e){var n=e.newVal,a=e.oldVal,i=e.marker;y(n,a)||(r.push(i),t&&clearTimeout(t),t=setTimeout((function(){o.setMarkers(r),r=[]}),0))}}},data:function(){return{ymapId:"yandexMap".concat(Math.round(1e5*Math.random())),myMap:{},style:this.ymapClass?"":"width: 100%; height: 100%;",isReady:!1,debounce:null,markers:[]}},props:{coords:{type:Array,required:!0},zoom:{validator:function(e){return!Number.isNaN(e)},default:18},bounds:Array,clusterOptions:{type:Object,default:function(){return{}}},clusterCallbacks:{type:Object,default:function(){return{}}},behaviors:{type:Array,default:function(){return["default"]}},controls:{type:Array,default:function(){return["default"]},validator:function(e){return g(e)}},detailedControls:{type:Object,validator:function(e){return g(Object.keys(e))}},scrollZoom:{type:Boolean,default:!0},mapType:{type:String,default:"map",validator:function(e){return["map","satellite","hybrid"].includes(e)}},placemarks:{type:Array,default:function(){return[]}},useObjectManager:{type:Boolean,default:!1},objectManagerClusterize:{type:Boolean,default:!0},ymapClass:String,initWithoutMarkers:{type:Boolean,default:!0},debug:{type:Boolean,default:!1},settings:{type:Object,default:function(){return{}}},options:{type:Object,default:function(){return{}}},showAllMarkers:Boolean,disablePan:Boolean},computed:{coordinates:function(){return this.coords.map((function(e){return+e}))}},methods:{init:function(){var e=this;window.ymaps&&ymaps.GeoObjectCollection&&(this.initWithoutMarkers||this.$slots.default||this.placemarks.length)&&(this.$emit("map-initialization-started"),this.myMap=new ymaps.Map(this.ymapId,{center:this.coordinates,zoom:+this.zoom,bounds:this.bounds,behaviors:this.behaviors,controls:this.controls,type:"yandex#".concat(this.mapType)},this.options),C.forEach((function(t){return e.myMap.events.add(t,(function(o){return e.$emit(t,o)}))})),this.myMap.events.add("boundschange",(function(t){var o=t.originalEvent,n=o.newZoom,r=o.newCenter,a=o.newBounds;e.$emit("boundschange",t),e.$emit("update:zoom",n),e.$emit("update:coords",r),e.$emit("update:bounds",a)})),this.detailedControls&&Object.keys(this.detailedControls).forEach((function(t){e.myMap.controls.remove(t),e.myMap.controls.add(t,e.detailedControls[t])})),!1===this.scrollZoom&&this.myMap.behaviors.disable("scrollZoom"),this.isReady=!0,this.$emit("map-was-initialized",this.myMap))},addMarker:function(e){var t=this;this.markers.push(e),this.debounce&&clearTimeout(this.debounce),this.debounce=setTimeout((function(){t.setMarkers(t.markers)}),0)},setMarkers:function(e){var t=this,o={options:this.clusterOptions,callbacks:this.clusterCallbacks,map:this.myMap,useObjectManager:this.useObjectManager,objectManagerClusterize:this.objectManagerClusterize};if(this.markers!==e){var n=e.map((function(e){return t.useObjectManager?e.id:e.properties.get("markerId")}));this.deleteMarkers(n),d(e,o),this.$emit("markers-was-change",n)}else d(e,o);this.markers=[],this.showAllMarkers&&this.myMap.setBounds(this.myMap.geoObjects.getBounds())},deleteMarkers:function(e){var t=this;this.myMap.geoObjects.each((function(o){var n=[];if(t.useObjectManager)o.remove(e);else{var r,a=function(t){var o=t.properties.get("markerId");e.includes(o)&&n.push(t)};if(o.each)o.each(a),r=o.getLength();else if(o.getGeoObjects){var i=o.getGeoObjects();i.forEach(a),r=i.length}0===r||r===n.length?t.myMap.geoObjects.remove(o):n.length&&n.forEach((function(e){return o.remove(e)}))}})),this.$emit("markers-was-delete",e)}},watch:{coordinates:function(e){this.disablePan?this.myMap.setCenter&&this.myMap.setCenter(e):this.myMap.panTo&&this.myMap.getZoom()&&this.myMap.panTo(e,{checkZoomRange:!0})},zoom:function(){this.myMap.setZoom(this.zoom)},bounds:function(e){this.myMap.setBounds&&this.myMap.setBounds(e)}},render:function(e){return e("section",{class:"ymap-container",ref:"mapContainer"},[e("div",{attrs:{id:this.ymapId,class:this.ymapClass,style:this.style}}),this.isReady&&e("div",{ref:"markersContainer",attrs:{class:"ymap-markers"}},[this.$slots.default])])},mounted:function(){var e=this;if(this.$attrs["map-link"]||this.$attrs.mapLink)throw new Error("Vue-yandex-maps: Attribute mapLink is not supported. Use settings.");if(this.placemarks&&this.placemarks.length)throw new Error("Vue-yandex-maps: Attribute placemarks is not supported. Use marker component.");this.mapObserver=new MutationObserver((function(){e.myMap.container&&e.myMap.container.fitToViewport()}));var t=this.$refs.mapContainer;if(this.mapObserver.observe(t,{attributes:!0,childList:!0,characterData:!0,subtree:!1}),O.scriptIsNotAttached){var o=this.debug;v(function(e){for(var t=1;t<arguments.length;t++){var o=null!=arguments[t]?arguments[t]:{},n=Object.keys(o);"function"==typeof Object.getOwnPropertySymbols&&(n=n.concat(Object.getOwnPropertySymbols(o).filter((function(e){return Object.getOwnPropertyDescriptor(o,e).enumerable})))),n.forEach((function(t){u(e,t,o[t])}))}return e}({},this.$options.pluginOptions,this.settings,{debug:o}))}O.ymapReady?ymaps.ready(this.init):O.$on("scriptIsLoaded",(function(){ymaps.ready(e.init)}))},beforeDestroy:function(){this.myMap.geoObjects&&this.myMap.geoObjects.removeAll()}},M=["placemark","polyline","rectangle","polygon","circle"],w=["balloonclose","balloonopen","click","contextmenu","dblclick","drag","dragend","dragstart","hintclose","hintopen","mouseenter","mouseleave"],L={inject:["useObjectManager","addMarker","deleteMarker","compareValues"],props:{coords:Array,hintContent:String,icon:Object,balloon:Object,markerType:{type:String,validator:function(e){return M.includes(e.toLowerCase())},default:"placemark"},markerFill:Object,markerStroke:Object,clusterName:[String,Number],circleRadius:{validator:function(e){return!Number.isNaN(e)},default:1e3},balloonTemplate:String,markerId:{type:[String,Number],required:!0},properties:Object,options:Object},data:function(){return{unwatchArr:[]}},render:function(e){return this.$slots.balloon&&e("div",{style:"display: none;"},[this.$slots.balloon])},mounted:function(){var e=this;Object.keys(this.$props).forEach((function(t){e.unwatchArr.push(e.$watch(t,(function(t,o){return e.compareValues({newVal:t,oldVal:o,marker:e.defineMarker()})})))})),this.addMarker(this.defineMarker())},methods:{defineMarker:function(){var e=this,t={};this.balloonTemplate&&(t={balloonContentLayout:ymaps.templateLayoutFactory.createClass(this.balloonTemplate)}),this.$slots.balloon&&(t={balloonContentLayout:ymaps.templateLayoutFactory.createClass(this.$slots.balloon[0].elm.outerHTML)});var o={markerId:this.markerId,markerType:this.markerType||"placemark",coords:h(this.coords),hintContent:this.hintContent,markerFill:this.markerFill,circleRadius:+this.circleRadius,clusterName:this.clusterName,markerStroke:this.markerStroke,balloon:this.balloon,properties:this.properties,options:this.options,balloonOptions:t};this.icon&&["default#image","default#imageWithContent"].includes(this.icon.layout)?(o.iconContent=this.icon.content,o.iconLayout=this.icon.layout,o.iconImageHref=this.icon.imageHref,o.iconImageSize=this.icon.imageSize,o.iconImageOffset=this.icon.imageOffset,o.iconContentOffset=this.icon.contentOffset,this.icon.contentLayout&&"string"==typeof this.icon.contentLayout&&(o.iconContentLayout=ymaps.templateLayoutFactory.createClass(this.icon.contentLayout))):o.icon=this.icon;var n=function(e,t){var o=m(e);if(!t)return o;switch(o){case"Placemark":return"Point";case"Polyline":return"LineString";default:return o}}(o.markerType,this.useObjectManager),r={hintContent:o.hintContent,iconContent:o.icon?o.icon.content:o.iconContent,markerId:o.markerId},a=o.balloon?{balloonContentHeader:o.balloon.header,balloonContentBody:o.balloon.body,balloonContentFooter:o.balloon.footer}:{},i=Object.assign(r,a,o.properties),s=o.iconLayout?{iconLayout:o.iconLayout,iconImageHref:o.iconImageHref,iconImageSize:o.iconImageSize,iconImageOffset:o.iconImageOffset,iconContentOffset:o.iconContentOffset,iconContentLayout:o.iconContentLayout}:{preset:o.icon&&"islands#".concat(f(o),"Icon")},c=o.markerStroke?{strokeColor:o.markerStroke.color||"0066ffff",strokeOpacity:parseFloat(o.markerStroke.opacity)>=0?parseFloat(o.markerStroke.opacity):1,strokeStyle:o.markerStroke.style,strokeWidth:parseFloat(o.markerStroke.width)>=0?parseFloat(o.markerStroke.width):1}:{},l=o.markerFill?{fill:o.markerFill.enabled||!0,fillColor:o.markerFill.color||"0066ff99",fillOpacity:parseFloat(o.markerFill.opacity)>=0?parseFloat(o.markerFill.opacity):1,fillImageHref:o.markerFill.imageHref||""}:{},u=Object.assign(s,c,l,o.balloonOptions,o.options);"Circle"===n&&(o.coords=[o.coords,o.circleRadius]);var p=function(e,t){var o=t?{type:"Feature",id:e.properties.markerId,geometry:{type:e.markerType,coordinates:e.coords},properties:e.properties,options:e.options}:new ymaps[e.markerType](e.coords,e.properties,e.options);return o.clusterName=e.clusterName,o}({properties:i,options:u,markerType:n,coords:o.coords,clusterName:o.clusterName},this.useObjectManager,this.$emit);return this.useObjectManager||w.forEach((function(t){return p.events.add(t,(function(o){return e.$emit(t,o)}))})),p}},beforeDestroy:function(){this.unwatchArr.forEach((function(e){return e()})),this.deleteMarker(this.markerId)}};j.install=function e(t){var o=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};e.installed||(e.installed=!0,j.pluginOptions=o,t.component("yandex-map",j),t.component("ymap-marker",L))},"undefined"!=typeof window&&window.Vue&&window.Vue.use(j);var I=j,S={name:"yandex-maps-page",components:{yandexMap:I},data:function(){return{placemarks:[{coords:[54.8,38.9],properties:{},options:{},clusterName:"1",balloonTemplate:'<div>"Your custom template"</div>',callbacks:{}}]}}};S.render=s;t["default"]=S}}]);
//# sourceMappingURL=chunk-2d20884a.d7607c60.js.map