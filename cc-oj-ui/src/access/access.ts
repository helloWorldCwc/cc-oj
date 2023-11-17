// 用于路由的权限校验
import router from '../router/index.ts'
import { useUserStore } from '../store/modules/user.ts'
import AuthEnmu from '../constant/AuthEnmu'
router.beforeEach((to, from, next) => {
    const {userInfo: {role}} = useUserStore()
    if(to.meta.isAccess && to.meta.isAccess === AuthEnmu.ADMIN){
        console.log(role);
        if(!role || role !== AuthEnmu.ADMIN){
            return next('/401');
        }
    }
    next()
})