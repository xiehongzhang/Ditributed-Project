/**
* Title: JsonResultUtil.java  

* Description:作为前端和后台的数据进行json格式转化的转化类

* @author xhz  

* @date 2019年8月5日  
 
 */
package com.imooc.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author JoyPlay
 *  操作步骤：进行json数据的各种转换
 */
public class JsonUtils {
	/**
	 * 静态类对象
	 */
	public static final ObjectMapper OBJECT_MAPPER=new ObjectMapper();
	
	/**
	 * @name:objectToJson
	 * @discription:将java对象转为json数据
	 * @paramter:java对象
	 * @return:json数据
	 */
	public static String objectToJson(Object data){
		try {
			String jsonSting=OBJECT_MAPPER.writeValueAsString(data);
			return jsonSting;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @name:jsonToObject
	 * @discription:将json数据转为普通pojo对象
	 * @paramter:json数据，普通pojo对象
	 * @return:普通pojo对象
	 */
	public static <T> T jsonToObject(String jsonData,Class<T> beanType){
		try {
			T t=OBJECT_MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @name:jsonToList
	 * @discription:将json字符串转为list集合
	 * @paramter:json字符串，pojo类
	 * @return:类类型的集合
	 */
	public static <T> List<T> jsonToList(String jsonData,Class<T> beanType){
		JavaType javaType=OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list=OBJECT_MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
