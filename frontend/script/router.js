import {createRouter, createWebHistory} from 'vue-router/dist/vue-router'
import loginService from "./LoginService";

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
        console.log("!2")
    }else{
        next();
    }
})

export default router;