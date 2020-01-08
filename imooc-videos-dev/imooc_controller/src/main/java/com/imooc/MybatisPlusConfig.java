/**
* Title: WebmvcConfig.java  

* Description   

* @author xhz  

* @date 2019年9月4日  
 
 */
package com.imooc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.imooc.controller.interceptor.AuthInterceptor;

/**
 * @author xhz
 * @description Mybatis-plus的配置
 */
@Configuration
public class MybatisPlusConfig{	
	
	/**
	 * @name paginationInterceptor
	 * @Description 配置Mybatis-plus的分页插件
	 * @return 
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor(){
		return new PaginationInterceptor();
		
	}
}
