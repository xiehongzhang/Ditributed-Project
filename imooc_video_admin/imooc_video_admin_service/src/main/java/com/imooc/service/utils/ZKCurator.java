/**
* Title: ZKCurator.java  

* Description   

* @author xhz  

* @date 2019年10月12日  
 
 */
package com.imooc.service.utils;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xhz
 * @description 使用curatorFramework进行操作
 */
public class ZKCurator {

	//使用CuratorFramework
	private CuratorFramework client=null;
	
	//使用slf4j日志框架(是一种接口) ，方便代码编写风格的统一和不同日志框架的转换
	public static final Logger log=LoggerFactory.getLogger(ZKCurator.class);
	
	//使用构造方法初始化CuratorFramework
	public ZKCurator(CuratorFramework client){
		this.client=client;
	}
	
	/**
	 * @name init
	 * @Description 一旦和zookeeper服务器连接成功，初始化client,并创建节点/admin/bgm
	 * @param 
	 * @return 
	 */
	public void init(){
		//使用namespace方便管理
		client=client.usingNamespace("admin");
		//判断是否存在某个节点
		try {
			if((client.checkExists().forPath("/bgm")) == null){
				//创建节点（类型有持久节点，顺序持久节点，临时节点，顺序临时节点）
				client.create().creatingParentsIfNeeded()
					.withMode(CreateMode.PERSISTENT)
					.withACL(Ids.OPEN_ACL_UNSAFE)
					.forPath("/bgm");
				log.info("admin后台ZKCurator client创建成功！，zookeeper服务的状态：{}",client.getState());
			}else{
				log.info("节点已经存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("节点创建失败");
		}
	}
	
	/**
	 * @name setZNode
	 * @Description 根据用户对BGM的不同操作（不同的type），设置ZNode（带有不同的信息）
	 * @param bgmId
	 * @param bgmId
	 * @return 
	 */
	public void setZNode(String bgmId, String operObj){
		log.info("zookeeper服务的状态：{}",client.getState());
		//创建节点（类型有持久节点，顺序持久节点，临时节点，顺序临时节点）
		try {
			client.create().creatingParentsIfNeeded()
				.withMode(CreateMode.PERSISTENT)
				.withACL(Ids.OPEN_ACL_UNSAFE)
				.forPath("/bgm/"+bgmId,operObj.getBytes());
			log.info("setZNode成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
