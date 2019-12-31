package com.imooc.dao_1;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.imooc.pojo1.UsersLikeVideos;
import com.imooc.pojo1.UsersLikeVideosExample;
@Repository
public interface UsersLikeVideosMapper {
    int countByExample(UsersLikeVideosExample example);

    int deleteByExample(UsersLikeVideosExample example);

    int deleteByPrimaryKey(String id);

    int insert(UsersLikeVideos record);

    int insertSelective(UsersLikeVideos record);

    List<UsersLikeVideos> selectByExample(UsersLikeVideosExample example);

    UsersLikeVideos selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UsersLikeVideos record, @Param("example") UsersLikeVideosExample example);

    int updateByExample(@Param("record") UsersLikeVideos record, @Param("example") UsersLikeVideosExample example);

    int updateByPrimaryKeySelective(UsersLikeVideos record);

    int updateByPrimaryKey(UsersLikeVideos record);
}