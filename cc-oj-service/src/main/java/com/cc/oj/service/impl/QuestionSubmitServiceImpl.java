package com.cc.oj.service.impl;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.oj.constant.CommonConstant;
import com.cc.oj.model.dto.question.submit.QuestionSubmitJudgeInfo;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cc.oj.common.ErrorCode;
import com.cc.oj.exception.BusinessException;
import com.cc.oj.exception.ThrowUtils;
import com.cc.oj.mapper.QuestionSubmitMapper;
import com.cc.oj.model.dto.question.submit.QuestionSubmitAddRequest;
import com.cc.oj.model.dto.question.submit.QuestionSubmitQueryRequest;
import com.cc.oj.model.entity.QuestionInfo;
import com.cc.oj.model.entity.QuestionSubmit;
import com.cc.oj.model.entity.User;
import com.cc.oj.model.enums.QuestionSubmitLanguageEnum;
import com.cc.oj.model.enums.QuestionSubmitStatusEnum;
import com.cc.oj.model.vo.QuestionSubmitVO;
import com.cc.oj.service.QuestionInfoService;
import com.cc.oj.service.QuestionSubmitService;
import com.cc.oj.service.UserService;
import com.cc.oj.utils.SqlUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author cc
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2023-11-23 14:39:45
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{
    @Resource
    private UserService userService;
    @Resource
    private QuestionInfoService questionInfoService;

    @Override
    @Transactional
    public int doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        QuestionSubmit questionSubmit = BeanUtil.copyProperties(questionSubmitAddRequest, QuestionSubmit.class);
        questionSubmit.setUserId(loginUser.getId());
        questionSubmit.setStatus(0); // 带判题
        this.validQuestionSubmit(questionSubmit, true);
        Long questionId = questionSubmitAddRequest.getQuestionId();
        QuestionInfo questionInfo = questionInfoService.getById(questionId);
        if(questionInfo == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目不存在");
        }
        boolean save = save(questionSubmit);
        ThrowUtils.throwIf(!save, ErrorCode.SYSTEM_ERROR);
        // TODO 判题服务执行
        return 1;
    }

    @Override
    public void validQuestionSubmit(QuestionSubmit questionSubmit, boolean isAdd) {
        Long id = questionSubmit.getId();
        String language = questionSubmit.getLanguage();
        Long questionId = questionSubmit.getQuestionId();
        String code = questionSubmit.getCode();
        Integer status = questionSubmit.getStatus();
        if(!isAdd){
            if(id == null || id <= 0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "id不能为空");
            }
        }
        if(questionId == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "问题id不能为空");
        }
        if(!QuestionSubmitLanguageEnum.getValues().contains(language)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "暂不支持该语言");
        }
        if(!QuestionSubmitStatusEnum.getValues().contains(status)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "所选择状态有误");
        }
        if(StrUtil.isBlank(code)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "提交的代码不能为空");
        }
    }

    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest submitQueryRequest, User loginUser) {
         QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
         String language = submitQueryRequest.getLanguage();
         Long questionId = submitQueryRequest.getQuestionId();
         QuestionSubmitJudgeInfo submitJudgeInfo = submitQueryRequest.getSubmitJudgeInfo();
         Integer status = submitQueryRequest.getStatus();
         String sortField = submitQueryRequest.getSortField();
         String sortOrder = submitQueryRequest.getSortOrder();
         queryWrapper.eq(status != null,"status", status);
         queryWrapper.like(StrUtil.isNotBlank(language),"language", language);
         // 没有被删除的数据
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> page, User loginUser) {
        List<QuestionSubmit> questionSubmitList = page.getRecords();
        Page<QuestionSubmitVO> questionSubmitVOpage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        if(CollectionUtils.isEmpty(questionSubmitList)){
            return questionSubmitVOpage;
        }
        List<Long> userIds = questionSubmitList.stream().map(questionSubmit -> questionSubmit.getUserId()).collect(Collectors.toList());
        Map<Long, User> userMap = userService.listByIds(userIds).stream().collect(Collectors.toMap(User::getId, user -> user));
        // 管理员啊可以看见所有用户提交的答案，否则只能看见自己
        List<QuestionSubmitVO> questionSubmitVOList = questionSubmitList.stream().map(questionSubmit -> {
            QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
            if (!userService.isAdmin(loginUser) && !Objects.equals(questionSubmit.getUserId(), loginUser.getId())) {
                questionSubmit.setCode("");
            }
            User user = userMap.get(questionSubmit.getUserId());
            questionSubmitVO.setUser(userService.getUserVO(user));
            questionSubmitVO.setSubmitJudgeInfo(JSONUtil.toBean(questionSubmit.getJudgeInfo(), QuestionSubmitJudgeInfo.class));
            BeanUtil.copyProperties(questionSubmit, questionSubmitVO);

            return questionSubmitVO;
        }).collect(Collectors.toList());
        questionSubmitVOpage.setRecords(questionSubmitVOList);
        return questionSubmitVOpage;
    }
}




