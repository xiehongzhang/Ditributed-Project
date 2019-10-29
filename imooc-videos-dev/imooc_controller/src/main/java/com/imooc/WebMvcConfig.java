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

import com.imooc.controller.interceptor.AuthInterceptor;

/**
 * @author xhz
 * @description 功能类似于web.xml文件；作为工程的配置类
 * 				1：作为工程的静态资源的配置类，包括文件和图片等,以及swagger-ui的位置；
 * 				2：注册拦截器
 *              3:初始化zookeeper服务
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{
	
	/**
	 * @name addResourceHandlers 
	 * @Description 重写父类的addResourceHandlers，为工程添加静态的资源，和需要拦截的路径
	 * @return   
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/META-INF/resources/")
				.addResourceLocations("file:C:/imooc_video_dev/");
	}
	
	/**
	 * @name zkCurator
	 * @Description 将ZKCurator注册成Bean，交给容器管理
	 * @return 
	 */
	@Bean(initMethod="init")
	public ZKCurator zkCurator(){
		return new ZKCurator();
	}
	
	/**
	 * @name firstInterceptor
	 * @Description 将FirstInterceptor注册成Bean，交给容器管理
	 * @return 
	 */
	@Bean
	public AuthInterceptor firstInterceptor(){
		return new AuthInterceptor();
	}

	
	/**
	 * @name addInterceptors
	 * @Description 重写父类的addResourceHandlers,将拦截器注册到注册中心去,以及需要拦截的路径
	 * @return 
	 */
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(firstInterceptor()).addPathPatterns("/loginRegister/**");
		super.addInterceptors(registry);
	}
	
}
