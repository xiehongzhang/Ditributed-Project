package com.imooc.pojo.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value="视频视图实体",description="视频视图实体类")
@Data
@Accessors(chain=true)
public class VideoVO {
	@ApiModelProperty
    private String id;

	@ApiModelProperty
    private String userId;

	@ApiModelProperty
    private String audioId;

	@ApiModelProperty
    private String videoDesc;

	@ApiModelProperty
    private Float videoSeconds;

	@ApiModelProperty
    private Integer videoWidth;

	@ApiModelProperty
    private Integer videoHeight;

	@ApiModelProperty
    private String videoPath;

	@ApiModelProperty
    private String coverPath;

	@ApiModelProperty
    private Long likeCounts;

	@ApiModelProperty
    private Integer status;

	@ApiModelProperty
    private Date createTime;
	
	@ApiModelProperty
    private String faceImage;
    
    @ApiModelProperty
    private String nickname;

}