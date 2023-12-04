package com.cc.oj.judge.codesandbox;

import com.cc.oj.judge.codesandbox.impl.ExampleCodeSandBoxImpl;
import com.cc.oj.judge.codesandbox.impl.RemoteCodeSandBoxImpl;
import com.cc.oj.judge.codesandbox.impl.ThirdPartyCodeSandBoxImpl;

/**
 * @Author cwc
 * @Date 2023/12/1 11:10
 * @Version 1.0
 */
// 代码沙箱工厂类，用于创建代码沙箱实例
public class CodeSandBoxFactory {
    public static CodeSandBox newInstance(String type){
        switch (type){
            case "example":
                return new ExampleCodeSandBoxImpl();
            case "remote":
                return new RemoteCodeSandBoxImpl();
            case "thirdParty":
                return new ThirdPartyCodeSandBoxImpl();
            default:
                return new ExampleCodeSandBoxImpl();
        }
    }
}

