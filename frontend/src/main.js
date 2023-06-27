import { createApp } from 'vue'
import App from './App.vue'

import {createPinia} from "pinia";
import router from "../script/routes/router";
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'




createApp(App)
    .use(createPinia())
    .use(router)
    .mount('#app');
