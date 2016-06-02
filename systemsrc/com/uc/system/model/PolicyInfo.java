/**
 * ***************************************************
 * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
 *****************************************************
 * 类的详细说明 
 * 
 * @author 东临碣石
 * @Date 2016年4月7日
 * @version 1.00 
 */
package com.uc.system.model;

import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * @Description: 政策信息 倒叙排列
 * @ClassName: PolicyInfo
 * @author 落花流水
 * @date 2016年4月7日 上午11:47:11
 */
public class PolicyInfo {
	@Id
	private long id;//
	private String title;// 政策标题

	private String sourceUrl;// 发布来源ID

	private int department;// 发布部门

	private int policyType;// 政策类型

	private int industry;// 行业（技术领域）

	private String location;// 区域 (地区 位置)

	private int topState;// 置顶字段

	private int order;// 排序字段

	private String topStateEndTime;// 信息有效期，有效期到后自动消除置顶

	private int province;// 省 （ID）

	private int city;// 市 （ID）

	private int downtown;// 区

	private String content;// 内容

	private String releaseTime;// 发布时间

	private List<Resource> resourceList;// 资源文件

	private String createTime;

	private String lastUpdateTime;

	@Override
	public String toString() {
		return "PolicyInfo [title=" + title + ", sourceUrl=" + sourceUrl
				+ ", department=" + department + ", policyType=" + policyType
				+ ", industry=" + industry + ", location=" + location
				+ ", topState=" + topState + ", order=" + order
				+ ", topStateEndTime=" + topStateEndTime + ", province="
				+ province + ", city=" + city + ", downtown=" + downtown
				+ ", content=" + content + ", releaseTime=" + releaseTime
				+ ", resourceList=" + resourceList + ", createTime="
				+ createTime + ", lastUpdateTime=" + lastUpdateTime + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public int getPolicyType() {
		return policyType;
	}

	public void setPolicyType(int policyType) {
		this.policyType = policyType;
	}

	public int getIndustry() {
		return industry;
	}

	public void setIndustry(int industry) {
		this.industry = industry;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getTopState() {
		return topState;
	}

	public void setTopState(int topState) {
		this.topState = topState;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}


	public String getTopStateEndTime() {
		return topStateEndTime;
	}

	public void setTopStateEndTime(String topStateEndTime) {
		this.topStateEndTime = topStateEndTime;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getDowntown() {
		return downtown;
	}

	public void setDowntown(int downtown) {
		this.downtown = downtown;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
