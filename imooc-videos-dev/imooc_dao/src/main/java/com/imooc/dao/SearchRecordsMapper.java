package com.imooc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.pojo.SearchRecords;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xhz
 * @since 2019-12-27
 */
@Repository
public interface SearchRecordsMapper extends BaseMapper<SearchRecords> {

	/**
	 * @name xhz
	 * @Description 查询热搜词
	 * @return 
	 */
	List<String> queryHotRecords();

}
