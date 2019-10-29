/**
* Title: MultipartFileConfig.java  

* Description   

* @author xhz  

* @date 2019年8月28日  
 
 */
package com.imooc.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

///**
// * @author 
// * @description 
// */
//@Configuration
//public class MultipartFileConfig {
//	@Bean(name="multipartResolver")
//    public MultipartResolver multipartResolver(){
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(1000000);
//        return multipartResolver;
//    }
//}

@Configuration
public class MultipartFileConfig {
    //显示声明CommonsMultipartResolver为mutipartResolver
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        //resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setResolveLazily(true);
        resolver.setMaxInMemorySize(40960);
        //上传文件大小 100M 100*1024*1024
        resolver.setMaxUploadSize(100 * 1024 * 1024);
        return resolver;
    }

}
