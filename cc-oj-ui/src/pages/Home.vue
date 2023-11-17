<template>
home
dasdas
</template>

<script setup lang="ts">
import { ref, reactive } from "vue";
import { useUserStore } from "../store/modules/user";
const userStore = useUserStore();
console.log(userStore.userName);
</script>

<style scoped></style>
