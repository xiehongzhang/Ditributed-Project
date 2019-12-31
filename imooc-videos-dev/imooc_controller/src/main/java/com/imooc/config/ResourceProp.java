/**
* Title: ResourceProp.java  

* Description   

* @author xhz  

* @date 2019年10月24日  
 
 */
package com.imooc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author xhz
 * @description 作为资源属性类,对应resources.properties文件
 */
@Configuration
@ConfigurationProperties(prefix = "imooc.config")
@PropertySource(value="classpath:resource/resources-dev.properties")
public class ResourceProp {
	private String zookeeperServer;
	private String downloadNamespace;
	private String uploadNamespace;
	
	public ResourceProp() {
	}
	public String getZookeeperServer() {
		return zookeeperServer;
	}
	public void setZookeeperServer(String zookeeperServer) {
		this.zookeeperServer = zookeeperServer;
	}
	public String getDownloadNamespace() {
		return downloadNamespace;
	}
	public void setDownloadNamespace(String downloadNamespace) {
		this.downloadNamespace = downloadNamespace;
	}
	public String getUploadNamespace() {
		return uploadNamespace;
	}
	public void setUploadNamespace(String uploadNamespace) {
		this.uploadNamespace = uploadNamespace;
	}
}
