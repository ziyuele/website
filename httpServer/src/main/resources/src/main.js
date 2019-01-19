import Vue from 'vue';
import Index from './index.vue';
// element ui support
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';


Vue.use(ElementUI);

var app = new Vue({
  el: '#web-site',
  render: h => h(Index)
})
