package com.example.demo.mapper;

import com.example.demo.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectByNo(String no);

    /**
     * 用户列表数据查询
     * @Param sysUser 查找用户
     * @return 用户数据分页
     */
    List<SysUser> selectAll(@Param("sysUser") SysUser sysUser);
}