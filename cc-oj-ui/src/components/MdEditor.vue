
<template>
    <Editor :value="mdValue" mode="split" :plugins="plugins" @change="handleChange" />
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { Editor } from '@bytemd/vue-next'
import gfm from '@bytemd/plugin-gfm'
import hightLight from '@bytemd/plugin-highlight'
interface Props {
    mdValue: string,
    mode: string
}
// 设置props的默认值（TS中）
withDefaults(defineProps<Props>(), {
    mdValue: () => '',
    mode: 'split'
})
const emits = defineEmits(['update:mdValue'])
const plugins = [gfm(),hightLight()]
const handleChange = (v: string) => {
  emits('update:mdValue', v)
}
</script>

<style scoped>
.bytemd-toolbar-right .bytemd-tippy-right:last-child {
    display: none;
}
</style>