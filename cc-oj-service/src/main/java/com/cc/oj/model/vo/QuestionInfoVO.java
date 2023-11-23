package com.cc.oj.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cc.oj.model.entity.QuestionInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 帖子视图
 *
 * @author <a href="https://github.com/helloWorldCwc">程序员cc</a>
 * 
 */
@Data
public class QuestionInfoVO implements Serializable {

    private final static Gson GSON = new Gson();
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
    private String tags;
    

    /**
     * 提交数
     */
    private Integer submitNum;

    /**
     * 通过数
     */
    private Integer acceptedNum;

    /**
     * 测试用例
     */
    private String judgeCase;

    /**
     * 评测配置
     */
    private String judgeConfig;

    /**
     * 题目提示
     */
    private String tips;

    /**
     * 通过率
     */
    private Integer acceptedRate;

    

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 包装类转对象
     *
     * @param questionVO
     * @return
     */
    public static QuestionInfo voToObj(QuestionInfoVO questionVO) {
        if (questionVO == null) {
            return null;
        }
        QuestionInfo question = new QuestionInfo();
        BeanUtils.copyProperties(questionVO, question);
        List<String> tagList =  GSON.fromJson(question.getTags(), new TypeToken<List<String>>() {
        }.getType());

        if (tagList != null) {
            question.setTags(GSON.toJson(tagList));
        }
        return question;
    }

    /**
     * 对象转包装类
     *
     * @param question
     * @return
     */
    public static QuestionInfoVO objToVo(QuestionInfo question) {
        if (question == null) {
            return null;
        }
        QuestionInfoVO questionVO = new QuestionInfoVO();
        BeanUtils.copyProperties(question, questionVO);
        questionVO.setTags(GSON.fromJson(question.getTags(), new TypeToken<List<String>>() {
        }.getType()));
        return questionVO;
    }
}
