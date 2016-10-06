package com.excelib.domain.services.intf;

import java.math.BigDecimal;

import com.excelib.domain.model.Employees;
import java.util.List;

public interface EmployeesServices {

	public Employees selectByEmpId(Integer employeeId);
	
	public List<Employees> selectByDeptIdAndSalary(Integer deptId, BigDecimal salary);
	
	public List<Employees> selectByDeptIdList(List<Integer> deptIdList);
	
	public List<Employees> selectByDeptIdList2(String deptIdList);

}
