import { createRouter, createWebHistory } from 'vue-router'
import store from './store';
import Login from './components/SignIn.vue';
import SignUp from './components/SignUp.vue';
import Chat from './components/ChatUsers.vue';

const routes = [
    {
        path: '/',
        redirect: '/signin', // перенаправление на /dashboard при попытке зайти на /
    },
    {
        path: '/signin',
        component: Login, // перенаправление на /dashboard при попытке зайти на /
    },

    {
        path: '/signup',
        component: SignUp, // перенаправление на /dashboard при попытке зайти на /
    },
    {
        path: '/chat',
        component: Chat, // перенаправление на /dashboard при попытке зайти на /
    },



]

const router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!store.getters.isAuthenticated) {
            next('/signin')
        } else {
            next()
        }
    } else {
        next()
    }
})

export default router