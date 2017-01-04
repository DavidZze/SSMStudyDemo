package com.excelib.base;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.excelib.domain.model.Employees;
import com.excelib.domain.services.intf.Async1Services;
import com.excelib.domain.services.intf.EmployeesServices;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:Spring-mybatis.xml"})  
public class TestDemo {

    public TestDemo() {
    }

    
    private static Logger logger = Logger.getLogger(TestDemo.class);  

    
    // 员工服务
    @Resource  
    private EmployeesServices userService = null;  
  
    // 异步服务
    @Resource
    private Async1Services asyncDemoServices;
    
    @Test  
    public void testX() {  
        Employees user = userService.selectByEmpId(1);  
        logger.info("----------test:::: " + JSON.toJSONString(user));  
    }  
    
}
