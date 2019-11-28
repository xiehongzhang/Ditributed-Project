/**
* Title: ZKClient.java  

* Description   

* @author xhz  

* @date 2019年10月16日  
 
 */
package com.imooc;

import java.io.File;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imooc.config.ResourceProp;
import com.imooc.enums.BGMOperatorEnum;
import com.imooc.utils.JsonUtils;

/**
 * @author xhz
 * @description zookeeper的java客户端apache curator，主要是 
 *   1:重连zookeeper服务;
 *   2：创建客户端
 *   3：初始化客户端
 *   4:监听某个节点的变化
 *   5：做出相应的处理
 */  
@Component
public class ZKCurator {
	//使用CuratorFramework
	private CuratorFramework client;
	
	@Autowired
	private ResourceProp resourceProp;
		
	//使用slf4j日志框架(是一种接口) ，方便代码编写风格的统一和不同日志框架的转换
	public static final Logger log=LoggerFactory.getLogger(ZKCurator.class);
	
	//Zookeeper服务器的主机和端口号
//	public static final String ZOOKEEPER_SERVER="192.168.6.101:2181";
	
	RetryPolicy retryPolicy=new ExponentialBackoffRetry(1000, 5);

	public void init(){
		if (client == null) {
			//创建客户端
			client=CuratorFrameworkFactory.builder()
					.connectString(resourceProp.getZookeeperServer())
					.sessionTimeoutMs(10000)
					.connectionTimeoutMs(1000)
					.retryPolicy(retryPolicy)
					.namespace("admin")
					.build();
			//开启客户端
			client.start();
			log.info("dev:init方法被调用！zookeeper的客户端创建成功,状态为：{}",client.isStarted());
			//测试获取zookeeper的节点数据
			try {				
				//监听节点
				watchNode("/bgm");				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @name watchNode
	 * @Description 监听某一个nodePath下发生的事情,并做出相应的处理
	 * @param nodePath
	 * @return 
	 * @throws Exception 
	 */
	public void watchNode(String nodePath) throws Exception{
		//实例一个Path Node 
		final PathChildrenCache cache=new PathChildrenCache(client, nodePath, true);
		//开启cache
		cache.start();
		//将cache添加到监听器中
		cache.getListenable().addListener(new PathChildrenCacheListener() {
			//对子节点的变化做出相应的处理
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event)
					throws Exception {
				if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)){
					log.info("dev:被监听的事件为：CHILD_ADDED");
					//将获取的data字符串数据转换为Map对象
					Map<String, String> operObj=JsonUtils.jsonToObject(new String(event.getData().getData()),Map.class);
					//获取节路径，操作的类型和bgmPath
					String nodePath=event.getData().getPath();
					String operType=operObj.get("operType");
					String fileDBPath=operObj.get("bgmPath");
					
//					String operateType=new String(event.getData().getData());
					
//					String arr[]=nodePath.split("/");
//					String bgmId=arr[arr.length-1];
//					
//					//1 根据id获取BGM对象
//					Bgm bgm=bgmService.queryBgmById(bgmId);
//					if (bgm == null) {
//						return;
//					}
//					//2 获取BGM对象的path
//					String fileDBPath=bgm.getPath();
					
					//上传BGM文件的路径
					String uploadFilePath=resourceProp.getUploadNamespace()+fileDBPath;
					
					String pathArr[]=fileDBPath.split("\\\\");					
					//文件的相对路径
					String filePath="";
					for(int i=0; i<pathArr.length; i++){
						if (StringUtils.isNotBlank(pathArr[i])) {
							filePath += "/";
							filePath +=URLEncoder.encode(pathArr[i], "UTF-8");
						}
					}
					//判断operType的值，为0则下载BGM，为1则删除BGM
					if(operType.equals(BGMOperatorEnum.ADD.value)){
						//3 下载BGM文件的路径
						String downloadFilePath=resourceProp.getDownloadNamespace()+filePath;
						//后台管理文件系统的url
						URL url=new URL(downloadFilePath);
						//运用后台的文件路径
						File file =new File(uploadFilePath);
						FileUtils.copyURLToFile(url, file);
						//删除zookeeper节点
						client.delete().forPath(nodePath);
					}else if(operType.equals(BGMOperatorEnum.DELETE.value)){
						File file =new File(uploadFilePath);
						FileUtils.forceDelete(file);
						//删除zookeeper节点
						client.delete().forPath(nodePath);
					}
				}
			}
		});
	}
}
