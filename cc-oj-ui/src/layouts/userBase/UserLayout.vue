<template>
    <div id="layout">
      <a-layout>
        <a-layout-header class="header">
            用户相关
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
  #layout .header, 
  #layout .content,
  #layout .footer {
    width: 100%;
    max-width: 1400px;
    margin: 0 auto;
    background-color: aliceblue;
  }
  #layout .header {
    box-shadow: 0px 0px 10px rgba(0,0,0,0.5);
    margin-bottom: 10px;
  }
  #layout .content {
    min-height: 100vh;
    margin-bottom: 10px;
  }
  #layout .footer {
    height: 50px;
    text-align: center;
    line-height: 50px;
  }
  </style>
  