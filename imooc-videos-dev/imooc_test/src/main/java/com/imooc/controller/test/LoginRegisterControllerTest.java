/**
* Title: LoginRegisterControllerTest.java  

* Description   

* @author xhz  

* @date 2019年12月30日  
 
 */
package com.imooc.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;

import com.imooc.Application;
import com.imooc.pojo.Users;
import com.imooc.utils.JsonUtils;

/**
 * @author xhz
 * @description 测试LoginRegisterController中的方法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={Application.class})
@WebAppConfiguration
public class LoginRegisterControllerTest {
	
	//创建web运用上下文
	@Autowired
	private WebApplicationContext webApplicationContext;
	//声明mock
	private MockMvc mockMvc;
	
	//实例化mock
	@Before  
	public void setupMockMvc(){
		mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * @description 测试接口：@PostMapping("/regist")
	 * 				用户注册
	 * @param Users   
	 */
	@Test
	public void testRegist() throws Exception{
		Users users=new Users();
		users.setUsername("真的不对");
		users.setPassword("123456");
		String usersString=JsonUtils.objectToJson(users);
		MvcResult mvcResult = this.mockMvc.perform(post("/regist")
				.content(usersString)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn();
//		Collection<String> headerName=mvcResult.getResponse().getHeaderNames();
//		String handlder=(String) mvcResult.getHandler();
//		mvcResult.getRequest().getAsyncContext();
	}
	
	/**
	 * @description 测试@PostMapping("/login")
	 *   			用户登录
	 * @param Users
	 */
	@Test
	public void testLogin() throws Exception{
		Users users=new Users();
		users.setUsername("imooc");
		users.setPassword("imooc");
		String usersString=JsonUtils.objectToJson(users);
		this.mockMvc.perform(post("/login")
				.content(usersString)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	/**
	 * @description 测试@DeleteMapping("/logout/{userId}")
	 *   			注销登录
	 * @param userId
	 */
	@Test
	public void testLogout() throws Exception{
		String userId="191105C7PYKHBSCH";
		this.mockMvc.perform(delete("/logout/"+userId)
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
	}	
	
	/**
	 * @description 测试@PostMapping(value="/user_icons/{userId}" , headers="content-type=multipart/form-data")
	 *   			上传头像
	 * @param userId
	 * @param file
	 */
	@Test
	public void testUploadFace(){
		String userId="191105C7PYKHBSCH";
		String faceImage="C:\\imooc_video_dev\\videoAndaudio\\Image\\glass.jpg";
	}
	
}
