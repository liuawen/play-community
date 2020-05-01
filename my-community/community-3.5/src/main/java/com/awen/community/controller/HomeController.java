package com.awen.community.controller;

import com.awen.community.entity.DiscussPost;
import com.awen.community.entity.Page;
import com.awen.community.entity.User;
import com.awen.community.service.DiscussPostService;
import com.awen.community.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-25 17:57
 */
//没写Controller There was an unexpected error (type=Not Found, status=404).
//No message available
@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

//    @RequestMapping(path = "/", method = RequestMethod.GET)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        //方法调用之前，Spring MVC 会自动实例化Model和Page，并将Page注入Model
        //所以，在Thymeleaf中可以直接访问Page对象中的数据。
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");


//        List<DiscussPost> list = discussPostService.findDiscussPosts(0, 0,10);
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(),page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null){
            for (DiscussPost post: list){
                Map<String , Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }
//        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("discussPosts", discussPosts);

        return "/index";
    }
}
