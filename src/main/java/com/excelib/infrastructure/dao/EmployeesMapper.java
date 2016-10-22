package com.excelib.infrastructure.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    
    public List<Employees> inQuauseQuery(List<Integer> inList);
    
    public List<Employees> inQuauseQuery2(String inList);
    
    public List<Employees> inQuauseQuery3(Map<String, Object> paramMap);
    
    /** 获取序列值 */
    public int queryEmpSeq();
    
    /** oracle 复杂函数调用测试*/
    public List<Integer> queryOrclComplex();
    
    
}