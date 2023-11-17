<template>
  <a-menu mode="horizontal" @menu-item-click="changeRoute" :selected-keys="selectKeys" >
    <a-menu-item key="0" :style="{ padding: 0, marginRight: '38px' }" disabled>
      <div
        :style="{
          width: '80px',
          height: '30px',
          borderRadius: '2px',
          cursor: 'text',
        }"
        class="logo"
      >
      <img style="height: 30px;" src="../../assets/logo.png" alt="">
      <span style="color: #000;margin-left: 5px;">oj答题</span>
    </div>
    </a-menu-item>
    <a-menu-item v-for="route in  routeList" :key="route.path">{{ route.name }}</a-menu-item>
    <a-menu-item disabled style="float: right;">
      <span style="color: #000;">{{ userStore.userInfo.username }}</span>
    </a-menu-item>
  </a-menu>
</template>

<script setup lang="ts">
import { ref, onMounted ,computed } from "vue";
import routes from '../../router/routes.ts'
import { useRouter,RouteRecordRaw } from 'vue-router'
import { useUserStore } from '../../store/modules/user'
import { isAccess } from '../../access/accessUtils'
const routeList = ref<RouteRecordRaw[]>([])
const selectKeys = ref(['/']);
const router = useRouter();
const userStore = useUserStore();
const username = ref<string>('未登录')
const changeRoute = (route:string) => {
  selectKeys.value = [route];
  router.push({
    path: route
  })
}
// 路由后置守卫
router.afterEach((to, from) => {
  selectKeys.value = [to.path];
})
setTimeout(() => {
  userStore.loginUser()
}, 3000)
routeList.value = computed(() => {
  return routes.filter(route => route.meta?.isVisible && isAccess(route))
}).value
</script>

<style scoped>
.logo {
  display: flex;
}
</style>
