package com.cc.oj.judge.codesandbox.impl;

import com.cc.oj.judge.codesandbox.CodeSandBox;
import com.cc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.cc.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.cc.oj.model.dto.question.submit.JudgeInfo;
import com.cc.oj.model.enums.JudgeInfoMessageEnum;
import com.cc.oj.model.enums.QuestionSubmitStatusEnum;

import java.util.Arrays;

/**
 * @Author cwc
 * @Date 2023/12/1 11:05
 * @Version 1.0
 */
public class ExampleCodeSandBoxImpl implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest request) {
        ExecuteCodeResponse response = ExecuteCodeResponse.builder()
                .message("ok")
                .status(QuestionSubmitStatusEnum.SUCCEED.getValue())
                .outputList(Arrays.asList("1 2", "3 4"))
                .judgeInfo(new JudgeInfo(100,200, JudgeInfoMessageEnum.ACCEPTED.getValue()))
                .build();
        return response;
    }
}
