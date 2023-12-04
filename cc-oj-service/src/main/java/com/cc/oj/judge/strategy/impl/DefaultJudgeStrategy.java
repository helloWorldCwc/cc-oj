package com.cc.oj.judge.strategy.impl;
import java.util.Date;

import cn.hutool.json.JSONUtil;
import com.cc.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.cc.oj.judge.codesandbox.model.JudgeContext;
import com.cc.oj.judge.strategy.JudgeStrategy;
import com.cc.oj.model.dto.question.JudgeCase;
import com.cc.oj.model.dto.question.JudgeConfig;
import com.cc.oj.model.dto.question.submit.JudgeInfo;
import com.cc.oj.model.entity.QuestionInfo;
import com.cc.oj.model.enums.JudgeInfoMessageEnum;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author cwc
 * @Date 2023/12/4 9:26
 * @Version 1.0
 */
// 默认策略（答题）
public class DefaultJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        ExecuteCodeResponse executeCodeResponse = judgeContext.getExecuteCodeResponse();
        JudgeInfo responseJudgeInfo = executeCodeResponse.getJudgeInfo();
        QuestionInfo questionInfo = judgeContext.getQuestionInfo();
        String judgeCase = questionInfo.getJudgeCase();
        String judgeConfig = questionInfo.getJudgeConfig();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCase, JudgeCase.class);
        JudgeConfig questionJudgeConfig = JSONUtil.toBean(judgeConfig, JudgeConfig.class);
        // 4. 对结果进行判断,修改判题状态.
        String executeMessage = JudgeInfoMessageEnum.ACCEPTED.getValue();
        List<String> executeOutputList = executeCodeResponse.getOutputList();
        JudgeInfo executeJudgeInfo = executeCodeResponse.getJudgeInfo();
        // 判断
//        内存大小超出限制
        if(executeJudgeInfo.getMemoryLimit() > questionJudgeConfig.getMemoryLimit()){
            executeMessage = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED.getValue();
        }
//        时间
        if(executeJudgeInfo.getTimeLimit() > questionJudgeConfig.getTimeLimit()){
            executeMessage = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED.getValue();
        }
        List<String> questionOutputList = judgeCaseList.stream().map(JudgeCase::getOutput).collect(Collectors.toList());
        if(questionOutputList.size() != executeOutputList.size()){
            executeMessage = JudgeInfoMessageEnum.WRONG_ANSWER.getValue();
        }
        for (int i = 0; i < questionOutputList.size(); i++) {
            if(!questionOutputList.get(i).equals(executeOutputList.get(i))){
                executeMessage = JudgeInfoMessageEnum.WRONG_ANSWER.getValue();
                break;
            }
        }
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(executeMessage);
        judgeInfo.setTimeLimit(responseJudgeInfo.getTimeLimit());
        judgeInfo.setMemoryLimit(responseJudgeInfo.getMemoryLimit());
        return judgeInfo;

    }
}
