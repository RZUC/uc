/**
 * 
 */
package com.uc.system.model;

/**
 * @author Simple 分页信息
 */
public class Page {
	private int pageSize;// 每页条数
	private int pageNum;// 当前页面

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
