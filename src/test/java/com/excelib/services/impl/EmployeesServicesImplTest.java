package com.excelib.services.impl;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excelib.domain.model.Employees;
import com.excelib.infrastructure.dao.EmployeesMapper;


@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:Spring-mybatis.xml"})  
public class EmployeesServicesImplTest {

    @Resource
    public EmployeesMapper employeeMapper;
    

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    
    
    @Test
    public void testEmployeesServicesImpl() {
        fail("Not yet implemented");
        
    }

    @Test
    public void testSelectByEmpId() {
        Employees emp = employeeMapper.selectByPrimaryKey(100);
        System.out.println("------: " + emp);
    }

    @Test
    public void testSelectByDeptIdAndSalary() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectByDeptIdList() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectByDeptIdList2() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectByDeptIdList3() {
        fail("Not yet implemented");
    }

    @Test
    public void testInsertSelectiveTest() {
        fail("Not yet implemented");
    }

    @Test
    public void testInsertSelectiveTest_Batch() {
        fail("Not yet implemented");
    }

    @Test
    public void testQueryOrclComplex() {
        fail("Not yet implemented");
    }

}
