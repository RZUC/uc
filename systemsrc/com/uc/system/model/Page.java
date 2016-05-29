/**
 * 
 */
package com.uc.system.model;

/**
 * @author Simple 分页信息
 */
public class Page {
	public Page(int pageSize, int pageNum) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}

	private int pageSize;// 每页条数
	private int pageNum;// 当前页面

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

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
