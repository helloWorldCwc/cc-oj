import { useUserStore } from '../store/modules/user'
import type {RouteRecordRaw } from 'vue-router'
export const isAccess = (route: RouteRecordRaw ):boolean => {
    const userStore = useUserStore();
    return route.meta?.isAccess ? route.meta?.isAccess === userStore.userInfo.role : true;
}