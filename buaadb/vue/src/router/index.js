import Vue from 'vue'
import VueRouter from 'vue-router'
import ManagerView from '../views/ManagerView.vue'
import StudentCourseChosenView from "@/views/StudentCourseChosenView.vue";
import StudentView from "@/views/StudentView.vue";
import StudentCourseView from "@/views/StudentCourseView.vue";
import TeacherView from "@/views/TeacherView.vue";
import TeacherCourseWaitView from "@/views/TeacherCourseWaitView.vue";
import TeacherCourseOpenView from "@/views/TeacherCourseOpenView.vue";
import TeacherCourseView from "@/views/TeacherCourseView.vue";
import login from "@/views/Login.vue";
import ManagerWorkView from "@/views/ManagerWorkView.vue";
import ManagerCourseView from "@/views/ManagerCourseView.vue";
import ManagerManageAllView from "@/views/ManagerManageAllView.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/student',
        name: 'student',
        component: StudentView,
        children: [
            {path: "course", name: "studentCourse", component: StudentCourseView},
            {path: "courseChosen", name: "studentCourseChosen", component: StudentCourseChosenView},

        ]
    },
    {
        path: '/teacher',
        name: 'teacher',
        component: TeacherView,
        children: [
            {path: "course", name: "teacherCourse", component: TeacherCourseView},
            {path: "courseWait", name: "teacherCourseWait", component: TeacherCourseWaitView},
            {path: "courseOpen", name: "teacherCourseOpen", component: TeacherCourseOpenView},
        ]
    },
    {
        path: '/manager',
        name: 'manager',
        component: ManagerView,
        children: [
            {path: "course", name: "ManagerCourse", component: ManagerCourseView},
            {path: "work", name: "ManagerWork", component: ManagerWorkView},
            {path: "manageAll", name: "ManagerManageAll", component: ManagerManageAllView},
        ]
    },
    {
        path: '/login',
        name: 'login',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: login
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
