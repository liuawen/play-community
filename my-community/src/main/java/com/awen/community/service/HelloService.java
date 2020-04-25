package com.awen.community.service;

import com.awen.community.dao.HelloDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-24 19:20
 */
//容器来管理  自动调
@Service
//默认singleton
//@Scope("singleton")
//@Scope("prototype")
//每次访问bean创建一个实例
//很少使用

public class HelloService {
    //service依赖dao
    @Autowired
    private HelloDao helloDao;
    public String find(){
        return helloDao.select();
    }
    public HelloService(){
        System.out.println("实例化HelloService");
    }
    //构造之后调用
    @PostConstruct
    public void init(){
        System.out.println("初始化HelloService");
    }
    //销毁之前调用
    @PreDestroy
    public void destroy(){
        System.out.println("销毁HelloService");
    }
}
