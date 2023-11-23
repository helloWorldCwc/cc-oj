package com.cc.oj.service;

import com.cc.oj.model.dto.question.submit.QuestionSubmitAddRequest;
import com.cc.oj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.oj.model.entity.User;

/**
* @author cc
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2023-11-23 14:39:45
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    int doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);
}
