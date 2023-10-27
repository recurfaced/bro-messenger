import { createRouter, createWebHistory } from 'vue-router'
import store from './store';
import Login from './components/SignIn.vue';
import SignUp from './components/SignUp.vue';
import Chat from './components/ChatUsers.vue';
import Main from './components/mainPage.vue'
import ListFriends from "@/components/Friends.vue";

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
    {
        path: '/main',
        component: Main, // перенаправление на /dashboard при попытке зайти на /
    },
    {
        path: '/mainPage/:userId', // Этот путь ожидает параметр userId
        name: 'mainPage', // Имя маршрута
        component: Main, // Компонент, который будет отображаться
        props: true, // Позволяет передавать параметры как props в компонент
    },
    {
        path: '/friends/:userId', // Этот путь ожидает параметр userId
        name: "UserFriendsList",
        component: ListFriends, // Компонент, который будет отображаться
        props: true,
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