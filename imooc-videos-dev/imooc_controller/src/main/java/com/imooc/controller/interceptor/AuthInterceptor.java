/**
* Title: FirstInterceptor.java  

* Description   

* @author xhz  

* @date 2019年9月24日  
 
 */
package com.imooc.controller.interceptor;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.utils.JsonResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisUtils;

/**
 * @author xhz
 * @description 全局拦截器 主要拦截页面的访问，做到安全控制
 * 
 */
public class AuthInterceptor implements HandlerInterceptor {
	
//	@Autowired
//	private RedisUtils redisUtils;
//	
//	//用户的redis的 session token
//	public static final String REDIS_USER_SESSION="redis_user_session";
//
//	/**
//	 * 前置方法：访问控制器之前执行的方法,可以执行一些http路径的控制
//	 * @Description 判断用户是否通过前端登录，是否在另一台终端登录（业务是否有需求）
//	 */
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		
//		//获取前端传输过来的用户iduserId和userToken
//		String userId=request.getHeader("userId");
//		String userToken=request.getHeader("userToken");
//		//判断userId和userToken是否为空
//		if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(userToken)) {
//			//判断userToken是否存在
//			String uniqueToken=redisUtils.get(REDIS_USER_SESSION + ":" + userId);
//			if (StringUtils.isBlank(uniqueToken) || StringUtils.isEmpty(uniqueToken)) {
//				System.out.println("账号在另一台终端登录...");
//				returnErrorJsonMsg(response, new JsonResult().errorTokenMsg("账号在另一台终端登录..."));
//				return false;
//			}else{
//				if (!userToken.equals(uniqueToken)) {
//					System.out.println("请登录...");
//					returnErrorJsonMsg(response,new JsonResult().errorTokenMsg("请登录..."));
//					return false;
//				}
//			}
//			
//		}else{
//			System.out.println("请登录...");
//			returnErrorJsonMsg(response,new JsonResult().errorTokenMsg("请登录..."));
//			return false;
//		}
//			
//		return true;
//		
//	}
//
//	/**
//	 * 后置方法：访问控制器中之后，渲染视图之前执行的方法，可以作为权限的控制
//	 */
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		// TODO Auto-generated method stub
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//	}
//
//	/**
//	 * 完成方法：访问拦截器所有动作都完成了之后执行的方法
//	 */
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		// TODO Auto-generated method stub
//		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//	}
//
//	/**
//	 * @name returnErrorJsonMsg
//	 * @Description 将错误的信息以json的形式返回给前端
//	 * @param response
//	 * @param jsonResult
//	 * @return 
//	 * @throws IOException 
//	 */
//	public void returnErrorJsonMsg(HttpServletResponse response,JsonResult jsonResult) throws IOException{
//		OutputStream outputStream=null;
//		try {
//			response.setCharacterEncoding("utf-8");
//			response.setContentType("text/json");
//			//将响应的信息以留的形式写出去
//			outputStream=response.getOutputStream();
//			outputStream.write(JsonUtils.objectToJson(jsonResult).getBytes("utf-8"));
//			outputStream.flush();
//		} finally {
//			if (outputStream != null) {
//				outputStream.close();
//			}
//		}
//	}	
}
