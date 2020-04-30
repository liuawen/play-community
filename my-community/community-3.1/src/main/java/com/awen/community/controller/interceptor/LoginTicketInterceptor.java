package com.awen.community.controller.interceptor;

import com.awen.community.entity.LoginTicket;
import com.awen.community.entity.User;
import com.awen.community.service.UserService;
import com.awen.community.util.CookieUtil;
import com.awen.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-30 11:01
 */
@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从cookie中获取凭证   cookie 获取 ticket
        // request获取cookie  来封装一下啊 CookieUtil   request ticket
        String ticket = CookieUtil.getValue(request, "ticket");
        //登录了  查询ticket
        if (ticket != null) {
            // 查询凭证
            LoginTicket loginTicket = userService.findLoginTicket(ticket);

            // 检查凭证是否有效
            if (loginTicket != null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date())) {
                // 根据凭证查询用户
                User user = userService.findUserById(loginTicket.getUserId());
                // 在本次请求中持有用户
                // 一个浏览器处理多个  多线程并发
                // user 存进去
                hostHolder.setUser(user);
            }
        }

        return true;
    }
    //模板之前调
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //得到当前线程持有的user
        User user = hostHolder.getUser();
        if (user != null && modelAndView != null) {
            modelAndView.addObject("loginUser", user);
        }
    }
    //清理掉
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}