package com.cc.oj.judge.codesandbox.model;

import com.cc.oj.model.dto.question.submit.JudgeInfo;
import lombok.*;

import java.util.List;

/**
 * @Author cwc
 * @Date 2023/12/1 10:06
 * @Version 1.0
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class ExecuteCodeResponse {
    /**
     * 判题状态
     */
    private Integer status;
    /**
     * 处理消息
     */
    private String message;

    /**
     * 输出用例
     */
    private List<String> outputList;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;
}
