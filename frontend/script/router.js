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
    const isLogin = await loginService.initCheck();
    console.log(isLogin)
    console.log(to.meta.requiresAuth)
    if (to.meta.requiresAuth && !isLogin) {
        next('/login')
    } else if (!to.meta.requiresAuth && isLogin) {
        next('/')
    } else {
        axios.post(process.env.VUE_APP_BASEURL_V1+"/myInfo/init",null,{headers: authHeader()})
            .then(({data})=>{
                console.log(data.result)
                store.commit('setUser',data.result)
            })
        next()
    }
})

export default router;