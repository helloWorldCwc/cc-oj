package com.cc.oj.judge.codesandbox;

import com.cc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.cc.oj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @Author cwc
 * @Date 2023/12/1 10:03
 * @Version 1.0
 */
public interface CodeSandBox {
    ExecuteCodeResponse executeCode(ExecuteCodeRequest request);
}
