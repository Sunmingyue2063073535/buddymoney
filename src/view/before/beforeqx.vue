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
import { Toast } from 'vant'
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
        toNext() {
            if (this.isChecked) {
                isNext()
            } else {
                Toast('Please check the agreement first')
            }
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