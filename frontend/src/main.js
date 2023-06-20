import { createApp } from 'vue'
import App from './App.vue'
import store from "../script/store";
import router from "../script/router";



createApp(App)
    .use(store)
    .use(router)
    .mount('#app')
