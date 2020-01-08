/**
* Title: UsersSeriveceImpl.java  

* Description   

* @author xhz  

* @date 2019年8月6日  
 
 */
package com.imooc.service.impl;


import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.dao.UserReportMapper;
import com.imooc.dao.UsersFansMapper;
import com.imooc.dao.UsersLikeVideosMapper;
import com.imooc.dao.UsersMapper;
import com.imooc.pojo.UserReport;
import com.imooc.pojo.Users;
import com.imooc.pojo.UsersFans;
import com.imooc.pojo.UsersLikeVideos;
import com.imooc.service.UsersService;
import com.imooc.utils.MD5Util;

/**
 * @author 
 * @description 
 */
@Service
public class UsersSeriveceImpl implements UsersService {
	//生成一个全局的唯一id
	@Autowired
	private Sid sid;

	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private UsersLikeVideosMapper usersLikeVideosMapper;
	
	@Autowired 
	private UsersFansMapper usersFansMapper;
	
	@Autowired 
	private UserReportMapper userReportMapper;

	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.SUPPORTS)
	@Override
	public boolean queryUsersIsExists(String username) {
//		Users users=new Users();
//		users.setUsername(username);
//		Users usersResult=usersMapper.queryUsersByUsername(users);
//		return usersResult != null ? true : false ;
		QueryWrapper<Users> wrapper=new QueryWrapper();
		wrapper.eq("username", username);
		Users usersResult=usersMapper.selectOne(wrapper);
		return usersResult != null ? true : false ;
	}
	
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public void saveUsers(Users users) {
		String id=sid.nextShort();
		users.setId(id);
		usersMapper.insert(users);
	}

	@Override
	public Users queryUsersByUP(String username,String password) {
		Users users=new Users();
		try {
			users.setPassword(MD5Util.getMD5String(password));
			users.setUsername(username);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		QueryWrapper<Users> usersWrapper=new QueryWrapper<>();
		usersWrapper.eq("username", users.getUsername());
		usersWrapper.eq("password", users.getPassword());
		Users usersResult=usersMapper.selectOne(usersWrapper);
		return usersResult;
		
	}

	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	@Override
	public void uploadFaceImage(Users users) {
		//根据id，更新实体
		usersMapper.updateById(users);
		
	}

	@Override
	public Users selectByPrimaryKey(String id) {
		return usersMapper.selectById(id);
	}

	@Override
	public Users queryUserInfo(String userId) {
		Users users=usersMapper.selectById(userId);
		return users;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public Boolean isLike(String userId, String videoId) {
		if (StringUtils.isBlank(videoId) || StringUtils.isBlank(userId)) {
			return false;
		}
		//根据用户id和视频id查询是否存在关系
//		UsersLikeVideosExample example=new UsersLikeVideosExample();
//		com.imooc.pojo1.UsersLikeVideosExample.Criteria criteria=example.createCriteria();
//		criteria.andUserIdEqualTo(userId);
//		criteria.andVideoIdEqualTo(videoId);
//		List<UsersLikeVideos> list=usersLikeVideosMapper.selectByExample(example);
		QueryWrapper<UsersLikeVideos> wrapper=new QueryWrapper<>();
		wrapper.eq("user_id", userId);
		wrapper.eq("video_id", videoId);
		List<UsersLikeVideos> list=usersLikeVideosMapper.selectList(wrapper);
		if (list != null && list.size()>0) {
			return true;
		}
		return false;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void saveFollowUsers(String userId, String publisherId) {
		//保存关注记录
		UsersFans usersFans=new UsersFans();
		String id=sid.nextShort();
		usersFans.setId(id);
		usersFans.setUserId(publisherId);
		usersFans.setFanId(userId);
		//保存用户和粉丝的记录
		usersFansMapper.insert(usersFans);
		//用户本身的关注数加一
		usersMapper.addFollowCounts(userId);
		//被关注的用户的粉丝数加一
		usersMapper.addFansCounts(publisherId);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void deleteFollowUsers(String userId, String publisherId) {
		//删除关注的记录
//		UsersFansExample example=new UsersFansExample();
//		com.imooc.pojo1.UsersFansExample.Criteria criteria=example.createCriteria();
//		criteria.andFanIdEqualTo(userId);
//		criteria.andUserIdEqualTo(followedUserId);
		UpdateWrapper<UsersFans> wrapper=new UpdateWrapper<>();
		wrapper.eq("user_id", publisherId);
		wrapper.eq("fan_id", userId);
		usersFansMapper.delete(wrapper);
		//用户的关注数减一
		usersMapper.reduceFollowCounts(userId);
		//视频发布者粉丝数减一
		usersMapper.reduceFansCounts(publisherId);
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public Boolean isFollow(String userId, String followerId) {
//		//查询是否存在这样的记录
//		UsersFansExample example=new UsersFansExample();
//        Criteria criteria=example.createCriteria();
//		criteria.andFanIdEqualTo(userId);
//		criteria.andUserIdEqualTo(followerId);
//		List<UsersFans> list = usersFansMapper.selectByExample(example);
		//查询是否存在记录
		QueryWrapper<UsersFans> wrapper=new QueryWrapper<>();
		wrapper.eq("user_id", userId);
		wrapper.eq("fan_id", followerId);
		List<UsersFans> list=usersFansMapper.selectList(wrapper);
		if (list != null && list.size()>0) {
			return true;
		}
		return false;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public IPage<Users> queryFollowUser(String userId, Integer current, Integer pageSize) {
		//准备分页查询
//		PageHelper.startPage(pageNum, pageSize);
		Page<Users> page=new Page<Users>(current,pageSize);
		IPage<Users> iPage=usersMapper.queryFollowUser(page,userId);
		return iPage;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void saveUserReport(UserReport userReport) {
		String id=sid.nextShort();
		userReport.setId(id);
		userReport.setCreateTime(LocalDateTime.now());
		userReportMapper.insert(userReport);
	}

}
