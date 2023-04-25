<template>
    <div class="payType">
        <TopDesc desc="Repayment mode"></TopDesc>
        <div class="amount">₹ {{ toThousands(this.$store.state.orderInfo.actualAmount) }}</div>
        <div class="title">Choose your payment mode</div>
        <div class="type">
            <div class="list" v-for="item in list" @click="doClick">{{ item.repayMethod }}</div>
        </div>
        <div class="desc">This loan is provided by a third-party company </div>
    </div>
</template>
<script>
import { getOrderPayType, getOrderLinkAPI } from "../../api";
import { add, unt } from "../../utils/AES.js";
export default {
    data() {
        return {
            list: []
        }
    },
    methods: {
        //获取还款方式
        async getPayType() {
            const f = {
                model: {
                    orderId: this.$store.state.orderId
                }
            }
            const res = await getOrderPayType(add(f))
            console.log(unt(res.data))
            this.list = unt(res.data).model.methods
        },
        //去还款
        async doClick() {
            const f = {
                model: {
                    orderId: this.$store.state.orderId,
                    repayMethod: '',
                    methodCode: '',
                    repayType: 'IMMEDIATE'
                }
            }
            f.model.repayMethod = this.list[0].repayMethod
            f.model.methodCode = this.list[0].methodCode
            console.log(f)
            const res = await getOrderLinkAPI(add(f))
            try {
                console.log(unt(res.data), '获取还款链接')
                const href = unt(res.data).model.repayCode
                setTimeout(() => {
                    window.location.href = href
                }, 1000);
            } catch (error) {

            }
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
        this.getPayType()
    }
}
</script>
<style lang="less" scoped>
.payType {
    width: 100vw;
    height: 100vh;
    background-color: #f6f6f6;
    padding-top: (70/@a);

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

    .type {
        width: (359/@a);
        background: #FFFFFF;
        box-shadow: 0 (2/@a) (5/@a) rgba(0, 0, 0, 0.16);
        border-radius: (23/@a);
        margin-top: (12/@a);
        margin-left: (8/@a);
        padding: (20/@a);

        .list {
            background-color: #1e005a;
            border-radius: (20/@a);
            font-size: (18/@a);
            font-family: Source Han Sans CN;
            font-weight: 500;
            color: #fff;
            text-align: center;
            line-height: (50/@a);
            margin-top: (10/@a);
        }
    }

    .title {
        font-size: (21/@a);
        font-family: Source Han Sans CN;
        font-weight: 400;
        color: #101010;
        margin-top: (18/@a);
        margin-left: (10/@a);
    }

    .amount {
        width: (359/@a);
        height: (71/@a);
        background: #FFFFFF;
        box-shadow: 0 (2/@a) (5/@a) rgba(0, 0, 0, 0.16);
        border-radius: (14/@a);
        text-align: center;
        line-height: (71/@a);
        margin-left: (8/@a);
        margin-top: (10/@a);
        font-size: (38/@a);
        font-weight: bolder;
        font-style: Source Han Sans CN;
        color: #1E005A;
    }
}
</style>