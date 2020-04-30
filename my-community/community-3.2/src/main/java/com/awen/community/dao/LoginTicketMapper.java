package com.awen.community.dao;

import com.awen.community.entity.LoginTicket;
import com.mysql.jdbc.log.Log;
import org.apache.ibatis.annotations.*;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-28 16:53
 */
@Mapper
public interface LoginTicketMapper {
    //写这个非常容易出错的

    // 2种实现这个 一种mapper/xxx-mapper.xml 一种注解SQL

    @Insert({
            "insert into login_ticket(user_id,ticket,status,expired) ",
            "values(#{userId},#{ticket},#{status},#{expired})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    @Select({
            "select id,user_id, ticket, status, expired ",
            "from login_ticket where ticket = #{ticket}"
    })
    LoginTicket selectByTicket(String ticket);

    //状态  更改    逻辑删除  作废了
//    @Update({
//            "update login_ticket set status=#{status} where ticket=#{ticket}"
//    })
    @Update({
            "<script> ",
            "update login_ticket set status=#{status} where ticket=#{ticket} ",
            "<if test=\"ticket!=null\"> ",
            "and 1=1 ",
            "</if>",
            "</script>"
    })
    int updateStatus(String ticket, int status);
}
