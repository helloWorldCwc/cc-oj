<template>

<a-table :columns="columns" :loading="isLoading" :data="data" :pagination="false">
    <template #tags="{ record }">
        <a-space>
            <a-tag v-for="tag in record.tags" color="green">{{ tag }}</a-tag>
        </a-space>
    </template>
    <template #judgeConfig="{ record }">
       222
    </template>
    <template #judgeCase="{ record }">
      111
    </template>
    <template #optional="{ record }">
    <a-space>
        <a-button @click="doDelete(record)" >删除</a-button>
        <a-button @click="doEdite(record)" >编辑</a-button>
    </a-space>
    </template>
  </a-table>
  <a-pagination :total="total" @change="changeCurrent" :current="queryParams.current" :page-size="queryParams.pageSize" show-total  />
</template>


<script setup lang="ts">
import {ref, reactive, onMounted} from 'vue'
import {QuestionInfoInfoControllerService} from '@/apis/services/QuestionInfoInfoControllerService.ts'
import { Message } from '@arco-design/web-vue';
import { useRouter } from 'vue-router';
const router = useRouter();
const isLoading = ref(false)
const columns = [{
  title: '题目名称',
  dataIndex: 'title',
}, {
  title: '标签',
  dataIndex: 'tags',
  slotName: 'tags'
},
{
  title: '题目内容',
  dataIndex: 'content',
},  {
  title: '题目配置',
  dataIndex: 'judgeConfigDto',
  slotName: 'judgeConfig'
},
{
  title: '测试用例',
  dataIndex: 'judgeCaseList',
  slotName: 'judgeCase'
},{
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
const goDoQuestion = () => {
    console.log('做题啦');
    
} 
const getData = async () => {
    isLoading.value = true
   const res = await QuestionInfoInfoControllerService.listQuestionInfoVoByPageUsingPost(queryParams.value)
   if(res.code === 0){
    data.value = res.data.records
    total.value = Number(res.data.total)
   }
   isLoading.value = false
}
const doDelete = async (record: any) => {
    console.log(record);
    const res = await QuestionInfoInfoControllerService.deleteQuestionInfoUsingPost({id: record.id})
    if(res.code === 0){
        getData()
        Message.success('删除成功')
    }else {
        Message.error('删除失败，' + res.message)
    }
}
const doEdite = async (record : any) =>{
    console.log(record);
    router.push({
        path: '/add/question',
        query: {
            id: record.id
        }
    })
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