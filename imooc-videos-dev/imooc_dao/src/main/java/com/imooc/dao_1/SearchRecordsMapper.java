package com.imooc.dao_1;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.imooc.pojo1.SearchRecords;
import com.imooc.pojo1.SearchRecordsExample;
@Repository
public interface SearchRecordsMapper {
    int countByExample(SearchRecordsExample example);

    int deleteByExample(SearchRecordsExample example);

    int deleteByPrimaryKey(String id);

    int insert(SearchRecords record);

    int insertSelective(SearchRecords record);

    List<SearchRecords> selectByExample(SearchRecordsExample example);

    SearchRecords selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SearchRecords record, @Param("example") SearchRecordsExample example);

    int updateByExample(@Param("record") SearchRecords record, @Param("example") SearchRecordsExample example);

    int updateByPrimaryKeySelective(SearchRecords record);

    int updateByPrimaryKey(SearchRecords record);
    
    //查询热搜词 
    List<String> queryHotRecords();
}