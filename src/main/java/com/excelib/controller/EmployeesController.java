package com.excelib.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.excelib.domain.model.periphery.ComplexPOJO;
import com.excelib.domain.services.intf.Async1Services;
import com.excelib.domain.services.intf.EmployeesServices;
import com.excelib.util.ResultObj;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

	
	private final Log logger = LogFactory.getLog(EmployeesController.class.getName());
	
	@Resource
	private EmployeesServices employeesServices;
	
	
    // 异步服务
    @Resource
    private Async1Services asyncDemoServices;
	
	
	public EmployeesController() {
	}
	
	@RequestMapping(value="/queryOrclComplex", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultObj queryComplexFunc() {
	    // 结果对象
        ResultObj resultObj = new ResultObj();
        
        // 业务逻辑处理
        Object data = employeesServices.queryOrclComplex();
        
        // 结果处理
        resultObj.setCode(ResultObj.CODE_FORBIDDEN);
        resultObj.setMessage("success");
        resultObj.setData(data);
        
//        if (1 == 1) {
//            throw new RuntimeException();
//        }
        
        return resultObj;
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
		logger.info("-----: " + name);
		
		
		PrintWriter writer = response.getWriter();  
        writer.println(request.getMethod() + " " + request.getRequestURL() + " " + request.getQueryString());  
        logger.info(request.getMethod() + " " + request.getRequestURL() + " " + request.getQueryString());
        
        
        Map<String, String[]> params = request.getParameterMap();  
        String queryString = "";  
        for (String key : params.keySet()) {  
        	
//        	logger.info("---- param Name: " + key);
            String[] values = params.get(key);  
            for (int i = 0; i < values.length; i++) {  
                String value = values[i];  
                queryString += key + "=" + value + "&";  
            }  
        }  
        // 去掉最后一个空格  
//        queryString = queryString.substring(0, queryString.length() - 1);  
        writer.println(request.getMethod() + " " + request.getRequestURL() + " " + queryString); 
        logger.info(request.getMethod() + " " + request.getRequestURL() + " " + queryString);
		 
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
		String departmentName = dept.getDepartmentName();
		logger.info("--- deptName: " + departmentName);
		
		List<Employees> employeesList = dept.getEmployeesList();
		int size = employeesList.size();
		if(size > 0) {
			String firstName = employeesList.get(0).getFirstName();
			logger.info("----firstName: " + firstName);
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
	public  ResultObj getEmp(@PathVariable Integer empId) {
		
		// 结果对象
		ResultObj resultObj = new ResultObj();
		
		// 业务逻辑处理
		Employees employeesPOJO = employeesServices.selectByEmpId(empId);
		
		// 异步测试
		asyncDemoServices.testAsyncMethodNoReturnA();
		
		String result = JSON.toJSONString(employeesPOJO);
		logger.info("-----result:json: " + result);
		
		// 结果处理
		resultObj.setCode(ResultObj.CODE_OK);
		resultObj.setMessage("success");
		resultObj.setData(employeesPOJO);
		
		return resultObj;
	}
	
//  测试在控制器中写异步方法。  
//	@Async
//    public void testAsyncMethod(){
//        try {
//            //让程序暂停100秒，相当于执行一个很耗时的任务
//            logger.info("begin ------ testAsyncMethod");
//            Thread.sleep(10000);
//            logger.info("end ------ testAsyncMethod");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }	
	
	
	
	/** 
	 *  方式一：通过@RequestParam 的方式传参
	 *  返回多条数据情况测试 
	 *  描述：根据部门 id 与薪资查询符合条件的员工信息 
	 */
	@RequestMapping(value="/emps1", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResultObj getEmp1(@RequestParam Integer deptId, 
								   @RequestParam BigDecimal salary) {
		// 结果对象
		ResultObj resultObj = new ResultObj();
		
		// 业务逻辑处理
		List<Employees> empList = employeesServices.selectByDeptIdAndSalary(deptId, salary);
		
		// 结果处理
		resultObj.setCode(ResultObj.CODE_OK);
		resultObj.setMessage("success");
		resultObj.setData(empList);
		
		return resultObj;
		
	}
	
	/** 
	 *  方式二：通过@RequestBody 的方式传参
	 *  返回多条数据情况测试 
	 *  描述：根据部门 id 与薪资查询符合条件的员工信息 
	 */
	@RequestMapping(value="/emps2", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResultObj getEmp2(@RequestBody Employees employees) {
		
		// 结果对象
		ResultObj resultObj = new ResultObj();
		
		// 业务逻辑处理
		Integer deptId = employees.getDepartmentId();
		BigDecimal salary = employees.getSalary();
		List<Employees> empList = employeesServices.selectByDeptIdAndSalary(deptId, salary);
		
		// 结果处理
		resultObj.setCode(ResultObj.CODE_OK);
		resultObj.setMessage("success");
		resultObj.setData(empList);
		
		return resultObj;
		
	}
	
	
	/**
	 * 描述：查询多个部门下的员工：
	 * 重点：参数为数组Array
	 * 备注：
	 * 不推荐使用数组作为参数接口。数组作为接口影响性能。具体百度。
	 * @param deptIdList
	 * @return
	 */
	@RequestMapping(value= "/emps3", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Employees> getEmpByDeptIdList(@RequestBody Integer[] deptIdList){
		
		logger.info("------deptIdList: " + deptIdList[0]);
//		List<Employees> empList = employeesServices.selectByDeptIdList(deptIdList);
		return null;
	}
	
	/**
	 * 描述：查询多个部门下的员工：
	 * 重点：使用集合类型List.
	 * @param deptIdList
	 * @return
	 */
	@RequestMapping(value= "/emps4", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResultObj getEmpByDeptIdList2(@RequestBody List<Integer> deptIdList){
		
		// 结果对象
		ResultObj resultObj = new ResultObj();
		
		// 业务逻辑处理
		logger.info("------deptIdList: " + deptIdList.get(0));
		List<Employees> empList = employeesServices.selectByDeptIdList(deptIdList);
		
		// 结果处理
		resultObj.setCode(ResultObj.CODE_OK);
		resultObj.setMessage("success");
		resultObj.setData(empList);
		
		return resultObj;
	}
	
	/**
     * 描述：查询多个部门下的员工：
     * 重点：使用集合类型List.背后逻辑区别于方法3.
     * 该方法测试MyBatis 含有List 与其它参数的情况。
     * @param deptIdList
     * @return
     */
    @RequestMapping(value= "/empsInQuery", method={RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ResultObj getEmpByDeptIdList3(@RequestBody List<Integer> deptIdList){
        
        // 结果对象
        ResultObj resultObj = new ResultObj();
        
        // 业务逻辑处理
//        logger.info("------参数deptIdList: " + deptIdList.get(0));
        List<Employees> empList = employeesServices.selectByDeptIdList3(deptIdList, 10000);
        
        // 结果处理
        resultObj.setCode(ResultObj.CODE_OK);
        resultObj.setMessage("success");
        resultObj.setData(empList);
        
        return resultObj;
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
	public ResultObj insertEmployee(@RequestBody Employees employees){
		
		// 结果对象
		ResultObj resultObj = new ResultObj();
		
		// 业务逻辑处理
		if( null != employees) {
			logger.info("---- empName: " + employees.getFirstName());
		}
		employeesServices.insertSelectiveTest(employees);
		
		// 结果处理
		resultObj.setCode(ResultObj.CODE_OK);
		resultObj.setMessage("success");
//		resultObj.setData(null);
		
		return resultObj;
	}
	
	/**
	 * 描述：插入多条员工记录
	 * @param employees
	 */
	@RequestMapping(value="/insertBatch", method={RequestMethod.POST})
	@ResponseBody
	public ResultObj insertEmployee_Batch(@RequestBody List<Employees> employeesList){
		
		// 结果对象
		ResultObj resultObj = new ResultObj();
		
		// 业务逻辑处理
		if(employeesList.size() > 0) {
			logger.info("---- empName: " + employeesList.get(0).getFirstName());
		}
		employeesServices.insertSelectiveTest_Batch(employeesList);
		
		// 结果处理
		resultObj.setCode(ResultObj.CODE_OK);
		resultObj.setMessage("success");
//		resultObj.setData(null);
		
		return resultObj;
	}
	
	
	
	@RequestMapping(value= "/complexServices/{type}", method={RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ResultObj complexPOJOTest(@PathVariable String type){
        
        // 结果对象
        ResultObj resultObj = new ResultObj();
        
        // 业务逻辑处理
        ComplexPOJO complexPOJO = new ComplexPOJO();
        List<Object> lines = new ArrayList<Object>();
        if ("dept".equals(type)) {
            Departments departments1 = new Departments();
            departments1.setDepartmentName("IT");
            Departments departments2 = new Departments();
            lines.add(departments1);
            departments2.setDepartmentName("HR");
            List<Employees> employeesList = new ArrayList<>();
            Employees employees1 = new Employees();
            employees1.setFirstName("zhouze");
            Employees employees2 = new Employees();
            employees2.setFirstName("liuting");
            employeesList.add(employees1);
            employeesList.add(employees2);
            departments2.setEmployeesList(employeesList);
            lines.add(departments2);
        } else {
            Employees employees1 = new Employees();
            Employees employees2 = new Employees();
            lines.add(employees1);
            lines.add(employees2);
        }
        
        complexPOJO.setLines(lines);
        
        
        ComplexPOJO complexPOJO2 = new ComplexPOJO();
        Employees employees1 = new Employees();
        employees1.setFirstName("zhouze----liuting");
        complexPOJO2.setEmployees(employees1);
        complexPOJO2.setLines(lines);
        complexPOJO.setLines2(complexPOJO2);
        
        
        // 结果处理
        resultObj.setCode(ResultObj.CODE_OK);
        resultObj.setMessage("success");
        resultObj.setData(complexPOJO);
        
        return resultObj;
    }
	
	
	

}
