package com.awen.community.config;

import com.awen.community.annotation.LoginRequired;
import com.awen.community.controller.interceptor.HelloInterceptor;
import com.awen.community.controller.interceptor.LoginRequiredInterceptor;
import com.awen.community.controller.interceptor.LoginTicketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-30 11:00
 */
@Configuration
//配置类  拦截器
public class WebMvcConfig implements WebMvcConfigurer {
    //注入
//    @Autowired(required = false)
    @Autowired
    private HelloInterceptor helloInterceptor;

    @Autowired
    private LoginTicketInterceptor loginTicketInterceptor;

    @Autowired
    private LoginRequiredInterceptor loginRequiredInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(helloInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg")
                .addPathPatterns("/register", "/login");
        //排除  静态资源不需要拦截  /**/*.css   static 所有的
        //addPathPatterns  明确拦截的路径

        registry.addInterceptor(loginTicketInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");

        //不处理静态的
        registry.addInterceptor(loginRequiredInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
    }

}
