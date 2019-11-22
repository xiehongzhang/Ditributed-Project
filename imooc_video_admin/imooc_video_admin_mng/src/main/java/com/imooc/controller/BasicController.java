/**
* Title: HelloController.java  

* Description   

* @author xhz  

* @date 2019年8月8日  
 
 */
package com.imooc.controller;

import org.springframework.stereotype.Controller;

/**
 * @author xhz
 * @description 基础控制类 
 */
@Controller
public class BasicController {
	
	//文件保存的空间
	public static final String FILE_NAMESPACE="C:/imooc_video_dev";
	
//	//ffmpeg的安装路径
//	public static final String FFMPEG_PATH="D:\\installed software\\FFMpeg\\bin";
	
	//每页显示的记录条数
	public static final int PAGE_SIZE=10;
}

