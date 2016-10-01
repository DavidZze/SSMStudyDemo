package com.excelib.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// org.springframework.web.servlet.DispatcherServlet

@Controller
public class GoController implements EnvironmentAware{

	private final Log logger = LogFactory.getLog(GoController.class);
	
	private Environment environment = null;
	
	// 处理Head类型的请求 匹配："/"
	@RequestMapping(value={"/"},method= {RequestMethod.HEAD})
	public String head() {
		logger.info("======method Head() ========");
		return "go.jsp";
	}
	
	// 处理 GET 类型的请求 匹配："/index" "/"请求
	@RequestMapping(value={"/index","/"},method= {RequestMethod.GET})
	public String index(Model model) throws Exception {
		logger.info("======processed by index========");
		// 返回msg 参数
		model.addAttribute("msg", "GO GO GO!");
		return "go.jsp";
	}
	

	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		this.environment = environment;
	}
	
}
