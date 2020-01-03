/**
* Title: UsersVideoVO.java  

* Description   

* @author xhz  

* @date 2019年9月25日  
 
 */
package com.imooc.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xhz
 * @description 用户和视频的关系实体类
 */
@Data
@Accessors(chain=true)
public class UsersVideoVO {
	private UsersVO publisher;//用户显示层实体类
	private boolean userLikeVideo;//用户和视频的点赞关系
	
	
}
