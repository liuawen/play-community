package com.awen.community.dao;

import com.awen.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-25 15:57
 */
//@Repository
@Mapper
public interface UserMapper {
    User selectById(int id);
    User selectByName(String username);
    User selectByEmail(String email);
    int insertUser(User user);
    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);
    int updatePassword(int id, String password);

}
