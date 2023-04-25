<template>
    <div class="quanxian">
        <iframe
            src="https://app.cashviolin.xyz/cossack-graver-underdogger/ruminative-heartbreaking-messy/boozy-helianthus/iivskc.html"
            frameborder="0"></iframe>
        <div class="quanxian-bottom">
            <div class="quanxian-select">
                <van-checkbox v-model="$store.state.quanxianActive" shape="square" @click="doChange"></van-checkbox>
                <span class="quanxian-desc">Please read the agreement</span>
            </div>
            <div class="quanxian-btn">
                <div class="quanxian-back" @click="doCall">I Disagree</div>
                <div class="quanxian-next" @click="toNext">I Agree</div>
            </div>
        </div>
    </div>
</template>
<script>
import { Toast } from 'vant'
export default {
    data() {
        return {
        }
    },
    methods: {
        doCall() {
            this.$store.commit('xieyi/untxieyi')
        },
        //切换小框
        doChange() {
            this.$store.commit('xieyi/changequanxian')
        },
        //跳转到下一页
        toNext() {
            if (this.$store.state.quanxianActive) {
                this.$store.commit('xieyi/addxieyi')
                this.$router.push('/shouye')
            } else {
                Toast('Please check the agreement first')
            }
        }
    },
}
</script>
<style scoped lang="less">
@a: 3.75vw;

.quanxian {
    overflow: hidden;
    padding-bottom: (100/@a);

    iframe {
        width: 105vw;
        height: (667/@a);
    }

    .quanxian-bottom {
        position: fixed;
        bottom: 0;
        left: 0;
        z-index: 999;

        .quanxian-select {
            box-sizing: border-box;

            padding-left: (10/@a);
            height: (40/@a);
            background-color: #e7e7e7;
            display: flex;

            .quanxian-desc {
                padding-top: (10/@a);
                margin-left: (10/@a);
            }
        }

        .quanxian-btn {
            display: flex;
            height: (60/@a);
            text-align: center;
            line-height: (60/@a);
            color: #fff;
            font-size: (18/@a);

            .quanxian-back {
                width: 50vw;
                background-color: #cccccc;
            }

            .quanxian-next {
                width: 50vw;
                background-color: #e1a08b;
            }
        }
    }

}
</style>