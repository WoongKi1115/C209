import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/icebreaking/HomeView.vue'
import LoginView from '../views/user/LoginView.vue'
import SignupView from '../views/user/SignupView.vue'
import IceMainView from '../views/icebreaking/IceMainView.vue'
import GameMainView from '../views/game/GameMainView.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginView
  },
  {
    path: '/signup',
    name: 'Signup',
    component: SignupView
  },
  {
    path: '/icemain',
    name: 'IceMain',
    component: IceMainView
  },
  {
    path: '/gamemain',
    name: 'GameMain',
    component: GameMainView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
