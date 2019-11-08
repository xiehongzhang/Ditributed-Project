/**
* Title: FileStringUtils.java  

* Description   

* @author xhz  

* @date 2019年11月8日  
 
 */
package com.imooc.utils;

/**
 * @author xhz
 * @description 字符串处理工具
 */
public class FileStringUtils {

	/**
	 * @name getFileName
	 * @Description 如果字符串中包含多个".",获取最后一个"."之前的所有字符串
	 * @param fileName
	 * @return 
	 */
	public static String getFileName(String fileName){
		String fileNameString="";
		String fileName1[]=fileName.split("\\.");
		if (fileName1.length <= 2) {
			return fileName1[0];
		}
		for(int i = 0 ; i < fileName1.length-1 ; i++){
			fileNameString += fileName1[i];
			if(i<fileName1.length-2){
				fileNameString+=".";
			}
		}
		return fileNameString;
	}
}
