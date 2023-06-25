import { createApp } from 'vue'
import App from './App.vue'

import router from "../script/routes/router";
import {createPinia} from "pinia";




createApp(App)
    .use(createPinia())
    .use(router)
    .mount('#app')
