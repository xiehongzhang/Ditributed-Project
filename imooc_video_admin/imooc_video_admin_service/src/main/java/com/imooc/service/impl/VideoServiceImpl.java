/**
* Title: VideoServiceImpl.java  

* Description   

* @author xhz  

* @date 2019年10月9日  
 
 */
package com.imooc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.mapper.VideoMapper;
import com.imooc.pojo.Video;
import com.imooc.pojo.VideoExample;
import com.imooc.service.VideoService;

/**
 * @author 
 * @description 
 */
@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoMapper videoMapper;
	
	@Override
	public List<Video> queryVideo() {
		VideoExample videoExample=new VideoExample();
		return videoMapper.selectByExample(videoExample);
	}

}
