
<template>
    <Viewer :value="mdValue"  :plugins="plugins" />
</template>

<script setup lang="ts">
import { Viewer } from '@bytemd/vue-next'
import gfm from '@bytemd/plugin-gfm'
import hightLight from '@bytemd/plugin-highlight'
interface Props {
    mdValue: string,
}
// 设置props的默认值（TS中）
withDefaults(defineProps<Props>(), {
    mdValue: () => '',
})
const plugins = [gfm(),hightLight()]
</script>

<style scoped>
</style>