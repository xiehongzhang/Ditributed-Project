/**
* Title: PageResult.java  

* Description   

* @author xhz  

* @date 2019年9月18日  
 
 */
package com.imooc.utils;

import java.util.List;

/**
 * @author xhz
 * @param <T>
 * @description 封装后的页面数据 
 */
public class PageResult {
	//当前页页数
	private int page;
	//总的页数
	private int total;
	//总的记录数
	private int records;
	//每条记录要显示的内容，即查询的每个对象
	private List<?> rows;
	
	public PageResult() {
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
