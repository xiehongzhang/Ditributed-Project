package com.imooc.pojo1;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="举报视频信息实体", description="举报视频的信息实体类")
public class UserReport {
	@ApiModelProperty(hidden=true)
    private String id;//id

	@ApiModelProperty
    private String dealUserId;//被舉報的用戶

	@ApiModelProperty
    private String dealVideoId;//被舉報的視頻

	@ApiModelProperty
    private String title;//標題

	@ApiModelProperty
    private String content;//內容

	@ApiModelProperty
    private String userId;//舉報者

	@ApiModelProperty
    private Date createTime;//創建時間

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId == null ? null : dealUserId.trim();
    }

    public String getDealVideoId() {
        return dealVideoId;
    }

    public void setDealVideoId(String dealVideoId) {
        this.dealVideoId = dealVideoId == null ? null : dealVideoId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}