/**
* Title: VideoService.java  

* Description   

* @author xhz  

* @date 2019年10月9日  
 
 */
package com.imooc.service;

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
	PageResult queryVideo(Video video , Integer page , Integer pageSize);

	/**
	 * @Description 查询用户举报列表
	 */
	PageResult queryReportList(UserReport userReport, Integer page, Integer pageSize);

	/**
	 * @Description 改变视频的状态
	 */
	void updateVideoStatus(String videoId , Integer status);

}
