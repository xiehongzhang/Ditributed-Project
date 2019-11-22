/**
* Title: LoginController.java  

* Description   

* @author xhz  

* @date 2019年10月10日  
 
 */
package com.imooc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xhz
 * @description 作为验证页面
 */
@Controller
public class IndexController {

	/**
	 * @name center
	 * @Description 作为门面页面，检查用户是否登录;
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="center", method=RequestMethod.GET)
	public String center(){
//		ModelAndView modelAndView=new ModelAndView();
//		modelAndView.setViewName("center");
//		return modelAndView;
		return "center";
	}
	
}
