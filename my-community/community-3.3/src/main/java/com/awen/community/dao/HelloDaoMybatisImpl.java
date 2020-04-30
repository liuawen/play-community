package com.awen.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-24 18:27
 */
@Repository
@Primary
//优先
public class HelloDaoMybatisImpl implements  HelloDao {

    @Override
    public String select() {
        return "Mybatis";
    }
}
