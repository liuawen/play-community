package com.awen.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-24 18:23
 */
//@Repository
//扫描 装配
@Repository("helloHibernate")
//给bean来个名字
//bean默认是类名小写
public class HelloDaoHibernateImpl implements HelloDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}
