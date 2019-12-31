package com.imooc.pojo1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户和视频的关系实体", description="用户和视频关系实体类")
public class UsersLikeVideos {
	@ApiModelProperty
    private String id;

	@ApiModelProperty
    private String userId;

	@ApiModelProperty
    private String videoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId == null ? null : videoId.trim();
    }
}