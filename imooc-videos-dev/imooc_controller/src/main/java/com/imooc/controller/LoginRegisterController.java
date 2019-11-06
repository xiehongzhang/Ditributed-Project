/**
* Title: UserController.java  

* Description:   

* @author xhz  

* @date 2019年8月2日  
 
 */
package com.imooc.controller;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.BasicController;
import com.imooc.pojo.Users;
import com.imooc.pojo.vo.UsersVO;
import com.imooc.service.UsersService;
import com.imooc.utils.JsonResult;
import com.imooc.utils.MD5Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author JoyPlay
 * @Description 进行用户的有关操作：1：用户注册 2：用户登录
 */
@Api(value = "注册，登录和注销接口", tags = { "注册，登录和注销等操作接口" })
@RestController
public class LoginRegisterController extends BasicController{

	@Autowired
	private UsersService usersService;

	/**
	 * @name register
	 * @Description 用户注册方法 : 1:判断用户或者密码是否为空 2：查询用户名是否存在 3:插入用户
	 * @param users
	 * @return 返回一个操作状态
	 */
	@ApiOperation(value = "用户注册方法", notes = "用户进行注册操作")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "用户名", name = "username", paramType = "query", dataType = "String", required = true),
			@ApiImplicitParam(value = "密码", name = "password", paramType = "query", dataType = "String", required = true) })
	@PostMapping("/regist")
	public JsonResult regist(@RequestBody Users users) {
		// 判断用户名和密码是否为空
		if (StringUtils.isBlank(users.getUsername()) || StringUtils.isBlank(users.getPassword())) {
			return JsonResult.errorMsg("请输入用户和密码");
		}
		// 查询用户是否存在
		boolean usernameIsExists = usersService.queryUsersIsExists(users.getUsername());
		// 不存在插入用户，存在报错误
		try {
			if (!usernameIsExists) {
				users.setUsername(users.getUsername());
				users.setPassword(MD5Util.getMD5String(users.getPassword()));
				users.setFaceImage(null);
				users.setNickname(users.getUsername());
				users.setFansCounts(0);
				users.setFollowCounts(0);
				users.setReceiveLikeCounts(0);
				usersService.saveUsers(users);
			} else {
				return JsonResult.errorMsg("用户名已存在，请换一个用户名");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		users.setPassword("");
		UsersVO usersVO=setUsersRedisSession(users);
		// 返回操作结果
		return JsonResult.ok(usersVO);
	}

	/**
	 * @name login
	 * @Description 用户登录操作 :   1:判断用户名和密码是否为空    2: 查询用户是否存在    3: 判断用户名和密码是否正确
	 * @param users
	 * @return Json数据
	 */
	@ApiOperation(value="用户登录操作",notes="用户进行登录操作")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户名", name="username", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="密码", name="password", paramType="query", dataType="String", required=true)
	})
	@PostMapping("/login")
	public JsonResult login(@RequestBody Users users) {
		// 1: 判断用户名和密码是否为空
		if (StringUtils.isBlank(users.getUsername()) || StringUtils.isBlank(users.getPassword())) {
			return JsonResult.errorMsg("请输入用户名和密码");
		}
		// 2: 查询用户是否存在
		boolean usersIsExists=usersService.queryUsersIsExists(users.getUsername());
		if(!usersIsExists){
			return JsonResult.errorMsg("该用户名未注册，请及时注册！");
		}
		Users usersResult=usersService.queryUsersByUP(users.getUsername(), users.getPassword());
		// 3：判断用户名和密码是否正确
		if(null != usersResult){
			usersResult.setPassword("");
			UsersVO usersVO=setUsersRedisSession(usersResult);
			return JsonResult.ok(usersVO);
		}else {
			return JsonResult.errorMsg("用户名和密码不正确，请重新输入");
		}
	}
	
	/**
	 * @name logout
	 * @Description 注销操作：通过前端传递进来的id，删除用户登录的redisSession，切断前端和后台的联系，达到退出目的
	 * @param userId
	 * @return 
	 */
	@ApiOperation(value = "用户注销操作",notes="用户进行注销操作" )
	@ApiImplicitParam(value="用户id",name="userId",paramType="query",dataType="String",required=true)
	@PostMapping("/logout")
	public  JsonResult logout(String userId){
		if (StringUtils.isBlank(userId) ) {
			return JsonResult.errorMsg("用户id不能为空");
		}else{
			String userSession=redisUtils.get(REDIS_USER_SESSION+":"+userId);
			if(StringUtils.isBlank(userSession)){
				return JsonResult.errorMsg("用户Session不存在");
			}else{
				redisUtils.delKeys(REDIS_USER_SESSION+":"+userId);
				return JsonResult.ok();
			}
		}
	}
	
	/**
	 * @name setUsersRedisSession
	 * @Description 生成用户的redisSession返回数据层的用户对象
	 * @param users
	 * @return UserVO
	 */
	public UsersVO setUsersRedisSession(Users users){
		String userToken=UUID.randomUUID().toString();
		redisUtils.set(REDIS_USER_SESSION+":"+users.getId(), userToken);
		UsersVO usersVO=new UsersVO();
		BeanUtils.copyProperties(users, usersVO);
		usersVO.setUserToken(userToken);
		return usersVO;
	}
	}
