package com.example.demo.controller;

import com.example.demo.common.ResultGenerator;
import com.example.demo.model.SysUser;
import com.example.demo.service.SysUserService;
import com.example.demo.vo.ResultVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统用户控制器
 *
 * @author ZZF
 * @date 2020/08/21
 */

@Controller
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/user/list")
    @ResponseBody
    public ResultGenerator<PageInfo> getUserLiset(Integer page, Integer limit, SysUser sysUser) {
        return sysUserService.userList(page-1, limit, sysUser);
    }

}
