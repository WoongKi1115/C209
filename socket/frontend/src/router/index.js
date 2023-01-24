import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import IndexPage from '@/components/IndexPage'
import chat from '@/components/chat'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'IndexPage',
      component: IndexPage
    },
    {
      path: '/chat',
      name: 'chat',
      component: chat
    }
  ]
})
