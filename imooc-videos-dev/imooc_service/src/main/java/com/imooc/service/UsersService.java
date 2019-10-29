/**
* Title: UsersService.java  

* Description   

* @author xhz  

* @date 2019年8月6日  
 
 */
package com.imooc.service;

import java.util.List;

import com.imooc.pojo.UserReport;
import com.imooc.pojo.Users;

/**
 * @author 
 * @description 
 */
public interface UsersService {
	/**
	 * @Description 通过用户名查询用户是否存在
	 */
	public boolean queryUsersIsExists(String username);
	
	/**
	 * @Description 保存用户
	 */
	public void saveUsers(Users users);
	
	/**
	 * @Description 通过用户名和密码查询用户
	 */
	public Users queryUsersByUP(String username, String password);
	
	/**
	 * @Description 上传用户头像文件
	 */
	public void uploadFaceImage(Users users);

	/**
	 * @Description 通过主键查询用户
	 */
	public Users selectByPrimaryKey(String id);

	/**
	 * @Description 查询用户信息
	 */
	public Users queryUserInfo(String userId);

	/**
	 * @Description 用户和视频的点赞关系
	 */
	public Boolean isLike(String userId, String videoId);

	/**
	 * @Description 保存关注的记录，用户本身的关注数加一，同时被关注用户的粉丝数加一
	 */
	public void saveFollowUsers(String userId, String followedUserId);

	/**
	 * @Description 取消关注的记录，用户本身的关注数减一，同时被关注者的粉丝数减一
	 */
	public void deleteFollowUsers(String userId, String followedUserId);

	/**
	 * @Description 查询用户和被关注者的关系是否存在
	 */
	public Boolean isFollow(String userId, String followerId);

	/**
	 * @Description 查询用户关注的所有用户
	 */
	public List<Users> queryFollowUser(String userId, Integer pageNum, Integer pageSize);

	/**
	 * @Description 保存用户举报的信息
	 */
	public void saveUserReport(UserReport userReport);

}
