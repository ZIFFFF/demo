package com.example.demo.controller;

import com.example.demo.service.SysUserService;
import com.example.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器，用于处理登录请求
 */

@Controller
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("LoginAction")
    @ResponseBody
    public Object Login(@RequestParam("no") String no, @RequestParam("password") String password, HttpServletRequest request) {
        return sysUserService.checkLoginByShiro(no, password, request);
    }

    @PostMapping("RegisterAction")
    @ResponseBody
    public Object Register(@RequestParam("username") String username, @RequestParam("password") String password) {
        return sysUserService.userRegister(username, password);
    }

}
