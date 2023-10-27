import { createStore } from "vuex"
const store = createStore({
    state: {
        isAuthenticated: false, // Изначально пользователь не авторизован
    },
    mutations: {
        setAuthenticated(state, isAuthenticated) {
            state.isAuthenticated = isAuthenticated
        },
        setUserId(state, userId) {
            state.userId = userId;
        },
    },
    actions: {
        login({ commit }) {
            // Здесь должна быть логика авторизации, которая устанавливает isAuthenticated в true
            commit('setAuthenticated', true)
        },
        logout({ commit }) {
            // Здесь должна быть логика выхода из системы, которая устанавливает isAuthenticated в false
            commit('setAuthenticated', false)
        },
    },
    getters: {
        isAuthenticated: state => state.isAuthenticated,
    },
})
export default store