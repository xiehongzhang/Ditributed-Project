/**
* Title: TestFile.java  

* Description   

* @author xhz  

* @date 2019年10月18日  
 
 */
package com.imooc.test;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.imooc.Application;
import com.imooc.pojo.Bgm;
import com.imooc.service.BgmService;

/**
 * @author xhz
 * @description 测试文件操作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={Application.class})
public class TestFile {
	@Autowired
	private BgmService bgmService;

	@Test
	public void testFilePath(){
		Bgm bgm=bgmService.queryBgmById("1910168AKR5X763C");
		String path=bgm.getPath();
		String arr[]=path.split("/");
		for(int i=0; i<arr.length; i++){
			System.out.println(arr[i]);
		}
//		char[] arr=path.toCharArray();
//		for(int i=0; i<arr.length; i++){
//			System.out.println(arr[i]);
//		}
		System.out.println(path);
		
	}
}
