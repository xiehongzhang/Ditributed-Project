/**
* Title: TestTimeStringUtils.java  

* Description   

* @author xhz  

* @date 2019年9月29日  
 
 */
package com.imooc.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import com.imooc.utils.TimeStringUtils;

/**
 * @author 
 * @description 
 */
public class TimeStringUtilsTest {

	/**
	 * @name 
	 * @Description 
	 * @param 
	 * @return 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:m:s");
//		Date date=format.parse("2018-10-01 09:09:30");
//		Date date1=format.parse("2017-09-28 09:09:30");
//		System.out.println(date);
//		System.out.println(date.getTime());
//		System.out.println("//////////////////");
//		System.out.println(date);
//		System.out.println(new Date().getTime()-date1.getTime());
//		String out=TimeStringUtils.format(LocalDateTime.parse("2018-10-01T09:09:30"));
//		System.out.println(out);
		long time=LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
		System.out.println(time);
		System.out.println("------------------------------------");
		String out=TimeStringUtils.format(LocalDateTime.parse("2018-10-01T09:09:30"));
		System.out.println(out);
		System.out.println("------------------------------------");
		long agoTime=LocalDateTime.parse("2018-10-01T09:09:30").toEpochSecond(ZoneOffset.UTC);
		System.out.println(agoTime);
		System.out.println("----------------------------------------");
		long value=time-agoTime;
		System.out.println(value/(24*60*60));
	}

}
