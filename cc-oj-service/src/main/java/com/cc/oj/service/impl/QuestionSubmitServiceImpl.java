package com.cc.oj.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cc.oj.mapper.QuestionSubmitMapper;
import com.cc.oj.model.dto.question.submit.QuestionSubmitAddRequest;
import com.cc.oj.model.entity.QuestionSubmit;
import com.cc.oj.model.entity.User;
import com.cc.oj.service.QuestionSubmitService;
import org.springframework.stereotype.Service;

/**
* @author cc
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2023-11-23 14:39:45
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{

    @Override
    public int doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        QuestionSubmit questionSubmit = BeanUtil.copyProperties(questionSubmitAddRequest, QuestionSubmit.class);
        boolean save = save(questionSubmit);
        return 1;
    }
}




