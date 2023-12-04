package com.cc.oj.judge;

/**
 * @Author cwc
 * @Date 2023/12/1 14:41
 * @Version 1.0
 */
// 判题服务接口
public interface JudgeService {
    /**
     *
     * @param questionSubmitId 题目提交id
     */
    void doJudgeSubmit(long questionSubmitId);
}