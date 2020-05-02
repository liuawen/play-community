package com.awen.community.controller;

import com.awen.community.entity.Comment;
import com.awen.community.service.CommentService;
import com.awen.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-05-01 20:38
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private HostHolder hostHolder;
    //discussPostId 帖子id 搞定
    //Comment  隐含的值
    @RequestMapping(path = "/add/{discussPostId}", method = RequestMethod.POST)
    public String addComment(@PathVariable("discussPostId") int discussPostId, Comment comment) {
        //有可能用户没有登录  我可以验证
        comment.setUserId(hostHolder.getUser().getId());
        //有效的
        comment.setStatus(0);
        comment.setCreateTime(new Date());
        commentService.addComment(comment);

        return "redirect:/discuss/detail/" + discussPostId;
    }

}
