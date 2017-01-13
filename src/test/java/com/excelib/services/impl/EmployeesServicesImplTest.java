package com.excelib.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.excelib.domain.model.Employees;
import com.excelib.domain.services.impl.EmployeesServicesImpl;
import com.excelib.domain.services.intf.EmployeesServices;
import com.excelib.infrastructure.dao.EmployeesMapper;
import com.excelib.util.AbstractContextControllerTests;

 



public class EmployeesServicesImplTest  extends AbstractContextControllerTests {

    @Mock
    private EmployeesMapper employeeMapper;
    //  
    private EmployeesServices employeesServices;

    @Before
    public void setUp() throws Exception {
        // 手动的设置一个fake实例进去
        MockitoAnnotations.initMocks( this );
        employeesServices = new EmployeesServicesImpl(employeeMapper);
    }

    @After
    public void tearDown() throws Exception {
    }


    
    
    @Test
    public void testEmployeesServicesImpl() {
    }

    @Test
    public void testSelectByEmpId() {
      

        Employees employees = new Employees();
        employees.setEmployeeId(10000);
        // 打桩，设置桩值
        when( employeeMapper.selectByPrimaryKey(100) ).thenReturn(employees);
        System.out.println("----: " + employeesServices.selectByEmpId(100).getEmployeeId());
//        System.out.println("----: " + employeesServices.selectByEmpId(1001).getEmployeeId());  // 不是定义的桩值则出错 NullpointException
        System.out.println("------: " + verify(employeeMapper).selectByPrimaryKey(100));
        assertEquals("10000", employeesServices.selectByEmpId(100).getEmployeeId().toString());
        
    }

    
    
    
    
//    @Test
//    public void testSelectByDeptIdAndSalary() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testSelectByDeptIdList() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testSelectByDeptIdList2() {
//        fail("Not yet implemented");
//    }

    @Test
    public void testSelectByDeptIdList3() {
        
        Employees employees = new Employees();
        employees.setEmployeeId(10000);
        
        
        List<Integer> deptIdList = new ArrayList<Integer>();
        deptIdList.add(1);
        
        // 打桩，设置桩值
        Map<String, Object> stubValue = new HashMap<String, Object>();
        stubValue.put("size", 1);
        stubValue.put("deptIdList", deptIdList);
        stubValue.put("salary", 0);
        List<Employees> returnList = new ArrayList<Employees>();
        returnList.add(employees);
        when( employeeMapper.inQuauseQuery3(stubValue)).thenReturn(returnList);
        // 进行测试
        System.out.println( "-------testSelectByDeptIdList3 :" + employeesServices.selectByDeptIdList3(deptIdList, 0).get(0).getEmployeeId());
    }

    
    
    
    
//    @Test
//    public void testInsertSelectiveTest() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testInsertSelectiveTest_Batch() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testQueryOrclComplex() {
//        fail("Not yet implemented");
//    }

}
