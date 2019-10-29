/**
* Title: VideoStateEnum.java  

* Description   

* @author xhz  

* @date 2019年9月11日  
 
 */
package com.imooc.enums;

/**
 * @author xhz
 * @description 定义视频播放的状态，0：正常播放     1：禁止播放
 */
public enum VideoStatuEnum {
	FORBID(0),//禁止播放 
	NORNAL(1);//正常播放
	
	private int value;
	
	private VideoStatuEnum(int value){
		this.value=value;
	}
	
	public int getValue(){
		return value;
	}
}
