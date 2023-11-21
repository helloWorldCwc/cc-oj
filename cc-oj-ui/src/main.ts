import { createApp } from "vue";
import "./style.css";
import App from "@/App.vue";
import ArcoVue from "@arco-design/web-vue";
import "@arco-design/web-vue/dist/arco.css";
import Router from "@/router";
// 加入权限参与执行
import './access/access.ts';
import pinia from './store/'
const app = createApp(App);
app.use(ArcoVue);
app.use(Router);
app.use(pinia);
app.mount("#app");
