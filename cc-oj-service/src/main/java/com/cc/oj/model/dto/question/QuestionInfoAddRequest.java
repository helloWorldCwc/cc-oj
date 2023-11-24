package com.cc.oj.model.dto.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 创建请求
 *
 * @author <a href="https://github.com/helloWorldCwc">程序员cc</a>
 * 
 */
@Data
public class QuestionInfoAddRequest implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    private String tips;

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



    private static final long serialVersionUID = 1L;
}