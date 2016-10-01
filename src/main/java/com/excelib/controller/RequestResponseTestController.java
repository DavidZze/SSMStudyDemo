package com.excelib.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excelib.domain.model.Person;

/**
 * 这个控制器类用于检验SpringMVC 获取HttpServerltRequest 与 HttpServletResponse：
 * 测试场景：
 * 校验线程安全，即后来的Request 与先前的Request 是相互独立的。（不会发生覆盖的情况，不会出现取值失败的情况）。
 * 客户端发送第一个 http 请求（URL 携带参数值1），
 *     >>>>>> 服务端Controller 接收后使当前线程暂停5s，
 *                >>>>>> 在这5s 内客户端再发送一个请求(携带参数值2)
 *                           >>>>> 服务端获取 URL 的参数值（HttpServletRequest 中获取Param）
 * 校验：
 * 5s 后服务端对请求1处理的拿到的参数值是参数值1则表示成功，参数值2则表示失败（线程不安全）。
 * 
 * 
 * 测试用例：
 * Case1: @ModelAttribute 注解方式 ；
 * Case2: @Autowrite 方式（Spring 注入方式）；
 * Case3: 直接引用HttpServletRequest , HttpServletResponse
 * Case4: RequestContextListener方式；（Web.xml 创建监听的方式）
 * 
 * 
 * 结论：
 * 线程不安全的根源在于，@Controller 组件类默认是单例类，如果定义了成员变量，则会出现线程安全问题。
 * @ModelAttribute 只是在每个
 * 
 * @author zhouze
 *
 */
@Controller
@Scope("prototype")
public class RequestResponseTestController {

	public RequestResponseTestController() {
		// TODO Auto-generated constructor stub
	}
	
	
	/*
	 * 测试@Scope("prototype") 单例与非单例
	 */
	private static int st = 0;      //静态的
    private int index = 0;          //非静态
	
    // http://localhost:8080/TestApp/test
    @RequestMapping("/test")
    public void test() {
        System.out.println(st++ + " | " + index++);
    }
    
    
    
	
	/*
	 * Case1: 
	 */
	protected HttpServletRequest request1; 
	protected HttpServletResponse response1;  
    protected HttpSession session1;  
      
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
    	System.out.println("---- @modelAttibute do -------");
        this.request1 = request;  
        this.response1 = response;  
        this.session1 = request.getSession();  
    }  
	
	
	/*
	 * Case2: Spring 依赖注入的方式
	 */
    @Autowired
    protected  HttpServletRequest request2;
    @Autowired
    protected  HttpServletResponse response2;
	
    
    
    
    
    
    
    
    
    /*
     *  测试：
     */
	@RequestMapping(value="/test/person/profile/{id}/{name}/{status}", method=RequestMethod.GET)
    public @ResponseBody Person profile(@PathVariable int id, 
    									@PathVariable String name, 
    									@PathVariable boolean status,
    									@RequestParam String param,
    									HttpServletRequest request, HttpServletResponse response) {
		System.out.println(this);
		System.out.println("----@RequestParam: " + param);
		// Case3: 参数直接拿
		System.out.println("----Param: " + request);
		System.out.println("----response: " + response);
		
		
		// Case1:
		this.test_befor_func(this.request1, this.response1);
		// Case2:
//		this.test_befor_func(this.request2, this.response2);
		
		try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
           System.out.println(e.getMessage());
        }
		
		// Case1:
		this.test_after_func(this.request1, this.response1);
		// Case2:
//		this.test_after_func(this.request2, this.response2);
		
    	Person person = new Person(id, name, status);
    	System.out.println("---- profile >>> Person is: " + person.toString()) ;
    	return person;
    	
    }
	
	
	public void test_befor_func(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(this);
		System.out.println("---before--- httpRequest: " + request);
    	System.out.println("---before--- httpResponse: " + response);
    	System.out.println("---before--- Param: " + request.getParameter("param"));
    	
	}
	
	
	public void test_after_func(HttpServletRequest request, HttpServletResponse response){
		
		System.out.println("---after--- httpRequest: " + request);
    	System.out.println("---after--- httpResponse: " + response);
    	System.out.println("---after--- Param: " + request.getParameter("param"));
    	
    	response.setCharacterEncoding("UTF-8");
    	response.setHeader("Content-type", "text/html;charset=UTF-8");
    	response.setHeader("Access-Control-Allow-Origin", "*");
	
	}
	
	
	

// Case1:
// 结果：after 的结果相同均为2，线程不安全！（ModelAttributer 作为共享的内存，后面的会覆盖先前的）	
//	---before--- httpRequest: org.apache.catalina.connector.RequestFacade@241558bd
//	---before--- httpResponse: org.apache.catalina.connector.ResponseFacade@7574f2cc
//	---before--- Param: 1
//	---before--- httpRequest: org.apache.catalina.connector.RequestFacade@2ecad1ef      >>>>> 地址
//	---before--- httpResponse: org.apache.catalina.connector.ResponseFacade@59cb9e9
//	---before--- Param: 2
//	---after--- httpRequest: org.apache.catalina.connector.RequestFacade@2ecad1ef
//	---after--- httpResponse: org.apache.catalina.connector.ResponseFacade@59cb9e9
//	---after--- Param: 2
//	---- profile >>> Person is: id: 101 name: zhouze status: true
//	---after--- httpRequest: org.apache.catalina.connector.RequestFacade@2ecad1ef
//	---after--- httpResponse: org.apache.catalina.connector.ResponseFacade@59cb9e9
//	---after--- Param: 2
//	---- profile >>> Person is: id: 102 name: liuting status: true
//  客户端：
//  后一个Reponse 解决了跨域问题
//  先触发请求的Reponse 没有解决跨域问题。 因为5s 后，获取到的将是后掉的Response 对象。	
	
	
// Case2: 成功。	
//	com.excelib.controller.RequestResponseTestController@3d91e0d5
//	com.excelib.controller.RequestResponseTestController@3d91e0d5
//	---before--- httpRequest: Current HttpServletRequest                  >>>>>> Current 关键字（引用）
//	---before--- httpResponse: Current HttpServletResponse
//	---before--- Param: 1
//	com.excelib.controller.RequestResponseTestController@3d91e0d5
//	com.excelib.controller.RequestResponseTestController@3d91e0d5
//	---before--- httpRequest: Current HttpServletRequest
//	---before--- httpResponse: Current HttpServletResponse
//	---before--- Param: 2
//	---after--- httpRequest: Current HttpServletRequest
//	---after--- httpResponse: Current HttpServletResponse
//	---after--- Param: 1
//	---- profile >>> Person is: id: 101 name: zhouze status: true
//	---after--- httpRequest: Current HttpServletRequest
//	---after--- httpResponse: Current HttpServletResponse
//	---after--- Param: 2
//	---- profile >>> Person is: id: 102 name: liuting status: true
	
	
	
	
	

}
