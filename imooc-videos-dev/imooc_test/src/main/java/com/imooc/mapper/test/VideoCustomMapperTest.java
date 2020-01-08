/**
* Title: VideoCustomMapperTest.java  

* Description   

* @author xhz  

* @date 2020年1月8日  
 
 */
package com.imooc.mapper.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.Application;
import com.imooc.dao.VideoMapperCustom;
import com.imooc.pojo.vo.VideoVO;

/**
 * @author 
 * @description 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={Application.class})
public class VideoCustomMapperTest {

	@Autowired
	private VideoMapperCustom videoMapperCustom;
	@Test
	public void testQueryAllVideo(){
		Page<VideoVO> page = new Page<VideoVO>(1,5);
		IPage<VideoVO> ipage=videoMapperCustom.queryAllVideo(page, "接口", "190903CHC87ZPXD4");
		List<VideoVO> list=ipage.getRecords();
		System.out.println("当前页为:"+ipage.getCurrent());
		System.out.println("每页的记录数："+ipage.getSize());
		System.out.println("总的条数"+ipage.getTotal());
		for(VideoVO videoVO : list){
			System.out.println("视频："+videoVO);
		}
	}
}
