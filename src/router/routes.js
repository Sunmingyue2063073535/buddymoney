import login from '@/view/login'
import Shouye from '@/view/shouye/shouye.vue'
import me from '@/view/shouye/me.vue'
const routes = [
    { path: '/', component: login },
    { path: '/shouye', component: Shouye },
    { path: '/me', component: me },
]

export default routes