/**
* Title: BgmService.java  

* Description   

* @author xhz  

* @date 2019年10月9日  
 
 */
package com.imooc.service;

import java.util.List;

import com.imooc.pojo.Bgm;

/**
 * @author xhz
 * @description bgm service层
 */
public interface BgmService {

	/**
	 * @Description 查询所有的BGM
	 */
	List<Bgm> queryAllBgm();

	/**
	 * @Description 通过id查询bgm
	 */
	Bgm queryBgm(String id);

	/**
	 * @Description 添加bgm,将BGM的信息写进数据库，同时向zookeeper写进一个带有增加BGM标志的节点
	 */
	void addBgm(Bgm bgm);

	/**
	 * @Description 删除BGM，同时向zookeeper写进一个带有删除标志的节点
	 */
	void deleteBgm(String bgmId);

}
