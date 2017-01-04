package com.excelib.base;


import javax.annotation.Resource;  
  


import com.excelib.domain.model.Employees;
import com.excelib.domain.services.intf.Async1Services;
import com.excelib.domain.services.intf.EmployeesServices;

import org.apache.log4j.Logger;  
import org.junit.Test;  
import org.junit.runner.RunWith;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
  
import com.alibaba.fastjson.JSON;



@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:Spring-mybatis.xml"})  
  
public class TestMyBatis {  
    
    
    private static Logger logger = Logger.getLogger(TestMyBatis.class);  
    
    // 员工服务
    @Resource  
    private EmployeesServices userService = null;  
  
    // 异步服务
    @Resource
    private Async1Services asyncDemoServices;
    
    @Test  
    public void testX() {  
        Employees user = userService.selectByEmpId(1);  
        
//        this.testAsyncMethodNoReturn2();
        asyncDemoServices.testAsyncMethodNoReturnA();
        logger.info("----------test:::: " + JSON.toJSONString(user));  
    }  
    
    
    @Async
    public void testAsyncMethodNoReturn2(){
        try {
            //让程序暂停100秒，相当于执行一个很耗时的任务
            System.out.println("begin ------ testAsyncMethod");
            Thread.sleep(10000);
            System.out.println("end ------ testAsyncMethod");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
}  











//public class MybatisTest {
//    
//    SqlSession session ;
//    
//    @Before
//    public void initData() throws Exception{
//        String resource="mybatis-config.xml";
//        //输入流
//        Reader reader = Resources.getResourceAsReader(resource);
//        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(reader);
//        session = factory.openSession();
//        //将一个字符串和硬盘上的真实文件关联 ， 在内存形成流
//        
//    }
//    
//    
//    /**
//     * 01.查找所有部门
//     * 
//     */
//    @Test
//    public void testSelect() throws Exception{
//        List<Dept> list = session.selectList("cn.happy.entity.Dept.selectAllDept");
//        for (Dept item : list) {
//            System.out.println(item);
//        }
//    }
//    
//    
//    /**
//     * 01.带条件查询部门
//     * 
//     */
//    @Test
//    public void testSelectById() {
//        Dept dt=new Dept();
//        dt.setDeptNo(2);
//
//        Dept dept = session.selectOne("cn.happy.entity.Dept.selectById",dt);
//         System.out.println(dept.getDeptName());
//    }
//    
//    /**
//     * 02.新增部门
//     * 
//     */
//    @Test
//    public void testInsert() throws Exception{
//        Dept dept=new Dept();
//        dept.setDeptNo(1);
//        dept.setDeptName("小弟部");
//
//        int count = session.insert("cn.happy.entity.Dept.insertDept",dept);
//        
//        //提交
//        session.commit();
//        
//        System.out.println(count);
//    }
//    
//    
//    /**
//     * 03.修改部门
//     * 
//     */
//    @Test
//    public void testUpdate() throws Exception{
//        Dept dept=new Dept();
//        dept.setDeptNo(1);
//        dept.setDeptName("小弟部1");
//        
//        int count = session.update("cn.happy.entity.Dept.updateDept",dept);
//        
//        //提交
//        session.commit();
//        
//        System.out.println(count);
//    }
//    
//    
//    /**
//     * 04.删除部门
//     * 
//     */
//    @Test
//    public void testDelete() throws Exception{
//        Dept dept=new Dept();
//        dept.setDeptNo(1);
//        
//        int count = session.delete("cn.happy.entity.Dept.deleteDept",dept);
//        
//        //提交
//        session.commit();
//        
//        System.out.println(count);
//    }
//    
//    
//    
//    /**
//     * 04.模糊查询部门
//     * 
//     */
//    @Test
//    public void testSelectLike() throws Exception{
//        Dept dept=new Dept();
//        dept.setDeptName("大神");
//        
//        List<Dept> list = session.selectList("cn.happy.entity.Dept.selectLikeDept",dept);
//        
//        for (Dept item : list) {
//            System.out.println(item);
//        }
//        
//    }
//    
//    /**
//     * 01.查找所有部门
//     * 
//     */
//    @Test
//    public void testGetAllDepts() throws Exception{
//        IDeptDao mapper = session.getMapper(IDeptDao.class);
//        List<Dept> list = mapper.getAllDepts();
//        for (Dept item : list) {
//            System.out.println(item.getDeptName());
//        }
//    }
//    
//}