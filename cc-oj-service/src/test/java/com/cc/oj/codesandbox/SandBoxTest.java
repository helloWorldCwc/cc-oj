package com.cc.oj.codesandbox;

import com.cc.oj.judge.codesandbox.CodeSandBox;
import com.cc.oj.judge.codesandbox.CodeSandBoxFactory;
import com.cc.oj.judge.codesandbox.CodeSandBoxProxy;
import com.cc.oj.judge.codesandbox.impl.ExampleCodeSandBoxImpl;
import com.cc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.cc.oj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cwc
 * @Date 2023/12/1 11:06
 * @Version 1.0
 */
@SpringBootTest
@Slf4j
public class SandBoxTest {
    @Test
    void test(){
        CodeSandBox codeSandBox = new ExampleCodeSandBoxImpl();
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }
    @Test
    void testFactory(){
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance("remote");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }
    @Test
    void testProxy(){
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance("remote");
        codeSandBox = new CodeSandBoxProxy(codeSandBox);
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }
}
