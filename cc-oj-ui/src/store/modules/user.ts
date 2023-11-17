import { defineStore } from "pinia";
export const useUserStore = defineStore("userStroe", {
  state: () => ({
    userInfo: {
      username: '未登录',
      role: ''
    },
  }),
  getters: {},
  actions: {
    loginUser(){
      this.userInfo.username = 'admin'
      this.userInfo.role = 'admin'
      // TODO 这儿需要替换成api接口地址
    }
  },
});
