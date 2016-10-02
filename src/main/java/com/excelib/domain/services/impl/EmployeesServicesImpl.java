package com.excelib.domain.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.excelib.domain.model.Employees;
import com.excelib.domain.services.intf.EmployeesServices;
import com.excelib.infrastructure.dao.EmployeesMapper;
import com.sun.tools.javac.util.List;

@Service("EemployeesService")
public class EmployeesServicesImpl implements EmployeesServices{

	@Resource
	public EmployeesMapper employeeMapper;
	
	public EmployeesServicesImpl() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public Employees selectByEmpId(Integer employeeId) {
		// TODO Auto-generated method stub
		System.out.println("----- do ServiceImpl ----");
		
		return employeeMapper.selectByPrimaryKey(employeeId);
	}

	/** 根据部门 id 与薪资进行查询，返回多条数据*/
	@Override
	public List<Employees> selectByDeptIdAndSalary(Integer deptId, Integer salary) {
		// TODO Auto-generated method stub
		
		return employeeMapper.selectManyRecords(deptId, salary);
	}

}
