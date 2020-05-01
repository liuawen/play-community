package com.awen.community.util;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-30 11:02
 */

import com.awen.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * 持有用户信息,用于代替session对象.
 * 容器作用
 */
@Component
public class HostHolder {
    //存每个线程对应的user   线程隔离
    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
        return users.get();
    }
    //清理
    public void clear() {
        users.remove();
    }

}

