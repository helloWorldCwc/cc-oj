package com.cc.oj.model.dto.question.submit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author cwc
 * @Date 2023/12/1 14:26
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JudgeInfo {
    private Integer timeLimit;
    private Integer memoryLimit;
    private String message;
}
