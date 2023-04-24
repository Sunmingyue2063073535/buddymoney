import { getwwcFormApi } from '../../api'
import { add, unt } from "../../utils/AES";

export default {
    state: {
        //未完成表单数据
        formData: [],
    },
    getters: {

    },
    mutations: {
        //设置未完成表单
        setFormData(state, payload) {
            state.formData = payload
        },
    },
    actions: {
        //获取一个未完成表单
        async weiwancehngform(content) {
            const form = {
                model: {
                    nodeType: ''
                }
            }
            const res = await getwwcFormApi(add(form))
            const result = unt(res.data)
            console.log('未完成的表单vuex', result)
            content.commit('setFormData', result.model.forms)
        }
    },
    namespaced: true,
}