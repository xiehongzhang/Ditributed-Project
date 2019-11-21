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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.UserReportMapperCustom;
import com.imooc.mapper.VideoMapper;
import com.imooc.pojo.Bgm;
import com.imooc.pojo.UserReport;
import com.imooc.pojo.Video;
import com.imooc.pojo.VideoExample;
import com.imooc.pojo.VideoExample.Criteria;
import com.imooc.service.VideoService;
import com.imooc.utils.PageResult;

/**
 * @author 
 * @description 
 */
@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoMapper videoMapper;
	
	@Autowired
	private UserReportMapperCustom userReportMapperCustom;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public PageResult queryVideo(Video video , Integer page , Integer pageSize) {
		String videoDesc=null;
		PageHelper.startPage(page, pageSize);
		VideoExample videoExample=new VideoExample();
		if(video != null){
			videoDesc=video.getVideoDesc();
			if (videoDesc!=null) {
				Criteria criteria=videoExample.createCriteria();
				criteria.andVideoDescLike("%" + videoDesc + "%");
			}
		}
		List<Video> list=videoMapper.selectByExample(videoExample);
		PageInfo<Video> pageInfo=new PageInfo<>(list);
		PageResult pageResult=new PageResult();
		pageResult.setPage(page);
		pageResult.setTotal(pageInfo.getPages());
		pageResult.setRecords((int)pageInfo.getTotal());
		pageResult.setRows(list);
		return pageResult;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public PageResult queryReportList(UserReport userReport, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Bgm> list=userReportMapperCustom.queryReportList();
		PageInfo<Bgm> pageInfo=new PageInfo<>(list);
		PageResult pageResult=new PageResult();
		pageResult.setPage(page);
		pageResult.setTotal(pageInfo.getPages());
		pageResult.setRecords((int)pageInfo.getTotal());
		pageResult.setRows(list);
		return pageResult;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void updateVideoStatus(String videoId , Integer status) {
		Video video=new Video();
		video.setId(videoId);
		video.setStatus(status);
		videoMapper.updateByPrimaryKeySelective(video);
	}

}
