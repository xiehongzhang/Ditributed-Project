package com.imooc.pojo1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户和粉丝关系实体", description="用户和粉丝关系实体类")
public class UsersFans {
	@ApiModelProperty
    private String id;

	@ApiModelProperty
    private String userId;

	@ApiModelProperty
    private String fanId;

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

    public String getFanId() {
        return fanId;
    }

    public void setFanId(String fanId) {
        this.fanId = fanId == null ? null : fanId.trim();
    }
}