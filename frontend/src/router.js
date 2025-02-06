
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ProjectProjectManager from "./components/listers/ProjectProjectCards"
import ProjectProjectDetail from "./components/listers/ProjectProjectDetail"

import TaskTaskManager from "./components/listers/TaskTaskCards"
import TaskTaskDetail from "./components/listers/TaskTaskDetail"

import UserUserManager from "./components/listers/UserUserCards"
import UserUserDetail from "./components/listers/UserUserDetail"

import PdfPdfManager from "./components/listers/PdfPdfCards"
import PdfPdfDetail from "./components/listers/PdfPdfDetail"



import JwtJwtManager from "./components/listers/JwtJwtCards"
import JwtJwtDetail from "./components/listers/JwtJwtDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/projects/projects',
                name: 'ProjectProjectManager',
                component: ProjectProjectManager
            },
            {
                path: '/projects/projects/:id',
                name: 'ProjectProjectDetail',
                component: ProjectProjectDetail
            },

            {
                path: '/tasks/tasks',
                name: 'TaskTaskManager',
                component: TaskTaskManager
            },
            {
                path: '/tasks/tasks/:id',
                name: 'TaskTaskDetail',
                component: TaskTaskDetail
            },

            {
                path: '/users/users',
                name: 'UserUserManager',
                component: UserUserManager
            },
            {
                path: '/users/users/:id',
                name: 'UserUserDetail',
                component: UserUserDetail
            },

            {
                path: '/pdfs/pdfs',
                name: 'PdfPdfManager',
                component: PdfPdfManager
            },
            {
                path: '/pdfs/pdfs/:id',
                name: 'PdfPdfDetail',
                component: PdfPdfDetail
            },



            {
                path: '/jwts/jwts',
                name: 'JwtJwtManager',
                component: JwtJwtManager
            },
            {
                path: '/jwts/jwts/:id',
                name: 'JwtJwtDetail',
                component: JwtJwtDetail
            },



    ]
})
