<template>
    <div class="login">
        <div class="login-img">
            <img src="../../assets/login.png" alt="">
        </div>
        <div class="otp" @click="getCode" v-if="!isyzm">
            <img src="../../assets/opt.png" alt="">
        </div>
        <div class="djs" v-else>
            {{ num }} s
        </div>
        <van-form class="form">
            <van-field class="phone" type="tel" v-model="phone" name="用户名" placeholder="Phone number"
                :rules="[{ required: true }]" />
            <van-field class="code" type="digit" v-model="code" name="密码" placeholder="verification code"
                :rules="[{ required: true }]" />
            <van-button class="btn" @click="doSubmit">LOG IN</van-button>
        </van-form>
    </div>
</template>
<script>
import { add, unt } from "../../utils/AES.js";
import { getyzmApi, getLoginApi } from "../../api";
import { Toast, } from "vant";
export default {
    data() {
        return {
            phone: '',
            code: '',
            isyzm: false,
            num: 0
        };
    },
    methods: {
        //登录
        async doSubmit() {
            const f = {
                phone: this.phone,
                phoneCode: '+86',
                code: this.code
            }
            const res = await getLoginApi(add(f))
            console.log(unt(res.data))
            if (!unt(res.data).status) {
                //登录成功
                this.$store.commit('setUserInfo', unt(res.data).user)
                this.$router.push('/shouye')
                this.$store.commit('changeLogin', true)
                Toast('Login Successful')
            }
        },
        //获取验证码
        async getCode() {
            const f = {
                model: {
                    phone: this.phone,
                    phoneCode: '+86'
                }
            }
            if (f.model.phone) {
                const res = await getyzmApi(add(f))
                if (unt(res.data).status === 0) {
                    //发送成功
                    this.djs()
                    console.log(unt(res.data))
                }
            } else {
                Toast('Please enter phone number')
            }
        },
        //倒计时
        djs() {
            this.isyzm = true
            this.num = 60
            let timer = setInterval(() => {
                this.num--
                if (this.num < 0) {
                    this.isyzm = false
                    clearInterval(timer)
                }
            }, 1000)
        }
    },
};
</script>
<style lang="less" scoped>
.login {
    width: 100vw;
    min-height: (667/@a);

    .login-img {
        width: 100vw;
        min-height: (667/@a);

        img {
            width: 100vw;
            height: (667/@a);
        }
    }

    .djs {
        position: absolute;
        z-index: 1;
        top: (265/@a);
        right: (30/@a);
        color: #2e1069;
        font-size: (20/@a);
    }

    .otp {
        position: absolute;
        top: (255/@a);
        right: (10/@a);
        width: (105/@a);
        height: (47/@a);
        z-index: 1;

        img {
            width: 100%;
        }
    }

    .form {
        position: absolute;
        top: (255/@a);
        left: (5/@a);

        .btn {
            width: (330/@a);
            height: (52/@a);
            background: linear-gradient(270deg, #8B70C0 0%, #1E005A 100%);
            opacity: 1;
            border-radius: (10/@a);
            text-align: center;
            line-height: (52/@a);
            font-size: (42/@a);
            font-family: Source Han Sans CN;
            font-weight: 500;
            line-height: (11/@a);
            color: #FFFFFF;
            margin: (16/@a);
        }

        .phone {
            width: (359/@a);
            height: (47/@a);
            background: #F0EFEF;
            opacity: 1;
            border-radius: (8/@a);
        }

        .code {
            width: (359/@a);
            height: (47/@a);
            background: #F0EFEF;
            opacity: 1;
            border-radius: (8/@a);
            margin-top: (35/@a);
        }
    }
}
</style>