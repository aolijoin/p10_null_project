import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/Home.vue'
import bar from '@/components/bar.vue'
import pie from "@/components/pie.vue";
import Login from "@/components/Login.vue";
import Borrow from "@/components/Borrow.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'login',
        component: Login
    },{
        path: '/home',
        name: 'home',
        component: Home
    },
    {
        path: '/bar',
        name: 'bar',
        component: bar
    }, {
        path: '/pie',
        name: 'pie',
        component: pie
    }, {
        path: '/borrow',
        name: 'borrow',
        component: Borrow
    },

]

const router = new VueRouter({
    routes
})

export default router
