/**
* Title: MockTest.java  

* Description   

* @author xhz  

* @date 2019年10月15日  
 
 */
package com.imooc.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.imooc.pojo.Bgm;
import com.imooc.service.BgmService;

/**
 * @author xhz
 * @description 进行videoController类中的方法进行单元测试
 */

//配置spring和junit测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//加载上下文和springmvc的配置
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml","classpath:spring/springmvc.xml"})
@WebAppConfiguration
public class VideoControllerTest {
	
	
	@Autowired
	//配置web运用上下文
	private WebApplicationContext context;
	//mock
	private MockMvc mockMvc;
	
	@Autowired
	private BgmService bgmService;
	
	//初始化mockMvc
	@Before
	public  void setMockMvc (){
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	/**
	 * @name testShowAddBgm
	 * @Description 测试BGM的上传
	 * @param 
	 * @return 
	 */
	@Test
	public void testShowAddBgm() throws Exception{
		// 实例一个文件对象
		File file = new File("C:\\imooc_video_dev\\bgm\\v3.mp3");
		// 文件的名字
		String originalFilename = file.getName();
		// 创建一个文件输入流
		FileInputStream fileInputStream = new FileInputStream(file);
		// 实例一个MockMultipartFile对象
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", originalFilename,
				MediaType.TEXT_PLAIN_VALUE, fileInputStream);
		// 调用MockMvcBuilders的静态方法，fileUpload
		this.mockMvc
				.perform(fileUpload("/video/showAddBgm")
				.file(mockMultipartFile));
	}
	
	/**
	 * @name testAddBgm
	 * @Description 测试向数据库写进一个BGM的信息，同时在zookeeper服务器中创建一个节点
	 * @param 
	 * @return 
	 */
	@Test
	public void testAddBgm(){
		//创建一个BGM对象
		Bgm bgm=new Bgm();
		bgm.setAuthor("张二山");
		bgm.setName("分手快乐");
		bgm.setPath("\\bgm\\v4.mp3");
		bgmService.addBgm(bgm);
		System.out.println("This is a method of addBgm");
	}
	
	/**
	 * @name testDeleteBgm
	 * @Description 测试向数据库写进一个BGM的信息，同时在zookeeper服务器中创建一个节点
	 * @param 
	 * @return 
	 */
	@Test
	public void testDeleteBgm(){
		String bgmId="1910248PWZK85PM8";
		bgmService.deleteBgm(bgmId);
		System.out.println("This is a method of deleteBgm");
	}
	
	/**
	 * @name testQueryReportList
	 * @Description 测试查询用户举报列表
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@Test
	public void testQueryReportList() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.post("/video/reportList")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn();
	}
	
}
