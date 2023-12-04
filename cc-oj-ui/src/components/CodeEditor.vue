<template>
    <div id="codeEditor">
        <div ref="editorContainer" :style="{height: height}"></div>
    </div>
</template>


<script setup lang="ts">
import {ref, watch, onMounted, toRaw} from 'vue'
import * as monaco from 'monaco-editor'
const editorContainer = ref<any>(null)
const editor = ref<any>(null)
interface CodeEditorProps {
    codeValue: string,
    height: string,
    language: string
}
const props = withDefaults(defineProps<CodeEditorProps>(), {
    codeValue: () => '',
    height: '600px',
    language: 'java'
})
const emits = defineEmits(['update:codeValue'])
onMounted(() => {
  editor.value = monaco.editor.create(editorContainer.value,{
    value: props.codeValue,
    language: props.language,
    folding: true, // 是否折叠
    theme: 'vs-dark',
    foldingHighlight: true, // 折叠等高线
    foldingStrategy: "indentation", // 折叠方式  auto | indentation
    showFoldingControls: "always", // 是否一直显示折叠 always | mouseover
    disableLayerHinting: true, // 等宽优化
    emptySelectionClipboard: false, // 空选择剪切板
    selectionClipboard: false, // 选择剪切板
    automaticLayout: true, // 自动布局
    codeLens: true, // 代码镜头
    scrollBeyondLastLine: false, // 滚动完最后一行后再滚动一屏幕
    colorDecorators: true, // 颜色装饰器
    accessibilitySupport: "off", // 辅助功能支持  "auto" | "off" | "on"
    lineNumbers: "on", // 行号 取值： "on" | "off" | "relative" | "interval" | function
    lineNumbersMinChars: 5, // 行号最小字符   number
    readOnly: false, //是否只读  取值 true | false
  })


  // 监听编辑器的变化获取值
  editor.value.onDidChangeModelContent(() => {
    const code = toRaw(editor.value).getValue();
    emits('update:codeValue', code)
  })
})
// 切换编程语言
watch(() => props.language, (newValue) => {
  if(editor.value){
    monaco.editor.setModelLanguage(
      toRaw(editor.value).getModel(),
      props.language
    )
  }
})
</script>


<style scoped>


</style>