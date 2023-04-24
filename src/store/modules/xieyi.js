export default {
    state: {
        yinsiActive: true,
        quanxianActive: true,
        xieyiCount: 2
    },
    getters: {

    },
    mutations: {
        changeyinsi(state) {
            state.yinsiActive = !state.yinsiActive
        },
        changequanxian(state) {
            state.quanxianActive = !state.quanxianActive
        },
        //选中协议后加一
        addxieyi(state) {
            state.xieyiCount += 1
        },
        untxieyi(state) {
            state.xieyiCount -= 1
        }
    },
    actions: {},
    namespaced: true,
}