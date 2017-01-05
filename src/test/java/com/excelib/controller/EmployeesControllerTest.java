/**
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.excelib.controller;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.excelib.util.AbstractContextControllerTests;
import com.excelib.util.MockUtil;

/**
 * 测试 EmployeesController
 * @author zhouze 
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
public class EmployeesControllerTest extends AbstractContextControllerTests{

    private final Log logger = LogFactory.getLog(EmployeesControllerTest.class.getName());

    private MockMvc mockMvc;
    
    @Resource
    private EmployeesController employeesController;
    
    
    
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        logger.info("-------begin test ------");
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeesController).build();
    }
    
    
    
    /**
     * Test method for {@link com.excelib.controller.EmployeesController#EmployeesController()}.
     */
    @Test
    public void testEmployeesController() {
        assertTrue(true);
    }

    /**
     * Test method for {@link com.excelib.controller.EmployeesController#queryComplexFunc()}.
     * @throws Exception 
     * @throws UnsupportedEncodingException 
     */
    @Test
    public void testQueryComplexFunc() throws Exception {
    	
        String re = MockUtil.mockHttpGet(this.mockMvc, "/employees/queryOrclComplex");
        logger.info("-------------" + re);
    }

    /**
     * Test method for {@link com.excelib.controller.EmployeesController#getRequestParamValue(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
     */
    @Test
    public void testGetRequestParamValue()  throws Exception {
    	
    	Map<String, Object> sessionMap = new HashMap<String, Object>();
    	sessionMap.put("param9", "zhouze----");
    	
        String re = MockUtil.mockHttpGet(this.mockMvc, "/employees/paramTest?param1=xxx&&param2=xxx", sessionMap);
        logger.info("-------------" + re);
    }

    /**
     * Test method for {@link com.excelib.controller.EmployeesController#getRequestBodyValue(com.excelib.domain.model.Departments)}.
     */
    @Test
    public void testGetRequestBodyValue()  throws Exception {
        String re = MockUtil.mockHttpPost(this.mockMvc, "/employees/postBodyValue",
                "{\"departmentId\":\"100\",\"departmentName\":\"zz\",\"managerId\":\"\",\"locationId\":\"\",\"fauxColumn\":\"\",\"employeesList\":[{\"firstName\":\"liuting\"},{\"firstName\":\"zhouze\"}]}");
        logger.info("-------------" + re);
        String re2 = MockUtil.mockHttpPost(this.mockMvc, "/employees/postBodyValue",
                "{\"departmentId\":\"100\",\"departmentName\":\"zz\",\"managerId\":\"\",\"locationId\":\"\",\"fauxColumn\":\"\",\"employeesList\":[]}");
        logger.info("-------------" + re2);
    }

    /**
     * Test method for {@link com.excelib.controller.EmployeesController#getEmp(java.lang.Integer)}.
     */
    @Test
    public void testGetEmp() throws Exception {
        String re = MockUtil.mockHttpGet(this.mockMvc, "/employees/100");
        logger.info("-------------" + re);
    }


    /**
     * Test method for {@link com.excelib.controller.EmployeesController#getEmp1(java.lang.Integer, java.math.BigDecimal)}.
     */
    @Test
    public void testGetEmp1()  throws Exception {
        String re = MockUtil.mockHttpGet(this.mockMvc, "/employees/emps1?deptId=30&&salary=1000");
        logger.info("-------------" + re);
    }

//    /**
//     * Test method for {@link com.excelib.controller.EmployeesController#getEmp2(com.excelib.domain.model.Employees)}.
//     */
//    @Test
//    public void testGetEmp2() {
//        fail("Not yet implemented");
//    }
//
//    /**
//     * Test method for {@link com.excelib.controller.EmployeesController#getEmpByDeptIdList(java.lang.Integer[])}.
//     */
//    @Test
//    public void testGetEmpByDeptIdList() {
//        fail("Not yet implemented");
//    }
//
//    /**
//     * Test method for {@link com.excelib.controller.EmployeesController#getEmpByDeptIdList2(java.util.List)}.
//     */
//    @Test
//    public void testGetEmpByDeptIdList2() {
//        fail("Not yet implemented");
//    }
//
//    /**
//     * Test method for {@link com.excelib.controller.EmployeesController#getEmpByDeptIdList3(java.util.List)}.
//     */
//    @Test
//    public void testGetEmpByDeptIdList3ListOfInteger() {
//        fail("Not yet implemented");
//    }
//
//    /**
//     * Test method for {@link com.excelib.controller.EmployeesController#getEmpByDeptIdList3(java.lang.String)}.
//     */
//    @Test
//    public void testGetEmpByDeptIdList3String() {
//        fail("Not yet implemented");
//    }
//
//    /**
//     * Test method for {@link com.excelib.controller.EmployeesController#insertEmployee(com.excelib.domain.model.Employees)}.
//     */
//    @Test
//    public void testInsertEmployee() {
//        System.out.println("执行单元测试:testInsertEmployee");
//        fail("Not yet implemented");
//    }

    /**
     * Test method for {@link com.excelib.controller.EmployeesController#insertEmployee_Batch(java.util.List)}.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testInsertEmployee_Batch() throws Exception{
        String reString = MockUtil.mockHttpPost(this.mockMvc, "/employees/insertBatch", "[{\n" +
                "\t\"employeeId\":\"3000\",\n" +
                "\t\"firstName\":\"llll\",\n" +
                "\t\"lastName\":\"_lt2\",\n" +
                "\t\"departmentId\":\"10\",\n" +
                "\t\"email\":\"1112416535aa@qq.com\",\n" +
                "\t\"hireDate\":\"2016-10-06\",\n" +
                "\t\"jobId\":\"FI_ACCOUNT\"\n" +
                "},\n" +
                "{\n" +
                "\t\"employeeId\":\"30001\",\n" +
                "\t\"firstName\":\"tttt\",\n" +
                "\t\"lastName\":\"_lt3\",\n" +
                "\t\"departmentId\":\"10\",\n" +
                "\t\"email\":\"1231232@qq.com\",\n" +
                "\t\"hireDate\":\"2016-10-06\",\n" +
                "\t\"jobId\":\"FI_ACCOUNT\"\n" +
                "},\n" +
                "{\n" +
                "\t\"employeeId\":\"3002\",\n" +
                "\t\"firstName\":\"z5z\",\n" +
                "\t\"lastName\":\"_lt\",\n" +
                "\t\"departmentId\":\"10\",\n" +
                "\t\"email\":\"4234231sdfs@qq.com\",\n" +
                "\t\"hireDate\":\"2016-10-06\",\n" +
                "\t\"jobId\":\"FI_ACCOUNT\"\n" +
                "}]");
        logger.info("----------: " + reString);
        String reString2 = MockUtil.mockHttpPost(this.mockMvc, "/employees/insertBatch", "[]");
        logger.info("----------: " + reString2);
    }

//    /**
//     * Test method for {@link com.excelib.controller.EmployeesController#complexPOJOTest(java.lang.String)}.
//     */
//    @Test
//    public void testComplexPOJOTest() {
//        System.out.println("执行单元测试:testComplexPOJOTest");
//        fail("Not yet implemented");
//    }

}
