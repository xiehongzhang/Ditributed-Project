/**
* Title: TestTimeStringUtils.java  

* Description   

* @author xhz  

* @date 2019年9月29日  
 
 */
package com.imooc.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.imooc.utils.TimeStringUtils;

/**
 * @author 
 * @description 
 */
public class TestTimeStringUtils {

	/**
	 * @name 
	 * @Description 
	 * @param 
	 * @return 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:m:s");
		Date date=format.parse("2018-10-01 09:09:30");
//		Date date1=format.parse("2017-09-28 09:09:30");
//		System.out.println(date);
//		System.out.println(date.getTime());
//		System.out.println("//////////////////");
//		System.out.println(date);
//		System.out.println(new Date().getTime()-date1.getTime());
		String out=TimeStringUtils.format(date);
		System.out.println(out);
	}

}
