package com.example.demo.controller;

import com.example.demo.service.SysUserService;
import com.example.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/getList")
    public Object getUserLiset(Integer pageNum, Integer pageSize) {
        return sysUserService.userList(pageNum, pageSize);
    }

}
