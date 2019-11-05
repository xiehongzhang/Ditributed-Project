package com.imooc;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xhz
 * @description 作为工程的启动类
 */
@SpringBootApplication
// @EnableAutoConfiguration+@ComponentScan+@Configuration可替代@SpringBootApplication
@MapperScan(basePackages = { "com.imooc.dao" })
@ComponentScan(basePackages = { "com.imooc", "org.n3r.idworker"})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
 