import type { RouteRecordRaw } from "vue-router";
import Home from "@/pages/Home.vue";
import Topic from "@/pages/MyTopic.vue";
import NotAuth from "@/pages/error/401.vue";
import AuthEnmu from '@/constant/AuthEnmu'
import UserLogin from "@/pages/user/UserLogin.vue";
import QuestionUpdate from "@/pages/question/QuestionUpdate.vue";
import DoQuesstionView from "@/pages/question/DoQuesstionView.vue";
import QuestionList from "@/pages/question/QuestionList.vue";
import UserRegister from "@/pages/user/UserRegister.vue";
import UserLayout from '@/layouts/userBase/UserLayout.vue'
import Layout from '@/layouts/Layout.vue'



const routes: RouteRecordRaw[] = [
    // 业务路由
    { 
        path: "/", 
        name: '首页',
        component: Layout ,
        redirect: '/home',
        children: [
            { 
                path: "home", 
                name: '主页',
                component: Home, 
                meta: {
                     isVisible: true
                } 
            },
            {
                path: "see/question",
                 name: '题目管理',
                 component: QuestionList,
                 meta: {
                   isVisible: true,
                   isAccess: AuthEnmu.ADMIN,
               } 
           },
            {
                 path: "topic",
                  name: '我的题目',
                  component: Topic,
                  meta: {
                    isVisible: true
                } 
            },
            { 
                path: "401", 
                name: '无权限',
                component: NotAuth ,
                meta: {
                    isVisible: false
                }
            },
            { 
                path: "add/question", 
                name: '创建题目',
                component: QuestionUpdate, 
                meta: {
                     isVisible: true,
                     isAccess: AuthEnmu.ADMIN,
                } 
            },
            { 
                path: "question/doQuestion/:questionId", 
                name: '题目作答',
                component: DoQuesstionView, 
                props: true,
                meta: {
                     isVisible: false,
                     isAccess: AuthEnmu.USER,
                } 
            },
        ]
    },
    // 用户路由
    {
        path: "/user", 
        name: '用户页',
        component: UserLayout,
        children: [
            { 
                path: "/user/login", 
                name: '登录',
                component: UserLogin ,
                meta: {
                    isVisible: false
                }
            },
            { 
                path: "/user/register", 
                name: '注册',
                component: UserRegister ,
                meta: {
                    isVisible: false
                }
            },
        ]
    }
];
export default routes;
