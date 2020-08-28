package com.example.demo.controller;

import com.example.demo.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZZF
 * @date 2020/8/27
 */
@Controller
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @RequestMapping("/depart/list")
    @ResponseBody
    public Object getDepartmentsList() {
        return sysDeptService.DepartmentList();
    }
}
