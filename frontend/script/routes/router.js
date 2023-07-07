import {createRouter, createWebHistory} from 'vue-router/dist/vue-router'
import loginService from "../LoginService";

const routes = [
    {
        path: '/channel/',
        name: 'baseURL',
        redirect: '/channel/lobby'
    },{
        path: '/',
        name: 'baseURL',
        redirect: '/channel/lobby'
    },
    {
        path: '/login',
        name: 'login',
        component: () => import(/*webpackChunkName: "login", webpackPrefetch: true */ "@/Pages/Login.vue"),
        meta: {requiresAuth: false}
    },
    {
        path: '/join',
        name: 'join',
        component: () => import(/*webpackChunkName: "join", webpackPrefetch: true */ "@/Pages/Join.vue")
    },
    {
        path: '/channel/**',
        name: 'main',
        component: () => import(/*webpackChunkName: "main", webpackPrefetch: true */ "@/Pages/Main.vue"),
        meta: {requiresAuth: true}
    },
    {
        path:'/channel/:channel_title',
        name: 'main',
        component: () => import(/*webpackChunkName: "main", webpackPrefetch: true */ "@/Pages/Main.vue"),
        meta: {requiresAuth: true}
    },
    {
        path: '/channel/:channel_title/chat/room/enter/:roomId',
        name: 'ChannelChat',
        component: () => import("@/Pages/Main.vue"),
        meta: {requiresAuth: true},
        props: true
    },
    {
        path: '/channel/friend/:memberUID',
        name: 'FriendLobby',
        component: () => import(/*webpackChunkName: "main", webpackPrefetch: true */ "@/Pages/Main.vue"),
        meta: {requiresAuth: true}

    },
    {


    }
]
const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach(async (to, from, next) => {
    const isLogin = await loginService.initCheck();
    if (to.meta.requiresAuth && !isLogin) {
        next('/login')
    } else if (!to.meta.requiresAuth && isLogin) {
        next('/channel/lobby')
    } else{
        next();
    }
    console.log("beforeEach")
})

export default router;