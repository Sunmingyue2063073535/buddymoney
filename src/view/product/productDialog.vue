<template>
    <div class="productdialog">
        <div class="leave" @click="$emit('closeDialog')">
            <img src="../../assets/x.png" alt="">
        </div>
        <div class="pd-title">Trial calculation of handling fee</div>
        <ul class="pd-content" v-for="item in sxfList">
            <li>
                <div class="left">Loan name:</div>
                <div class="right">{{ item.name }}</div>
            </li>
            <li>
                <div class="left">Loan amount:</div>
                <div class="right">{{ item.amount }}</div>
            </li>
            <li>
                <div class="left">Loan Period:</div>
                <div class="right">{{ item.term }} {{ item.termUnit }}</div>
            </li>
            <li>
                <div class="left">adminAmount:</div>
                <div class="right">{{ item.adminAmount }}</div>
            </li>
            <li>
                <div class="left">repayAmount:</div>
                <div class="right">{{ item.repayAmount }}</div>
            </li>
            <li>
                <div class="left">actualAmount:</div>
                <div class="right">{{ item.actualAmount }}</div>
            </li>
        </ul>

        <!-- 去贷款 -->
        <div class="apply" @click="doApply">Apply</div>
    </div>
</template>
<script>
import { add, unt } from "../../utils/AES.js";
import { getOrderListAPI, getOrderPayAPI, dkUploadAPI } from "../../api";
export default {
    props: { sxfList: [] },
    data() {
        return {
            // sxfList: [],
        }
    },
    methods: {
        //申请贷款
        async doApply() {
            const f = {
                model: {
                    productIds: []
                }
            }
            f.model.productIds.push(this.$store.state.productId)
            const res = await dkUploadAPI(add(f))
            this.$router.push('/loan')
        },
        //获取产品试算
        async getList() {
            const f = {
                model: {
                    productIds: []
                }
            }
            f.model.productIds.push(this.$store.state.productId)
            console.log(f)
            const res = await getOrderPayAPI(add(f))
            console.log(unt(res.data), '手续费试算结果')
            this.sxfList = unt(res.data).model
            console.log(this.sxfList)
        }
    },
    created() {
        // this.getList()
        // console.log(this.sxfList, '子传父的数据')
    }
}
</script>
<style lang="less" scoped>
.productdialog {
    position: relative;
    padding-bottom: (30/@a);

    .pd-content {
        margin-top: (25/@a);
        margin-left: (16/@a);

        li {
            display: flex;
            justify-content: space-between;
            padding-left: (14/@a);
            padding-right: (14/@a);
            width: (280/@a);
            height: (46/@a);
            background: #FAEAEC;
            border-radius: (10/@a);
            margin-top: (1/@a);
            line-height: (46/@a);

            &:nth-child(odd) {
                background-color: #b9abd5;
            }

            &:nth-child(even) {
                background-color: #9d7ce0;
            }

            .left {
                font-size: (13/@a);
                font-family: Alibaba PuHuiTi;
                font-weight: bold;
                color: #fff;
            }

            .right {
                font-size: (13/@a);
                font-family: Alibaba PuHuiTi;
                font-weight: 500;
                color: #fff;
            }
        }
    }

    .apply {
        width: (200/@a);
        height: (50/@a);
        margin-top: (20/@a);
        background: linear-gradient(180deg, #8B70C0 0%, #1E005A 100%);
        text-align: center;
        line-height: (50/@a);
        margin-left: (50/@a);
        border-radius: (10/@a);
        font-size: (19/@a);
        font-family: Alibaba PuHuiTi;
        font-weight: bold;
        color: #FFFFFF;
    }

    .leave {
        position: absolute;
        top: (10/@a);
        right: (10/@a);
        width: (30/@a);
        height: (30/@a);

        img {
            width: 100%;
        }
    }

    .pd-title {
        padding-top: (30/@a);
        padding-left: (30/@a);
        font-size: (16/@a);
        font-family: Alibaba PuHuiTi;
        font-weight: bold;
        color: #3E3E3E;
    }
}
</style>