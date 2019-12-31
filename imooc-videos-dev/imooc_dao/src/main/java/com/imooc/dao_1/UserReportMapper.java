package com.imooc.dao_1;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.imooc.pojo1.UserReport;
import com.imooc.pojo1.UserReportExample;
@Repository
public interface UserReportMapper {
    int countByExample(UserReportExample example);

    int deleteByExample(UserReportExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserReport record);

    int insertSelective(UserReport record);

    List<UserReport> selectByExample(UserReportExample example);

    UserReport selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserReport record, @Param("example") UserReportExample example);

    int updateByExample(@Param("record") UserReport record, @Param("example") UserReportExample example);

    int updateByPrimaryKeySelective(UserReport record);

    int updateByPrimaryKey(UserReport record);
}