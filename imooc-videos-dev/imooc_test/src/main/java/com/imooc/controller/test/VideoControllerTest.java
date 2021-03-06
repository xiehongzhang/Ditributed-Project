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

import javax.xml.stream.events.Comment;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import com.imooc.Application;
import com.imooc.pojo.Comments;
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

	/**
	 * @description 测试@GetMapping("/search_records")
	 *   			查询热搜词
	 *              hot()
	 * @throws Exception 
	 */
	@Test
	public void testHot() throws Exception{
		this.mockMvc.perform(get("/videos/search_records")
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
	}


	/**
	 * @description 测试@PostMapping("/like_video/{videoId}")
	 *   			点赞视频
	 *              userLike(String userId, @PathVariable(value="videoId") String videoId, String videoCreaterId)
	 * @param String userId
	 * @param String videoId
	 * @param String videoCreaterId
	 * @throws Exception 
	 */
	@Test
	public void testUserLike() throws Exception{
		String userId="190903CHC87ZPXD4";
		String videoId="191112D0MAN77X1P";
		String videoCreaterId="191112D07KXN6PBC";
		this.mockMvc.perform(post("/videos/like_video/" + videoId + "?userId=" + userId + "&videoCreaterId=" + videoCreaterId) 
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	/**
	 * @description 测试@PostMapping("/un_like_video/{videoId}")
	 *   			取消点赞
	 *              userUnLike(String userId, @PathVariable(value="videoId") String videoId, String videoCreaterId)
	 * @param String userId
	 * @param String videoId
	 * @param String videoCreaterId
	 * @throws Exception 
	 */
	@Test
	public void testUserUnLike() throws Exception{
		String userId="190903CHC87ZPXD4";
		String videoId="191112D0MAN77X1P";
		String videoCreaterId="191112D07KXN6PBC";
		this.mockMvc.perform(post("/videos/un_like_video/" + videoId + "?userId=" + userId + "&videoCreaterId=" + videoCreaterId) 
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
	}
	


	/**
	 * @description 测试@GetMapping("/show_like_video/{userId}")
	 *   			查询用户点赞的视频
	 *              showMyLike(@PathVariable(value="userId") String userId, Integer page, Integer pageSize)
	 * @param String userId
	 * @param Integer page
	 * @param Integer pageSize
	 * @throws Exception 
	 */
	@Test
	public void testShowMyLike() throws Exception{
		String userId="190903CHC87ZPXD4";
		this.mockMvc.perform(get("/videos/show_like_video/" + userId) 
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	/**
	 * @description 测试@GetMapping("/show_follow_video/{userId}")
	 *   			查询用户关注的视频
	 *              showMyFollow(@PathVariable(value="userId") String userId, Integer page, Integer pageSize)
	 * @param String userId
	 * @param Integer page
	 * @param Integer pageSize
	 * @throws Exception 
	 */
	@Test
	public void testShowMyFollow() throws Exception{
		String userId="190903CHC87ZPXD4";
		this.mockMvc.perform(get("/videos/show_follow_video/" + userId) 
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
	}

	/**
	 * @description 测试@PostMapping("/comments")
	 *   			评论视频
	 *              saveComment(@RequestBody Comments comments, String fatherCommentId, String toUserId)
	 * @param Comments comments
	 * @param String fatherCommentId
	 * @param String toUserId
	 * @throws Exception 
	 */
	@Test
	public void testSaveComment() throws Exception{
		Comments comments=new Comments();
		String fatherCommentId="";
		String toUserId="";
		comments.setComment("我不会唱歌！");
		comments.setVideoId("200110D0Y5RF95YW");
		comments.setFromUserId("190903CHC87ZPXD4");
		String commentsString=JsonUtils.objectToJson(comments);
		this.mockMvc.perform(post("/videos/comments") 
				.content(commentsString)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
}
