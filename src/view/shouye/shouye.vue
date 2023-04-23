<template>
    <div class="shouye">
        <div class="bgimg">
            <img src="../../assets/shouye.png" alt="">
        </div>
        <div class="apply" @click="doApply">APPLY NOW</div>
        <div class="sy-nav">
            <div class="sy-l">
                <img src="../../assets/home-a.png" alt="">
            </div>
            <div class="sy-r" @click="$router.push('/me')">
                <img src="../../assets/user.png" alt="">
            </div>
        </div>
    </div>
</template>
<script>
export default {
    methods: {
        //去贷款
        doApply() { }
    }
}
</script>
<style lang="less" scoped>
.shouye {
    width: 100vw;
    padding-bottom: (65/@a);
    padding-top: (10/@a);



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

    .bgimg {
        width: (375/@a);
        height: (667/@a);

        img {
            width: 100%;
        }
    }

    .apply {
        position: absolute;
        top: (340/@a);
        left: (5/@a);
        z-index: 1;
        width: (359/@a);
        height: (52/@a);
        background: linear-gradient(180deg, #8B70C0 0%, #1E005A 100%);
        opacity: 1;
        border-radius: (10/@a);
        font-size: (31/@a);
        font-family: Source Han Sans CN;
        font-weight: bold;
        text-align: center;
        color: #FFFFFF;
    }
}
</style>