import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate"
import form from './modules/form'
import xieyi from './modules/xieyi'
Vue.use(Vuex);

export default new Vuex.Store({
    namespaced: true,
    plugins: [createPersistedState()],
    state: {
        isLogin: false, //是否登录
        count: 0, //我的订单上面的状态
        isShow: false, //loadding
        userInfo: {}, //用户信息
        //ocr识别的结果
        ocrForm: {},
        //ocr照片的url
        ocrUrl: {},
        //选中贷款的id
        productId: '',
        //订单id
        orderId: '',
        //还款信息
        orderInfo: {},
        isupload: {}
    },
    getters: {},
    mutations: {
        //ocr图片
        setIsupload(state, payload) {
            state.isupload = payload
        },
        //设置还款产品信息
        setOrderInfo(state, payload) {
            state.orderInfo = payload
        },
        //设置订单id
        setOrderId(state, payload) {
            state.orderId = payload
        },
        //设置贷款的id
        setProductId(state, payload) {
            state.productId = payload
        },
        //设置ocr图片
        setOCRUrl(state, payload) {
            state.ocrUrl = payload
        },
        //设置ocr表单数据
        setocrForm(state, payload) {
            state.ocrForm = payload
        },
        //存入用户信息
        setUserInfo(state, payload) {
            state.userInfo = payload
        },
        //删除用户信息
        clearUserInfo(state) {
            state.userInfo = {}
        },
        //change title count
        changeCount(state, payload) {
            state.count = payload
        },
        // 登录或退出
        changeLogin(state, payload) {
            state.isLogin = payload
        },
        // 展示loadding方法
        showLoading(state) {
            state.isShow = true
        },
        // 影藏loadding方法
        hideLoading(state) {
            state.isShow = false
        },
    },
    actions: {},
    modules: { form, xieyi, },
});