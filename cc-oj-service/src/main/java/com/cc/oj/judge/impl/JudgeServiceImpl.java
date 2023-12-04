package com.cc.oj.judge.impl;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.json.JSONUtil;
import com.cc.oj.common.ErrorCode;
import com.cc.oj.exception.BusinessException;
import com.cc.oj.judge.JudgeService;
import com.cc.oj.judge.codesandbox.CodeSandBox;
import com.cc.oj.judge.codesandbox.impl.ExampleCodeSandBoxImpl;
import com.cc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.cc.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.cc.oj.judge.codesandbox.model.JudgeContext;
import com.cc.oj.judge.manager.JudgeManager;
import com.cc.oj.judge.strategy.JudgeStrategy;
import com.cc.oj.judge.strategy.impl.DefaultJudgeStrategy;
import com.cc.oj.judge.strategy.impl.JavaJudgeStrategy;
import com.cc.oj.model.dto.question.JudgeCase;
import com.cc.oj.model.dto.question.JudgeConfig;
import com.cc.oj.model.dto.question.submit.JudgeInfo;
import com.cc.oj.model.entity.QuestionInfo;
import com.cc.oj.model.entity.QuestionSubmit;
import com.cc.oj.model.enums.JudgeInfoMessageEnum;
import com.cc.oj.model.enums.QuestionSubmitLanguageEnum;
import com.cc.oj.model.enums.QuestionSubmitStatusEnum;
import com.cc.oj.service.QuestionInfoService;
import com.cc.oj.service.QuestionSubmitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author cwc
 * @Date 2023/12/1 16:29
 * @Version 1.0
 */
@Service
public class JudgeServiceImpl implements JudgeService {
    @Resource
    private QuestionSubmitService questionSubmitService;
    @Resource
    private QuestionInfoService questionInfoService;
    @Resource
    private JudgeManager judgeManager;
    @Override
    public void doJudgeSubmit(long questionSubmitId) {
        // 1. 传入题目提交id, 便于获取题目提交相信信息(以及题目的相关信息)
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        Long id = questionSubmit.getId();
        String language = questionSubmit.getLanguage();
        Long questionId = questionSubmit.getQuestionId();
        String code = questionSubmit.getCode();
        String judgeInfo = questionSubmit.getJudgeInfo();
        Integer status = questionSubmit.getStatus();
        // 题目相关信息学
        QuestionInfo questionInfo = questionInfoService.getById(questionId);
        String judgeCase = questionInfo.getJudgeCase();
        String judgeConfig = questionInfo.getJudgeConfig();
        if(questionInfo == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目信息不存在");
        }
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCase, JudgeCase.class);
        JudgeConfig questionJudgeConfig = JSONUtil.toBean(judgeConfig, JudgeConfig.class);
        // 2. 判断题目提交的状态(如果是非判题中的就不执行)
        if(!QuestionSubmitStatusEnum.WAITING.getValue().equals(status)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "无需判题");
        }
        // 3. 执行代码沙箱(获取执行的结果)
        CodeSandBox codeSandBox = new ExampleCodeSandBoxImpl();
        List<String> inputList =  judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);

        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setExecuteCodeResponse(executeCodeResponse);
        judgeContext.setQuestionInfo(questionInfo);
        // 代码简化
        JudgeInfo strategyJudgeInfo = judgeManager.doJudge(judgeContext);
        // 修改判题状态
        QuestionSubmit qsb = new QuestionSubmit();
        qsb.setId(questionSubmitId);
        qsb.setStatus(executeCodeResponse.getStatus());
        qsb.setJudgeInfo(JSONUtil.toJsonStr(strategyJudgeInfo));
        questionSubmitService.updateById(qsb);
    }
}
