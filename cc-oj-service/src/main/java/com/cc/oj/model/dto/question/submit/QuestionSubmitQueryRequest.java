package com.cc.oj.model.dto.question.submit;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cc.oj.common.PageRequest;
import com.cc.oj.model.dto.question.JudgeConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 查询请求
 *
 * @author <a href="https://github.com/helloWorldCwc">程序员cc</a>
 * 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    /**
     * 提交语言
     */
    private String language;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 提交代码
     */
    private String code;

    /**
     * 判题信息
     */
    private QuestionSubmitJudgeInfo submitJudgeInfo;

    /**
     * 判题状态(0待判题，1判题中，2成功，3失败)
     */
    private Integer status;

    /**
     * 提交用户id
     */
    private Long userId;


    private static final long serialVersionUID = 1L;
}