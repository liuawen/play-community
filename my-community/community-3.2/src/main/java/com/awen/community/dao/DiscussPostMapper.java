package com.awen.community.dao;

import com.awen.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-25 17:08
 */
@Mapper
public interface DiscussPostMapper {
    //我的帖子

    //分页  offset 页  limit 数
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

//    int selectDiscussPostRows(int userId);
    //@Param注解用于给参数取个别名
    //取一个参数 动态拼 有且只有一个条件  必须取别名
    //如果只有一个参数，并且在<if>里使用
    int selectDiscussPostRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);
}
