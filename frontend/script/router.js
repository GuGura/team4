import {createRouter, createWebHistory} from 'vue-router/dist/vue-router'
import loginService from "./LoginService";
import store from "./store";
import axios from "axios";
import authHeader from "./authHeader";

const routes = [
    {
        path: '/login',
        name: 'login',
        component: () => import(/*webpackChunkName: "login", webpackPrefetch: true */ "@/Pages/Login"),
        meta: {requiresAuth: false}
    },
    {
        path: '/join',
        name: 'join',
        component: () => import(/*webpackChunkName: "join", webpackPrefetch: true */ "@/Pages/Join.vue")
    },
    {
        path: '/',
        name: 'main',
        component: () => import(/*webpackChunkName: "main", webpackPrefetch: true */ "@/Pages/Main.vue"),
        meta: {requiresAuth: true}
    },
]
const router = createRouter({
    history: createWebHistory(),
    routes
});
router.beforeEach(async (to, from, next) => {
    console.log("1")
    const isLogin = await loginService.initCheck();
    console.log(isLogin)
    if (to.meta.requiresAuth && !isLogin) {
        next('/login')
    } else if (!to.meta.requiresAuth && isLogin) {
        next('/')
    } else if(from.meta.requiresAuth && isLogin){
        axios.put(process.env.VUE_APP_BASEURL_V1 + "/myInfo/init")
            .then(({data}) => {
                store.commit('setUser', data.result)
            })
    }else{
        next();
    }
})

export default router;