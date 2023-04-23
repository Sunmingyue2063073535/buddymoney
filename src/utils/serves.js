// 导出一个axios的实例  而且这个实例要有请求拦截器 响应拦截器
import axios from 'axios'
import store from '../store'
import router from '../router'
import { Toast } from "vant";
// 创建一个axios的实例
const service = axios.create({
    // 设置基地址
    // baseURL: 'https://app.buddymoney.xyz',
    withCredentials: true,
    timeout: 60000,
})
service.defaults.headers["Content-Type"] = "application/json";

// 请求拦截器
service.interceptors.request.use(
    async(config) => {
        const token = 'VIATQQJM27dc454fbb637f4b7abc6cf8a788e481fb694f05eba80875e339fd45532da4b2c032e6df809cf7b877ee6a04ccc79cbf4d1b2694e1968c3b0573baa0b1b09f957a1bbebab20b920f73952e955797aac1af94d3c9eefbc8980050a7212914c41deeecee8f00d206f293ff2ad82e98f547'
        config.headers.Token = token
        return config
    },
    (error) => {
        return Promise.reject(error)
    }
)

// 响应拦截器
service.interceptors.response.use(
    (response) => {
        //判断登录失效
        if (response.data.status === 1012) {
            // 跳到登录页
            router.push('/login')
                // 提示登录
            Toast('Please log in first')
        }
        //对请求错误的结果进行拦截
        try {
            if (response.data.status && response.data.status !== 0) {

                return Toast(response.data.message)
            }
        } catch (error) {

        }
        return response
    },
    (error) => {
        return Promise.reject(error)
    }
)

export default service // 导出axios实例