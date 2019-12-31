package com.imooc.dao_1;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.imooc.pojo1.Users;
import com.imooc.pojo1.UsersExample;
@Repository
public interface UsersMapper {
    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

	/**
	 * @Description 通过用户名查询用户
	 */
	public Users queryUsersByUsername(Users users);

	/**
	 * @Description 通过用户名和密码查询用户
	 */
	public Users queryUsersByUP(@Param("users") Users users);
	
	/**
	 * @Description 用户的点赞数加一
	 */
	public void addReceiveLikeCounts(String userId);
	/**
	 * @Description 用户的点赞数减一
	 */
	public void reduceReceiveLikeCounts(String userId);

	/**
	 * @Description 用户的关注数加一
	 */
	void addFollowCounts(String userId);

	/**
	 * @Description 被关注者的粉丝数加一
	 */
	void addFansCounts(String followedUserId);

	/**
	 * @Description 用户的关注数减一 
	 */
	void reduceFollowCounts(String userId);

	/**
	 * @Description 被关注的粉丝数减一
	 */
	void reduceFansCounts(String followedUserId);

	/**
	 * @Description 通过用户id查询他所关注过的用户
	 */
	List<Users> queryFollowUser(String userId);
}