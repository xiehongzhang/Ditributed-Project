/**
* Title: HelloController.java  

* Description   

* @author xhz  

* @date 2019年12月30日  
 
 */
package com.imooc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 
 * @description 
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

	@PostMapping("/index")
	public String hello(){
		return "hello";
	}
	
}
