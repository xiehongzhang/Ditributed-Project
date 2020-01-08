/**
* Title: VideoServiceTest.java  

* Description   

* @author xhz  

* @date 2020年1月7日  
 
 */
package com.imooc.service.test;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.imooc.Application;
import com.imooc.enums.VideoStatuEnum;
import com.imooc.pojo.Video;
import com.imooc.service.VideoService;

/**
 * @author xhz
 * @description 测试VideoService中的所用方法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={Application.class})
public class VideoServiceTest {
	
	@Autowired
	private VideoService videoService;

	/**
	 * 测试saveVideoInfo(Video video)
	 * 测试保存视频信息的接口方法
	 */
	@Test
	public void testaveVideoInfo(){
		//生命video实体
		Video video = new Video();
		video.setId("190916CdeAB302Y8");
		video.setUserId("190903CHC87ZPXD4");
		video.setAudioId("2222");
		video.setVideoDesc("通过业务接口添加的视频");
		video.setVideoSeconds(10f);
		video.setVideoWidth(10);
		video.setVideoHeight(10);
		video.setVideoPath("//1.jpg");
		video.setCoverPath("//ddd.mp4");
		video.setLikeCounts(0L);
		video.setStatus(VideoStatuEnum.NORNAL.getValue());
		video.setCreateTime(LocalDateTime.now());
		//将视频的信息保存到数据库中
		videoService.saveVideoInfo(video);
	}
}
