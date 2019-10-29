/**
* Title: UsersVideoVO.java  

* Description   

* @author xhz  

* @date 2019年9月25日  
 
 */
package com.imooc.pojo.vo;

/**
 * @author 
 * @description 
 */
public class UsersVideoVO {
	private UsersVO usersVO;//用户显示层实体类
	private Boolean isLike;//用户和视频的点赞关系
	public UsersVO getUsersVO() {
		return usersVO;
	}
	public void setUsersVO(UsersVO usersVO) {
		this.usersVO = usersVO;
	}
	public Boolean getIsLike() {
		return isLike;
	}
	public void setIsLike(Boolean isLike) {
		this.isLike = isLike;
	}
}
