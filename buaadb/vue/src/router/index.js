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
import TeacherCourseSelectView from "@/views/Teacher/TeacherCourseSelectView.vue";
Vue.use(VueRouter)
// 在父级路由上添加 meta 信息
var loc=0//localStorage.getItem("loc")

const routes = [
    {
        path: '/student',
        name: 'student',
        component: StudentView,
        meta: { title:"学生主页"},
        children: [
            {path: "course", name: "studentCourse",meta: { title:"全部课程"}, component: StudentCourseView},
            {path: "courseChosen", name: "studentCourseChosen",meta: { title:"学生选课"}, component: StudentCourseChosenView},
        ]
    },
    {
        path: '/teacher',
        name: 'teacher',
        component: TeacherView,
        meta: { title:"教师主页"},
        children: [
            {path: "course", name: "teacherCourse",meta: { title:"全部课程"}, component: TeacherCourseView},
            {path: "courseWait", name: "teacherCourseWait",meta: { title:"待审核课程"}, component: TeacherCourseWaitView},
            {path: "courseOpen", name: "teacherCourseOpen",meta: { title:"已开课程"}, component: TeacherCourseOpenView},
            {path: "search", name: "teacherCourseSelect",meta: { title:"选课学生"}, component: TeacherCourseSelectView},
        ]
    },
    {
        path: '/manager',
        name: 'manager',
        component: ManagerView,
        meta: { title:"管理员主页"},
        children: [
            {path: "course", name: "ManagerCourse",meta: { title:"课程管理"}, component: ManagerCourseView},
            {path: "work", name: "ManagerWork",meta: { title:"审核开课"}, component: ManagerWorkView},
            {path: "manageAll", name: "ManagerManageAll",meta: { title:"人员管理"}, component: ManagerManageAllView},
            {path: "department", name: "ManagerDepartment",meta: { title:"院系管理"}, component: ManagerDepartmentView},
            {path: "class", name: "ManagerClass",meta: { title:"班级管理"}, component: ManagerClassView},
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: login,
        meta: { title:"这是BUAA选课系统"},
    },
    {
        path: '/',
        name: 'about',
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    },
    {
        path: '*',
        name: '404',
        meta: { title:"想干嘛？"},
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
    var new_loc=0;
    if(to.fullPath.includes("student")){
        new_loc=1;
    }else if(to.fullPath.includes("teacher")){
        new_loc=2;
    }else if(to.fullPath.includes("manager")) {
        new_loc = 3;
    }
    loc=localStorage.getItem('loc');
    console.log(loc)
    console.log(to.fullPath)
    console.log(new_loc)

    if (!to.fullPath.includes("login")&&!from.fullPath.includes("login")&&new_loc!=loc) {

            if(loc===1){
                router.push('/student');
            }else if(loc===2){
                router.push('/teacher');
            }else if(loc===3){
                router.push('/manager');
            }else{
                alert("拒绝访问");
                router.push('/login');
            }

    } else {
        if(to.fullPath.includes("student")){
            loc=1;
            localStorage.setItem("loc",new_loc )
        }else if(to.fullPath.includes("teacher")){
            loc=2;
            localStorage.setItem("loc",new_loc )
        }else if(to.fullPath.includes("manager")){
            loc=3;
            localStorage.setItem("loc",new_loc )
        }
        next();
    }
});

export default router