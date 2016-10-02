package com.excelib.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.excelib.domain.model.Employees;
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
	
	/**
	 * 测试：从 POST 与 GET 请求中获取param。
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/paramTest", method={RequestMethod.GET, RequestMethod.POST})
	public void getRequestParamValue(HttpServletRequest request, 
							   		 HttpServletResponse response) throws IOException {
		
		
		String name = request.getParameter("name");
		System.out.println("-----: " + name);
		
		
		PrintWriter writer = response.getWriter();  
        writer.println(request.getMethod() + " " + request.getRequestURL() + " " + request.getQueryString());  
        System.out.println(request.getMethod() + " " + request.getRequestURL() + " " + request.getQueryString());
        
        
        Map<String, String[]> params = request.getParameterMap();  
        String queryString = "";  
        for (String key : params.keySet()) {  
        	
//        	System.out.println("---- param Name: " + key);
            String[] values = params.get(key);  
            for (int i = 0; i < values.length; i++) {  
                String value = values[i];  
                queryString += key + "=" + value + "&";  
            }  
        }  
        // 去掉最后一个空格  
//        queryString = queryString.substring(0, queryString.length() - 1);  
        writer.println(request.getMethod() + " " + request.getRequestURL() + " " + queryString); 
        System.out.println(request.getMethod() + " " + request.getRequestURL() + " " + queryString);
		 
	}
	
	
	
	public  void getRequestBodyValue() {
		// TODO Auto-generated method stub

	}
	
	
	
	
	
	
	
	
	
	/** 返回一条数据情况测试*/
	// 处理POST类型的请求 匹配："/"
	@RequestMapping(value={"/{empId}"},method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public  Employees getEmp(@PathVariable Integer empId) {
		logger.info("======method getEmp(@PathVariable Integer empId) ========");
		Employees employeesPOJO = employeesServices.selectByEmpId(empId);
		String result = JSON.toJSONString(employeesPOJO);
		System.out.println("-----result:json: " + result);
		return employeesPOJO;
	}
	
	/** 返回多条数据情况测试 */
	@RequestMapping(value="/emps", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Employees> getEmp(Integer deptId, Integer salary) {
		
		List<Employees> empList = employeesServices.selectByDeptIdAndSalary(deptId, salary);
		
		return empList;
	}
	
	
	
	

}
