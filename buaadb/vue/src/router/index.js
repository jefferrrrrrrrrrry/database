import Vue from 'vue'
import VueRouter from 'vue-router'
import ManagerView from '../views/ManagerView.vue'
import StudentCourseChosenView from "@/views/StudentCourseChosenView.vue";
import StudentView from "@/views/StudentView.vue";
import StudentCourseView from "@/views/StudentCourseView.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/student',
        name: 'student',
        component: StudentView,
        children: [
            {path: "courseChosen", name: "studentCourseChosen", component: StudentCourseChosenView},
            {path: "course", name: "studentCourse", component: StudentCourseView}
        ]
    },
    {
        path: '/manager',
        name: 'manager',
        component: ManagerView
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
