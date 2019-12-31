/**
* Title: BgmControllerTest.java  

* Description   

* @author xhz  

* @date 2019年12月27日  
 
 */
package com.imooc.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.imooc.Application;
import com.imooc.service.BgmService;

/**
 * @author xhz
 * @description 测试BgmController的接口
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes={WarApplication.class})
@SpringBootTest(classes={Application.class})
@WebAppConfiguration
public class BgmControllerTest {
	
	//创建web运用上下文
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	//声明mock
	private MockMvc mockMvc;
	
	//初始化mockMvc
	@Before
	public void setupMockMvc(){
		mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/**
	 * @name testBgms()
	 * @Description 测试@GetMapping("/bgms")
	 * @param 
	 * @return 
	 */
	@Test
	public void testBgms() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/bgms")
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}
