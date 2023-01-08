import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import BlogDetail from '../views/BlogDetail.vue'
import BlogEdit from '../views/BlogEdit.vue'
import Blogs from '../views/Blogs.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Index',
      redirect: '/blogs'
    },{
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/blogs',
      name: 'Blogs',
      //懒加载
      component: () => import('../views/Blogs.vue')
    },
    {
      // 注意放在 path: '/blog/:blogId'之前
      // 带有meta：requireAuth: true说明是需要登录字后才能访问的受限资源，后面我们路由权限拦截时候会用到。
      path: '/blog/add', // 注意放在 path: '/blog/:blogId'之前
      name: 'BlogAdd',
      meta: {
        requireAuth: true
      },
      component: BlogEdit
    },
    {
      path: '/blog/:blogId',
      name: 'BlogDetail',
      component: BlogDetail
    },
    {
      path: '/blog/:blogId/edit',
      name: 'BlogEdit',
      meta: {
        requireAuth: true
      },
      component: BlogEdit
    }
  ]
})

export default router



