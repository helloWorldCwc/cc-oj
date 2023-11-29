<template>
 <div id="quesiton">
    <a-form :model="form" :style="{ width: '600px' }" @submit="doSubmit">
      <a-form-item field="title" label="题目标题">
        <a-input v-model="form.title" placeholder="请输入" />
      </a-form-item>
      <a-form-item field="tags" label="题目标签">
        <a-input-tag v-model="form.tags"   placeholder="Please Enter" allow-clear/>
      </a-form-item>
      <a-form-item field="tips" label="题目提示">
        <a-input v-model="form.tips" placeholder="请输入" />
      </a-form-item>
      <a-form-item field="content" label="题目内容">
        <MdEditor v-model:mdValue="form.content" mode="split" style="min-width: 600px;"></MdEditor>
      </a-form-item>
      <a-form-item field="answer" label="题目答案">
        <MdEditor v-model:mdValue="form.answer" mode="split" style="min-width: 600px;"></MdEditor>
      </a-form-item>
      <a-form-item label="判题配置" :content-flex="false" :merge-props="false">
        <a-space direction="vertical" fill>
            <a-form-item field="judgeConfigDto.memoryLimit" label="内存限制">
            <a-input-number mode="button" :min="0" v-model="form.judgeConfigDto.memoryLimit" placeholder="请输入内存限制" />
            </a-form-item>
            <a-form-item field="judgeConfigDto.timeLimit" label="时间限制">
            <a-input-number mode="button"  :min="0" v-model="form.judgeConfigDto.timeLimit" placeholder="请输入时间限制" />
            </a-form-item>
        </a-space>
     </a-form-item>
     <a-form-item label="判题配置" :content-flex="false" :merge-props="false">
        <a-space direction="vertical" fill v-for="judgeCase, index in form.judgeCaseList">
            <a-form-item field="judgeCase.input" label="输入用例">
            <a-input v-model="judgeCase.input" placeholder="请输入输入用例" />
            </a-form-item>
            <a-form-item field="judgeCase.output" label="输出用例">
            <a-input v-model="judgeCase.output" placeholder="请输入输出用例" />
            </a-form-item>
            <a-button @click="handleDelete(index)" status="danger" :style="{marginLeft:'10px', marginBottom: '10px'}">删除</a-button>
        </a-space>
        <a-button @click="handleAdd" type="outline" :style="{marginLeft:'10px', marginTop: '10px'}">新增用例</a-button>
     </a-form-item>
      <a-form-item>
        <a-button html-type="submit" style="min-width: 200px;" type="primary">提交</a-button>
      </a-form-item>
    </a-form>
 </div>
</template>


<script setup lang="ts">
import {ref, onMounted, toRaw} from 'vue'
import { Message } from '@arco-design/web-vue';
import MdEditor from '@/components/MdEditor.vue'
import {QuestionInfoInfoControllerService} from '@/apis/services/QuestionInfoInfoControllerService.ts'
import { useRoute } from 'vue-router';
const route = useRoute();
const questionId = ref(0)
const form = ref<any>({
  "answer": "",
  "content": "",
  "judgeCaseList": [
    {
      "input": "",
      "output": ""
    }
  ],
  "judgeConfigDto": {
    "memoryLimit": 0,
    "timeLimit": 0
  },
  "tags": [],
  "tips": "",
  "title": ""
})
const resetData = () => {
  form.value = {
  "answer": "",
  "content": "",
  "judgeCaseList": [
    {
      "input": "",
      "output": ""
    }
  ],
  "judgeConfigDto": {
    "memoryLimit": 0,
    "timeLimit": 0
  },
  "tags": [],
  "tips": "",
  "title": ""
}
}
const doSubmit = async () => {
    console.log(toRaw(form.value));
    // 编辑
    if(questionId.value != -1 && questionId.value){
      form.value.id = questionId.value;
      const res = await QuestionInfoInfoControllerService.updateQuestionInfoUsingPost(toRaw(form.value));
      if(res.code === 0) {
        Message.success('修改成功');
      }else {
        console.log(4564546);
        
        Message.error('添加失败:' + res.message);
      }
    }else {
      const res = await QuestionInfoInfoControllerService.addQuestionInfoUsingPost(toRaw(form.value));
      if(res.code === 0) {
        Message.success('添加成功');
        // 清空表单
        resetData()
      }else {
        Message.success('添加失败，', res.message);
      }
    }
   
}
const handleAdd = () => {
      form.value.judgeCaseList.push( {
        "input": "",
        "output": ""
    })
};
const handleDelete = (index: number) => {
    form.value.judgeCaseList.splice(index, 1)
}
onMounted(async () => {
  const id = route.query.id as any // 不这样做，直接转换为nubmer会有精度缺失
  if(id) {
    questionId.value = id
    const res = await QuestionInfoInfoControllerService.getQuestionInfoVoByIdUsingGet(id)
    if(res.code === 0){
      form.value = res.data as any
    }else {
      resetData()
    }
  }
})
</script>


<style scoped>
#quesiton {
  display: flex;
  justify-content: center;
}

</style>
