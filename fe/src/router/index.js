import Vue from 'vue'
import VueRouter from 'vue-router'
import bar from '@/components/echarts/bar.vue'
import pie from "@/components/echarts/pie.vue";
import GoodsList from "@/components/GoodsList.vue";
import GoodsAdd from "@/components/GoodsAdd.vue";

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
    },  {
        path: '/',
        name: 'GoodsList',
        component: GoodsList
    },
    {
        path: '/GoodsAdd',
        name: 'GoodsAdd',
        component: GoodsAdd
    }

]

const router = new VueRouter({
    routes
})

export default router
