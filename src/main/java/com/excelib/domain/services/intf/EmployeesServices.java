package com.excelib.domain.services.intf;

import com.excelib.domain.model.Employees;
import com.sun.tools.javac.util.List;

public interface EmployeesServices {

	public Employees selectByEmpId(Integer employeeId);
	
	public List<Employees> selectByDeptIdAndSalary(Integer deptId, Integer salary);
	
	

}
