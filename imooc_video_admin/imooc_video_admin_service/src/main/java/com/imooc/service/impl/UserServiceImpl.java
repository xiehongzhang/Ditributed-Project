/**
* Title: UserServiceImpl.java  

* Description   

* @author xhz  

* @date 2019年10月24日  
 
 */
package com.imooc.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.UsersExample;
import com.imooc.pojo.UsersExample.Criteria;
import com.imooc.service.UserService;
import com.imooc.utils.PageResult;

/**
 * @author 
 * @description 
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired 
	private UsersMapper usersMapper;

	@Override
	public PageResult queryUser(Users user, Integer pageNum, Integer pageSize) {
		String username=null;
		String nickname=null;
		//判断username和nickname是否都为空
		if (user != null) {
			//获取用户名和昵称
			username=user.getUsername();
			nickname=user.getNickname();
		}
		PageHelper.startPage(pageNum, pageSize);
		//不为空则进行条件查询
		UsersExample example=new UsersExample();
		Criteria criteria =example.createCriteria();
		if (StringUtils.isNotBlank(username)) {
			criteria.andUsernameLike("%" + username + "%");
		}
		if (StringUtils.isNotBlank(nickname)) {
			criteria.andNicknameLike("%" + nickname + "%" );
		}
		//如果都为空则进行全表查询
		List<Users> userList=usersMapper.selectByExample(example);
		PageInfo<Users> pageInfo=new PageInfo<>(userList);
		//封装页面信息
		PageResult pageResult=new PageResult();
		pageResult.setPage(pageNum);
		pageResult.setTotal(pageInfo.getPages());
		pageResult.setRecords((int)pageInfo.getTotal());
		pageResult.setRows(userList);
		return pageResult;
	}

}
