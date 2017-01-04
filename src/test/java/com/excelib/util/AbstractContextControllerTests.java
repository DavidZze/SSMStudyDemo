package com.excelib.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * 描述：
 * 单元测试抽象类。
 * 作用：
 * 提供单测的公用方法与公用的配置。
 * 1. 提供 WebAppConfiguration ；
 * 2. 配置 ContextConfiguration。
 * @author zhouze
 *
 */

@WebAppConfiguration  
@ContextConfiguration(locations = { "classpath:Spring-mybatis.xml",    
"classpath:Spring-mvc.xml" })  
public abstract class AbstractContextControllerTests {

    @Autowired  
    protected WebApplicationContext wac;
    
    
    
    /** 缺省构造器*/
    public AbstractContextControllerTests() {
    }

    
    
    
}
