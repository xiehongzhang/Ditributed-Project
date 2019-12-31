package com.imooc.pojo1;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="举报信息实体", description="举报信息实体类")
public class Comments {
	@ApiModelProperty(hidden=true)
    private String id;//評論id

	@ApiModelProperty
    private String videoId;//視頻id

	@ApiModelProperty
    private String fromUserId;//評論者id

	@ApiModelProperty(hidden=true)
    private String parentCommentId;//父評論id

	@ApiModelProperty(hidden=true)
    private String toUserId;//被評論者id

	@ApiModelProperty(hidden=true)
    private Date createTime;//創建時間

	@ApiModelProperty
    private String comment;//評論內容

	@ApiModelProperty
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
}