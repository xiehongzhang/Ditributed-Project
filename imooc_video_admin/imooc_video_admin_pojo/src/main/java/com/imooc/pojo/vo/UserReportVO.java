package com.imooc.pojo.vo;

import java.util.Date;

/**
 * @author xhz
 * @description 用户举报显示层实体类
 */
public class UserReportVO {
    private String id;//id

    private String dealUsername;//被举报人
    
	private String dealVideoId;//被举报视频id
	
	private String title;//标题
	
	private String content;//内容
	
	private String videoPath;//被举报视频路径
	
	private Date createTime;//举报日期
	
	private Integer status;//视频状态
	
	private String submitUsername;//举报人用户名


    public String getDealUsername() {
		return dealUsername;
	}

	public void setDealUsername(String dealUsername) {
		this.dealUsername = dealUsername;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSubmitUsername() {
		return submitUsername;
	}

	public void setSubmitUsername(String submitUsername) {
		this.submitUsername = submitUsername;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}