import { createApp } from 'vue'
import App from './App.vue'
import store from "../script/store";
import router from "../script/router";
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'



createApp(App)
    .use(store)
    .use(router)
    .mount('#app')
