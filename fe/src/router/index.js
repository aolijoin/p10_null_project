import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/Home.vue'
import bar from '@/components/echarts/bar.vue'
import pie from "@/components/echarts/pie.vue";

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
        name: 'home',
        component: Home
    },

]

const router = new VueRouter({
    routes
})

export default router
