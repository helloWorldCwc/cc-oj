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
    <a-menu-item v-for="route in  routeList" :key="'/'+route.path">{{ route.name }}</a-menu-item>
    <div style="float: right;cursor: pointer;">
      <span style="color: #000;">{{ userStore.userInfo.userName }}</span>
    </div>
  </a-menu>
</template>

<script setup lang="ts">
import { ref, onMounted ,computed, watch } from "vue";
import routes from '../../router/routes.ts'
import { useRouter,RouteRecordRaw, useRoute } from 'vue-router'
import { useUserStore } from '../../store/modules/user'
import { isAccess } from '../../access/accessUtils'
const routeList = ref<RouteRecordRaw[]>([])
const selectKeys = ref(['/']);
const router = useRouter();
const route = useRoute();
const userStore = useUserStore();
const changeRoute = (route:string) => {
  selectKeys.value = [route];
  router.push({
    path: route
  })
}
// 路由后置守卫
// router.afterEach((to, from) => {
//   selectKeys.value = [to.path];
// })
watch(() => route.path, (newVal) => {
  selectKeys.value = [newVal];
}, {immediate: true})


routeList.value = computed(() => {
  const children = routes.find(route => route.path === '/')?.children;
  return children?.filter(route => route.meta?.isVisible && isAccess(route)) ?? []
}).value
</script>

<style scoped>
.logo {
  display: flex;
}
</style>
