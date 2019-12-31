package com.imooc.pojo1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户实体",description="用户实体类")
public class Users {
	@ApiModelProperty(hidden=true)
    private String id;//用户id
	
	@ApiModelProperty
    private String username;//用户名称
	
	@ApiModelProperty
	@JsonIgnoreProperties
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
    private Integer receiveLikeCounts;//用户接受的点赞数量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage == null ? null : faceImage.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
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

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", faceImage=" + faceImage
				+ ", nickname=" + nickname + ", fansCounts=" + fansCounts + ", followCounts=" + followCounts
				+ ", receiveLikeCounts=" + receiveLikeCounts + "]";
	}
    
}