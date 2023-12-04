package com.cc.oj.judge.manager;

import com.cc.oj.judge.codesandbox.model.JudgeContext;
import com.cc.oj.judge.strategy.JudgeStrategy;
import com.cc.oj.judge.strategy.impl.DefaultJudgeStrategy;
import com.cc.oj.judge.strategy.impl.JavaJudgeStrategy;
import com.cc.oj.model.dto.question.submit.JudgeInfo;
import com.cc.oj.model.enums.QuestionSubmitLanguageEnum;
import org.springframework.stereotype.Service;

/**
 * @Author cwc
 * @Date 2023/12/4 9:44
 * @Version 1.0
 */
@Service
public class JudgeManager {
    public JudgeInfo doJudge(JudgeContext judgeContext){
        String language = judgeContext.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if(QuestionSubmitLanguageEnum.JAVA.getValue().equals(language)){
            judgeStrategy = new JavaJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
