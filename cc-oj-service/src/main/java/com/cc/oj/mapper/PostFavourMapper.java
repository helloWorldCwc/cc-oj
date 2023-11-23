package com.cc.oj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.oj.model.entity.Post;
import com.cc.oj.model.entity.PostFavour;
import com.cc.oj.model.entity.QuestionInfo;
import com.cc.oj.model.entity.QuestionSubmit;
import org.apache.ibatis.annotations.Param;

/**
 * 帖子收藏数据库操作
 *
 * @author <a href="https://github.com/helloWorldCwc">程序员cc</a>
 * 
 */
public interface PostFavourMapper extends BaseMapper<PostFavour> {

    /**
     * 分页查询收藏帖子列表
     *
     * @param page
     * @param queryWrapper
     * @param favourUserId
     * @return
     */
    Page<Post> listFavourPostByPage(IPage<Post> page, @Param(Constants.WRAPPER) Wrapper<Post> queryWrapper,
            long favourUserId);

    /**
    * @author cc
    * @description 针对表【question_info(题目信息)】的数据库操作Mapper
    * @createDate 2023-11-23 14:39:45
    * @Entity com.cc.oj.model.entity.QuestionInfo
    */
    interface QuestionInfoMapper extends BaseMapper<QuestionInfo> {

    }

    /**
    * @author cc
    * @description 针对表【question_submit(题目提交)】的数据库操作Mapper
    * @createDate 2023-11-23 14:39:45
    * @Entity com.cc.oj.model.entity.QuestionSubmit
    */
    interface QuestionSubmitMapper extends BaseMapper<QuestionSubmit> {

    }
}




