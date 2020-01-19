/**
* Title: CommentsVO.java  

* Description   

* @author xhz  

* @date 2019年9月29日  
 
 */
package com.imooc.pojo.vo;

import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 
 * @description 
 */
@ApiModel(value="评论信息显示层实体", description="评论信息显示层实体类")
@Data
@Accessors(chain=true)
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
    private LocalDateTime createTime;//评论的创建时间

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

}
