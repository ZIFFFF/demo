package com.example.demo.controller;

import com.example.demo.model.SysUser;
import com.example.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZZF
 * @date 2021/2/15
 */
@Controller
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/sys_user/get")
    @ResponseBody
    public SysUser getSysUser(@RequestParam("id") Integer id) {
        return sysUserService.getById(id);
    }

    @PostMapping("/sys_user/add")
    public SysUser addUser(@RequestParam("user") SysUser sysUser) {
        return null;
    }

}
