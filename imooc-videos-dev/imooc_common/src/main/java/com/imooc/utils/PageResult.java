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
	private int pageNum;
	//当前页显示的行数
	private int pageSize;
	//总的页数
	private int pages;
	//总的记录数
	private int sizes;
	//每条记录要显示的内容，即查询的每个对象
	private List<?> list;
	
	public PageResult() {
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getSizes() {
		return sizes;
	}
	public void setSizes(int sizes) {
		this.sizes = sizes;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
}
