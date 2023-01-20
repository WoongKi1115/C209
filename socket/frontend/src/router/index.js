import Vue from 'vue'
import Router from 'vue-router'
import IndexPage from "@/components/IndexPage"

Vue.use(Router)

export default new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      name: 'IndexPage',
      component: IndexPage
    }
  ]
})
