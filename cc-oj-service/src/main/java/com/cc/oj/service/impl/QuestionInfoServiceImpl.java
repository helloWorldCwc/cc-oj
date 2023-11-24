package com.cc.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.oj.common.ErrorCode;
import com.cc.oj.constant.CommonConstant;
import com.cc.oj.exception.BusinessException;
import com.cc.oj.exception.ThrowUtils;
import com.cc.oj.mapper.QuestionInfoMapper;
import com.cc.oj.model.dto.question.QuestionInfoQueryRequest;
import com.cc.oj.model.entity.QuestionInfo;

import com.cc.oj.model.entity.User;
import com.cc.oj.model.vo.QuestionInfoVO;
import com.cc.oj.model.vo.UserVO;
import com.cc.oj.service.QuestionInfoService;
import com.cc.oj.service.UserService;
import com.cc.oj.utils.SqlUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 帖子服务实现
 *
 * @author <a href="https://github.com/helloWorldCwc">程序员cc</a>
 * 
 */
@Service
@Slf4j
public class QuestionInfoServiceImpl extends ServiceImpl<QuestionInfoMapper, QuestionInfo> implements QuestionInfoService {


    @Resource
    private UserService userService;

    @Override
    public void validQuestionInfo(QuestionInfo questionInfo, boolean add) {
        if (questionInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String content = questionInfo.getContent();
        String title = questionInfo.getTitle();
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(title, content), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(title) && title.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长");
        }
        if (StringUtils.isNotBlank(content) && content.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
    }

    /**
     * 获取查询包装类
     *
     * @param questionInfoQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<QuestionInfo> getQueryWrapper(QuestionInfoQueryRequest questionInfoQueryRequest) {
        QueryWrapper<QuestionInfo> queryWrapper = new QueryWrapper<>();
        if (questionInfoQueryRequest == null) {
            return queryWrapper;
        }
        String sortField = questionInfoQueryRequest.getSortField();
        String sortOrder = questionInfoQueryRequest.getSortOrder();
        Long id = questionInfoQueryRequest.getId();
        String title = questionInfoQueryRequest.getTitle();
        List<String> tagList = questionInfoQueryRequest.getTags();
        Long userId = questionInfoQueryRequest.getUserId();
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
        if (CollectionUtils.isNotEmpty(tagList)) {
            for (String tag : tagList) {
                queryWrapper.like("tags", "\"" + tag + "\"");
            }
        }
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }



    @Override
    public QuestionInfoVO getQuestionInfoVO(QuestionInfo questionInfo, HttpServletRequest request) {
        QuestionInfoVO questionInfoVO = QuestionInfoVO.objToVo(questionInfo);
        questionInfoVO.setUser(userService.getUserVO(userService.getById(questionInfo.getUserId())));
        return questionInfoVO;
    }

    @Override
    public Page<QuestionInfoVO> getQuestionInfoVOPage(Page<QuestionInfo> questionInfoPage, HttpServletRequest request) {
        List<QuestionInfo> questionInfoList = questionInfoPage.getRecords();
        Page<QuestionInfoVO> questionInfoVOPage = new Page<>(questionInfoPage.getCurrent(), questionInfoPage.getSize(), questionInfoPage.getTotal());
        if (CollectionUtils.isEmpty(questionInfoList)) {
            return questionInfoVOPage;
        }
        List<Long> userIds = questionInfoList.stream().map(QuestionInfo::getUserId).collect(Collectors.toList());
        Map<Long, User> userMap= userService.listByIds(userIds).stream().collect(Collectors.toMap(User::getId, user -> user));
        List<QuestionInfoVO> questionInfoVOList = questionInfoList.stream().map(questionInfo -> {
            QuestionInfoVO questionInfoVO = QuestionInfoVO.objToVo(questionInfo);
            User user = userMap.get(questionInfo.getUserId());
            questionInfoVO.setUser(userService.getUserVO(user));
            return questionInfoVO;
        }).collect(Collectors.toList());
        questionInfoVOPage.setRecords(questionInfoVOList);
        return questionInfoVOPage;
    }

}




