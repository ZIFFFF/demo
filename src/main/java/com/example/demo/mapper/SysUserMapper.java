package com.example.demo.mapper;

import com.example.demo.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author ZZF
 * @date 2021/2/15
 */

@Mapper
public interface SysUserMapper {

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    SysUser selectByPrimaryKey(Integer id);

}