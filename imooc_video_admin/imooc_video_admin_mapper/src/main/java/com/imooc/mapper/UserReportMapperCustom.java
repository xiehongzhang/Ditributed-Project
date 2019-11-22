package com.imooc.mapper;

import java.util.List;

import com.imooc.pojo.Bgm;
import com.imooc.pojo.UserReport;

public interface UserReportMapperCustom {

	/**
	 * @Description 查询用户举报列表
	 */
	List<UserReport> queryReportList();
    
}