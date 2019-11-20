package com.imooc.mapper;

import java.util.List;

import com.imooc.pojo.Bgm;

public interface UserReportMapperCustom {

	/**
	 * @Description 查询用户举报列表
	 */
	List<Bgm> queryReportList();
    
}