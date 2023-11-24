package com.cc.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.oj.model.dto.question.submit.QuestionSubmitAddRequest;
import com.cc.oj.model.dto.question.submit.QuestionSubmitQueryRequest;
import com.cc.oj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.oj.model.entity.User;
import com.cc.oj.model.vo.QuestionSubmitVO;

/**
* @author cc
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2023-11-23 14:39:45
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    int doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);
    void validQuestionSubmit(QuestionSubmit questionSubmit, boolean isAdd);

    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest submitQueryRequest, User loginUser);

    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> page, User loginUser);
}
