<template>
    <div id="question-submit-style">
        <a-row :gutter="24">
          <a-col :span="12">
            <a-tabs default-active-key="viewer">
            <a-tab-pane key="viewer" title="详情">
                <a-card>
                    <template #title>
                        {{ formData?.title }}
                    </template>
                    <template #extra>
                        <a-space>
                            <a-tag v-for="tag in formData?.tags" color="green"> {{ tag }}</a-tag>
                        </a-space>
                    </template>
                    <a-space>
                        <a-descriptions title="判题条件" :column="{xs:1, md:3, lg:4}">
                            <a-descriptions-item  label="内存限制"  :span="2">
                                {{ formData?.judgeConfigDto?.memoryLimit }}
                            </a-descriptions-item>
                            <a-descriptions-item  label="时间限制" >
                                {{ formData?.judgeConfigDto?.timeLimit }}
                            </a-descriptions-item>
                         </a-descriptions>
                    </a-space>
                    <MdViewer :mdValue="formData?.content"/>
                </a-card>
            </a-tab-pane>
            <a-tab-pane key="comment" title="评论" disabled>
             刹石狩
            </a-tab-pane>
            <a-tab-pane key="answer" title="题解">
                <a-card>
                    题解
                </a-card>
            </a-tab-pane>
        </a-tabs>
      
          </a-col>
          <a-col :span="12">
            <a-card :bordered="false">
                <a-form :model="submitFormData" label-align="left">
                    <a-form-item label="编程语言"   >
                        <a-select v-model="submitFormData.language" :style="{width:'320px'}" placeholder="请选择编程语言">
                            <a-option value="java">java</a-option>
                            <a-option value="go">go</a-option>
                            <a-option value="cpp">cpp</a-option>
                            <a-option value="html">html</a-option>
                        </a-select>
                    </a-form-item>
                </a-form>
                <CodeEditorVue v-model:codeValue="submitFormData.code" height="800px" :language="submitFormData.language"></CodeEditorVue>
                <a-divider  :size="0"/>
                <a-button type="primary" style="width: 300px;" size="large" @click="submitQuestion">提交</a-button>
            </a-card>
          </a-col>
        </a-row>
    </div>
</template>


<script setup lang="ts">
import { QuestionSubmitAddRequest, QuestionSubmitControllerService } from '@/apis';
import { QuestionInfoVO } from '@/apis/models/QuestionInfoVO';
import { QuestionInfoInfoControllerService } from '@/apis/services/QuestionInfoInfoControllerService';
import CodeEditorVue from '@/components/CodeEditor.vue';
import MdViewer from '@/components/MdViewer.vue';
import { Message } from '@arco-design/web-vue';
import {ref, onMounted} from 'vue'
const props = defineProps(['questionId'])
const formData = ref<QuestionInfoVO>();
const submitFormData = ref<QuestionSubmitAddRequest>({
    code: '<a></a>',
    language: 'java',
    questionId: props.questionId,
});
const getData = async () => {
    const res = await QuestionInfoInfoControllerService.getQuestionInfoVoByIdUsingGet(props.questionId)
    console.log(res);
    if(res.code === 0){
        formData.value = res.data;
    }else {
        Message.error('题目详情获取失败,' + res.message);
    }
}
const submitQuestion = async () => {
    const res = await QuestionSubmitControllerService.doQuestionSubmitUsingPost(submitFormData.value)
    if(res.code === 0){
        Message.success('提交成功');
    }else {
        Message.error('提交失败，' + res.message )
    }
}
onMounted( () => {
    getData();
})
</script>


<style scoped>


</style>