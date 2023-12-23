import Vue from 'vue'
import VueRouter from 'vue-router'
import ManagerView from '../views/Manager/ManagerView.vue'
import StudentCourseChosenView from "@/views/Student/StudentCourseChosenView.vue";
import StudentView from "@/views/Student/StudentView.vue";
import StudentCourseView from "@/views/Student/StudentCourseView.vue";
import TeacherView from "@/views/Teacher/TeacherView.vue";
import TeacherCourseWaitView from "@/views/Teacher/TeacherCourseWaitView.vue";
import TeacherCourseOpenView from "@/views/Teacher/TeacherCourseOpenView.vue";
import TeacherCourseView from "@/views/Teacher/TeacherCourseView.vue";
import login from "@/views/Login.vue";
import ManagerWorkView from "@/views/Manager/ManagerWorkView.vue";
import ManagerCourseView from "@/views/Manager/ManagerCourseView.vue";
import ManagerManageAllView from "@/views/Manager/ManagerManageAllView.vue";
import ManagerDepartmentView from "@/views/Manager/ManagerDepartmentView.vue";
import ManagerClassView from "@/views/Manager/ManagerClassView.vue";
import NotFoundView from "@/views/404View.vue";
Vue.use(VueRouter)
// 在父级路由上添加 meta 信息
const routes = [
    {
        path: '/student',
        name: 'student',
        component: StudentView,
        meta: { requiresAuth: true }, // 添加 meta 信息
        children: [
            {path: "course", name: "studentCourse", component: StudentCourseView},
            {path: "courseChosen", name: "studentCourseChosen", component: StudentCourseChosenView},
        ]
    },
    {
        path: '/teacher',
        name: 'teacher',
        component: TeacherView,
        meta: { requiresAuth: true }, // 添加 meta 信息
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
        meta: { requiresAuth: true }, // 添加 meta 信息
        children: [
            {path: "course", name: "ManagerCourse", component: ManagerCourseView},
            {path: "work", name: "ManagerWork", component: ManagerWorkView},
            {path: "manageAll", name: "ManagerManageAll", component: ManagerManageAllView},
            {path: "department", name: "ManagerDepartment", component: ManagerDepartmentView},
            {path: "class", name: "ManagerClass", component: ManagerClassView},
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: login
    },
    {
        path: '/about',
        name: 'about',
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    },
    {
        path: '*',
        name: '404',
        component: NotFoundView,
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

// 在路由守卫中检查 meta 信息
router.beforeEach((to, from, next) => {
    console.log("-----------------------------")
    console.log(to);
    console.log(from);
    console.log("-----------------------------")
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // 需要认证的路由
        if (!from.fullPath==="/"&&!from.fullPath.includes("login")&&to.fullPath.substring(0,4)!==from.fullPath.substring(0,4)) {
            alert("拒绝访问,请重新登录");
            router.push('/login');
        } else {
            // 用户已认证，继续导航
            next();
        }
    } else {
        next();
    }
});

export default router
