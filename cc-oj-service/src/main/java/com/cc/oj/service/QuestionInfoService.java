package com.cc.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.oj.model.dto.question.QuestionInfoQueryRequest;
import com.cc.oj.model.entity.QuestionInfo;
import com.cc.oj.model.vo.QuestionInfoVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 帖子服务
 *
 * @author <a href="https://github.com/helloWorldCwc">程序员cc</a>
 * 
 */
public interface QuestionInfoService extends IService<QuestionInfo> {

    /**
     * 校验
     *
     * @param questionInfo
     * @param add
     */
    void validQuestionInfo(QuestionInfo questionInfo, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionInfoQueryRequest
     * @return
     */
    QueryWrapper<QuestionInfo> getQueryWrapper(QuestionInfoQueryRequest questionInfoQueryRequest);

    /**
     * 获取帖子封装
     *
     * @param questionInfo
     * @param request
     * @return
     */
    QuestionInfoVO getQuestionInfoVO(QuestionInfo questionInfo, HttpServletRequest request);

    /**
     * 分页获取帖子封装
     *
     * @param questionInfoPage
     * @param request
     * @return
     */
    Page<QuestionInfoVO> getQuestionInfoVOPage(Page<QuestionInfo> questionInfoPage, HttpServletRequest request);
}
