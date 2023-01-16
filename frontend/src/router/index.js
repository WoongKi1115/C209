import { createRouter, createWebHistory } from "vue-router";

import Home from '../views/icebreaking/HomeView.vue'
import Login from '../views/user/LoginView.vue'
import Signup from '../views/user/SignupView.vue'

const routes = [
    { path: '/', component:Home},
    { path: '/login', component:Login},
    { path: '/signup', component:Signup},
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export {router}