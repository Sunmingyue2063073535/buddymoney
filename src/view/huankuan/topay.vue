<template>
    <div class="topay">
        <TopDesc desc="Gold Money"></TopDesc>
        <div class="topay-title">Get more amount after instant </div>
        <div class="ap-amount">
            <div class="ap-a-key">Repayment amount</div>
            <div class="ap-a-val">₹ {{ toThousands(orderInfo.actualAmount) }}</div>
            <div class="kefu" @click="$router.push('/kefuList')">
                <img src="../../assets/app-kefu.png" alt="">
            </div>
        </div>
        <!-- 列表 -->
        <div class="one">
            <div class="one-key">Loan note number: </div>
            <div class="one-val">{{ orderInfo.orderId }}</div>
        </div>
        <div class="one">
            <div class="one-key">Phone number:</div>
            <div class="one-val">{{ orderInfo.phone }}</div>
        </div>
        <div class="one">
            <div class="one-key">Bank cardnumber:</div>
            <div class="one-val">{{ orderInfo.bankCard }}</div>
        </div>
        <div class="one">
            <div class="one-key">Loan Period(Days):</div>
            <div class="one-val">{{ orderInfo.loanTerm }} {{ orderInfo.termUnit }}</div>
        </div>
        <div class="one">
            <div class="one-key">End date:</div>
            <div class="one-val">{{ new Date(orderInfo.expiryTime).toLocaleDateString() }}</div>
        </div>
        <div class="one">
            <div class="one-key">Interest:</div>
            <div class="one-val">{{ toPercent(orderInfo.interestRate) }}</div>
        </div>
        <div class="one">
            <div class="one-key">Total service charge:</div>
            <div class="one-val">₹ {{ toThousands(orderInfo.adminAmount) }}</div>
        </div>
        <div class="btn">
            <div class="repayment" @click="doClick">Repayment</div>
            <div class="rollover" @click="$router.push('/rollover')">Rollover</div>
        </div>
        <van-dialog v-model="show" :showConfirmButton="false" :showCancelButton="false">
            <toPayDialog @doClose="show = false"></toPayDialog>
        </van-dialog>
    </div>
</template>
<script>
import toPayDialog from './toPayDialog.vue'
export default {
    components: { toPayDialog },
    data() {
        return {
            show: false,
        }
    },
    methods: {
        doClick() {
            this.show = true
        },
        //转换百分数保留两位小数
        toPercent(point) {
            let str = Number(point * 100).toFixed(2);
            str += "%";
            return str;
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
    computed: {
        orderInfo() {
            return this.$store.state.orderInfo
        }
    }
}
</script>
<style lang="less" scoped>
.topay {
    width: 100vw;
    min-height: 100vh;
    background-color: #f6f6f6;
    padding-top: (70/@a);
    padding-bottom: (70/@a);

    .btn {
        width: 100vw;
        position: fixed;
        bottom: 0;
        left: 0;
        display: flex;

        .repayment {
            width: (174/@a);
            height: (45/@a);
            background: #1E005A;
            border-radius: (8/@a);
            text-align: center;
            font-size: (22/@a);
            font-family: Source Han Sans CN;
            font-weight: 400;
            line-height: (45/@a);
            color: #FFFFFF;
            margin-left: (8/@a);
            margin-right: (10/@a);
        }

        .rollover {
            width: (174/@a);
            height: (45/@a);
            background: #866BBB;
            border-radius: (8/@a);
            text-align: center;
            font-size: (22/@a);
            font-family: Source Han Sans CN;
            font-weight: 400;
            line-height: (45/@a);
            color: #FFFFFF;
        }
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

    .ap-amount {
        position: relative;
        width: (359/@a);
        height: (105/@a);
        background: #FFFFFF;
        box-shadow: 0 (2/@a) (5/@a) rgba(0, 0, 0, 0.16);
        border-radius: (14/@a);
        margin-left: (8/@a);

        .ap-a-key {
            font-size: (22/@a);
            font-family: Source Han Sans CN;
            font-weight: 500;
            color: #000000;
            margin-top: (15/@a);
            margin-left: (76/@a);
        }

        .ap-a-val {
            font-size: (38/@a);
            font-family: Source Han Sans CN;
            font-weight: bold;
            color: #1E005A;
            text-align: center;
        }

        .kefu {
            position: absolute;
            top: (20/@a);
            right: (20/@a);
            width: (37/@a);
            height: (37/@a);

            img {
                width: 100%;
            }
        }
    }

    .topay-title {
        width: 100vw;
        height: (38/@a);
        background-color: #fff;
        margin-top: (10/@a);
        text-align: center;
        line-height: (38/@a);
        font-size: (13/@a);
        font-family: Source Han Sans CN;
        font-weight: 400;
        color: #1E005A;
    }
}
</style>