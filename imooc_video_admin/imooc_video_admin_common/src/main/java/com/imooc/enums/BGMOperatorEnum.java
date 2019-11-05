/**
* Title: ZKOperator.java  

* Description   

* @author xhz  

* @date 2019年10月15日  
 
 */
package com.imooc.enums;

/**
 * @author xhz
 * @description 用户对BGM的操作，1:代表增加；
 * 						    2：代表删除
 */
public enum BGMOperatorEnum {
	ADD("0","增加BGM"),//增加
	DELETE("1","删除BGM");//删除
	
	public final String type;
	public final String value;
	
	BGMOperatorEnum(String type,String value){
		this.type=type;
		this.value=value;
	}
	
	public String getUserType(){
		return type;
	}
	
	public String getValue(){
		return value;
	}
	
	
	/**
	 * @name getValueByType
	 * @Description 通过type获取对应的value
	 * @param key
	 * @return 
	 */
	public String getValueByType(String key){
		for(BGMOperatorEnum b: BGMOperatorEnum.values()){
			if (b.getUserType().equals(key)) {
				return b.getValue();
			}
		}
		return null;
	}
}
