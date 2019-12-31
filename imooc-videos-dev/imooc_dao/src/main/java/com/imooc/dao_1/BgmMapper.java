package com.imooc.dao_1;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.imooc.pojo1.Bgm;
import com.imooc.pojo1.BgmExample;

@Repository
public interface BgmMapper {
	int countByExample(BgmExample example);

	int deleteByExample(BgmExample example);

	int deleteByPrimaryKey(String id);

	int insert(Bgm record);

	int insertSelective(Bgm record);

	List<Bgm> selectByExample(BgmExample example);

	Bgm selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") Bgm record, @Param("example") BgmExample example);

	int updateByExample(@Param("record") Bgm record, @Param("example") BgmExample example);

	int updateByPrimaryKeySelective(Bgm record);

	int updateByPrimaryKey(Bgm record);
}