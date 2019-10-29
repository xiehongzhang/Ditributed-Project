/**
O* Title: RedisUtils.java  

* Description   

* @author xhz  

* @date 2019年8月21日  
 
 */
package com.imooc.utils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


/**
 * @author RedisUtils
 * @description 对RedisTemplate 进行封装，减少代码量
 */ 
@Component
public class RedisUtils {
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	///////////////// key value 操作 /////////////////
	/**
	 * @name ttl
	 * @Description 获取key的剩余的超时时间 
	 * @param key
	 * @return 
	 */
	public long ttl(String key){
		return redisTemplate.getExpire(key);
	}
	
	/**
	 * @name setExpire
	 * @Description 设置缓存的超时时间
	 * @param key 和 timeout
	 * @return 
	 */
	public void setExpire(String key,long timeout){
		redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}
	
	/**
	 * @name incr
	 * @Description 对key增加一次 
	 * @param key和delta
	 * @return 
	 */
	public long incr(String key,long delta){
		return redisTemplate.opsForValue().increment(key, delta);
	}
	
	/**
	 * @name keys
	 * @Description 根据给定的pattern查询所有符合的keys
	 * @param 
	 * @return 
	 */
	public Set<String> keys(String pattern){
		return redisTemplate.keys(pattern);
	}
	
	/**
	 * @name delKey
	 * @Description 删除一个key 
	 * @param key
	 * @return 
	 */
	public void delKeys(String key){
		redisTemplate.delete(key);
	}
	
	/////////// String 操作 ///////////////
	/**
	 * @name set
	 * @Description 为key设置value 
	 * @param key和value
	 * @return 
	 */
	public void set(String key,String value){
		redisTemplate.opsForValue().set(key, value);
	}
	
	/**
	 * @name set
	 * @Description 为键值对设置超时时间 
	 * @param key，value和timeout
	 * @return 
	 */
	public void set(String key,String value,long timeout){
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}
	
	/**
	 * @name get 
	 * @Description 通过给定的key获取对应的value 
	 * @param key
	 * @return 
	 */
	public String get(String key){
		return (String)redisTemplate.opsForValue().get(key);
	}
	
	//////// hash 表操作  ///////////
	/**
	 * @name hset 
	 * @Description 将hash名为key的域设置为value 
	 * @param key，field和value
	 * @return 
	 */
	public void hset(String key,String field,Object value){
		redisTemplate.opsForHash().put(key, field, value);
	}
	
	/**
	 * @name hget 
	 * @Description  返回hash表给定field的value
	 * @param key和key
	 * @return 
	 */
	public String hget(String key,String field){
		return (String) redisTemplate.opsForHash().get(key, field);
	}
	
	/**
	 * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
	 * 
	 * @param key
	 * @param fields
	 */
	public void hdel(String key, Object... fields) {
		redisTemplate.opsForHash().delete(key, fields);
	}

	/**
	 * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
	 * 
	 * @param key
	 * @return
	 */
	public Map<Object, Object> hgetall(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	///////////// List（列表）操作 ///////////

	/**
	 * @name lpush
	 * @Description 将一个值 value插入到列表 key的表头
	 * @param key
	 * @param value
	 * @return 执行 LPUSH命令后，列表的长度。
	 */
	public long lpush(String key, String value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * @name lpop
	 * @Description LPOP key，移除并返回列表 key的头元素。
	 * @param key
	 * @return 列表key的头元素。
	 */
	public String lpop(String key) {
		return (String)redisTemplate.opsForList().leftPop(key);
	}

	/**
	 * @name rpush
	 * @Description 将一个值 value插入到列表 key的表尾(最右边)。
	 * @param value
	 * @return 执行 LPUSH命令后，列表的长度。
	 */
	public long rpush(String key, String value) {
		return redisTemplate.opsForList().rightPush(key, value);
	} 
}
