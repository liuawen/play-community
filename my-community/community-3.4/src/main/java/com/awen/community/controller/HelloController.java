package com.awen.community.controller;

import com.awen.community.service.HelloService;
import com.awen.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-24 17:58
 * controller 调用 service  调用 dao    依赖注入来搞
 * 理解IOC概念   管理Bean  不是我们去实现化去set
 * 而是容器去管理  降低bean的耦合度 
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private HelloService helloService;

    //controller调用service  注入service
    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return helloService.find();
    }
    @RequestMapping("/spring")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring!!!";
    }
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());//请求路径
        //很老的 没怎么用了
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
//        try {
//            PrintWriter writer = response.getWriter();
//            writer.write("<h1>你好呀<h1>");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //Java7小特性   会自动关掉
        try(
                PrintWriter writer = response.getWriter();
        ) {
            writer.write("<h1>你好呀<h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //更简便的处理  请求 响应  的方式
    //接受请求的数据  返回响应的数据

    //GET请求
    //获取数据 默认发送的

    // /students?current=1&limit=20
    //请求方式很多  限制只能GET
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
    // students/123  路径的一部分
    @RequestMapping(path = "students/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }
    //GET传递参数有限 在链接上

    // POST请求
    //? @PutMapping
    @RequestMapping(path = "/students", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 响应HTML数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","柳江雪老师");
        mav.addObject("age","25");
        mav.setViewName("/demo/view");//view.html
        return mav;
    }
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","xxx大学");
        model.addAttribute("age", "100");
        return "/demo/view";
    }
    //响应JSON数据（异步请求）
    //例如 注册B站的时候 我输完了用户名就显示该昵称已被占用 悄悄服务器请求去了
    //没刷新的  局部的
    //Java对象 返回浏览器  浏览器解析  JS对象   JSON两者兼容
    //Java对象 -> JSON字符串 -> JS对象
    //响应这样的字符串

    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "法外狂徒张三字");
        emp.put("age", 18);
        emp.put("salary", 8999.99);
        //自动map 转换 JSON
        return  emp;
    }
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "法外狂徒张三字");
        emp.put("age", 18);
        emp.put("salary", 8999.99);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 20);
        emp.put("salary", 9999.99);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "张六");
        emp.put("age", 40);
        emp.put("salary", 10999.99);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "张九");
        emp.put("age", 60);
        emp.put("salary", 11999.99);
        list.add(emp);

        return  list;
    }
    // cookie实例
    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        //创建cookie
        Cookie cookie =  new Cookie("code", CommunityUtil.generateUUID());
        //设置cookie生效的范围   内存  硬盘
        //限制
        cookie.setPath("/community/hello");
        //设置cookie的生存时间
        cookie.setMaxAge(60 * 10);
        //发送cookie
        response.addCookie(cookie);
        return "set cookie";
    }
    @RequestMapping(path = "cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code){
        System.out.println(code);
        return "get cookie";
    }
    //session 实例    服务端保存
    @RequestMapping(path = "session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session){
        session.setAttribute("id",1);
        session.setAttribute("name", "Test");
        return "set session";
    }
    @RequestMapping(path = "session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }
    // ajax示例
    @RequestMapping(path = "/ajax", method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return CommunityUtil.getJSONString(0, "操作成功!");
    }
}
