/**
* Title: UsersSeriveceImplTest.java  

* Description   

* @author xhz  

* @date 2019年12月30日  
 
 */
package com.imooc.service.test;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.Application;
import com.imooc.dao.UsersFansMapper;
import com.imooc.dao.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.service.UsersService;
import com.imooc.utils.MD5Util;

/**
 * @author xhz
 * @description 测试UsersSeriveceImpl下的的所有方法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={Application.class})
public class UsersSeriveceTest {

	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private UsersService usersService;

	/*
	 * 测试queryUsersIsExists(String username)
	 * 用户是否存在
	 */
	@Test
	public void testQueryUsersIsExists(){
		Users users=new Users();
		users.setUsername("imooc");
		QueryWrapper<Users> wrapper=new QueryWrapper<>();
		wrapper.eq("username", users.getUsername());
		Users usersResult=usersMapper.selectOne(wrapper);
		System.out.println(usersResult);
	}
	
	/*
	 *测试saveUsers(Users users)
	 *  保存用户 
	 */
	@Test
	public void testsaveUsers() throws NoSuchAlgorithmException{
		Users users =new Users();
		users.setUsername("别别别");
		users.setPassword(MD5Util.getMD5String("123456"));
		users.setFaceImage(null);
		users.setNickname("别别别");
		users.setFansCounts(0);
		users.setFollowCounts(0);
		users.setReceiveLikeCounts(0);
		usersService.saveUsers(users);		
	}
	
	/*
	 *测试queryUsersByUP(String username, String password)
	 *  通过用户名和密码查询用户
	 */
	@Test
	public void testQueryUsersByUP() {
		String username="imooc";
		String password="imooc";
		System.out.println(usersService.queryUsersByUP(username, password));
	}
	
	/*
	 * 测试 Users queryUserInfo(String userId)
	 * 通过id查询用户信息
	 */
	@Test
	public void testQueryUserInfo() {
		String userId="190903CHC87ZPXD4";
		System.out.println(usersService.queryUserInfo(userId));
	}

	/*
	 * 测试 void uploadFaceImage(Users users)
	 * 通过id更新用户的头像信息
	 */
	@Test
	public void testUploadFaceImage() {
		Users users=new Users();
		String userId="190903CHC87ZPXD4";
		String faceImage="/190903CHC87ZPXD4/face/boy1.jpg";
		users.setId(userId);
		users.setFaceImage(faceImage);
		usersService.uploadFaceImage(users);
	}
	
	
	/*
	 * 测试 Boolean isFollow(String userId, String followerId)
	 * 查询两者是否存在关注的关系
	 */
	@Test
	public void testIsFollow() {
		String userId="19092876SC9DGYA8";
		String followerId="191105C7PYKHBSCH";
		boolean isFollow=usersService.isFollow(userId,followerId);
		System.out.println(isFollow);
	}
	
	/*
	 * 测试 Boolean isLike(String userId, String videoId)
	 * 查询用户和视频的关系
	 */
	@Test
	public void testIsLike() {
		String userId="190916C8H2AT05GC";
		String videoId="";
		boolean isLike=usersService.isLike(userId,videoId);
		System.out.println(isLike);
	}
}
