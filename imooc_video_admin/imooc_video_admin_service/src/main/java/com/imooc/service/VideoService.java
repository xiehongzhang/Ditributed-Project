/**
* Title: VideoService.java  

* Description   

* @author xhz  

* @date 2019年10月9日  
 
 */
package com.imooc.service;

import java.util.List;

import com.imooc.pojo.UserReport;
import com.imooc.pojo.Video;
import com.imooc.utils.PageResult;

/**
 * @author xhz	
 * @description 视频service层
 */
public interface VideoService {

	/**
	 * @Description 
	 */
	List<Video> queryVideo();

	/**
	 * @Description 查询用户举报列表
	 */
	PageResult queryReportList(UserReport userReport, Integer page, int pageSize);

}
