package com.excelib.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.excelib.domain.model.EmployeesPOJO;
import com.excelib.domain.services.intf.EmployeesServices;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

	
	private final Log logger = LogFactory.getLog(EmployeesController.class);
	
	@Resource
	private EmployeesServices employeesServices;
	
	
	public EmployeesController() {
		// TODO Auto-generated constructor stub
	}
	
	
	// 处理POST类型的请求 匹配："/"
	@RequestMapping(value={"/{empId}"},method= {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody EmployeesPOJO getEmp(@PathVariable Integer empId) {
		logger.info("======method getEmp(@PathVariable Integer empId) ========");
		EmployeesPOJO employeesPOJO = employeesServices.selectByEmpId(empId);
		String result = JSON.toJSONString(employeesPOJO);
		System.out.println("-----result:json: " + result);
		return employeesPOJO;
	}
	
	
	
	

}
