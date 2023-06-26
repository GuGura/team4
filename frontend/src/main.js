import { createApp } from 'vue'
import App from './App.vue'
import router from "../script/router";
import {createPinia} from "pinia";
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

createApp(App)
    .use(createPinia())
    .use(router)
    .mount('#app')
