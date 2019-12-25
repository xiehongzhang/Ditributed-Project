/**
* Title: UsersSeriveceImpl.java  

* Description   

* @author xhz  

* @date 2019年8月6日  
 
 */
package com.imooc.service.impl;


import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.imooc.dao.UserReportMapper;
import com.imooc.dao.UsersFansMapper;
import com.imooc.dao.UsersLikeVideosMapper;
import com.imooc.dao.UsersMapper;
import com.imooc.pojo.UserReport;
import com.imooc.pojo.Users;
import com.imooc.pojo.UsersFans;
import com.imooc.pojo.UsersFansExample;
import com.imooc.pojo.UsersFansExample.Criteria;
import com.imooc.pojo.UsersLikeVideos;
import com.imooc.pojo.UsersLikeVideosExample;
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
		Users users=new Users();
		users.setUsername(username);
		Users usersResult=usersMapper.queryUsersByUsername(users);
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
		Users usersResult=usersMapper.queryUsersByUP(users);
		return usersResult;
		
	}

	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	@Override
	public void uploadFaceImage(Users users) {
		usersMapper.updateByPrimaryKeySelective(users);
		
	}

	@Override
	public Users selectByPrimaryKey(String id) {
		return usersMapper.selectByPrimaryKey(id);
	}

	@Override
	public Users queryUserInfo(String userId) {
		Users users=usersMapper.selectByPrimaryKey(userId);
		return users;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public Boolean isLike(String userId, String videoId) {
		if (StringUtils.isBlank(videoId) || StringUtils.isBlank(userId)) {
			return false;
		}
		//根据用户id和视频id查询是否存在关系
		UsersLikeVideosExample example=new UsersLikeVideosExample();
		com.imooc.pojo.UsersLikeVideosExample.Criteria criteria=example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andVideoIdEqualTo(videoId);
		List<UsersLikeVideos> list=usersLikeVideosMapper.selectByExample(example);
		if (list != null && list.size()>0) {
			return true;
		}
		return false;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void saveFollowUsers(String userId, String followedUserId) {
		//保存关注记录
		UsersFans usersFans=new UsersFans();
		String id=sid.nextShort();
		usersFans.setId(id);
		usersFans.setUserId(followedUserId);
		usersFans.setFanId(userId);
		usersFansMapper.insert(usersFans);
		//用户本身的关注数加一
		usersMapper.addFollowCounts(userId);
		//被关注的用户的粉丝数加一
		usersMapper.addFansCounts(followedUserId);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void deleteFollowUsers(String userId, String followedUserId) {
		//删除关注的记录
		UsersFansExample example=new UsersFansExample();
		com.imooc.pojo.UsersFansExample.Criteria criteria=example.createCriteria();
		criteria.andFanIdEqualTo(userId);
		criteria.andUserIdEqualTo(followedUserId);
		usersFansMapper.deleteByExample(example);
		//用户的关注数减一
		usersMapper.reduceFollowCounts(userId);
		//被关注者的粉丝数减一
		usersMapper.reduceFansCounts(followedUserId);
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public Boolean isFollow(String userId, String followerId) {
		//查询是否存在这样的记录
		UsersFansExample example=new UsersFansExample();
        Criteria criteria=example.createCriteria();
		criteria.andFanIdEqualTo(userId);
		criteria.andUserIdEqualTo(followerId);
		List<UsersFans> list = usersFansMapper.selectByExample(example);
		if (list != null && list.size()>0) {
			return true;
		}
		return false;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public List<Users> queryFollowUser(String userId, Integer pageNum, Integer pageSize) {
		//准备分页查询
		PageHelper.startPage(pageNum, pageSize);
		List<Users> usersList=usersMapper.queryFollowUser(userId);
		return usersList;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void saveUserReport(UserReport userReport) {
		String id=sid.nextShort();
		userReport.setId(id);
		userReport.setCreateTime(new Date());
		userReportMapper.insert(userReport);
	}

}
