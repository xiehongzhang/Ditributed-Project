/**
* Title: UserController.java  

* Description   

* @author xhz  

* @date 2019年10月24日  
 
 */
package com.imooc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.JsonResult;
import com.imooc.utils.PageResult;

/**
 * @author xhz
 * @description 用户控制类
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;

	/**
	 * @name userList
	 * @Description 分页查询查询用户列表：1：当没有查询条件的时候，进行全表查询   2：如果有条件，就根据给定的条件进行条件查询
	 * @param user
	 * @param pageNum
	 * @return 
	 */
	@PostMapping("/userList")
	@ResponseBody
	public JsonResult userList(Users user, Integer pageNum){
		PageResult pageResult=userService.queryUser(user,pageNum == null ? 1 : pageNum, 10);
		return JsonResult.ok(pageResult);
	}
	
}
