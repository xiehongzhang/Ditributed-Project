/**
* Title: VideoServiceImpl.java  

* Description   

* @author xhz  

* @date 2019年9月12日  
 
 */
package com.imooc.service.impl;

import java.util.Date;
import java.util.List;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.dao.CommentsCustomMapper;
import com.imooc.dao.CommentsMapper;
import com.imooc.dao.SearchRecordsMapper;
import com.imooc.dao.UsersLikeVideosMapper;
import com.imooc.dao.UsersMapper;
import com.imooc.dao.VideoMapper;
import com.imooc.dao.VideoMapperCustom;
import com.imooc.pojo.Comments;
import com.imooc.pojo.SearchRecords;
import com.imooc.pojo.UsersLikeVideos;
import com.imooc.pojo.UsersLikeVideosExample;
import com.imooc.pojo.UsersLikeVideosExample.Criteria;
import com.imooc.pojo.Video;
import com.imooc.pojo.vo.CommentsVO;
import com.imooc.pojo.vo.VideoVO;
import com.imooc.service.VideoService;
import com.imooc.utils.PageResult;

/**
 * @author xhz
 * @description 视频实现类
 */
@Service
public class VideoServiceImpl implements VideoService{
	
	@Autowired
	private Sid sid;
	
	@Autowired
	private VideoMapper videoMapper;
	
	@Autowired
	private VideoMapperCustom videoMapperCustom;
	
	@Autowired
	private SearchRecordsMapper searchRecordsMapper;

	@Autowired
	private UsersLikeVideosMapper usersLikeVideosMapper;
	
	@Autowired 
	private UsersMapper usersMapper;
	
	@Autowired
	private CommentsMapper commentsMapper;
	
	@Autowired
	private CommentsCustomMapper commentsCustomMapper;
	

	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	@Override
	public void saveVideoInfo(Video video) {
		videoMapper.insertSelective(video);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public PageResult queryAllVideo(Video video, Integer isSaveRecord, Integer pageNum, Integer pageSize) {
		String videoDesc=video.getVideoDesc();
		String userId=video.getUserId();
		//判断isSaveRecords是否不为空并且等于1
		if (isSaveRecord != null && isSaveRecord == 1) {
			SearchRecords searchRecords=new SearchRecords();
			String id=Sid.next();
			searchRecords.setId(id);
			searchRecords.setContent(videoDesc);
			//保存查询记录
			searchRecordsMapper.insert(searchRecords);
		}
		//准备分页查询
		PageHelper.startPage(pageNum, pageSize);
		//开始分页查询
		List<VideoVO> listVideoVO=videoMapperCustom.queryAllVideo(videoDesc,userId);
		//将查询的数据进行封装
		PageInfo<VideoVO> pageInfo =new PageInfo<>(listVideoVO);
		//实例化一个PageResult对象
		PageResult pageResult=new PageResult();
		pageResult.setRows(listVideoVO);
		pageResult.setPage(pageNum);
		pageResult.setRecords(pageInfo.getTotal());
		pageResult.setTotal(pageInfo.getPages());
		return pageResult;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public List<String> queryHotRecords() {		
		return searchRecordsMapper.queryHotRecords();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void saveLikeVideo(String userId, String videoId, String videoOwnerId) {
		//保存用户和喜欢的视频的关系表
		UsersLikeVideos usersLikeVideos = new UsersLikeVideos();
		String id = Sid.next();
		usersLikeVideos.setId(id);
		usersLikeVideos.setUserId(userId);
		usersLikeVideos.setVideoId(videoId);
		usersLikeVideosMapper.insert(usersLikeVideos);
		//保存用户喜欢视频的点赞数
		usersMapper.addReceiveLikeCounts(userId);
		//保存视频被点赞的点赞数
		videoMapperCustom.addLikeCount(videoId);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void saveUnlikeVideo(String userId, String videoId, String videoOwnerId) {
		//删除用户和喜欢的视频的关系表关系
		UsersLikeVideosExample example = new UsersLikeVideosExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andVideoIdEqualTo(videoId);
		usersLikeVideosMapper.deleteByExample(example);
		//取消用户喜欢视频的点赞数
		usersMapper.reduceReceiveLikeCounts(userId);
		//取消视频被点赞的点赞数
		videoMapperCustom.reduceLikeCount(videoId);
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public PageResult queryLikeVideo(String userId, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		//查询点赞过的视频
		List<VideoVO> videoVOList=videoMapperCustom.queryLikeVideoByUserId(userId);
		PageInfo<VideoVO> pageInfo=new PageInfo<>(videoVOList);
		PageResult pageResult=new PageResult();
		pageResult.setRows(videoVOList);
		pageResult.setPage(pageNum);
		pageResult.setRecords(pageInfo.getTotal());
		pageResult.setTotal(pageInfo.getPages());
		return pageResult;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void saveComments(Comments comments) {
		String id=sid.nextShort();
		comments.setId(id);
		comments.setCreateTime(new Date());
		//保存评论信息记录
		commentsMapper.insert(comments);		
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public PageResult queryAllComments(String videoId, Integer pageNum, Integer pageSize) {
		//进行分页查询
		PageHelper.startPage(pageNum, pageSize);
		List<CommentsVO> commentslist=commentsCustomMapper.queryAllComments(videoId);
		PageInfo<CommentsVO> pageInfo=new PageInfo<CommentsVO>(commentslist);
		PageResult pageResult=new PageResult();
		pageResult.setPage(pageNum);
		pageResult.setRecords(pageInfo.getPages());
		pageResult.setTotal((int)pageInfo.getTotal());
		pageResult.setRows(commentslist);
		return pageResult;
	}

	@Override
	public PageResult queryMyFollowVideos(String userId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<VideoVO> videolist = videoMapperCustom.queryMyFollowVideos(userId);
				
		PageInfo<VideoVO> pageInfo = new PageInfo<>(videolist);
		
		PageResult pagedResult = new PageResult();
		pagedResult.setTotal(pageInfo.getPages());
		pagedResult.setRows(videolist);
		pagedResult.setPage(page);
		pagedResult.setRecords(pageInfo.getTotal());
		
		return pagedResult;
	}


}
