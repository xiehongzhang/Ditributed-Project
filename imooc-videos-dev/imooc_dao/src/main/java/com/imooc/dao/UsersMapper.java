package com.imooc.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.pojo.Users;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xhz
 * @since 2019-12-27
 */
public interface UsersMapper extends BaseMapper<Users> {

	/**
	 * @name addFollowCounts
	 * @Description 用户的关注数量加一
	 * @param userId
	 * @return 
	 */
	void addFollowCounts(@Param(value = "userId") String userId);

	/**
	 * @name addFansCounts
	 * @Description 用户的粉丝数量加一
	 * @param followedUserId
	 * @return 
	 */
	void addFansCounts(@Param(value="publisherId") String publisherId);

	/**
	 * @name reduceFollowCounts
	 * @Description 用户的关注数量少一
	 * @param userId
	 * @return 
	 */
	void reduceFollowCounts(@Param(value = "userId") String userId);

	/**
	 * @name reduceFansCounts
	 * @Description 用户的粉丝数量加一
	 * @param publisherId
	 * @return 
	 */
	void reduceFansCounts(String publisherId);


}
