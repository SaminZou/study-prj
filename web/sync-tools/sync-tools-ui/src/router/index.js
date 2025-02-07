import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL), routes: [{
        path: '/',
        name: 'sync',
        component: () => import('../components/SyncPage.vue')
    }]
})

export default router
