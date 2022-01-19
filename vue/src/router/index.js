import {createRouter, createWebHistory} from 'vue-router'
import User from '../views/User.vue'
import Login from '../views/Login'
import Layout from '../layout/Layout'
import Register from '../views/Register'
import Person from '../views/Person'
import Book from '../views/Book'

const routes = [
    {
        path: '/',
        name: 'Layout',
        component: Layout,
        redirect: '/user',
        children: [
            {
                path: '/user',
                name: User,
                component: User
            },
            {
                path: '/person',
                name: Person,
                component: Person
            },
            {
                path: '/book',
                name: Book,
                component: Book
            },
        ]
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },


]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})


export default router
