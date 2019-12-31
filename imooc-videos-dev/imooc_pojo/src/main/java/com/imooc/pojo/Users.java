package com.imooc.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xhz
 * @since 2019-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="用户实体",description="用户实体类")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(hidden=true)
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty
	@JsonIgnoreProperties
    private String password;

    /**
     * 头像
     */
    @ApiModelProperty(hidden=true)
    private String faceImage;

    /**
     * 昵称
     */
    @ApiModelProperty(hidden=true)
    private String nickname;

    /**
     * 粉丝数量
     */
    @ApiModelProperty(hidden=true)
    private Integer fansCounts;

    /**
     * 关注数量
     */
    @ApiModelProperty(hidden=true)
    private Integer followCounts;

    /**
     * 收藏数量
     */
    @ApiModelProperty(hidden=true)
    private Integer receiveLikeCounts;


}
