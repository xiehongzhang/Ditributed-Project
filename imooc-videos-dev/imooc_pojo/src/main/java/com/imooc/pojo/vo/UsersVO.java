/**
* Title: UsersVO.java  

* Description   

* @author xhz  

* @date 2019年8月21日  
 
 */
package com.imooc.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xhz
 * @description 用户数据层实体类
 */
@Data
@Accessors(chain=true)
@ApiModel(value="用户数据层实体类",description="用于数据层显示")
public class UsersVO {
	@ApiModelProperty(hidden=true)
	private String userToken;//用户userToken
	
	@ApiModelProperty(hidden=true)
    private String id;//users表id
	
	@ApiModelProperty
    private String username;//用户名称
	
	@ApiModelProperty
	@JsonIgnore
    private String password;//账号密码
	
    @ApiModelProperty(hidden=true)
    private String faceImage;//用户头像
    
    @ApiModelProperty(hidden=true)
    private String nickname;//用户昵称
    
    @ApiModelProperty(hidden=true)
    private Integer fansCounts;//用户的粉丝数量
    
    @ApiModelProperty(hidden=true)
    private Integer followCounts;//用户的关注数量
    
    @ApiModelProperty(hidden=true)
    private Integer receiveLikeCounts;//用户的接受点赞的数量
    
    @ApiModelProperty(hidden=false)
    private Boolean follow;//是否关注   
}
