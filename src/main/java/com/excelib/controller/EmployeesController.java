package com.excelib.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.excelib.domain.model.Departments;
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
	 * 描述：获取客户端提交的数据与 http 信息（请求 url, 请求的方法，http 请求参数的值）
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
	
	
	/**
	 * 获取 POST 请求body 中的(主从表)数据：
	 * 描述：获取客户端提交的 json 数据，一个头记录行与多个子记录行
	 * url:http://localhost:8080/TestApp/employees/postBodyValue
	 * {
			"departmentId":"100",
			"departmentName":"zz",
			"managerId":"",
			"locationId":"",
			"fauxColumn":"",
			"employeesList":[{"firstName":"liuting"}, {"firstName":"zhouze"}]
		}
	 * @param emp
	 */
	@RequestMapping(value="/postBodyValue", method={RequestMethod.POST})
	@ResponseBody
	public  Departments getRequestBodyValue(@RequestBody Departments dept) {
		// TODO Auto-generated method stub
		String departmentName = dept.getDepartmentName();
		System.out.println("--- deptName: " + departmentName);
		
		List<Employees> employeesList = dept.getEmployeesList();
		int size = employeesList.size();
		if(size > 0) {
			String firstName = employeesList.get(0).getFirstName();
			System.out.println("----firstName: " + firstName);
		}
		
		
		dept.setDepartmentName(departmentName + "_add");
		return dept;
	}
	
	
	
	
	
	
	
	
	
	/** 返回一条数据情况测试
	 *  描述：根据员工 id 查询某位员工基本信息
	 */
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
	
	
	
	/** 
	 *  方式一：通过@RequestParam 的方式传参
	 *  返回多条数据情况测试 
	 *  描述：根据部门 id 与薪资查询符合条件的员工信息 
	 */
	@RequestMapping(value="/emps1", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Employees> getEmp1(@RequestParam Integer deptId, 
								   @RequestParam BigDecimal salary) {
		
		List<Employees> empList = employeesServices.selectByDeptIdAndSalary(deptId, salary);
		return empList;
	}
	
	/** 
	 *  方式二：通过@RequestBody 的方式传参
	 *  返回多条数据情况测试 
	 *  描述：根据部门 id 与薪资查询符合条件的员工信息 
	 */
	@RequestMapping(value="/emps2", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Employees> getEmp2(@RequestBody Employees employees) {
		
		Integer deptId = employees.getDepartmentId();
		BigDecimal salary = employees.getSalary();
		List<Employees> empList = employeesServices.selectByDeptIdAndSalary(deptId, salary);
		
		return empList;
	}
	
	
	/**
	 * 描述：查询多个部门下的员工：
	 * @param deptIdList
	 * @return
	 */
	@RequestMapping(value= "/emps3", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Employees> getEmpByDeptIdList(@RequestBody Integer[] deptIdList){
		
		System.out.println("------deptIdList: " + deptIdList[0]);
//		List<Employees> empList = employeesServices.selectByDeptIdList(deptIdList);
		return null;
	}
	
	/**
	 * 描述：查询多个部门下的员工：
	 * @param deptIdList
	 * @return
	 */
	@RequestMapping(value= "/emps4", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Employees> getEmpByDeptIdList2(@RequestBody List<Integer> deptIdList){
		
		System.out.println("------deptIdList: " + deptIdList.get(0));
		List<Employees> empList = employeesServices.selectByDeptIdList(deptIdList);
		return empList;
	}
	
	/**
	 * 失败
	 * 描述：查询多个部门下的员工：
	 * 结果：
	 * 异常，无效数字异常。
	 * @param deptIdList
	 * @return
	 */
	@RequestMapping(value= "/emps5", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Employees> getEmpByDeptIdList3( String deptIdList){		
		deptIdList = "(10,20,30)";
		List<Employees> empList = employeesServices.selectByDeptIdList2(deptIdList);
		return empList;
	}
	
	
	/**
	 * 描述：插入一条员工记录
	 * @param employees
	 */
	@RequestMapping(value="/insert", method={RequestMethod.POST})
	@ResponseBody
	public String insertEmployee(@RequestBody Employees employees){
		if( null != employees) {
			System.out.println("---- empName: " + employees.getFirstName());
		}
		employeesServices.insertSelectiveTest(employees);
		
		return "success";
	}
	
	/**
	 * 描述：插入多条员工记录
	 * @param employees
	 */
	@RequestMapping(value="/insertBatch", method={RequestMethod.POST})
	@ResponseBody
	public String insertEmployee_Batch(@RequestBody List<Employees> employeesList){
		if( null != employeesList.get(0)) {
			System.out.println("---- empName: " + employeesList.get(0).getFirstName());
		}
		employeesServices.insertSelectiveTest_Batch(employeesList);
		return "success";
	}
	
	

}
