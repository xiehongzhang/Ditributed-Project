package com.imooc.dao_1;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.imooc.pojo1.vo.VideoVO;

@Repository
public interface VideoMapperCustom {
    
    /**
     * @Description 查询所有的视频和有关用户的信息
     */
    List<VideoVO> queryAllVideo(@Param("videoDesc") String videoDesc, @Param("userId") String userId);
    
    /**
     * @Description 视频的点赞数加一
     */
    void addLikeCount(String videoId);
    
    /**
     * @Description 视频的点赞数减一
     */
    void reduceLikeCount(String videoId);

	/**
	 * @Description 查询用户点赞过的所有视频
	 */
	List<VideoVO> queryLikeVideoByUserId(String userId);

	
	/**
	 * @Description 查询我关注人发的视频
	 */
	List<VideoVO> queryMyFollowVideos(String userId);
}