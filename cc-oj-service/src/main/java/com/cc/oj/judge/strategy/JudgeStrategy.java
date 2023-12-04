package com.cc.oj.judge.strategy;
import com.cc.oj.judge.codesandbox.model.JudgeContext;
import com.cc.oj.model.dto.question.submit.JudgeInfo;

/**
 * @Author cwc
 * @Date 2023/12/4 9:23
 * @Version 1.0
 */
public interface JudgeStrategy {
    /**
     *
     * @param judgeContext 定义策略模式所需要的所有参数
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
