package com.excelib.domain.services.intf;

import java.math.BigDecimal;

import com.excelib.domain.model.Employees;
import java.util.List;

public interface EmployeesServices {

	public Employees selectByEmpId(Integer employeeId);
	
	public List<Employees> selectByDeptIdAndSalary(Integer deptId, BigDecimal salary);
	
	public List<Employees> selectByDeptIdList(List<Integer> deptIdList);
	
	public List<Employees> selectByDeptIdList2(String deptIdList);
	
	public List<Employees> selectByDeptIdList3(List<Integer> deptIdList, Integer Salary);
	
	/** 插入一条员工数据*/
	public void insertSelectiveTest(Employees employees);
	
	/**  插入多条员工数据 */
	public void insertSelectiveTest_Batch(List<Employees> employeesList);

	/** oracle复杂函数调用测试*/
	public List<Integer> queryOrclComplex();
	
}
