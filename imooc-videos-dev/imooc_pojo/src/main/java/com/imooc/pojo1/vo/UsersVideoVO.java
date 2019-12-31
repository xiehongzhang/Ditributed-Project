/**
* Title: UsersVideoVO.java  

* Description   

* @author xhz  

* @date 2019年9月25日  
 
 */
package com.imooc.pojo1.vo;

/**
 * @author 
 * @description 
 */
public class UsersVideoVO {
	private UsersVO publisher;//用户显示层实体类
	private boolean userLikeVideo;//用户和视频的点赞关系
	
	public UsersVO getPublisher() {
		return publisher;
	}
	public void setPublisher(UsersVO publisher) {
		this.publisher = publisher;
	}
	public Boolean getUserLikeVideo() {
		return userLikeVideo;
	}
	public void setUserLikeVideo(Boolean userLikeVideo) {
		this.userLikeVideo = userLikeVideo;
	}
}
