import login from '@/view/login'
import Shouye from '@/view/shouye/shouye.vue'
import me from '@/view/shouye/me.vue'
import beforeys from '@/view/before/beforeys.vue'
import beforeqx from '@/view/before/beforeqx.vue'
import ocr from '@/view/form/ocr.vue'
import huoti from '@/view/form/huoti.vue'
import baseForm from '@/view/form/baseForm.vue'
import productList from '../view/product/productList.vue'
import loan from '../view/product/loan.vue'
import kefuList from '../view/kefu/kefuList.vue'
import kefuForm from '../view/kefu/kefuForm.vue'
const routes = [
    { path: '/', component: login },
    { path: '/login', component: login },
    { path: '/shouye', component: Shouye },
    { path: '/me', component: me },
    { path: '/beforeys', component: beforeys },
    { path: '/beforeqx', component: beforeqx },
    { path: '/ocr', component: ocr },
    { path: '/huoti', component: huoti },
    { path: '/baseForm', component: baseForm },
    { path: '/productList', component: productList },
    { path: '/loan', component: loan },
    { path: '/kefuList', component: kefuList },
    { path: '/kefuForm', component: kefuForm },
]

export default routes