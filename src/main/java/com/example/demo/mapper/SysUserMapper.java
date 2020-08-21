package com.example.demo.mapper;

import com.example.demo.model.SysUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Mapper;

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
     * @param pageNum 页码
     * @param pagesize 页面大小
     * @return 用户数据分页
     */
    Page<SysUser> selectAll(Integer pageNum, Integer pagesize);
}