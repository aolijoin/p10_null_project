import Vue from 'vue'
import VueRouter from 'vue-router'
import bao from '@/components/bao.vue'
import bar from '@/components/echarts/bar.vue'
import pie from "@/components/echarts/pie.vue";
import Home from "@/views/Home.vue";
import Login from "@/views/Login.vue";
import Table from "@/views/Table.vue";

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
        path: '/bao',
        name: 'bao',
        component: bao
    }, {
        path: '/login',
        name: 'login',
        component: Login
    }, {
        path: '/table',
        name: 'Table',
        component: Table
    },
    {
        path: '/',
        name: 'home',
        component: Home
    },

]

const router = new VueRouter({
    routes
})

export default router
