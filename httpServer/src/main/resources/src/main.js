import Vue from 'vue';
import VueRouter from 'vue-router';
import Index from './index.vue';
// element ui support
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import Module from './module.vue';


const vueRouter = [
    { path:'/test', component: Module  }
]


Vue.use(ElementUI);
Vue.use(VueRouter);

const router = new VueRouter({
    routes: vueRouter
})

var app = new Vue({
  el: '#web-site',
  router: router,
  render: h => h(Index)
})
