package com.imooc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.pojo.vo.VideoVO;

@Repository
public interface VideoMapperCustom {
    
    /**
     * @Description 查询所有的视频和有关用户的信息
     */
	IPage<VideoVO> queryAllVideo(Page<VideoVO> page,@Param("videoDesc") String videoDesc, @Param("userId") String userId);
    
    /**
     * @Description 视频的点赞数加一
     */
    void addLikeCount(String videoId);
    
    /**
     * @Description 视频的点赞数减一
     */
    void reduceLikeCount(String videoId);

	/**
	 * @param page 
	 * @Description 查询用户点赞过的所有视频
	 */
	IPage<VideoVO> queryLikeVideoByUserId(Page<VideoVO> page, String userId);

	
	/**
	 * @param page 
	 * @Description 查询我关注人发的视频
	 */
	IPage<VideoVO> queryMyFollowVideos(Page<VideoVO> page, String userId);
}