/**
* Title: BgmService.java  

* Description   

* @author xhz  

* @date 2019年9月6日  
 
 */
package com.imooc.service;

import java.util.List;

import com.imooc.pojo1.Bgm;

/**
 * @author 
 * @description 
 */
public interface BgmService {

	/**
	 * @Description 查询背景音乐列表
	 */
	public List<Bgm> queryBgmList();

	/**
	 * @Description 通过id查询背景音乐
	 */
	public Bgm queryBgmById(String audioId);

}
