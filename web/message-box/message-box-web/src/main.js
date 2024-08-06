import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueRouter from "vue-router";
import routes from './config/route';
import apiRequest from './common/net'
import store from "./store/store";
import msgShow from "./common/msgShow"
import qs from "qs"
import urlInfo from "./config/apiRoute"
import config from "./config/config"

Vue.use(ElementUI);
Vue.use(VueRouter);

Vue.prototype.apiRequest = apiRequest;
Vue.prototype.msgShow = msgShow;
Vue.prototype.qs = qs;
Vue.prototype.url = urlInfo;
Vue.prototype.config = config;

Vue.config.productionTip = false;

const router = new VueRouter({
    routes
});

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
};

new Vue({
    router,
    render: h => h(App),
    store
}).$mount('#app');