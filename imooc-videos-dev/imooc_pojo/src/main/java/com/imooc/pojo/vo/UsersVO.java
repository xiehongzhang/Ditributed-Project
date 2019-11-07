/**
* Title: UsersVO.java  

* Description   

* @author xhz  

* @date 2019年8月21日  
 
 */
package com.imooc.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 * @description 
 */
@ApiModel(value="用户数据层实体类",description="用于数据层显示")
public class UsersVO {
	@ApiModelProperty(hidden=true)
	private String userToken;//用户userToken
	
	@ApiModelProperty(hidden=true)
    private String id;//users表id
	
	@ApiModelProperty
    private String username;//用户名称
	
	@ApiModelProperty
	@JsonIgnore
    private String password;//账号密码
	
    @ApiModelProperty(hidden=true)
    private String faceImage;//用户头像
    
    @ApiModelProperty(hidden=true)
    private String nickname;//用户昵称
    
    @ApiModelProperty(hidden=true)
    private Integer fansCounts;//用户的粉丝数量
    
    @ApiModelProperty(hidden=true)
    private Integer followCounts;//用户的关注数量
    
    @ApiModelProperty(hidden=true)
    private Integer receiveLikeCounts;//用户的接受点赞的数量
    
    @ApiModelProperty(hidden=false)
    private Boolean follow;//是否关注
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFaceImage() {
		return faceImage;
	}
	public void setFaceImage(String faceImage) {
		this.faceImage = faceImage;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getFansCounts() {
		return fansCounts;
	}
	public void setFansCounts(Integer fansCounts) {
		this.fansCounts = fansCounts;
	}
	public Integer getFollowCounts() {
		return followCounts;
	}
	public void setFollowCounts(Integer followCounts) {
		this.followCounts = followCounts;
	}
	public Integer getReceiveLikeCounts() {
		return receiveLikeCounts;
	}
	public void setReceiveLikeCounts(Integer receiveLikeCounts) {
		this.receiveLikeCounts = receiveLikeCounts;
	}
	
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public Boolean getFollow() {
		return follow;
	}
	public void setFollow(Boolean follow) {
		this.follow = follow;
	}

}
