(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2eb131d1"],{2064:function(e,t,n){"use strict";n("b0c0"),n("a4d3"),n("e01a");var c=n("7a23"),o={class:"color-presentation"},a={key:0,class:"color-presentation__description"},r={class:"color-presentation__name"},i={class:"color-presentation__text"},l=["value"];function s(e,t,n,s,d,u){var p=Object(c["resolveComponent"])("va-popover");return Object(c["openBlock"])(),Object(c["createElementBlock"])("div",o,[Object(c["createVNode"])(p,{color:"info",placement:d.popoverOptions.placement,message:d.popoverOptions.content},{default:Object(c["withCtx"])((function(){return[Object(c["createElementVNode"])("div",{class:"color-presentation__color",style:Object(c["normalizeStyle"])(u.computedStyle),onClick:t[0]||(t[0]=function(e){return u.colorCopy()})},null,4)]})),_:1},8,["placement","message"]),n.name||n.description?(Object(c["openBlock"])(),Object(c["createElementBlock"])("div",a,[Object(c["createElementVNode"])("div",r,Object(c["toDisplayString"])(n.name),1),Object(c["createElementVNode"])("div",i,Object(c["toDisplayString"])(n.description),1)])):Object(c["createCommentVNode"])("",!0),Object(c["createElementVNode"])("input",{value:u.computedBackground,ref:"hiddenInput",class:"hidden-input"},null,8,l)])}n("a9e3"),n("caad"),n("2532");var d=n("88d5"),u={name:"color-presentation",props:{color:{type:String,default:""},variant:{type:Array,default:function(){return[]}},width:{type:Number},name:{type:String,default:""},description:{type:String,default:""}},data:function(){return{popoverOptions:{content:"Click to copy the color to clipboard",placement:"right"}}},computed:{computedBackground:function(){var e=Object(d["j"])(),t=e.getColor,n=e.getGradientBackground,c=t(this.color);return this.variant.includes("gradient")?n(c):c},computedStyle:function(){var e=this,t=function(){return e.variant.includes("hovered")?"brightness(115%)":e.variant.includes("pressed")?"brightness(85%)":void 0};return{background:this.computedBackground,filter:t(),width:this.width?"".concat(this.width,"px"):""}}},methods:{colorCopy:function(){this.$refs.hiddenInput.select(),document.execCommand("copy"),this.notify()},notify:function(){this.$vaToast.init({message:"The color's copied to your clipboard",position:"bottom-right"})}}};n("e1b0");u.render=s;t["a"]=u},2532:function(e,t,n){"use strict";var c=n("23e7"),o=n("5a34"),a=n("1d80"),r=n("577e"),i=n("ab13");c({target:"String",proto:!0,forced:!i("includes")},{includes:function(e){return!!~r(a(this)).indexOf(r(o(e)),arguments.length>1?arguments[1]:void 0)}})},"37e85":function(e,t,n){},"44e7":function(e,t,n){var c=n("861d"),o=n("c6b6"),a=n("b622"),r=a("match");e.exports=function(e){var t;return c(e)&&(void 0!==(t=e[r])?!!t:"RegExp"==o(e))}},"498a":function(e,t,n){"use strict";var c=n("23e7"),o=n("58a8").trim,a=n("c8d2");c({target:"String",proto:!0,forced:a("trim")},{trim:function(){return o(this)}})},"4fa4":function(e,t,n){"use strict";n.r(t);var c=n("7a23"),o={class:"spacing flex xs12 md12"};function a(e,t,n,a,r,i){var l=Object(c["resolveComponent"])("va-card-title"),s=Object(c["resolveComponent"])("spacing-playgroud"),d=Object(c["resolveComponent"])("va-card-content"),u=Object(c["resolveComponent"])("va-card");return Object(c["openBlock"])(),Object(c["createElementBlock"])("div",o,[Object(c["createVNode"])(u,null,{default:Object(c["withCtx"])((function(){return[Object(c["createVNode"])(l,null,{default:Object(c["withCtx"])((function(){return[Object(c["createTextVNode"])(Object(c["toDisplayString"])(e.$t("spacing.title")),1)]})),_:1}),Object(c["createVNode"])(d,null,{default:Object(c["withCtx"])((function(){return[Object(c["createVNode"])(s,{title:""})]})),_:1})]})),_:1})])}n("498a");var r={class:"spacing-playground"},i={class:"row"},l={class:"flex xs12 sm6 md4"},s={class:"flex xs12 sm6 md2"},d={class:"flex xs12 sm6 md4"},u={class:"flex xs12 sm6 md2"},p={key:0,class:"row"},m={class:"flex xs12 content"},g={class:"code"},b={class:"row"},f={class:"flex xs12"},v={class:"playground-component"},O=Object(c["createElementVNode"])("div",{class:"playground-component__inner"},null,-1),j=[O],h={class:"row"},y={class:"flex xs12 sm6"},V={class:"flex xs12 sm6"};function x(e,t,n,o,a,O){var x=Object(c["resolveComponent"])("va-select"),N=Object(c["resolveComponent"])("color-presentation");return Object(c["openBlock"])(),Object(c["createElementBlock"])("div",r,[Object(c["createElementVNode"])("div",i,[Object(c["createElementVNode"])("div",l,[Object(c["createVNode"])(x,{options:a.directionList,modelValue:a.selectedMarginDirection,"onUpdate:modelValue":t[0]||(t[0]=function(e){return a.selectedMarginDirection=e}),label:e.$t("spacingPlayground.margin"),"max-height":null,"no-clear":""},null,8,["options","modelValue","label"])]),Object(c["createElementVNode"])("div",s,[Object(c["createVNode"])(x,{options:a.sizesList,modelValue:a.selectedMarginSize,"onUpdate:modelValue":t[1]||(t[1]=function(e){return a.selectedMarginSize=e}),label:e.$t("spacingPlayground.value"),"max-height":null,"no-clear":""},null,8,["options","modelValue","label"])]),Object(c["createElementVNode"])("div",d,[Object(c["createVNode"])(x,{options:a.directionList,modelValue:a.selectedPaddingDirection,"onUpdate:modelValue":t[2]||(t[2]=function(e){return a.selectedPaddingDirection=e}),label:e.$t("spacingPlayground.padding"),"max-height":null,"no-clear":""},null,8,["options","modelValue","label"])]),Object(c["createElementVNode"])("div",u,[Object(c["createVNode"])(x,{options:a.sizesList,modelValue:a.selectedPaddingSize,"onUpdate:modelValue":t[3]||(t[3]=function(e){return a.selectedPaddingSize=e}),label:e.$t("spacingPlayground.value"),"max-height":null,"no-clear":""},null,8,["options","modelValue","label"])])]),O.selectedMarginClass||O.selectedPaddingClass?(Object(c["openBlock"])(),Object(c["createElementBlock"])("div",p,[Object(c["createElementVNode"])("div",m,[Object(c["createElementVNode"])("pre",g,'class="'+Object(c["toDisplayString"])((O.selectedMarginClass+" "+O.selectedPaddingClass).trim())+'"',1)])])):Object(c["createCommentVNode"])("",!0),Object(c["createElementVNode"])("div",b,[Object(c["createElementVNode"])("div",f,[Object(c["createElementVNode"])("div",v,[Object(c["createElementVNode"])("div",{class:Object(c["normalizeClass"])(["playground-component__margin",O.selectedMarginClass])},[Object(c["createElementVNode"])("div",{class:Object(c["normalizeClass"])(["playground-component__padding",O.selectedPaddingClass])},j,2)],2)])])]),Object(c["createElementVNode"])("div",h,[Object(c["createElementVNode"])("div",y,[Object(c["createVNode"])(N,{color:"#ffd093",name:e.$t("spacingPlayground.margin")},null,8,["name"])]),Object(c["createElementVNode"])("div",V,[Object(c["createVNode"])(N,{color:"#c9f7db",name:e.$t("spacingPlayground.padding")},null,8,["name"])])])])}n("99af");var N=n("2064"),C={name:"spacing-playgroud",components:{ColorPresentation:N["a"]},data:function(){return{directionList:["a","y","x","t","r","b","l"],sizesList:["1","2","3","4","5","auto"],selectedMarginDirection:"y",selectedPaddingDirection:"x",selectedMarginSize:"3",selectedPaddingSize:"3"}},computed:{selectedMarginClass:function(){return this.selectedMarginDirection&&this.selectedMarginSize?"m".concat(this.selectedMarginDirection,"-").concat(this.selectedMarginSize):""},selectedPaddingClass:function(){return this.selectedPaddingDirection&&this.selectedPaddingSize?"p".concat(this.selectedPaddingDirection,"-").concat(this.selectedPaddingSize):""}}};n("edcb");C.render=x;var S=C,E={name:"spacing",components:{SpacingPlaygroud:S}};E.render=a;t["default"]=E},"5a34":function(e,t,n){var c=n("44e7");e.exports=function(e){if(c(e))throw TypeError("The method doesn't accept regular expressions");return e}},"9cea":function(e,t,n){},ab13:function(e,t,n){var c=n("b622"),o=c("match");e.exports=function(e){var t=/./;try{"/./"[e](t)}catch(n){try{return t[o]=!1,"/./"[e](t)}catch(c){}}return!1}},c8d2:function(e,t,n){var c=n("d039"),o=n("5899"),a="​᠎";e.exports=function(e){return c((function(){return!!o[e]()||a[e]()!=a||o[e].name!==e}))}},caad:function(e,t,n){"use strict";var c=n("23e7"),o=n("4d64").includes,a=n("44d2");c({target:"Array",proto:!0},{includes:function(e){return o(this,e,arguments.length>1?arguments[1]:void 0)}}),a("includes")},e01a:function(e,t,n){"use strict";var c=n("23e7"),o=n("83ab"),a=n("da84"),r=n("5135"),i=n("861d"),l=n("9bf2").f,s=n("e893"),d=a.Symbol;if(o&&"function"==typeof d&&(!("description"in d.prototype)||void 0!==d().description)){var u={},p=function(){var e=arguments.length<1||void 0===arguments[0]?void 0:String(arguments[0]),t=this instanceof p?new d(e):void 0===e?d():d(e);return""===e&&(u[t]=!0),t};s(p,d);var m=p.prototype=d.prototype;m.constructor=p;var g=m.toString,b="Symbol(test)"==String(d("test")),f=/^Symbol\((.*)\)[^)]+$/;l(m,"description",{configurable:!0,get:function(){var e=i(this)?this.valueOf():this,t=g.call(e);if(r(u,e))return"";var n=b?t.slice(7,-1):t.replace(f,"$1");return""===n?void 0:n}}),c({global:!0,forced:!0},{Symbol:p})}},e1b0:function(e,t,n){"use strict";n("37e85")},edcb:function(e,t,n){"use strict";n("9cea")}}]);
//# sourceMappingURL=chunk-2eb131d1.6f422646.js.map