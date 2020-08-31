package com.example.demo.mapper;

import com.example.demo.model.SysUser;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

/**
 * @author ZZF
 * @date 2020/8/31
 */

@Mapper
public interface SysUserMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(SysUser record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(SysUser record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    SysUser selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SysUser record);

    SysUser selectByNo(String no);

    /**
     * 用户列表数据查询
     *
     * @return 用户数据分页
     * @Param sysUser 查找用户
     */
    List<SysUser> selectAll(@Param("sysUser") SysUser sysUser);
}