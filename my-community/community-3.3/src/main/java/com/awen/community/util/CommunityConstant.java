package com.awen.community.util;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-27 19:45
 */
public interface CommunityConstant {
    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 0;
    /**
     * 重复激活
     */
    // ctrl + shift + u 大小写
    int ACTIVATION_REPEAT = 1;
    /**
     * 激活失败
     */
    int ACTIVATION_FAILURE = 2;


    /**
     * 默认状态的登录凭证的超时时间
     */
    //12小时
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * 记住状态的登录凭证超时时间
     */
    //比较长的时间 100天   一般是一个月吧 一个星期
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;
}
