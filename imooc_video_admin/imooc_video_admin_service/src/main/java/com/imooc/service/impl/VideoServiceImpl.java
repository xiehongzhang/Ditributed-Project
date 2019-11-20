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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.UserReportMapperCustom;
import com.imooc.mapper.VideoMapper;
import com.imooc.pojo.Bgm;
import com.imooc.pojo.UserReport;
import com.imooc.pojo.Video;
import com.imooc.pojo.VideoExample;
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
	
	@Override
	public List<Video> queryVideo() {
		VideoExample videoExample=new VideoExample();
		return videoMapper.selectByExample(videoExample);
	}

	@Override
	public PageResult queryReportList(UserReport userReport, Integer page, int pageSize) {
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

}
