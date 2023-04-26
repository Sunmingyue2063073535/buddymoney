<template>
  <div id="app">
    <router-view></router-view>
    <div class="loading" v-if="$store.state.isShow">
      <img src="./assets/loading.gif" alt="">
    </div>
  </div>
</template>
<script>
export default {
  name: 'App'
}
</script>
<style lang="less" scoped>
.loading {
  padding-top: (150/@a);
  z-index: 9999;
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: #f5f5f5;
  opacity: 0.7;

  img {
    width: 100%;
  }
}
</style>