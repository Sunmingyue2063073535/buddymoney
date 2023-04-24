<template>
    <div class="loan">
        <TopDesc desc="My Loan"></TopDesc>
        <ul class="list">
            <li v-for="(item, index) in TopTitle" :key="item.id" @click="doClick(item)"
                :class="{ 'li-active': index === isActive }">{{ item.name }}</li>
        </ul>
        <van-pull-refresh v-model="isLoading" @refresh="onRefresh" pulling-text="Pull down to refresh..."
            loading-text="Loading..." loosing-text="Release to refresh..." success-text="
Refresh successfully">
            <div class="ul-list" v-for="item in list">
                <div class="top">
                    <div class="top-icon">
                        <img :src="icon(item.product.icon)" alt="">
                    </div>
                    <div class="top-name">{{ item.product.name }}</div>
                </div>
                <div class="box">
                    <ul class="box-l">
                        <li>Loan Amount</li>
                        <li>Loan Period (Days)</li>
                        <li>Loan Date</li>
                        <li>Loan Note Number</li>
                    </ul>
                    <ul class="box-r">
                        <li>₹ {{ item.amount }}</li>
                        <li>{{ item.term }} {{ item.termUnit }}</li>
                        <li>{{ new Date(item.created).toLocaleDateString() }}</li>
                        <li>{{ item.id }}</li>
                    </ul>
                </div>
                <div class="btn" @click="doStatusBtn(item)" :style="{ 'background-color': item.statusColor }">{{ item.status
                }}
                </div>
            </div>
            <nothing v-if="!list.length"></nothing>
        </van-pull-refresh>
    </div>
</template>
<script>
import { getDingDanListAPI, dingdanhuankuanAPI } from "../../api";
import { add, unt } from "../../utils/AES.js";
import nothing from './nothing'
import { Toast } from "vant";
export default {
    components: { nothing },
    data() {
        return {
            TopTitle: [
                { id: 0, name: 'All', msg: null },
                { id: 1, name: 'Success', msg: 'LOAN_SUCCESS' },
                { id: 2, name: 'Overdue', msg: 'DUNNING' },
                { id: 3, name: 'Finish', msg: 'FINISH' },
            ],
            isActive: 0,
            list: [],
            isLoading: false
        }
    },
    methods: {
        doClick(item) {
            this.isActive = item.id
            this.getOrderList(item.msg)
        },
        //点击去还款
        async doStatusBtn(item) {
            if (!item.loan) {
                Toast(item.statusNote)
                return
            }
            //将id存入vuex
            this.$store.commit('setOrderId', item.id)
            const f = {
                model: {
                    orderId: ''
                }
            }
            f.model.orderId = this.$store.state.orderId
            const res = await dingdanhuankuanAPI(add(f))
            console.log(unt(res.data))
            this.$store.commit('setOrderInfo', unt(res.data).model)
            // this.$router.push('/topay')
            // console.log(unt(res.data).model)
        },
        //获取订单列表
        async getOrderList(status) {
            const f = {
                query: {
                    status: status,
                    pageNo: 1,
                    pageSize: 99
                }
            }
            const res = await getDingDanListAPI(add(f))
            this.isLoading = false
            console.log(unt(res.data), '获取列表')
            this.list = unt(res.data).page.content
        },
        //下拉刷新
        onRefresh() {
            let index = this.TopTitle.findIndex(item => item.id === this.isActive)
            let str = this.TopTitle[index].msg
            this.getOrderList(str)
        },
        icon(icon) {
            return `https://app.buddymoney.xyz/lt-image/resize/0x0/${icon}`
        },
    },
    created() {
        this.getOrderList(null)
    }
}
</script>
<style lang="less" scoped>
.loan {
    padding-top: (90/@a);
    width: 100vw;
    min-height: 100vh;
    padding-bottom: (40/@a);
    background-color: #f5f5f5;

    .ul-list {
        margin-top: (16/@a);
        background: #FFFFFF;
        box-shadow: 0 (2/@a) (7/@a) rgba(0, 0, 0, 0.16);
        padding-bottom: (20/@a);

        .top {
            display: flex;

            .top-icon {
                width: (34/@a);
                height: (34/@a);
                margin-top: (12/@a);
                margin-left: (95/@a);

                img {
                    width: 100%;
                }
            }

            .top-name {
                font-size: (20/@a);
                font-family: Source Han Sans CN;
                font-weight: 400;
                color: #4D4D4D;
                margin-left: (8/@a);
                margin-top: (18/@a);
            }
        }

        .box {
            width: 100vw;
            background-color: #fff;
            display: flex;
            justify-content: space-between;
            padding-top: (13/@a);

            .box-l {
                text-align: left;

                li {
                    font-size: (13/@a);
                    font-family: Alibaba PuHuiTi;
                    font-weight: 400;
                    color: #252525;
                    margin-bottom: (13/@a);
                    margin-left: (18/@a);
                }
            }

            .box-r {

                text-align: right;

                li {
                    font-size: (13/@a);
                    font-family: Alibaba PuHuiTi;
                    font-weight: 400;
                    color: #252525;
                    margin-bottom: (13/@a);
                    margin-right: (17/@a);
                }
            }
        }

        .btn {
            border-radius: (5/@a);
            margin-left: (120/@a);
            text-align: center;
            line-height: (26/@a);
            margin-right: (120/@a);
            font-size: (16/@a);
            font-family: Source Han Sans CN;
            font-weight: 400;
            text-align: center;
            color: #FFFFFF;
            padding: (5/@a);
        }
    }



    .list {
        display: flex;
        justify-content: space-around;

        .li-active {
            border-radius: (5/@a);
            border-bottom: #1E005A (5/@a) solid;
            color: #1E005A;
        }

        li {
            font-size: (16/@a);
            font-family: Source Han Sans CN;
            font-weight: 400;
            color: #220303;
            padding-bottom: (10/@a);
        }
    }
}
</style>