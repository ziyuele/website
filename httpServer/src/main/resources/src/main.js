import Vue from 'vue';
import VueRouter from 'vue-router';
import Index from './index.vue';
import WaterfallEasy from 'vue-waterfall-easy';
// element ui support
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';

import PDFS from './pdfs.vue';
import HOME from './home.vue';
import FILE from './file.vue';
import BLOGS from './blogs.vue';
import MARKDOWNEDITOR from './writeMarkDown.vue';
import INFO from './infos.vue';

const vueRouter = [
    { path: '/', component: HOME },
    { path:'/v1/pdfs', component: PDFS },
    { path: '/v1/files', component: FILE},
    { path: '/v1/blogs', component: BLOGS},
    { path: '/v1/writeMarkDown',component: MARKDOWNEDITOR },
    { path: '/v1/personal',component: INFO }
];



Vue.use(mavonEditor);
Vue.use(ElementUI);
Vue.use(VueRouter);
Vue.use(WaterfallEasy);

const router = new VueRouter({
    routes: vueRouter
});

var app = new Vue({
  el: '#web-site',
  router: router,
  render: h => h(Index)
});
