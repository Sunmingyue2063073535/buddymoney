<template>
    <div class="beforeys">
        <iframe src="https://app.buddymoney.xyz/gangrenopsis-riometer/trunkless/gaffer-overexert-pratincole/ap0e1e.html"
            frameborder="0"></iframe>
        <div class="beforeys-bottom">
            <div class="beforeys-select">
                <van-checkbox v-model="isChecked" shape="square"></van-checkbox>
                <span class="beforeys-desc">Please read the agreement</span>
            </div>
            <div class="beforeys-btn">
                <div class="beforeys-back" @click="$router.back()">Disagree</div>
                <div class="beforeys-next" @click="toNext">Agree</div>
            </div>
        </div>
    </div>
</template>
<script>
import { Toast } from 'vant'
export default {
    name: 'beforeys',
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
                this.$router.push('/beforeqx')
            } else {
                Toast('Please check the agreement first')
            }
        }
    }
}
</script>
<style scoped lang="less">
@a: 3.75vw;

.beforeys {
    overflow: hidden;
    padding-bottom: (100/@a);

    iframe {
        width: 100vw;
        height: (667/@a);
    }

    .beforeys-bottom {
        position: fixed;
        bottom: 0;
        left: 0;
        z-index: 999;

        .beforeys-select {
            box-sizing: border-box;

            padding-left: (10/@a);
            height: (40/@a);
            background-color: #e7e7e7;
            display: flex;

            .beforeys-desc {
                padding-top: (10/@a);
                margin-left: (10/@a);
            }
        }

        .beforeys-btn {
            display: flex;
            height: (60/@a);
            text-align: center;
            line-height: (60/@a);
            color: #fff;
            font-size: (18/@a);

            .beforeys-back {
                width: 50vw;
                background-color: #cccccc;
            }

            .beforeys-next {
                width: 50vw;
                background: linear-gradient(180deg, #8B70C0 0%, #1E005A 100%);
            }
        }
    }

}
</style>