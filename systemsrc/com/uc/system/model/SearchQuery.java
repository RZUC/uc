/**
 * ***************************************************
 * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
 *****************************************************
 * 类的详细说明 
 * 
 * @author 东临碣石
 * @Date 2016年4月19日
 * @version 1.00 
 */
package com.uc.system.model;

/**
 * @Description: 政策查询条件
 * @ClassName: Query
 * @author 落花流水
 * @date 2016年4月19日 下午9:29:40
 */
public class SearchQuery {
	private String word;
	private String startTime;
	private String endTime;
	private String policyTypeId;
	private String locationId;
	private String industryId;
	private int pageNum;
	private int pageSize;

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

	public String getPolicyTypeId() {
		return policyTypeId;
	}

	public void setPolicyTypeId(String policyTypeId) {
		this.policyTypeId = policyTypeId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getIndustryId() {
		return industryId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}
