<template>
    <div class="shouye">
        <!-- 底部导航栏 -->
        <div class="sy-nav">
            <div class="sy-l" @click="$router.push('/shouye')">
                <img src="../../assets/home.png" alt="">
            </div>
            <div class="sy-r" @click="$router.push('/me')">
                <img src="../../assets/user-a.png" alt="">
            </div>
        </div>
        <!-- 顶部背景 -->
        <div class="box">
            <img src="../../assets/topbeijin.png" alt="">
        </div>
        <!-- 用户 -->
        <div class="user">
            <div class="user-img">
                <img src="../../assets/me-user.png" alt="" v-if="!$store.state.isLogin">
                <img src="../../assets/me-user-a.png" alt="" v-else>
            </div>
            <div class="user-name" v-if="$store.state.isLogin">hello user</div>
            <div class="user-name-a" v-else>Please sign in</div>
            <div class="user-desc">Buddy Money makes your credit more valuable</div>
        </div>
        <!-- 列表 -->
        <ul class="tools">
            <li @click="goLoan">
                <div class="img">
                    <img src="../../assets/app-qianbao.png" alt="">
                </div>
                <div class="desc">My loan</div>
            </li>
            <li @click="doanquan">
                <div class="img">
                    <img src="../../assets/app-anquan.png" alt="">
                </div>
                <div class="desc">Security Protocol</div>
            </li>
            <li @click="doXinxi">
                <div class="img">
                    <img src="../../assets/app-info.png" alt="">
                </div>
                <div class="desc">About us</div>
            </li>
            <li @click="doUS">
                <div class="img">
                    <img src="../../assets/app-kefu.png" alt="">
                </div>
                <div class="desc">customer service</div>
            </li>
        </ul>
        <!-- 退出按钮 -->
        <div class="tuichuBtn" v-if="$store.state.isLogin" @click="doLogout">Log out</div>
        <!-- 登录按钮 -->
        <div class="tuichuBtn" v-else @click="doLogin">Log in</div>
        <!-- 协议弹窗 -->
        <van-dialog v-model="isAnquan" :show-cancel-button="false" :show-confirm-button="false">
            <anquan @anquanClose="anquanClose"></anquan>
        </van-dialog>
        <van-dialog v-model="isXinxi" :show-cancel-button="false" :show-confirm-button="false">
            <xinxi @xinxiClose="xinxiClose"></xinxi>
        </van-dialog>
    </div>
</template>
<script>
import anquan from './anquan'
import xinxi from './xinxi'
import { getEmail } from "../../utils/android.js";
import { Dialog, Toast } from "vant";
export default {
    components: { anquan, xinxi },
    data() {
        return {
            isAnquan: false,
            isXinxi: false
        }
    },
    methods: {
        //去订单页
        goLoan() {
            if (this.$store.state.isLogin) {
                this.$store.commit('changeCount', 0)
                this.$router.push('/loan')
            } else {
                Toast('please log in first')
                this.$router.push('/login')
            }
        },
        //安全协议
        doanquan() {
            this.isAnquan = true
        },
        //信息协议
        async doXinxi() {
            this.isXinxi = true
        },
        //联系我们
        doUS() {
            getEmail()
        },
        //关闭信息弹窗
        xinxiClose() {
            this.isXinxi = false
        },
        //关闭安全弹窗
        anquanClose() {
            this.isAnquan = false
        },
        //退出
        doLogout() {
            Dialog.confirm({
                message: 'Are you sure you want to quit?',
                confirmButtonText: 'Confirm',
                cancelButtonText: 'Cancel'
            }).then(() => {
                this.$store.commit('clearUserInfo')
                this.$store.commit('changeLogin', false)
                this.$router.push('/shouye')
                Toast('Exit Successfully')
            });
        },
        //登录
        doLogin() {
            this.$router.push('/login')
        }
    }
}
</script>
<style lang="less" scoped>
.shouye {
    width: 100vw;
    padding-bottom: (65/@a);
    padding-top: (10/@a);

    .tools {
        margin-top: (280/@a);
        margin-left: (8/@a);

        li {
            width: (359/@a);
            height: (44/@a);
            background: #F7F7F7;
            opacity: 1;
            border-radius: (5/@a);
            display: flex;
            margin-bottom: (20/@a);
            padding: (6/@a) (17/@a);
            align-self: center;

            .img {
                width: (31/@a);
                height: (31/@a);

                img {
                    width: 100%;
                }
            }

            .desc {
                margin-left: (16/@a);
                line-height: (32/@a);
                font-size: (20/@a);
            }
        }
    }

    .tuichuBtn {
        width: (274/@a);
        height: (52/@a);
        background: linear-gradient(180deg, #8B70C0 0%, #1E005A 100%);
        opacity: 1;
        border-radius: (10/@a);
        text-align: center;
        line-height: (45/@a);
        margin-left: (51/@a);
        font-size: (29/@a);
        font-family: Source Han Sans CN;
        font-weight: 500;
        color: #FFFFFF;
    }

    .user {
        position: absolute;
        z-index: 1;

        .user-img {
            margin-top: (30/@a);
            margin-left: (153/@a);
            width: (70/@a);
            height: (70/@a);

            img {
                width: 100%;
            }
        }

        .user-name {
            font-size: (35/@a);
            font-family: Source Han Sans CN;
            font-weight: 500;
            color: #fff;
            margin-left: (105/@a);
        }

        .user-name-a {
            font-size: (35/@a);
            font-family: Source Han Sans CN;
            font-weight: 500;
            color: #fff;
            margin-left: (75/@a);
        }

        .user-desc {
            font-size: (13/@a);
            font-family: Source Han Sans CN;
            font-weight: 400;
            color: #fff;
            margin-top: (14/@a);
            margin-left: (47/@a);
        }
    }


    .sy-nav {
        width: 100vw;
        height: (65/@a);
        background-color: #fff;
        position: fixed;
        bottom: 0;
        left: 0;
        display: flex;
        justify-content: space-around;
        align-items: center;

        .sy-l {
            width: (28/@a);
            height: (28/@a);

            img {
                width: 100%;
            }
        }

        .sy-r {
            width: (28/@a);
            height: (28/@a);

            img {
                width: 100%;
            }
        }
    }

    .box {
        position: absolute;
        top: 0;
        left: 0;
        width: 100vw;

        img {
            width: 100%;
        }
    }

}
</style>