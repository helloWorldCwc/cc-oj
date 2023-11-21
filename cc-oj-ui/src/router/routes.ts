import type { RouteRecordRaw } from "vue-router";
import Home from "@/pages/Home.vue";
import Topic from "@/pages/MyTopic.vue";
import NotAuth from "@/pages/error/401.vue";
import AuthEnmu from '@/constant/AuthEnmu'
import UserLogin from "@/pages/user/UserLogin.vue";
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
                 path: "topic",
                  name: '我的题目',
                  component: Topic,
                  meta: {
                    isVisible: true
                } 
            },
            { 
                path: "auth", 
                name: '有权限的路由',
                component: Topic ,
                meta: {
                    isAccess: AuthEnmu.ADMIN,
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
