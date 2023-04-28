import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import './style/base.css'
import Vant from 'vant';
import 'vant/lib/index.css';
import MyCom from './components/index'
import { uploadGoogleTokenAPI, uploadInstanceIdAPI, uploadInstallReferrerAPI } from "./api";
import { add, unt } from "./utils/AES";
Vue.use(Vant);
Vue.use(MyCom)
Vue.config.productionTip = false;
// 挂载全局函数， android调用
window.UploadThreeId = async function (item) {
    if (item && item.type == 1 && item.token) {
        console.log(JSON.stringify(item), '上报谷歌token')
        // console.log('111')
        const result = await uploadGoogleTokenAPI(add({ model: item.token || null }))
        // console.log(item.token, '谷歌token')
        console.log(JSON.stringify(unt(result.data)), '上报谷歌token')
        console.log(result, '上报谷歌token')
    }
    if (item && item.type == 2 && item.id) {
        console.log(JSON.stringify(item), '上报InstanceId')
        const result = await uploadInstanceIdAPI(add({ model: item.id || null }))
        // console.log(item.id, '第二个id')
        console.log(JSON.stringify(unt(result.data)), '上报InstanceId')
        console.log(result, '上报InstanceId')
        // console.log('222')
    }
    if (item && item.type == 3) {
        console.log(JSON.stringify(item), '上报InstallReferrer')
        // console.log(JSON.stringify(item), 'InstallReferrer')
        const result = await uploadInstallReferrerAPI(add({ model: item || {} }))
        console.log(JSON.stringify(unt(result.data)), '上报InstallReferrer')
        console.log(result, '上报InstallReferrer')
        // console.log('333')
    }
}
new Vue({
    router,
    store,
    render: (h) => h(App),
}).$mount("#app");