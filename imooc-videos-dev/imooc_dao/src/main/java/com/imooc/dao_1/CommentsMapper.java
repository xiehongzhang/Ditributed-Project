package com.imooc.dao_1;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.imooc.pojo1.Comments;
import com.imooc.pojo1.CommentsExample;

public interface CommentsMapper {
    int countByExample(CommentsExample example);

    int deleteByExample(CommentsExample example);

    int deleteByPrimaryKey(String id);

    int insert(Comments record);

    int insertSelective(Comments record);

    List<Comments> selectByExampleWithBLOBs(CommentsExample example);

    List<Comments> selectByExample(CommentsExample example);

    Comments selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Comments record, @Param("example") CommentsExample example);

    int updateByExampleWithBLOBs(@Param("record") Comments record, @Param("example") CommentsExample example);

    int updateByExample(@Param("record") Comments record, @Param("example") CommentsExample example);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKeyWithBLOBs(Comments record);

    int updateByPrimaryKey(Comments record);
}