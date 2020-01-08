/**
* Title: VideoControllerTest.java  

* Description   

* @author xhz  

* @date 2020年1月7日  
 
 */
package com.imooc.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import com.imooc.Application;
import com.imooc.pojo.Video;
import com.imooc.utils.JsonUtils;import scala.languageFeature.postfixOps;

/**
 * @author xhz
 * @description 测试VideoController中的所有接口
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={Application.class})
@WebAppConfiguration
public class VideoControllerTest {
	//注入web运用上下文
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	//生命MockMvc实例
	private MockMvc mockMvc;
	
	//通过web运用上下文实例mockMvc
	//使用@Before,在开始测试之前初始化mockMvc
	@Before 
	public void setupMockMvc(){
		this.mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/**
	 * @description 测试@PostMapping(value="/{userId}", headers="content-type=multipart/form-data")
	 *   			上传头像
	 *              upload(@PathVariable(value="userId") String userId, String bgmId, @ApiParam(value="file",allowEmptyValue=false) MultipartFile file, 
								  String desc, float videoSeconds, 
								  int videoWidth, int videoHeight)
	 * @param userId
	 * @param bgmId
	 * @param file
	 * @param desc
	 * @param videoSeconds
	 * @param videoWidth
	 * @param videoHeight
	 * @throws Exception 
	 */
	@Test
	public void testUpload() throws Exception{
		String userId="190903CHC87ZPXD4";
		String bgmId="1003";
		String desc="通过业务接口添加的视频";
		float videoSeconds=10f;
		int videoWidth=10;
		int videoHeight=10;
		File file=new File("C:\\imooc_video_dev\\videoAndaudio\\video\\v.mp4");
		String originalFilename=file.getName();
		String contentType="multipart/form-data";
		FileInputStream contentStream=new FileInputStream(file);
		//mock的文件
		MockMultipartFile mockFile=new MockMultipartFile("file", originalFilename, contentType, contentStream);
		//开始执行
		this.mockMvc.perform(multipart("/videos/" + userId + "?bgmId=" + bgmId + "&desc=" + desc + 
				"&videoSeconds=" + videoSeconds + "&videoWidth=" + videoWidth + "&videoHeight=" + videoHeight)
				.file(mockFile)
				.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
	
	/**
	 * @description 测试@PostMapping()
	 *   			上传头像
	 *              showAll(@RequestBody Video video, Integer isSaveRecord, Integer page, Integer pageSize)
	 * @param Integer isSaveRecord
	 * @param Integer page
	 * @param Integer pageSize
	 * @throws Exception 
	 */
	@Test
	public void testShowAll() throws Exception{
		int isSaveRecord=1;
		int page=1;
		int pageSize=5;
		Video video=new Video();
		video.setVideoDesc("接口");
		video.setUserId("190903CHC87ZPXD4");
		String videoString=JsonUtils.objectToJson(video);
		this.mockMvc.perform(post("/videos?isSaveRecord=" + isSaveRecord + "&page=" + page + "&pageSize=" + pageSize)
				.content(videoString)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
	}

}
