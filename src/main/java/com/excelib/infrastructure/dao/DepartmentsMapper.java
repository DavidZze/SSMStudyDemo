package com.excelib.infrastructure.dao;

import com.excelib.domain.model.Departments;

public interface DepartmentsMapper {
    int deleteByPrimaryKey(Short departmentId);

    int insert(Departments record);

    int insertSelective(Departments record);

    Departments selectByPrimaryKey(Short departmentId);

    int updateByPrimaryKeySelective(Departments record);

    int updateByPrimaryKey(Departments record);
}