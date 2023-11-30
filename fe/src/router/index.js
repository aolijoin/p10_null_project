import Vue from 'vue'
import VueRouter from 'vue-router'
import goods from '@/components/goods.vue'
import bar from '@/components/echarts/bar.vue'
import pie from "@/components/echarts/pie.vue";
import order from "@/components/order.vue";
import over from "@/components/over.vue";
import cart from "@/components/cart.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'goods',
        component: goods
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
        path: '/order',
        name: 'order',
        component: order
    },{
        path: '/over',
        name: 'over',
        component: over
    },{
        path: '/cart',
        name: 'cart',
        component: cart
    },

]

const router = new VueRouter({
    routes
})

export default router
