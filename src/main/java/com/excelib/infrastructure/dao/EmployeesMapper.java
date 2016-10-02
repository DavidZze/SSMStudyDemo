package com.excelib.infrastructure.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.excelib.domain.model.Employees;
import com.sun.tools.javac.util.List;

@Repository
public interface EmployeesMapper {

    public int deleteByPrimaryKey(Integer employeeId);

    public int insert(Employees record);

    public int insertSelective(Employees record);

    public Employees selectByPrimaryKey(Integer employeeId);

    public int updateByPrimaryKeySelective(Employees record);

    public int updateByPrimaryKey(Employees record);
    
    public List<Employees> selectManyRecords(@Param("deptId") Integer deptId, @Param("salary") Integer salary);
    
    
}