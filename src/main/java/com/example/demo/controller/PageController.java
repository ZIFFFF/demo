package com.example.demo.controller;

import com.example.demo.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面跳转控制器
 *
 * */

@Controller
public class PageController {

    @GetMapping("/")            //请求页面的接口链接
    public String LoginPage() { //跳转页面，链接本身是一段字符串，所以使用‘String’类型放回结果
        return "login";         //使用Thymeleaf，会自动定位到页面，如果没有对应的页面会显示‘Cannot resolve MVC View 'login'’
    }

    @GetMapping("/index")
    public String IndexPage(Model model){
        model.addAttribute("user", (SysUser) SecurityUtils.getSubject().getPrincipal());
        return "index";
    }

    @GetMapping("/main")
    public String MainPage() { return "main"; }

    @GetMapping("/user/list")
    public String UserIndexPage() { return "user/index"; }

    @GetMapping("/user/add")
    public String UserAddPage() {
        return "user/add";
    }

    @GetMapping("/user/update")
    public String UserUpdatePage() {
        return "detail";
    }

    @GetMapping("/noAuth")
    @ResponseBody
    public String onAuthPage() {
        return "您没有权限访问";
    }
}
