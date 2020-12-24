package com.example.demo.controller;

import com.example.demo.common.ResultGenerator;
import com.example.demo.model.SysUser;
import com.example.demo.service.SysUserService;
import com.example.demo.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    public Object getUserLiset(Integer page, Integer limit, SysUserVo sysUser) {
        List<SysUserVo> sysUserList = sysUserService.userList(sysUser);
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", sysUserList.size());
        result.put("data", sysUserList);
        return result;
    }

    @PostMapping("/user/add")
    @ResponseBody
    public ResultGenerator addUser(SysUser sysUser) {
        return sysUserService.userAdd(sysUser);
    }

    @PostMapping("/user/del")
    @ResponseBody
    public ResultGenerator delUser(String[] no) {
        return sysUserService.userDel(no);
    }

}
