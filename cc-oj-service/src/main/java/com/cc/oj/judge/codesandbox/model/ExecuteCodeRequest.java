package com.cc.oj.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class ExecuteCodeRequest {
    /**
     * 代码
     */
    private String code;
    /**
     * 编程语言
     */
    private String language;
    /***
     * 测试用例
     */
    private List<String> inputList;
}
