import { createApp } from 'vue'
import App from './App.vue'
import routs from "./routs.js";
import store from "./store.js";
import axios from 'axios';

axios.defaults.withCredentials = true;

createApp(App).use(routs).use(store).mount('#app')
