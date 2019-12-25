package com.imooc.pojo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="视频实体", description="视频实体类")
public class Video {
	@ApiModelProperty(hidden=true)
    private String id;//視頻id

	@ApiModelProperty(hidden=true)
    private String userId;//用戶id

	@ApiModelProperty(hidden=true)
    private String audioId;//BGM id

	@ApiModelProperty
    private String videoDesc;

	@ApiModelProperty(hidden=true)
    private Float videoSeconds;//視頻秒數

	@ApiModelProperty(hidden=true)
    private Integer videoWidth;//視頻的寬度

	@ApiModelProperty(hidden=true)
    private Integer videoHeight;//視頻的高度

	@ApiModelProperty(hidden=true)
    private String videoPath;//視頻保存路徑

	@ApiModelProperty(hidden=true)
    private String coverPath;//封面路徑

	@ApiModelProperty(hidden=true)
    private Long likeCounts;//點讚數

	@ApiModelProperty(hidden=true)
    private Integer status;//狀態

	@ApiModelProperty(hidden=true)
    private Date createTime;//日期

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

    public String getAudioId() {
        return audioId;
    }

    public void setAudioId(String audioId) {
        this.audioId = audioId == null ? null : audioId.trim();
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc == null ? null : videoDesc.trim();
    }

    public Float getVideoSeconds() {
        return videoSeconds;
    }

    public void setVideoSeconds(Float videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    public Integer getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    public Integer getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath == null ? null : videoPath.trim();
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath == null ? null : coverPath.trim();
    }

    public Long getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(Long likeCounts) {
        this.likeCounts = likeCounts;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}