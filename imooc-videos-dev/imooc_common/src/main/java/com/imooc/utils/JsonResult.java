/**
* Title: JsonResult.java  

* Description:   

* @author xhz  

* @date 2019年8月6日  
 
 */
package com.imooc.utils;

/**
 * @author xhz
 * @Description 自定义响应数据结构 此类是提供给门户，例如iOS，android，微信小程序使用的
 *              门户接受此类数据后需要使用本类的方法转换成对应的数据类型格式（类，或者list） 其他的自行处理
 *              200：表示成功
 *              500：表示错误，错误信息在msg字段中 
 *              501：bean验证错误，不管多少个错误都以map形式返回
 *              502：拦截器拦截到用户token出错 
 *              555：异常抛出信息
 */
public class JsonResult {
	// 响应业务状态
	private Integer status;

	// 响应提示消息
	private String msg;

	// 响应的数据
	private Object data;

	// 不使用
	private String ok;

	/**
	 * @name build
	 * @Description 创建一个JsonResult带有三个属性的对象
	 * @param status,msg,data
	 * @result JsonResult对象
	 */
	public static JsonResult build(Integer status, String msg, Object data) {
		return new JsonResult(status, msg, data);
	}

	/**
	 * @name ok
	 * @Desription 返回一个带有data属性的JsonResult对象
	 * @param data
	 * @result JsonResult对象
	 */
	public static JsonResult ok(Object data) {
		return new JsonResult(data);
	}

	/**
	 * @name ok
	 * @Description 返回一个data为null的对象
	 * @return:JsonResult对象
	 */
	public static JsonResult ok() {
		return new JsonResult(null);
	}

	/**
	 * @name errorMsg
	 * @Description 返回一个状态码为500的错误信息
	 * @param msg
	 * @return JsonResult对象
	 */
	public static JsonResult errorMsg(String msg) {
		return new JsonResult(500, msg, null);
	}

	/**
	 * @name errorMap
	 * @Description bean映射错误的时候，返回一个状态码为501的错误信息
	 * @param data
	 * @return JsonResutl对象
	 */
	public static JsonResult errorMap(Object data) {
		return new JsonResult(501, "error", data);
	}

	/**
	 * 
	 * @name errorTokenMsg
	 * @Description 拦截到用户token错误时，报502状态错误
	 * @param msg
	 * @return JsonResult对象
	 */
	public static JsonResult errorTokenMsg(String msg) {
		return new JsonResult(502, msg, null);
	}

	/**
	 * @name errorException
	 * @Description 抛出异常错误
	 * @param msg
	 * @return JsonResult对象
	 */
	public static JsonResult errorException(String msg) {
		return new JsonResult(505, msg, null);
	}

	public Boolean isOk() {
		return this.status == 200;
	}

	/**
	 * @param status
	 * @param msg
	 * @param data
	 */
	public JsonResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * @name JsonResult
	 * @Description 一个参数的构造函数，将status设置为200，msg设置为OK，data设置为传参的data
	 * @param data
	 */
	public JsonResult(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

	public JsonResult() {

	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

}
