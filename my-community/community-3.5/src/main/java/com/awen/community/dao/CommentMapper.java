package com.awen.community.dao;

import com.awen.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-05-01 14:15
 */

@Mapper
public interface CommentMapper {
    //实体的评论
    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);
    //查询  数据的条目数
    int selectCountByEntity(int entityType, int entityId);

}

