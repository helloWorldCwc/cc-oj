package com.cc.oj.model.dto.question.submit;

import lombok.Data;

/**
 * @Author cwc
 * @Date 2023/11/24 11:45
 * @Version 1.0
 */
@Data
public class QuestionSubmitJudgeInfo {
    private String  message;
    private Long time;
    private Long memory;
}
