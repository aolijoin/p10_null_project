import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/Home.vue'
import bar from '@/components/bar.vue'
import pie from "@/components/pie.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
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
    },

]

const router = new VueRouter({
    routes
})

export default router
