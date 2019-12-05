/**
* Title: TestJson.java  

* Description:   

* @author xhz  

* @date 2019年8月5日  
 
 */
package com.imooc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.imooc.Application;
import com.imooc.dao.BgmMapper;
import com.imooc.pojo.Bgm;
import com.imooc.utils.JsonUtils;

/**
 * @author JoyPlay
 * @description 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={Application.class})
public class JsonTest {
	
	@Autowired
	private BgmMapper bgmMapper=null;
	
	/**
	 * @name:objectToJsonTest
	 * @discription:测试java对象转化为json 数据
	 * @paramter:
	 * @return:
	 */
	@Test
	public void objectToJsonTest(){
		Bgm bgm=bgmMapper.selectByPrimaryKey("44444433343");
		String string =JsonUtils.objectToJson(bgm);
		System.out.println(string);
	}
	
	/**
	 * @name:jsonToObjectTest
	 * @discription:测试将json数据转为java对象
	 * @paramter:
	 * @return:
	 */
	@Test
	public void jsonToObjectTest(){
		//1：需要一个json数据
		String jsonData="{\"id\":\"443344334433\",\"author\":\"李四\",\"name\":\"演员\",\"path\":\"file://172.16.3.241\"}";
		//3:调用jsonToObject()
		Bgm bgm=JsonUtils.jsonToObject(jsonData,Bgm.class);
		//4:打印对象
		System.out.println(bgm.getClass()+bgm.getId()+bgm.getAuthor());
	}
	
}
