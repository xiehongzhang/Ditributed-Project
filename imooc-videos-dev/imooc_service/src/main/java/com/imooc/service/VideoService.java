/**
* Title: VideoService.java  

* Description   

* @author xhz  

* @date 2019年9月12日  
 
 */
package com.imooc.service;

import java.util.List;

import com.imooc.pojo.Comments;
import com.imooc.pojo.Video;
import com.imooc.pojo.vo.VideoVO;
import com.imooc.utils.PageResult;

/**
 * @author xhz
 * @description 视频service
 */
public interface VideoService {

	/**
	 * @Description 保存视频的相关信息
	 */
	void saveVideoInfo(Video video);

	/**
	 * @Description 查询所有的视频信息
	 */
	PageResult queryAllVideo(Video video, Integer isSaveRecord, Integer pageNum, Integer pageSize);

	/**
	 * @Description 查询热搜词
	 */
	List<String> queryHotRecords();

	/**
	 * @Description 保存用户喜欢的视频
	 */
	public void saveLikeVideo(String userId, String videoId, String videoOwnerId);
	
	/**
	 * @Description 取消用户喜欢的视频
	 */
	public void saveUnlikeVideo(String userId, String videoId, String videoOwnerId);

	/**
	 * @Description 查询用户收藏的视频（即点赞过的视频）
	 */
	List<VideoVO> queryLikeVideo(String userId, Integer pageNum, Integer pageSize);

	/**
	 * @Description 保存用户评论视频的信息
	 */
	void saveComments(Comments comments);

	/**
	 * @Description 查询所有的评论
	 */
	PageResult queryAllComments(String videoId, Integer pageNum, Integer pageSize);

}
