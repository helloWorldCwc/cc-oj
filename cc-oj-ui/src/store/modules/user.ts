import { defineStore } from "pinia";
import { getCurrentUser } from '@/apis/user.ts'
export const useUserStore = defineStore("userStroe", {
  state: () => ({
    userInfo: {
      userName: '未登录',
      role: ''
    },
  }),
  getters: {},
  actions: {
     async loginUser(){
      const {data} = await getCurrentUser()
      this.userInfo.userName = data?.userName ?? '未登录'
      this.userInfo.role =  data?.userRole ?? ''
      console.log(this.userInfo);
      
    }
  },
});
