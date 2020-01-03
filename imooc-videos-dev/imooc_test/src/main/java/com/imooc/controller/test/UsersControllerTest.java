/**
* Title: LoginRegisterControllerTest.java  

* Description   

* @author xhz  

* @date 2019年12月30日  
 
 */
package com.imooc.controller.test;



import java.io.File;
import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;


import com.imooc.Application;
import com.imooc.pojo.UserReport;
import com.imooc.pojo.Users;
import com.imooc.utils.JsonResult;
import com.imooc.utils.JsonUtils;

/**
 * @author xhz
 * @description 测试LoginRegisterController中的方法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={Application.class})
@WebAppConfiguration
public class UsersControllerTest {
	
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
	 * @description 测试@PostMapping(value="/user_icons/{userId}" , headers="content-type=multipart/form-data")
	 *   			上传头像
	 * @param userId
	 * @param file
	 * @throws Exception 
	 */
	@Test
	public void testUploadFace() throws Exception{
		String userId="191105C7PYKHBSCH";
		File faceImage=new File("C:\\imooc_video_dev\\videoAndaudio\\Image\\glass.jpg");
		String fileName=faceImage.getName();
		System.out.println(fileName);
		FileInputStream fileInputStream=new FileInputStream(faceImage);
		MockMultipartFile mockFile=new MockMultipartFile("file",fileName, MediaType.MULTIPART_FORM_DATA_VALUE,fileInputStream);
		this.mockMvc.perform(multipart("/users/user_icons/"+userId).file(mockFile)
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	/**
	 * @description 测试@GetMapping(value="/user_info/{userId}")
	 * 				query(@PathVariable(value="userId") String userId, String fanId)
	 *   			查询用户信息
	 * @param userId
	 * @param fanId
	 * @throws Exception 
	 */
	@Test
	public void testQuery() throws Exception {
		String userId="19092875HTCBC568";
		String fanId="190903CHC87ZPXD4";
		this.mockMvc.perform(get("/users/user_info/"+userId+"?fanId="+fanId)
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	/**
	 * @description 测试@GetMapping("/video_user_info/{videoId}")
	 * 				queryPublisher(String loginUserId, @PathVariable(value="videoId") String videoId, String publishUserId)
	 *   			查询用户信息
	 * @param loginUserId
	 * @param videoId
	 * @param publishUserId
	 * @throws Exception 
	 */
	@Test
	public void testQueryPublisher() throws Exception {
		String loginUserId="190903CHC87ZPXD4";
		String videoId="190916CBYAB302Y8";
		String publishUserId="190916C8H2AT05GC";
		this.mockMvc.perform(get("/users/video_user_info/"+videoId+"?loginUserId="+loginUserId+"&publishUserId="+publishUserId)
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
	}

	/**
	 * @description 测试@PostMapping("/follow_user/{userId}")
	 * 				beyourfans(@PathVariable(value="userId") String userId, String publisherId)
	 *   			关注用户
	 * @param userId
	 * @param fanId
	 * @throws Exception 
	 */
	@Test
	public void testBeyourfans() throws Exception {
		String userId="19092876SC9DGYA8";
		String publisherId="200102B3GB6BWHM8";
		this.mockMvc.perform(post("/users/follow_user/"+userId+"?publisherId="+publisherId)
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	/**
	 * @description 测试@PostMapping("/un_follow_user/{userId}")
	 * 				dontbeyourfans(@PathVariable(value="userId") String userId, String publisherId)
	 *   			取消关注
	 * @param userId
	 * @param publisherId
	 * @throws Exception 
	 */
	@Test
	public void testDontbeyourfans() throws Exception {
		String userId="191105C7PYKHBSCH";
		String publisherId="19092876SC9DGYA8";
		this.mockMvc.perform(post("/users/un_follow_user/"+userId+"?publisherId="+publisherId)
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	/**
	 * @description 测试@PostMapping("/report_video")
	 * 				reportUser(@RequestBody  UserReport userReport)
	 *   			举报视频
	 * @param userId
	 * @param publisherId
	 * @throws Exception 
	 */
	@Test
	public void testReportUser() throws Exception{
		UserReport userReport=new UserReport();
		userReport.setContent("都是假象");
		userReport.setDealUserId("191112D07KXN6PBC");
		userReport.setDealVideoId("191112D0MAN77X1P");
		userReport.setTitle("骗子");
		userReport.setUserId("190903CHC87ZPXD4");
		String userReportString=JsonUtils.objectToJson(userReport);
		this.mockMvc.perform(post("/users/report_video")
				.content(userReportString)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
}