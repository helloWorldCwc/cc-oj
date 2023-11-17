<script setup lang="ts">
import { ref } from "vue";
</script>

<template>
  <a-space>
    <a-button type="primary" status="success">Primary</a-button>
    <a-button status="success">Default</a-button>
    <a-button type="dashed" status="success">Dashed</a-button>
    <a-button type="outline" status="success">Outline</a-button>
    <a-button type="text" status="success">Text</a-button>
  </a-space>
</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
