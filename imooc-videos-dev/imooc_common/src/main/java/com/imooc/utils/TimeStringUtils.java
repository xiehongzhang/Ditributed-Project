/**
* Title: TimeStringUtils.java  

* Description   

* @author xhz  

* @date 2019年9月29日  
 
 */
package com.imooc.utils;

import java.util.Date;

/**
 * @author xhz
 * @description 时间的不同表达形式的操作工具
 */
public class TimeStringUtils {
	private static final long ONE_MINUTE=60000L;//一分钟
	private static final long ONE_HOUR=3600000L;//一个小时
	private static final long ONE_DAY=86400000L;//一天
	private static final long ONE_WEEK=604800000L;//一周
	
	private static final String ONE_SECOND_AGO="秒前";
	private static final String ONE_MINUTE_AGO="分钟前";
	private static final String ONE_HOUR_AGO="小时前";
	private static final String ONE_DAY_AGO="天前";
	private static final String ONE_MONTH_AGO="月前";
	private static final String ONE_YEAR_AGO="年前";
	
	//将当前的时间减去发表评论的时间，得到的时间进行格式化
	public static String format(Date agoDate){
		long milliseconds=new Date().getTime() - agoDate.getTime();
		System.out.println(milliseconds);
		if (milliseconds<1L*ONE_MINUTE) {
			long date=toSecond(milliseconds);
			return (date<0 ? 1 : date) +ONE_SECOND_AGO;
		}
		if (milliseconds<60L*ONE_MINUTE) {
			long date=toMinute(milliseconds);
			return (date<=0 ? 1 : date) + ONE_MINUTE_AGO;
		}
		if (milliseconds< 24L*ONE_HOUR) {
			long date = toHour(milliseconds);
			return (date<=0 ? 1 : date) + ONE_HOUR_AGO;
		}
		if (milliseconds < 30L*ONE_DAY){
			long date=toDay(milliseconds);
			return (date<= 0 ? 1 : date) + ONE_DAY_AGO;
		}
		if (milliseconds< 12L * 4L * ONE_WEEK) {
			long date = toMonth(milliseconds);
			return (date<=0 ? 1 : date) + ONE_MONTH_AGO;
		}else{
			long date=toYear(milliseconds);
			return (date<=0 ? 1 :date )+ ONE_YEAR_AGO;
		}
	}
	
	//将毫秒转换成秒
	public static long toSecond(long milliseconds){
		return milliseconds/1000L;

	}
	
	//将毫秒转换成分钟
	public static long toMinute(long milliseconds){
		return toSecond(milliseconds)/60L;
	}
	
	//将毫秒转换成小时
	public static long toHour(long milliseconds){
		return toMinute(milliseconds)/60L;
	}
	
	//将毫秒转换成天
	public static long toDay(long milliseconds){
		return toHour(milliseconds)/24L;
	}
	
	//将毫秒转换成月
	public static long  toMonth(long milliseconds){
		return toDay(milliseconds)/30L;
	}
	
	//将毫秒转换成年
	public static long toYear(long milliseconds){
		return toMonth(milliseconds)/365L;
	}
}
