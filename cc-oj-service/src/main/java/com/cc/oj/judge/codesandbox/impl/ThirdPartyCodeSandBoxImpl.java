package com.cc.oj.judge.codesandbox.impl;

import com.cc.oj.judge.codesandbox.CodeSandBox;
import com.cc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.cc.oj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @Author cwc
 * @Date 2023/12/1 10:51
 * @Version 1.0
 */
public class ThirdPartyCodeSandBoxImpl implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest request) {
        System.out.println("第三方代码沙箱实现");
        return null;
    }
}
