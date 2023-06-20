import {createRouter, createWebHistory} from 'vue-router/dist/vue-router'

const routes = [
    {
        path: '/login',
        name: 'login',
        component: () => import(/*webpackChunkName: "login", webpackPrefetch: true */ "@/Pages/Login")
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
        meta: {roles: ["USER_ROLE"]}
    }
]
const router = createRouter({
    history: createWebHistory(),
    routes
});


export default router;