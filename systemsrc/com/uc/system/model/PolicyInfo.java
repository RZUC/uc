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

import java.util.Date;
import java.util.List;

/**
 * @Description: 政策信息 倒叙排列
 * @ClassName: PolicyInfo
 * @author 落花流水
 * @date 2016年4月7日 上午11:47:11
 */
public class PolicyInfo {
	private long id;//
	private String title;// 政策标题

	private String sourceUrl;// 发布来源ID

	private String department;// 发布部门

	private int policyType;// 政策类型

	private String industry;// 行业（技术领域）

	private String location;// 区域 (地区 位置)

	private int topState;// 置顶字段

	private int order;// 排序字段

	private String topStateEndTime;// 信息有效期，有效期到后自动消除置顶

	private String province;// 省 （ID）

	private String city;// 市 （ID）

	private String downtown;// 区

	private String content;// 内容

	private String releaseTime;// 发布时间

	private List<Resource> resourceList;// 资源文件

	private Date createTime;

	private Date lastUpdateTime;

	// /**
	// * @Title: PolicyInfo
	// * @Description: TODO(这里用一句话描述这个方法的作用)
	// * @param 设定文件
	// * @return
	// */
	// public PolicyInfo() {
	// super();
	// }
	//
	// /**
	// * @Title: PolicyInfo
	// * @Description: TODO(这里用一句话描述这个方法的作用)
	// * @param @param title
	// * @param @param sourceUrl
	// * @param @param department
	// * @param @param policyType
	// * @param @param industry
	// * @param @param location
	// * @param @param topState
	// * @param @param order
	// * @param @param topStateEndTime
	// * @param @param province
	// * @param @param city
	// * @param @param downtown
	// * @param @param content
	// * @param @param releaseTime
	// * @param @param resourceList
	// * @param @param createTime
	// * @param @param lastUpdateTime 设定文件
	// * @return
	// */
	// public PolicyInfo(String title, String sourceUrl, String department,
	// int policyType, String industry, String location, int topState,
	// int order, String topStateEndTime, String province, String city,
	// String downtown, String content, String releaseTime,
	// List<Resource> resourceList, Date createTime, Date lastUpdateTime) {
	// super();
	// this.title = title;
	// this.sourceUrl = sourceUrl;
	// this.department = department;
	// this.policyType = policyType;
	// this.industry = industry;
	// this.location = location;
	// this.topState = topState;
	// this.order = order;
	// this.topStateEndTime = topStateEndTime;
	// this.province = province;
	// this.city = city;
	// this.downtown = downtown;
	// this.content = content;
	// this.releaseTime = releaseTime;
	// this.resourceList = resourceList;
	// this.createTime = createTime;
	// this.lastUpdateTime = lastUpdateTime;
	// }
	//
	//
	// @PersistenceConstructor
	// public PolicyInfo(String title, String sourceUrl, String department,
	// int policyType, String industry, String location, int topState,
	// int order, String province, String topStateEndTime, String city,
	// String downtown, String content, String releaseTime) {
	// super();
	// this.title = title;
	// this.sourceUrl = sourceUrl;
	// this.department = department;
	// this.policyType = policyType;
	// this.industry = industry;
	// this.location = location;
	// this.topState = topState;
	// this.order = order;
	// this.province = province;
	// this.city = city;
	// this.downtown = downtown;
	// this.content = content;
	// this.releaseTime = releaseTime;
	// this.topStateEndTime = topStateEndTime;
	// }

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the sourceUrl
	 */
	public String getSourceUrl() {
		return sourceUrl;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @return the policyType
	 */
	public int getPolicyType() {
		return policyType;
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the downtown
	 */
	public String getDowntown() {
		return downtown;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return the releaseTime
	 */
	public String getReleaseTime() {
		return releaseTime;
	}

	/**
	 * @return the resourceList
	 */
	public List<Resource> getResourceList() {
		return resourceList;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param sourceUrl
	 *            the sourceUrl to set
	 */
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	/**
	 * @param 发布部门
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @param policyType
	 *            the policyType to set
	 */
	public void setPolicyType(int policyType) {
		this.policyType = policyType;
	}

	/**
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param downtown
	 *            the downtown to set
	 */
	public void setDowntown(String downtown) {
		this.downtown = downtown;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @param releaseTime
	 *            the releaseTime to set
	 */
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	/**
	 * @param resourceList
	 *            the resourceList to set
	 */
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	/**
	 * @return the topState
	 */
	public int getTopState() {
		return topState;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param topState
	 *            the topState to set
	 */
	public void setTopState(int topState) {
		this.topState = topState;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	public String getTopStateEndTime() {
		return topStateEndTime;
	}

	public void setTopStateEndTime(String topStateEndTime) {
		this.topStateEndTime = topStateEndTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @param lastUpdateTime
	 *            the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

}
