import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/components/Login.vue'
import bar from '@/components/echarts/bar.vue'
import pie from "@/components/echarts/pie.vue";
import goods from "@/components/Goods.vue";
import apply from "@/components/Apply.vue";
Vue.use(VueRouter)

const routes = [
    {
        path: '/bar',
        name: 'bar',
        component: bar
    }, {
        path: '/pie',
        name: 'pie',
        component: pie
    }, {
        path: '/',
        name: 'login',
        component: Login
    },{
        path: '/goods',
        name: 'goods',
        component: goods
    },{
        path: '/apply',
        name: 'apply',
        component: apply
    },

]

const router = new VueRouter({
    routes
})

export default router
