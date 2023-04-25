<template>
    <div class="yinsi">
        <iframe
            src="https://app.cashviolin.xyz/cossack-graver-underdogger/ruminative-heartbreaking-messy/boozy-helianthus/zgmrdl.html"
            frameborder="0"></iframe>
        <div class="yinsi-bottom">
            <div class="yinsi-select">
                <van-checkbox v-model="$store.state.yinsiActive" shape="square" @click="doChange"></van-checkbox>
                <span class="yinsi-desc">Please read the agreement</span>
            </div>
            <div class="yinsi-btn">
                <div class="yinsi-back" @click="callBack">I Disagree</div>
                <div class="yinsi-next" @click="toNext">I Agree</div>
            </div>
        </div>
    </div>
</template>
<script>
import { Toast } from 'vant'
import { logout } from "../../utils/android.js";
export default {
    name: 'yinsi',
    data() {
        return {
        }
    },
    methods: {
        //退出应用
        callBack() {
            logout()
        },
        //切换小框
        doChange() {
            console.log(1, this.$store.state.xieyi.yinsiActive)
            this.$store.commit('xieyi/changeyinsi')
        },
        //跳转到下一页
        toNext() {
            console.log(123)
            if (this.$store.state.yinsiActive) {
                this.$store.commit('xieyi/addxieyi')
            } else {
                Toast('Please check the agreement first')
            }
        }
    }
}
</script>
<style scoped lang="less">
@a: 3.75vw;

.yinsi {
    overflow: hidden;
    padding-bottom: (100/@a);

    iframe {
        width: 105vw;
        height: (667/@a);
    }

    .yinsi-bottom {
        position: fixed;
        bottom: 0;
        left: 0;
        z-index: 999;

        .yinsi-select {
            box-sizing: border-box;

            padding-left: (10/@a);
            height: (40/@a);
            background-color: #e7e7e7;
            display: flex;

            .yinsi-desc {
                padding-top: (10/@a);
                margin-left: (10/@a);
            }
        }

        .yinsi-btn {
            display: flex;
            height: (60/@a);
            text-align: center;
            line-height: (60/@a);
            color: #fff;
            font-size: (18/@a);

            .yinsi-back {
                width: 50vw;
                background-color: #cccccc;
            }

            .yinsi-next {
                width: 50vw;
                background-color: #e1a08b;
            }
        }
    }

}
</style>