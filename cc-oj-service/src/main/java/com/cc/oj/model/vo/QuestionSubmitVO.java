package com.cc.oj.model.vo;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cc.oj.model.dto.question.submit.QuestionSubmitJudgeInfo;
import com.cc.oj.model.entity.QuestionSubmit;
import com.google.gson.Gson;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子视图
 *
 * @author <a href="https://github.com/helloWorldCwc">程序员cc</a>
 * 
 */
@Data
public class QuestionSubmitVO implements Serializable {

    private final static Gson GSON = new Gson();
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
    private UserVO user;

    /**
     * 创建时间
     */
    private Date createTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 包装类转对象
     *
     * @param questionSubmitVO
     * @return
     */
    public static QuestionSubmit voToObj(QuestionSubmitVO questionSubmitVO) {
        if (questionSubmitVO == null) {
            return null;
        }
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitVO, questionSubmit);
        questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(questionSubmitVO.getSubmitJudgeInfo()));
        return questionSubmit;
    }

    /**
     * 对象转包装类
     *
     * @param questionSubmit
     * @return
     */
    public static QuestionSubmitVO objToVo(QuestionSubmit questionSubmit) {
        if (questionSubmit == null) {
            return null;
        }
        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
        BeanUtils.copyProperties(questionSubmit, questionSubmitVO);
        questionSubmitVO.setSubmitJudgeInfo(JSONUtil.toBean(questionSubmit.getJudgeInfo(), QuestionSubmitJudgeInfo.class));
        return questionSubmitVO;
    }
}
