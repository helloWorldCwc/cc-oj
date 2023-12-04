package com.cc.oj.judge.codesandbox;

import com.cc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.cc.oj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author cwc
 * @Date 2023/12/1 11:41
 * @Version 1.0
 */
@Slf4j
public class CodeSandBoxProxy implements CodeSandBox{
    private final CodeSandBox codeSandBox;
    public CodeSandBoxProxy(CodeSandBox codeSandBox){
        this.codeSandBox = codeSandBox;
    }
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest request) {
        log.info("请求参数{}", request.toString());
        ExecuteCodeResponse response = this.codeSandBox.executeCode(
                request
        );
        log.info("响应参数{}", response);
        return response;
    }
}
