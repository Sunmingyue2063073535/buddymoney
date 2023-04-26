<template>
    <div class="formTopDesc">
        <div class="callback" @click="$router.push('/shouye')">
            <img src="../assets/callback.png" alt="">
        </div>
        <div class="desc">{{ desc }}</div>
        <!-- <div class="info" v-if="info">{{ info }}</div> -->
        <div class="info">{{ info }}</div>
    </div>
</template>

<script>
export default {
    name: 'formTopDesc',
    props: { desc: String, info: String }
}
</script>

<style lang="less" scoped>
.formTopDesc {
    width: 100vw;
    height: (70/@a);
    position: fixed;
    top: 0;
    z-index: 99;
    background-color: #1e005a;
    width: 100vw;
    text-align: center;
    padding-bottom: (8/@a);


    .info {
        margin-top: (20/@a);
        font-size: (18/@a);
        font-family: Source Han Sans CN;
        font-weight: 500;
        color: #fff;
    }

    .desc {
        font-size: (18/@a);
        font-family: Source Han Sans CN;
        font-weight: 500;
        color: #fff;
    }

    .callback {
        line-height: (80/@a);
        width: (25/@a);
        height: (25/@a);
        margin-left: (22/@a);

        img {
            width: 100%;
        }
    }
}
</style>