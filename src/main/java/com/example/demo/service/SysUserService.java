package com.example.demo.service;

import com.example.demo.mapper.SysUserMapper;
import com.example.demo.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZZF
 * @date 2021/2/15
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser getById(Integer id){

        return sysUserMapper.selectByPrimaryKey(id);
    }

}
