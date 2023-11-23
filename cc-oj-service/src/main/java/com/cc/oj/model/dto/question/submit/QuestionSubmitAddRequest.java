package com.cc.oj.model.dto.question.submit;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Author cwc
 * @Date 2023/11/23 15:47
 * @Version 1.0
 */
@Data
public class QuestionSubmitAddRequest {

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
     * 判题状态(0待判题，1判题中，2成功，3失败)
     */
    private Integer status;

}
