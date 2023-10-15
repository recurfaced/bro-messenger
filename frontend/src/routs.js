import { createRouter, createWebHistory } from 'vue-router'
import store from './store';
import Login from './components/SignIn.vue';
import SignUp from './components/SignUp.vue';
import User from './components/User.vue';


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
        path: '/user',
        component: User, // перенаправление на /dashboard при попытке зайти на /
    },

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

// Добавляем маршрут перенаправления для неавторизованных пользователей
router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!store.getters.isAuthenticated) { // Проверяем статус авторизации в Vuex
            next('/signin') // Перенаправляем на страницу входа
        } else {
            next()
        }
    } else {
        next()
    }
})

export default router