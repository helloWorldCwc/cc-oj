<template>

  <a-table :columns="columns" :data="data" :pagination="false">
      <template #optional="{ record }">
        <a-button @click="goDoQuestion(record)">做题</a-button>
      </template>
    </a-table>
    <a-pagination :total="total" @change="changeCurrent" :current="queryParams.current" :page-size="queryParams.pageSize" show-total show-page-size />
  </template>
  
  
  <script setup lang="ts">
  import {ref, reactive, onMounted} from 'vue'
  import {QuestionInfoInfoControllerService} from '@/apis/services/QuestionInfoInfoControllerService.ts'
  const columns = [{
    title: '题目名称',
    dataIndex: 'title',
  }, {
    title: '标签',
    dataIndex: 'tips',
  },
  {
    title: '题目内容',
    dataIndex: 'content',
  },  {
    title: '通过率',
    dataIndex: 'acceptedRate',
  }, {
    title: '创建时间',
    dataIndex: 'createTime',
  }, {
    title: '操作',
    slotName: 'optional'
  }];
  const total = ref(0);
  const queryParams = ref({
    current: 1,
    pageSize: 10,
    title: '',
    tags: []
  })
  const data = ref([]);
  const goDoQuestion = (record) => {
      console.log('做题啦', record);
      
  } 
  const getData = async () => {
     const res = await QuestionInfoInfoControllerService.listQuestionInfoVoByPageUsingPost(queryParams.value)
     if(res.code === 0){
      data.value = res.data.records
      total.value = Number(res.data.total)
     }
  }
  const changeCurrent = (current: number) => {
      queryParams.value.current = current
      getData();
  }
  onMounted(() => {
      getData()
  })
  
  </script>
  
  
  <style scoped>
  
  
  </style>