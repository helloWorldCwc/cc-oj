package com.cc.oj.judge.codesandbox.model;

import com.cc.oj.model.entity.QuestionInfo;
import lombok.Data;
import lombok.ToString;

/**
 * @Author cwc
 * @Date 2023/12/4 9:23
 * @Version 1.0
 */
@Data
@ToString
public class JudgeContext {

    private ExecuteCodeResponse executeCodeResponse;

    private QuestionInfo questionInfo;

    private String language;
}
