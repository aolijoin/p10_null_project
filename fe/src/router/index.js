import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/Home.vue'
import bar from '@/components/bar.vue'
import pie from "@/components/pie.vue";
import p10 from "@/views/p10.vue";
import p10notify from "@/views/p10notify.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/p10',
        name: 'p10',
        component: p10
    }, {
        path: '/',
        name: 'p10',
        component: p10
    },
    {
        path: '/p10notify',
        name: 'p10notify',
        component: p10notify
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
