package com.awen.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-25 7:07
 */
//装配一个第三方bean

@Configuration
public class HelloConfig {
    //实例化一次反复用
    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    //返回的对象将被装配到容器里
}
