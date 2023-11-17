import type { RouteRecordRaw } from "vue-router";
import Home from "../pages/Home.vue";
import Topic from "../pages/MyTopic.vue";
import NotAuth from "../pages/error/401.vue";
import AuthEnmu from '../constant/AuthEnmu'

const routes: RouteRecordRaw[] = [
    { path: "/", name: '主页',component: Home, meta: {
        isVisible: true
    } },
    { path: "/topic", name: '我的题目',component: Topic,meta: {
        isVisible: true
    } },
    { 
        path: "/auth", 
        name: '有权限的路由',
        component: Topic ,
        meta: {
            isAccess: AuthEnmu.ADMIN,
            isVisible: true
        }
    },
    { 
        path: "/401", 
        name: '无权限',
        component: NotAuth ,
        meta: {
            isVisible: false
        }
    },
];
export default routes;
