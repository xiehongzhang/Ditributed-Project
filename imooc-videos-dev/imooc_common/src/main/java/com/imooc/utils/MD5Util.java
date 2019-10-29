/**
* Title: MD5Util.java  

* Description:  作为账号密码的加密工具，保证数据的安全性 

* @author xhz  

* @date 2019年8月5日  
 
 */
package com.imooc.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;


/**
 * @author JoyPlay
 *
 */
public class MD5Util {
	/*
	 * 加密方法:encryption()
	 * 参数：密码字符串
	 * 操作：     1：实例化一个MessageDiget的实例
	 * 		2：将参数转为字节数组
	 * 		3：执行digest()
	 * 		4:通过Apache comment的base64进行加密，方便在网络上传输
	 * 结果：加密后的字符串
	 */
	public static String getMD5String(String message) throws NoSuchAlgorithmException{		
		MessageDigest messageDigest=MessageDigest.getInstance("MD5");
		String newMessage=Base64.encodeBase64String(messageDigest.digest(message.getBytes()));
		return newMessage;
	}

}
