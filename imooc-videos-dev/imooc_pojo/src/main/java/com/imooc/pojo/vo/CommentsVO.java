/**
* Title: CommentsVO.java  

* Description   

* @author xhz  

* @date 2019年9月29日  
 
 */
package com.imooc.pojo.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 * @description 
 */
@ApiModel(value="评论信息显示层实体", description="评论信息显示层实体类")
public class CommentsVO {
	@ApiModelProperty
    private String id;//id

	@ApiModelProperty
    private String videoId;//视频id

	@ApiModelProperty
    private String fromUserId;//发表人的id

	@ApiModelProperty
    private String parentCommentId;//父评论的id

	@ApiModelProperty
    private String toUserId;//被回复人的id

	@ApiModelProperty
    private Date createTime;//评论的创建时间

	@ApiModelProperty
    private String comment;//评论的内容
	
	@ApiModelProperty
	private String nickname;//评论人的昵称
	
	@ApiModelProperty
	private String faceImage;//评论人的头像
	

	@ApiModelProperty
	private String toNickname;//被回复者的昵称
	
	@ApiModelProperty
	private String timeAgoStr;//回复的时间

	@ApiModelProperty
	public String getFaceImage() {
		return faceImage;
	}
	
	public void setFaceImage(String faceImage) {
		this.faceImage = faceImage;
	}
	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId == null ? null : videoId.trim();
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId == null ? null : fromUserId.trim();
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId == null ? null : parentCommentId.trim();
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId == null ? null : toUserId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getToNickname() {
		return toNickname;
	}

	public void setToNickname(String toNickname) {
		this.toNickname = toNickname;
	}

	public String getTimeAgoStr() {
		return timeAgoStr;
	}

	public void setTimeAgoStr(String timeAgoStr) {
		this.timeAgoStr = timeAgoStr;
	}

}
