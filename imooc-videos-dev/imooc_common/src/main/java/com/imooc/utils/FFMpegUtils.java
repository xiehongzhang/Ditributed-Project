/**
* Title: FFMpegUtils.java  

* Description   

* @author xhz  

* @date 2019年9月10日  
 
 */
package com.imooc.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;





/**
 * @author xhz
 * @description java程序中执行ffmpeg的命令
 */

public class FFMpegUtils{
	
	//ffmpeg的windows安装路径
//	public static final String FFMPEG_PATH="D:\\installed software\\FFMpeg\\bin";
	//ffmpeg 的linux安装路径
	public static final String FFMPEG_PATH="/usr/local/ffmpeg/bin";
	
	/**
	 * @name fetchVideoCover
	 * @Description 截图视频中的一帧作为视频封面
	 * @param source
	 * @param target
	 * @return 
	 */
	public static void fetchVideoCover(String source , String target) throws IOException{
		/**
		 * 创建执行的命令command 
		 * ffmpeg -i source 
		 *        -ss 00:00:0.5 
		 *        -y 
		 *        -r 25 
		 *        -q:v 2 
		 *        -frames:v 1 
		 *        target
		 */
		List<String> command =new ArrayList<>();
		command.add(FFMPEG_PATH+File.separator+"ffmpeg");
		command.add("-i");
		command.add(source);
		command.add("-ss");
		command.add("00:00:01");
		command.add("-y");
		command.add("-r");
		command.add("1");
		command.add("-q:v");
		command.add("2");
		command.add("-frames:v");
		command.add("1");
		command.add(target);
		//创建进程
		ProcessBuilder processBuilder=new ProcessBuilder(command);
		//执行命令
		Process process=processBuilder.start();
		//关闭进程
		closeProcess(process);		
	}
	
	public static void mergeVideoAndAudio(String videoSource, String audioSource, float time, String target) throws IOException{
		/**
		 * 创建执行的命令command
		 * ffmpeg -i videoSourc
		 *        -i audioSource
		 *        -t time
		 *        -y
		 *        target
		 */
		List<String> command =new ArrayList<>();
		command.add(FFMPEG_PATH+File.separator+"ffmpeg");
		command.add("-i");
		command.add(videoSource);
		command.add("-i");
		command.add(audioSource);
		command.add("-c:v");
		command.add("copy");
		command.add("-map");
		command.add("0:v");
		command.add("-map");
		command.add("1:a");
		command.add("-t");
		command.add(String.valueOf(time));
		command.add(target);
		//创建进程
		ProcessBuilder processBuilder=new ProcessBuilder(command);
		Process process=processBuilder.start();
		//关闭进程
		closeProcess(process);
	}
	
	//将mp4格式的文件转为avi格式
	public static void mp4ToAvi() throws IOException{
		/**
		 *  创建执行的命令command 
		 * ffmpeg -i C:\\imooc_video_dev\\videoAndaudio\\video\\video2.mp4 
		 *           C:\\imooc_video_dev\\videoAndaudio\\test\\video2.avi
		 */
		List<String> command =new ArrayList<>();
		command.add(FFMPEG_PATH+File.separator+"ffmpeg");
		command.add("-i");
		command.add("C:\\imooc_video_dev\\videoAndaudio\\video\\video2.mp4");
		command.add("C:\\imooc_video_dev\\videoAndaudio\\test\\video2.avi");
		//创建进程
		ProcessBuilder processBuilder=new ProcessBuilder();
		processBuilder.command(command).start();
		
	}

	//关闭进程
	public static void closeProcess(Process process) throws IOException{
		//获取异常流
		InputStream is=process.getErrorStream();
		InputStreamReader isr=new InputStreamReader(is);
		BufferedReader br=new BufferedReader(isr);
		
		String line="";
		while((line=br.readLine())!= null){			
		}
		if (br != null) {
			br.close();
		}
		if (isr != null) {
			isr.close();
		}
		if (is != null) {
			is.close();
		}
	}
	
}
