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
        redirect: '/signin',
    },
    {
        path: '/signin',
        component: Login,
    },

    {
        path: '/signup',
        component: SignUp,
    },
    {
        path: '/chat-user',
        name: 'chat-user',
        component: Chat,
    },
    {
        path: '/main',
        component: Main,
    },
    {
        path: '/mainPage',
        name: 'mainPage',
        component: Main,
    },
    {
        path: '/friends',
        name: "UserFriendsList",
        component: ListFriends,
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