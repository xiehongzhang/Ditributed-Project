/**
* Title: Slf4jTest.java  

* Description   

* @author xhz  

* @date 2019年10月14日  
 
 */
package com.imooc.controller.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 
 * @description 
 */
public class Slf4jTest {
	//slf4j日志框架
	public static final Logger LOGGER=LoggerFactory.getLogger(Slf4jTest.class);

	/**
	 * @name xhz
	 * @Description 测试slf4j
	 * @param 
	 * @return 
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LOGGER.info("slf4j测试类创建成功了！");
	}

}
