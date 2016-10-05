package com.excelib.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excelib.domain.model.Person;

/**
 * @author zhouze
 */
@Controller
@RequestMapping("person")
public class PersonController {

	
	private final Log logger = LogFactory.getLog(GoController.class);
	
	
	/**
	 * 构造器
	 */
	public PersonController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	protected HttpServletRequest request; 
	@Autowired
	protected HttpServletResponse response;  
	
	

	
	
//	// 处理 GET 类型的请求 匹配："/index" 
//	@RequestMapping(value={"/login"},method= {RequestMethod.GET})
//	public String login(Model model) throws Exception {
//		logger.info("======processed by login========");
//		return "/html/login.html";
//	}
	
	
	
	/**
	 * 登录1：
	 * 供非 js 方式调用，比如 POSTMAN 调用（非 javascript ，则不会存在所谓的 javaScript 同源安全策略的影响了）
	 * @param person
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody Person login(@RequestBody Person person) {
		this.passDomain();
		System.out.println("---- Server do login ----");
		return person;
	}
	
	
	/**
	 * 登录2:
	 * 供 javaScript 调用，这里与前端约定使用 JSONP 的传输方式。
	 * @param person
	 * @return
	 */
	@RequestMapping(value="/login2", method=RequestMethod.GET)
	@ResponseBody
	public Object login2(@RequestParam String name,
					     @RequestParam int id,
					     @RequestParam boolean status) {
		
		System.out.println("---- Server do login ----");
//		this.passDomain();
		
		MappingJacksonValue result = null;
		
		Map map = new HashMap();
		map.put("name", name);
		map.put("id", id);
		map.put("status", status);
		
		String jsonpCallback = this.request.getParameter("callback");
		result = new MappingJacksonValue(map);
		if(null != jsonpCallback) {
            result.setJsonpFunction(jsonpCallback); 
		}
		
		System.out.println("------result: " + result.getValue());
		return result;
	}
	
	
	
	

	/** 
     * 查询个人信息 
     *  
     * @param id 
     * @return 
     */  
	@RequestMapping(value="/person/profile/{id}/{name}/{status}", method=RequestMethod.GET)
    public @ResponseBody Person profile(@PathVariable int id, 
    									@PathVariable String name, 
    									@PathVariable boolean status) {
    	this.passDomain();
    	Person person = new Person(id, name, status);
    	System.out.println("---- profile >>> Person is: " + person.toString()) ;
    	return person;
    }
  
    
    private void passDomain() {
    	System.out.println("------ httpResponse: " + this.response);
    	this.response.setCharacterEncoding("UTF-8");
    	this.response.setHeader("Content-type", "text/html;charset=UTF-8");
    	this.response.setHeader("Access-Control-Allow-Methods","POST, GET, OPTIONS");
    	this.response.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
	}
    
    
	
	
}
