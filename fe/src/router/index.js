import Vue from 'vue'
import VueRouter from 'vue-router'
import p10 from '../views/user/p10.vue';
import p10notify from '../views/user/p10-notify.vue';

Vue.use(VueRouter)

const routes = [
  
  {
    path: '/p10',
    name: 'p10',
    component: p10
  },{
    path: '/',
    name: 'p10',
    component: p10
  },
  {
    path: '/p10notify',
    name: 'p10notify',
    component: p10notify
  }
]

const router = new VueRouter({
  routes
})

export default router