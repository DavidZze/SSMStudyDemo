package com.excelib.infrastructure.dao;

import org.springframework.stereotype.Repository;

import com.excelib.domain.model.EmployeesPOJO;
import com.sun.tools.javac.util.List;

@Repository
public interface EmployeesDao {

    public int deleteByPrimaryKey(Integer employeeId);

    public int insert(EmployeesPOJO record);

    public int insertSelective(EmployeesPOJO record);

    public EmployeesPOJO selectByPrimaryKey(Integer employeeId);

    public int updateByPrimaryKeySelective(EmployeesPOJO record);

    public int updateByPrimaryKey(EmployeesPOJO record);
    
    public List<EmployeesPOJO> selectManyRecords();
    
    
}