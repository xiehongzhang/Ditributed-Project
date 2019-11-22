/**
* Title: LoginInterceptor.java  

* Description   

* @author xhz  

* @date 2019年11月18日  
 
 */
package com.imooc.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xhz
 * @description 拦截器，拦截用户的请求判断用户是否登录
 */
public class LoginInterceptor implements HandlerInterceptor{
	
	private List<String> unCheckUrls;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		//获取用户的请求路径
		String requestUrl=request.getRequestURI();
		requestUrl=requestUrl.replaceAll(request.getContextPath(), "");
		if (unCheckUrls.contains(requestUrl)) {
			return true;
		}else if(null == request.getSession().getAttribute("sessionUser")){
			response.sendRedirect(request.getContextPath()+"/users/login.action");
			return false;
		}
		return true;
	} 
	

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView mav)
			throws Exception {
		
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}


	public List<String> getUnCheckUrls() {
		return unCheckUrls;
	}


	public void setUnCheckUrls(List<String> unCheckUrls) {
		this.unCheckUrls = unCheckUrls;
	}



}
