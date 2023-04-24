<template>
    <div class="productList">
        <FormTopDesc desc="Product List"></FormTopDesc>
        <div class="top">
            <div class="top-desc">My Loan History</div>
            <div class="top-view">View</div>
        </div>
        <!-- 产品列表 -->
        <ul class="list">
            <li v-for="(item, index) in list" :key="item.id" @click="doClick(item, index)"
                :class="{ active: index === checked }">
                <div class="list-l" @click="doChecked" :class="{ isrouter: isnone }">
                    <img src="../../assets/callback.png" alt="">
                </div>
                <div class="list-r">
                    <div class="list-r-top">
                        <div class="list-r-top-img">
                            <img :src="icon(item.icon)" alt="">
                        </div>
                        <div class="list-r-top-name">{{ item.name }}</div>
                    </div>
                    <div class="list-r-box">
                        <div class="loan">
                            <div class="loan-left">
                                <div class="amount-key">Loan amount</div>
                                <div class="amount-value">{{ item.amount }} (₹)</div>
                            </div>
                            <div class="loan-right">
                                <div class="amount-key">Loan Period</div>
                                <div class="amount-value">{{ item.term }}({{ item.termUnit }})</div>
                            </div>
                        </div>
                        <div class="loan-btn" @click="isnone = !isnone">Loan details</div>
                        <div :class="{ aaa: !isnone }">
                            <hr>
                            <div class="one-key">Loan amount:</div>
                            <div class="one-value">{{ item.amount }}</div>
                            <div class="one-key">Daily interest rate:</div>
                            <div class="one-value">{{ toPercent(item.dayRate) }}</div>
                            <div class="one-key">Loan Date:</div>
                            <div class="one-value">{{ new Date(item.created).toLocaleDateString() }}</div>
                            <div class="one-key">Total interest and service fee:</div>
                            <div class="one-value">{{ (item.serviceAmount + item.amount * item.term *
                                item.dayRate).toFixed(2)
                            }}</div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
        <!-- 手续费试算 -->
        <van-dialog v-model="show" :showCancelButton="false" :showConfirmButton="false">
            <ProductDialog @closeDialog="show = false" :sxfList="sxfList"></ProductDialog>
        </van-dialog>
        <!-- 底部按钮 -->
        <div class="btn" @click="doSubmit">Submit</div>
    </div>
