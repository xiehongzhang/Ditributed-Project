/**
* Title: HelloController.java  

* Description   

* @author xhz  

* @date 2019年8月8日  
 
 */
package com.imooc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.config.ResourceProp;
import com.imooc.utils.RedisUtils;

/**
 * @author xhz
 * @description 基础控制类 
 */
@RestController
public class BasicController {
	@Autowired
	public  RedisUtils redisUtils;
	
	//用户的redis的 session token
	public static final String REDIS_USER_SESSION="redis_user_session";
	
	//文件保存的空间
//	public static final String FILE_NAMESPACE="C:\\imooc_video_dev";
	
//	//ffmpeg的安装路径
//	public static final String FFMPEG_PATH="D:\\installed software\\FFMpeg\\bin";
	
	//每页显示的记录条数
	public static final int PAGE_SIZE=5;
}

