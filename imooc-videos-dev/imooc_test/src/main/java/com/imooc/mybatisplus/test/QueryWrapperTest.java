/**
* Title: QueryWrapperTest.java  

* Description   

* @author xhz  

* @date 2019年12月30日  
 
 */
package com.imooc.mybatisplus.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.Application;
import com.imooc.dao.UsersMapper;
import com.imooc.dao.VideoMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.Video;
import com.imooc.service.UsersService;

/**
 * @author xhz
 * @description 测试QueryWrapper中的方法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={Application.class})
public class QueryWrapperTest {
	@Autowired 
	private UsersService usersService;
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private VideoMapper videoMapper;
	
	/**
	 * 测试eq方法
	 */
	@Test
	public void testEq(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("video_desc", "spring");
		System.out.println(videoMapper.selectByMap(map));
	}
	
	/**
	 * 测试通过id查询
	 */
	@Test
	public void testSelectUsersById(){
		String id="19092876SC9DGYA8";
		Users users=usersMapper.selectById(id);
		System.out.println(users);
	}
}
