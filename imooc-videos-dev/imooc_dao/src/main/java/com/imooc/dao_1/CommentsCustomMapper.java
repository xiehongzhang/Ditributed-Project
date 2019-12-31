/**
* Title: CommentsCustomMapper.java  

* Description   

* @author xhz  

* @date 2019年9月29日  
 
 */
package com.imooc.dao_1;

import java.util.List;

import com.imooc.pojo1.vo.CommentsVO;

/**
 * @author xhz
 * @description 自定义的comments映射接口
 */
public interface CommentsCustomMapper {

	/**
	 * @Description 查询所有的评论以及相对应的用户昵称
	 */
	List<CommentsVO> queryAllComments(String videoId);

}
