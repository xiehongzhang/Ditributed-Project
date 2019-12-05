/**
* Title: TestUtil.java  

* Description: 对每一个工具类进行测试  

* @author xhz  

* @date 2019年8月5日  
 
 */
package com.imooc.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.imooc.utils.MD5Util;

/**
 * @author JoyPlay
 *
 */
public class JsonUtilTest {
	@Test
	public void jsonTest() throws NoSuchAlgorithmException, CloneNotSupportedException{
		String string=MD5Util.getMD5String("123456");
		MessageDigest messageDigest=MessageDigest.getInstance("MD5");
		Object object=messageDigest.clone();
		System.out.println(object);		
		System.out.println(object.getClass());		
		System.out.println(string);
	}
}
