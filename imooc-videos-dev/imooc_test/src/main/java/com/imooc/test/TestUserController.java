/**
* Title: TestUserController.java  

* Description   

* @author xhz  

* @date 2019年8月29日  
 
 */
package com.imooc.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.imooc.Application;

/**
 * @author xhz
 * @description 对userController接口中的方法进行测试
 */
// 配置spring和junit测试环境
@RunWith(SpringJUnit4ClassRunner.class)
// 打开主程序入口
@SpringBootTest(classes = { Application.class })
// 配置web运用程序
@WebAppConfiguration
public class TestUserController {

	// 配置web运用上下文
	@Autowired
	private WebApplicationContext webApplicationContext;
	// 实例MockMvc
	private MockMvc mockMvc;

	@Before
	// 初始化mockMvc
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * @name faceImageUploadTest
	 * @Description 进行头像文件上传测试
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void faceImageUploadTest() throws Exception {
		// 实例一个文件对象
		File file = new File("C:\\imooc_video_dev\\videoAndaudio\\Image\\xiexiaozhang.jpg");
		// 文件的名字
		String originalFilename = file.getName();
		// 用户id
		String userId = "190916CPTY19Z540";
		// 创建一个文件输入流
		FileInputStream fileInputStream = new FileInputStream(file);
		// 实例一个MockMultipartFile对象
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", originalFilename,
				MediaType.TEXT_PLAIN_VALUE, fileInputStream);
		// 调用MockMvcBuilders的静态方法，fileUpload
		this.mockMvc
				.perform(fileUpload("/user/uploadFaceImage")
						// 要上传的文件
						.file(mockMultipartFile)
						// 要传的参数
						.param("userId", userId))
				// 调用MockMvcResultMatchers的静态方法status()
				.andExpect(status().is(200))
				// 调用MockMvcResultMatchers的静态方法content()
				.andExpect(content().string("success"));
	}
}
