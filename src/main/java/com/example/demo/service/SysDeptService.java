package com.example.demo.service;

import com.example.demo.mapper.SysDeptMapper;
import com.example.demo.model.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZZF
 * @date 2020/8/27
 */
@Service
public class SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    public List<SysDept> DepartmentList() {
        return sysDeptMapper.selectAll();
    }

}
