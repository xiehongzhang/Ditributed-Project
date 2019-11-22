/**
* Title: UserControllerTest.java  

* Description   

* @author xhz  

* @date 2019年10月25日  
 
 */
package com.imooc.controller.test;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.imooc.pojo.Users;
import com.imooc.utils.JsonUtils;

/**
 * @author 
 * @description 
 */
@SuppressWarnings("deprecation")
//测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//加载上下文
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml","classpath:spring/springmvc.xml"})
//配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
//指明是WebAppConfiguration
@WebAppConfiguration
public class UserControllerTest {
	@Autowired
	//上下文
	public WebApplicationContext context;
	
	//mock
	public MockMvc mockMvc;
	
	@Before
	public void setMock(){
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	/**
	 * @name testUserList 
	 * @Description 测试查询用户列表
	 * @param 
	 * @return 
	 */
	@Test
	public void testUserList() throws Exception{
		Users user=new Users();
//		user.setUsername("1三");
//		user.setNickname("9");
//		String iString="2";
		String uString=JsonUtils.objectToJson(user);	
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/users/list?page=3")
				.contentType(MediaType.APPLICATION_JSON)
				.content(uString))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		        
	}
	
	/**
	 * @name testShowList
	 * @Description 测试跳到用户列表
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@Test 
	public void testShowList() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/users/showList"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();
	}
}
