package com.excelib.infrastructure.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.excelib.domain.model.Employees;


@Repository
public interface EmployeesMapper {

    public int deleteByPrimaryKey(Integer employeeId);

    public int insert(Employees record);

    public int insertSelective(Employees record);

    public Employees selectByPrimaryKey(Integer employeeId);

    public int updateByPrimaryKeySelective(Employees record);

    public int updateByPrimaryKey(Employees record);
    
    public List<Employees> selectManyRecords(Integer deptId, BigDecimal salary);
    
    public List<Employees> inQuauseQuery(List inList);
    
}