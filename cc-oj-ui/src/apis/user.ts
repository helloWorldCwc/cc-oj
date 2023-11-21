import request from '@/plugins/requests.ts'
import { UserLoginReq } from '@/modules/user.d.ts'
export const getCurrentUser = () => {
    return request({
        url: '/user/get/login',
        method: 'get'
    })
}

export const userLogin = (data: UserLoginReq) => {
    return request({
        url: '/user/login',
        method: 'post',
        data
    })
}