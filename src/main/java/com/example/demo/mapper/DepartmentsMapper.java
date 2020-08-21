package com.example.demo.mapper;

import com.example.demo.model.Departments;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Departments record);

    int insertSelective(Departments record);

    Departments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Departments record);

    int updateByPrimaryKey(Departments record);
}