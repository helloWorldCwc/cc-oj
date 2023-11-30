<template>
  <div id="question-style">
    <a-form :model="queryParams" layout="inline">
      <a-form-item field="title" label="题目标题" style="width: 270px">
        <a-input v-model="queryParams.title" placeholder="请输入题目标题" />
      </a-form-item>
      <a-form-item field="tags" label="标签" style="width: 270px">
        <a-input-tag v-model="queryParams.tags" placeholder="请输入标签" />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="goSearch"> 搜索</a-button>
      </a-form-item>
    </a-form>

    <a-table
      :columns="columns"
      :loading="isLoading"
      :data="data"
      :pagination="false"
    >
      <template #tags="{ record }">
        <a-space>
          <a-tag v-for="tag in record.tags" color="green">{{ tag }}</a-tag>
        </a-space>
      </template>
      <template #judgeConfig="{ record }" >
        <a-descriptions  :column="1" style="width: 120px;">
            <a-descriptions-item label="内存限制" >
                {{ record.judgeConfigDto.memoryLimit }}
            </a-descriptions-item>
            <a-descriptions-item label="时间限制" >
                {{ record.judgeConfigDto.timeLimit }}
            </a-descriptions-item>
        </a-descriptions>
      </template>
      <template #acceptedRate="{ record }">
        <span style="display: inline-block; width: 120px;">
            {{
                `${record.acceptedRate}% (${record.submitNum}/${record.acceptedNum})`
            }}
        </span>
      </template>
   
      <template #optional="{ record }">
        <a-space>
          <a-button @click="goDoQuestion(record)">做 题</a-button>
        </a-space>
      </template>
    </a-table>
    <a-pagination
      style="float: right; margin-top: 10px;"
      :total="total"
      @change="changeCurrent"
      :current="queryParams.current"
      :page-size="queryParams.pageSize"
      show-total
    />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { QuestionInfoInfoControllerService } from "@/apis/services/QuestionInfoInfoControllerService.ts";
import { Message } from "@arco-design/web-vue";
import { useRouter } from "vue-router";
const router = useRouter();
const isLoading = ref(false);
const columns = [
  {
    title: "题目名称",
    dataIndex: "title",
  },
  {
    title: "标签",
    dataIndex: "tags",
    slotName: "tags",
  },
  // {
  //   title: "题目内容",
  //   dataIndex: "content",
  // },
  {
    title: "通过率",
    dataIndex: "acceptedRate",
    slotName: "acceptedRate",
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
  },
  {
    title: "操作",
    slotName: "optional",
  },
];
const total = ref(0);
const queryParams = ref({
  current: 1,
  pageSize: 10,
  title: "",
  tags: [],
});
const data = ref([]);
const goDoQuestion = (record) => {
  console.log("做题啦");
  router.push({
    path: '/question/doQuestion/' + record.id,
  })
};
const getData = async () => {
  isLoading.value = true;
  const res =
    await QuestionInfoInfoControllerService.listQuestionInfoVoByPageUsingPost(
      queryParams.value,
    );
  if (res.code === 0) {
    data.value = res.data.records;
    total.value = Number(res.data.total);
  }
  isLoading.value = false;
};

const changeCurrent = (current: number) => {
  queryParams.value.current = current;
  getData();
};
const goSearch = () => {
  queryParams.value.current = 1; // 重置
  getData();
};
// 当getData函数所涉及到的变量发生变换，函数就会执行一次
// watchEffect(() => {
//     getData()
// })
onMounted(() => {
  getData();
});
</script>

<style scoped></style>
