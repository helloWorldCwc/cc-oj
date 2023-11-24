package com.cc.oj.model.dto.question;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 更新请求
 *
 * @author <a href="https://github.com/helloWorldCwc">程序员cc</a>
 * 
 */
@Data
public class QuestionInfoUpdateRequest implements Serializable {
    private long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 题目标签列表（json 数组）
     */
    private List<String> tags;

    /**
     * 答案
     */
    private String answer;
    /**
     * 测试用例
     */
    private List<JudgeCase> judgeCaseList;

    /**
     * 评测配置
     */
    private JudgeConfig judgeConfigDto;

    /**
     * 提交数
     */
    private Integer submitNum;

    /**
     * 通过数
     */
    private Integer acceptedNum;


    /**
     * 通过率
     */
    private Integer acceptedRate;



    private static final long serialVersionUID = 1L;
}