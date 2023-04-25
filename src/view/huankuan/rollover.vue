
<template>
    <div class="rollover">
        <TopDesc desc="Rollover"></TopDesc>
        <div class="title">Payment has been completed and repayment ls delayed.</div>
        <div class="amount">
            <div class="a-k">Pay niw</div>
            <div class="a-v">₹ {{ toThousands(o.loanAmount) }}</div>
        </div>
        <div class="one">
            <div class="one-key">Delayed date:</div>
            <div class="one-val">{{ new Date(o.endTime).toLocaleDateString() }}</div>
        </div>
        <div class="one">
            <div class="one-key">Extend time:</div>
            <div class="one-val">{{ o.delayTerm }} {{ o.termUnit }}</div>
        </div>
        <div class="one">
            <div class="one-key">Recording delay(max.10000):</div>
            <div class="one-val">{{ o.limitTimes }}</div>
        </div>
        <div class="desc">This loan is provided by a third-party company </div>
        <div class="btn" @click="$router.push('/payType')">Need to repay loan ₹ {{ o.loanAmount }}</div>
    </div>
</template>
<script>
import { zhanqiAPI } from "../../api";
import { add, unt } from "../../utils/AES.js";
export default {
    data() {
        return {
            o: {}
        }
    },
    methods: {
        async getZhanqi() {
            const f = {
                model: {
                    orderId: this.$store.state.orderId
                }
            }
            const res = await zhanqiAPI(add(f))
            console.log(unt(res.data))
            this.o = unt(res.data).model
        },
        //三个数字前加逗号
        toThousands(num) {
            const result = []; let counter = 0
            num = (num || 0).toString().split('')
            for (let i = num.length - 1; i >= 0; i--) {
                counter++
                result.unshift(num[i])
                if (!(counter % 3) && i !== 0) { result.unshift(',') }
            }
            return result.join('')
        }
    },
    created() {
        this.getZhanqi()
    }
}
</script>
<style lang="less" scoped>
.rollover {
    width: 100vw;
    height: 100vh;
    background-color: #f6f6f6;
    padding-top: (80/@a);

    .amount {
        width: (359/@a);
        height: (155/@a);
        background: #FFFFFF;
        box-shadow: 0 (2/@a) (5/@a) rgba(0, 0, 0, 0.16);
        border-radius: (19/@a);
        margin-top: (10/@a);
        margin-left: (8/@a);
        text-align: center;

        .a-k {
            font-size: (36/@a);
            font-family: Source Han Sans CN;
            font-weight: 400;
            color: #101010;
            margin-top: (8/@a);
        }

        .a-v {
            font-size: (68/@a);
            font-weight: bold;
            font-style: Source Han Sans CN;
            color: #1E005A;
        }
    }

    .desc {
        width: (310/@a);
        height: (52/@a);
        font-size: (18/@a);
        font-family: Source Han Sans CN;
        font-weight: 400;
        color: #A1A1A1;
        margin-top: (39/@a);
        text-align: center;
        margin-left: (32/@a);
    }

    .one {
        .one-key {
            font-size: (16/@a);
            font-family: Source Han Sans CN;
            font-weight: 400;
            color: #999999;
            margin-top: (10/@a);
            margin-left: (8/@a);

        }

        .one-val {
            width: (359/@a);
            height: (30/@a);
            padding: (5/@a) (21/@a);
            margin-left: (8/@a);
            background: #FFFFFF;
            box-shadow: 0 (2/@a) (5/@a) rgba(0, 0, 0, 0.16);
            border-radius: (15/@a);
        }
    }

    .title {
        width: 100vw;
        height: (38/@a);
        padding: (11/@a) (16/@a);
        background: #FFFFFF;
        font-size: (12/@a);
        font-family: Source Han Sans CN;
        font-weight: 400;
        color: #1E005A;
    }

    .btn {
        position: fixed;
        bottom: 0;
        left: 0;
        width: 100vw;
        height: (52/@a);
        background: #1e005a;
        font-size: (19/@a);
        text-align: center;
        line-height: (52/@a);
        font-family: Alibaba PuHuiTi;
        font-weight: bold;
        color: #FEFEFE;
        margin-top: (39/@a);
    }
}
</style>