</template>
<script>
import { getOrderListAPI, getOrderPayAPI } from '../../api'
import { add, unt } from "../../utils/AES.js";
import ProductDialog from './productDialog'
import { Toast } from 'vant'
export default {
    components: { ProductDialog },
    data() {
        return {
            list: [],
            item: '',//选中的商品
            checked: '',//选中的index
            id: '',
            isnone: true,
            show: false,
            sxfList: []
        }
    },
    methods: {
        //
        doSubmit() {
            if (this.checked === '') {
                Toast('Please tick the product')
                return
            }
            this.getSxf()
            this.getSX()
            setTimeout(() => {
                this.show = true
            }, 1000);
        },
        //获取产品试算
        async getSX() {
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
        },
        //获取产品列表
        async getList() {
            const f = {
                query: {}
            }
            const res = await getOrderListAPI(add(f))
            console.log(unt(res.data))
            this.list = unt(res.data).page.content
            console.log(this.list)
        },
        //点击产品选中
        doClick(item, index) {
            this.item = item
            this.checked = index
            //将商品id存入vuex
            this.$store.commit('setProductId', this.item.id)
        },
        //产品手续费试算
        async getSxf() {
            const f = {
                model: {
                    productIds: []
                }
            }
            f.model.productIds.push(this.$store.state.productId)
            console.log(f)
            const res = await getOrderPayAPI(add(f))
            console.log(unt(res.data), '手粗费')
        },
        icon(icon) {
            return `https://app.buddymoney.xyz/lt-image/resize/0x0/${icon}`
        },
        toPercent(point) {
            let str = Number(point * 100).toFixed(2);
            str += "%";
            return str;
        },
        doChecked() {
            this.isnone = !this.isnone
            console.log(123)
        }
    },
    created() {
        this.getList()
    }
}
</script>
<style lang="less" scoped>
.productList {
    width: 100vw;
    background-color: #f5f5f5;
    padding-top: (70/@a);
    padding-bottom: (100/@a);

    .aaa {
        display: none;
        transition: all 0.5s;
    }

    .isrouter {
        transform: rotate(270deg) !important;
    }

    .list {
        .active {
            border: (2px) solid #1e005a;
        }

        li {
            width: 100vw;
            // height: (402/@a);
            background: #FFFFFF;
            box-shadow: 0 (2/@a) (7/@a) rgba(0, 0, 0, 0.16);
            margin-top: (21/@a);
            display: flex;
            justify-content: start;
            border: (2px) solid transparent;
            padding-bottom: (20/@a);

            .list-l {
                transform: rotate(180deg);
                width: (30/@a);
                height: (30/@a);
                background: #1E005A;
                border-radius: 50%;
                margin-top: (108/@a);
                margin-left: (18/@a);
            }

            .list-r {
                display: flex;
                flex-direction: column;
                margin-left: (10/@a);
                margin-top: (10/@a);


                .list-r-top {
                    display: flex;
                    margin-left: (40/@a);
                    margin-bottom: (10/@a);

                    .list-r-top-img {
                        width: (34/@a);
                        height: (34/@a);

                        img {
                            width: 100%;
                        }
                    }

                    .list-r-top-name {
                        font-size: (20/@a);
                        font-family: Source Han Sans CN;
                        font-weight: 400;
                        color: #4D4D4D;
                        margin-left: (9/@a);
                    }
                }

                .list-r-box {
                    width: (295/@a);
                    // height: (335/@a);
                    background: #EEEEEE;
                    opacity: 1;
                    padding-left: (14/@a);
                    padding-right: (9/@a);
                    padding-bottom: (10/@a);

                    .loan {
                        display: flex;
                        justify-content: space-between;

                        .amount-key {
                            font-size: (16/@a);
                            font-family: Source Han Sans CN;
                            font-weight: 400;
                            color: #252525;
                            margin-top: (16/@a);
                            margin-left: (11/@a);
                        }

                        .amount-value {
                            width: (119/@a);
                            height: (26/@a);
                            background: #E0E0E0;
                            border-radius: (7/@a);
                            font-size: (16/@a);
                            text-align: center;
                            line-height: (26/@a);
                            font-family: Source Han Sans CN;
                            font-weight: 400;
                            color: #252525;
                            margin-top: (10/@a);
                        }
                    }

                    .loan-btn {
                        width: (164/@a);
                        height: (30/@a);
                        background: linear-gradient(180deg, #8B70C0 0%, #1E005A 100%);
                        border-radius: (5/@a);
                        font-size: (21/@a);
                        font-family: Source Han Sans CN;
                        font-weight: 400;
                        color: #FFFFFF;
                        text-align: center;
                        line-height: (30/@a);
                        margin-top: (12/@a);
                        margin-left: (52/@a);
                        margin-bottom: (12/@a);
                    }

                    .one-key {
                        margin-top: (6/@a);
                        font-size: (13/@a);
                        font-family: Source Han Sans CN;
                        font-weight: 400;
                        color: #252525;
                    }

                    .one-value {
                        width: (267/@a);
                        height: (22/@a);
                        background: #FFFFFF;
                        border-radius: (11/@a);
                        padding-top: (3/@a);
                        padding-left: (10/@a);
                        font-size: (13/@a);
                        font-family: Source Han Sans CN;
                        font-weight: 400;
                        color: #252525;
                    }
                }
            }
        }
    }

    .btn {
        position: fixed;
        bottom: 0;
        left: 0;
        width: 100vw;
        height: (49/@a);
        background: #1E005A;
        font-size: (26/@a);
        font-family: Source Han Sans CN;
        font-weight: 400;
        text-align: center;
        line-height: (49/@a);
        color: #FFFFFF;
    }

    .top {
        width: 100vw;
        height: (56/@a);
        background: #fff;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-left: (8/@a);
        padding-right: (12/@a);
        margin-bottom: (21/@a);

        .top-desc {
            font-size: (18/@a);
            font-family: Source Han Sans CN;
            font-weight: 400;
            line-height: (10/@a);
            color: #000000;
        }

        .top-view {
            width: (41/@a);
            height: (41/@a);
            background: #1E005A;
            border-radius: 50%;
            font-size: (15/@a);
            font-family: Source Han Sans CN;
            font-weight: 400;
            text-align: center;
            line-height: (41/@a);
            color: #FFFFFF;
        }
    }
}
</style>