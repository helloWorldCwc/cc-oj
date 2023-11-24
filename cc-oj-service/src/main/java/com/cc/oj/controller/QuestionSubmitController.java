package com.cc.oj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.oj.common.BaseResponse;
import com.cc.oj.common.ErrorCode;
import com.cc.oj.common.ResultUtils;
import com.cc.oj.exception.BusinessException;
import com.cc.oj.model.dto.question.submit.QuestionSubmitAddRequest;
import com.cc.oj.model.dto.question.submit.QuestionSubmitJudgeInfo;
import com.cc.oj.model.dto.question.submit.QuestionSubmitQueryRequest;
import com.cc.oj.model.entity.QuestionSubmit;
import com.cc.oj.model.entity.User;
import com.cc.oj.model.vo.QuestionSubmitVO;
import com.cc.oj.service.QuestionSubmitService;
import com.cc.oj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子点赞接口
 *
 * @author <a href="https://github.com/helloWorldCwc">程序员cc</a>
 * 
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 点赞 / 取消点赞
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return resultNum 本次点赞变化数
     */
    @PostMapping("/submit")
    public BaseResponse<Integer> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
            HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能提交
        final User loginUser = userService.getLoginUser(request);
        int result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(result);
    }

    @GetMapping("/page/vo/list")
    public BaseResponse<Page<QuestionSubmitVO>> pageList(QuestionSubmitQueryRequest submitQueryRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        QueryWrapper<QuestionSubmit> queryWrapper =  questionSubmitService.getQueryWrapper(submitQueryRequest, loginUser);
        Page<QuestionSubmit> page = questionSubmitService.page(new Page<QuestionSubmit>(submitQueryRequest.getCurrent(), submitQueryRequest.getPageSize()), queryWrapper);
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(page, loginUser));
    }
}
