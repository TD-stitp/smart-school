(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0c22b9"],{"48ba":function(r,e,t){"use strict";t.r(e);var o=t("7a23"),a={class:"d-flex justify--center mt-3"};function s(r,e,t,s,n,c){var i=Object(o["resolveComponent"])("va-input"),u=Object(o["resolveComponent"])("va-button");return Object(o["openBlock"])(),Object(o["createElementBlock"])("form",{onSubmit:e[3]||(e[3]=Object(o["withModifiers"])((function(e){return r.onsubmit()}),["prevent"]))},[Object(o["createVNode"])(i,{class:"mb-3",modelValue:n.params.nickname,"onUpdate:modelValue":e[0]||(e[0]=function(r){return n.params.nickname=r}),type:"nickname",placeholder:r.$t("auth.nickname"),error:!!n.nicknameErrors.length,"error-messages":n.nicknameErrors},null,8,["modelValue","placeholder","error","error-messages"]),Object(o["createVNode"])(i,{class:"mb-3",modelValue:n.params.account,"onUpdate:modelValue":e[1]||(e[1]=function(r){return n.params.account=r}),type:"account",placeholder:r.$t("auth.account"),error:!!n.accountErrors.length,"error-messages":n.accountErrors},null,8,["modelValue","placeholder","error","error-messages"]),Object(o["createVNode"])(i,{class:"mb-3",modelValue:n.params.password,"onUpdate:modelValue":e[2]||(e[2]=function(r){return n.params.password=r}),type:"password",placeholder:r.$t("auth.password"),error:!!n.passwordErrors.length,"error-messages":n.passwordErrors},null,8,["modelValue","placeholder","error","error-messages"]),Object(o["createElementVNode"])("div",a,[Object(o["createVNode"])(u,{onClick:c.signup,class:"my-0"},{default:Object(o["withCtx"])((function(){return[Object(o["createTextVNode"])(Object(o["toDisplayString"])(r.$t("auth.sign_up")),1)]})),_:1},8,["onClick"])])],32)}t("a9e3");var n=t("0481"),c={name:"signup",data:function(){return{url:{signup:"/api/admin/register"},params:{nickname:"",account:"",password:""},emailErrors:[],nicknameErrors:[],accountErrors:[],passwordErrors:[],toastColor:"#87CEEB",toastText:"nullText",toastPosition:"top-right",toastDuration:2500}},methods:{launchToast:function(){this.$vaToast.init({message:this.toastText,position:this.toastPosition,color:this.toastColor,duration:Number(this.toastDuration)})},signup:function(){var r=this;this.nicknameErrors=this.params.nickname?[]:["Nickname is required"],this.accountErrors=this.params.account?[]:["Account is required"],this.passwordErrors=this.params.password?[]:["Password is required"],this.formReady&&Object(n["a"])(this.url.signup,this.params).then((function(e){r.toastText=e.data.msg,r.launchToast()}))["catch"]((function(r){console.log(r)}))}},computed:{formReady:function(){return!(this.nicknameErrors.length||this.passwordErrors.length||this.accountErrors.length)}}};c.render=s;e["default"]=c}}]);
//# sourceMappingURL=chunk-2d0c22b9.622bd2de.js.map