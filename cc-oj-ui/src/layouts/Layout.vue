<template>
  <div id="layout">
    <a-layout>
      <a-layout-header class="header">
        <div class="menu-style">
          <Menu> </Menu>
        </div>
      </a-layout-header>
      <a-layout-content class="content">
        <RouterView />
      </a-layout-content>
      <a-layout-footer class="footer"
        >copyright
        <a href="https://github.com/helloWorldCwc" style="text-decoration: none;"> 程序员cc</a>
      </a-layout-footer>
    </a-layout>
  </div>
</template>
<script setup lang="ts">
import { ref, reactive } from "vue";
import Menu from "./base/Menu.vue";
</script>

<style scoped>
#layout {
  background-image: linear-gradient(to right, 
  #f794a4, 
  #fdd6ad,
  #feeacf,
  #eaf490,
  #c5e0b4);
}
#layout .content
 {
  width: 100%;
  max-width: 1400px;
  margin: 0 auto;
  
}
#layout .header .menu-style {
  max-width: 1400px;
  margin: 0 auto;
}
#layout .header {
  box-shadow: 0px 0px 10px rgba(0,0,0,0.5);
  margin-bottom: 10px;
  background-color: #fff;

}
#layout .content {
  box-sizing: border-box;
  min-height: 90vh;
  margin-bottom: 10px;
  padding: 15px;
  background-color: #fff;
}
#layout .footer {
  position: sticky;
  bottom: 0;
  height: 50px;
  text-align: center;
  line-height: 50px;
  background-color: #fff;
}
</style>
