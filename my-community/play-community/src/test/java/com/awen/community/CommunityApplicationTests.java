package com.awen.community;

import com.awen.community.dao.HelloDao;
import com.awen.community.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class CommunityApplicationTests implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Test
    public void contextLoads() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Test
    public void testApplicationContext(){

        System.out.println(applicationContext);
        //调用方与实现类不会多关系
        HelloDao helloDao = applicationContext.getBean(HelloDao.class);
        System.out.println(helloDao.select());

        //还想要Hibernate实现的   那就指定bean的名字
        helloDao = applicationContext.getBean("helloHibernate", HelloDao.class);
        System.out.println(helloDao.select());
    }
    /*
    程序启动 Bean实例化  程序停止bean销毁   单例的   只被实例化一起 销毁一次
实例化HelloService
初始化HelloService
2020-04-25 06:58:46.025  INFO 22548 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2020-04-25 06:58:46.891  WARN 22548 --- [           main] ion$DefaultTemplateResolverConfiguration : Cannot find template location: classpath:/templates/ (please add some templates or check your Thymeleaf configuration)
2020-04-25 06:58:47.152  INFO 22548 --- [           main] c.a.community.CommunityApplicationTests  : Started CommunityApplicationTests in 7.328 seconds (JVM running for 10.043)
com.awen.community.service.HelloService@6eb089e6
2020-04-25 06:58:48.127  INFO 22548 --- [extShutdownHook] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
销毁HelloService
     */
    @Test
    public void testBeanManagement(){
        HelloService helloService = applicationContext.getBean(HelloService.class);
        System.out.println(helloService);
        //只实例化一次了  说明Spring容器管理  默认是单例的
        helloService = applicationContext.getBean(HelloService.class);
        System.out.println(helloService);
        //com.awen.community.service.HelloService@6eb089e6
        //com.awen.community.service.HelloService@6eb089e6

        //怎么不一样呢  HelloService加注解 @Scope("prototype") 很少使用
    }
    @Test
    public void testBeanConfig(){

        SimpleDateFormat simpleDateFormat =
                applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
        //2020-04-25 07:12:07
        //这是我们主动去获取容器  拿一个bean   还有更简便的   我们是主动获取不好
        // Spring IOC 控制反转   DI 依赖注入  我们可以使用
    }
    //声明  当前的bean注入  方便简单 不用实例化
    // 构造器注入  set注入   属性注入
    @Autowired
    //我有多个实现  我想指定一个
    @Qualifier("helloHibernate")
    private HelloDao helloDao;
    //获取HelloService
    @Autowired
    private HelloService helloService;
    @Autowired
    private  SimpleDateFormat simpleDateFormat;
    @Test
    public void testDI(){
        System.out.println(helloDao);
        //可以
        System.out.println(helloService);

        System.out.println(simpleDateFormat);
        System.out.println(simpleDateFormat.format(new Date()));

        //实例化HelloService
        //初始化HelloService
        //com.awen.community.dao.HelloDaoHibernateImpl@b791a81
        //com.awen.community.service.HelloService@4391a2d8
        //java.text.SimpleDateFormat@4f76f1a0
        //2020-04-25 07:20:01
    }

}
