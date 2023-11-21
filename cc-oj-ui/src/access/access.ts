// 用于路由的权限校验
import router from '@/router/index.ts'
import { useUserStore } from '@/store/modules/user.ts'
import AuthEnmu from '@/constant/AuthEnmu'
const whitePaths = ['/user/login', '/user/register', '/401']
router.beforeEach(async (to, from, next) => {
    const {meta} = to;
    // 白名单
    if(whitePaths.includes(to.path)){
        return next();
    }
    // 不用登录
    if(meta?.isAccess === AuthEnmu.NOT_LOGIN){
        return next();
    }
    // 需要登录
    const userStore =   useUserStore()
    await userStore.loginUser(); // 尝试获取登录信息
    const {userInfo: {role}}  = userStore;
    if(to.meta.isAccess && to.meta.isAccess === AuthEnmu.ADMIN){
        // 尝试获取信息失败，跳转到登录页
        if(!role){
            return next('/user/login?redirect=' + to.fullPath)
        }
        if(role !== AuthEnmu.ADMIN){
            return next('/401');
        }
    }
    next()
})