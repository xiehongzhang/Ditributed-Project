/**
* Title: AdminUser.java  

* Description   

* @author xhz  

* @date 2019年11月19日  
 
 */
package com.imooc.pojo;

/**
 * @author xhz
 * @description 管理者实体类
 */
public class AdminUser {
	private String username;
	private String password;
	private String token;
	public AdminUser() {
	}
	public AdminUser(String username, String password, String token) {
		this.username = username;
		this.password = password;
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
