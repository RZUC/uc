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
	private int totalPage;// 总页数
	private int totalSize;// 总条数

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

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
}
