package com.awen.community.service;

import com.awen.community.dao.UserMapper;
import com.awen.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-25 17:51
 */
@Service
public class UserService {
    @Autowired(required = false)
    private UserMapper userMapper;
    public User findUserById(int id){
        return userMapper.selectById(id);
    }
}
