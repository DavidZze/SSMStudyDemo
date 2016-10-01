package com.excelib.domain.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.excelib.domain.model.EmployeesPOJO;
import com.excelib.domain.services.intf.EmployeesServices;
import com.excelib.infrastructure.dao.EmployeesDao;

@Service("EemployeesService")
public class EmployeesServicesImpl implements EmployeesServices{

	@Resource
	public EmployeesDao employeeMapper;
	
	public EmployeesServicesImpl() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public EmployeesPOJO selectByEmpId(Integer employeeId) {
		// TODO Auto-generated method stub
		System.out.println("----- do ServiceImpl ----");
		
		return employeeMapper.selectByPrimaryKey(employeeId);
	}

}
