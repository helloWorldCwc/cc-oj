<template>
  <div style="height: 90vh;display: flex;flex-direction: column;justify-content: center;align-items: center;">
    <div style="display: flex;justify-content: center;align-items: center;margin-bottom: 10px;">
      <img style="height: 30px;" src="../../assets/logo.png" alt="">
      <span style="color: #000;margin-left: 6px;">oj答题</span>
    </div>
    <div>
      <h2>用户登录</h2>
    </div>
    <a-form :model="form" :style="{ width: '600px' }" @submit="handleSubmit">
      <a-form-item
        field="userAccount"
        label="用户名"
        validate-trigger="input"
        required
      >
        <a-input v-model="form.userAccount" placeholder="请输入用户名" />
      </a-form-item>
      <a-form-item field="userPassword" label="密码" validate-trigger="input" required>
        <a-input v-model="form.userPassword" placeholder="请输入密码" />
      </a-form-item>
      <a-form-item field="isRead">
        <a-button html-type="submit" type="primary" style="width: 100px;">登 录</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue";
// import { userLogin } from '@/apis/user.ts'
import { useRouter, useRoute } from "vue-router";
import { Message } from '@arco-design/web-vue';
import {UserControllerService} from '@/apis/services/UserControllerService.ts'
const form = reactive({
  userAccount: "ccDog",
  userPassword: "12345678",
  isRead: false,
});
const router = useRouter();
const route = useRoute();
const handleSubmit = async () => {
  const res = await UserControllerService.userLoginUsingPost(form);
  if(res.code === 0){
    Message.success('登录成功')
    router.replace({
        path: route.query.redirect || '/',
    })
  }else {
    Message.error(res.message)
  }
};
</script>

<style scoped></style>
