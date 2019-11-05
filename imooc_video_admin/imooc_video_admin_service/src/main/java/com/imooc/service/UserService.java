/**
* Title: UserService.java  

* Description   

* @author xhz  

* @date 2019年10月24日  
 
 */
package com.imooc.service;

import java.util.List;

import com.imooc.pojo.Users;
import com.imooc.utils.PageResult;

/**
 * @author xhz
 * @description UserService层
 */
public interface UserService {

	/**
	 * @Description 分页查询查询用户列表：1：当没有查询条件的时候，进行全表查询   2：如果有条件，就根据给定的条件进行条件查询
	 */
	PageResult queryUser(Users user, Integer pageNum, Integer pageSize);

}
