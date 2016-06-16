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
	private String province;// 省 （ID）
	private String city;// 市 （ID）
	private String downtown;// 区
	private String industryLeveOneId;
	private String industryLeveTwoeId;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDowntown() {
		return downtown;
	}

	public void setDowntown(String downtown) {
		this.downtown = downtown;
	}

	public String getIndustryLeveOneId() {
		return industryLeveOneId;
	}

	public void setIndustryLeveOneId(String industryLeveOneId) {
		this.industryLeveOneId = industryLeveOneId;
	}

	public String getIndustryLeveTwoeId() {
		return industryLeveTwoeId;
	}

	public void setIndustryLeveTwoeId(String industryLeveTwoeId) {
		this.industryLeveTwoeId = industryLeveTwoeId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}
