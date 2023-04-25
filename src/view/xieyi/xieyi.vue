<template>
    <div class="xieyi">
        <yinsi v-if="$store.state.xieyi.xieyiCount === 2"></yinsi>
        <quanxian v-if="$store.state.xieyi.xieyiCount === 3"></quanxian>
    </div>
</template>
<script>
import quanxian from './quanxian'
import yinsi from './yinsi'
export default {
    components: { quanxian, yinsi },
    mounted() {
        if (this.$store.state.xieyi.xieyiCount === 4) {
            this.$router.push('/shouye')
        }
    },
}
</script>