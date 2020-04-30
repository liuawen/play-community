package com.awen.community.controller;

import com.awen.community.entity.DiscussPost;
import com.awen.community.entity.User;
import com.awen.community.service.DiscussPostService;
import com.awen.community.util.CommunityUtil;
import com.awen.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-05-01 0:00
 */

@Controller
@RequestMapping("/discuss")
public class DiscussPostController {

    @Autowired
    private DiscussPostService discussPostService;

    //得获取当前用户呢
    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title, String content) {
        //发帖要登录哦
        User user = hostHolder.getUser();
        if (user == null) {
            //403 没有权限
            return CommunityUtil.getJSONString(403, "你还没有登录哦!");
        }

        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);

        // 报错的情况,将来统一处理.  错误其他得地方处理
        return CommunityUtil.getJSONString(0, "发布成功!");
    }

}

