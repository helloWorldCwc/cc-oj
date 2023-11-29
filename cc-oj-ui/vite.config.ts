import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import monacoEditorPlugin from 'vite-plugin-monaco-editor';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue(), monacoEditorPlugin({})],
  resolve: {
    alias: {
      '@': '/src', // 以 @ 作为别名，映射到 /src 目录
      '@root': '/'
    },
  },
});
