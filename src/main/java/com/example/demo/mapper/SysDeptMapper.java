package com.example.demo.mapper;

import com.example.demo.model.SysDept;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

/**
 * @author ZZF
 * @date 2020/8/31
 */

@Mapper
public interface SysDeptMapper {
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
    int insert(SysDept record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(SysDept record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    SysDept selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SysDept record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SysDept record);

    List<SysDept> selectAll();
}