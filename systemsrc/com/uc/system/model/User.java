/**
 * ***************************************************
 * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
 *****************************************************
 * 类的详细说明 
 * 
 * @author 东临碣石
 * @Date 2016年1月15日
 * @version 1.00 
 */
package com.uc.system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description: 本系统的用户
 * @ClassName: User
 * @author 落花流水
 * @date 2016年1月15日 上午10:06:06
 */
@Document(collection = "user")
public class User {
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", createTime=" + createTime + ", whence=" + whence
				+ ", province=" + province + ", city=" + city + ", downtown="
				+ downtown + ", userTypeId=" + userTypeId
				+ ", industryLevelOneId=" + industryLevelOneId
				+ ", industryLevelTwoId=" + industryLevelTwoId + ", Linkman="
				+ Linkman + ", mobilephone=" + mobilephone
				+ ", enterpriseName=" + enterpriseName + ", enterpriseAddress="
				+ enterpriseAddress + ", organizationName=" + organizationName
				+ ", organizationType=" + organizationType
				+ ", organizLocation=" + organizLocation + ", url=" + url
				+ ", summary=" + summary + ", serviceContent=" + serviceContent
				+ ", serviceContentDescription=" + serviceContentDescription
				+ ", regiseAddress=" + regiseAddress + ", telephone="
				+ telephone + ", department=" + department + ", job=" + job
				+ "]";
	}

	@Id
	private String id;

	@Indexed
	private String name;

	@Indexed
	private String password;
	private String createTime;// 创建时间
	private String whence;// 来源 导入，或者注册
	private int province;// 省 （ID）
	private int city;// 市 （ID）
	private int downtown;// 区

	private String userTypeId;// 企业 服务机构
	int industryLevelOneId;// 技术领域
	int industryLevelTwoId;// 技术领域
	private String Linkman;// 联系人
	private String mobilephone;// 联系电话

	/* 企业信息 */
	private String enterpriseName;// 企业名称
	private String enterpriseAddress;// 企业地址

	/* 服务机构 */
	private String organizationName;// 机构名称
	private int organizationType;// 机构类别
	private String organizLocation; // 服务区域
	private String url;// 网址
	private String summary;// 简介
	private String serviceContent;// 可提供服务
	private String serviceContentDescription;// 服务内容描述
	private String regiseAddress;
	private String telephone;// 固定电话
	private String department;// 部门
	private String job;// 职务

	public User(String id, String name, String password, String createTime,
			String whence, String userTypeId, int province, int city,
			int downtown) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.createTime = createTime;
		this.whence = whence;
		this.userTypeId = userTypeId;
		this.province = province;
		this.city = city;
		this.downtown = downtown;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getWhence() {
		return whence;
	}

	public void setWhence(String whence) {
		this.whence = whence;
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

	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public int getIndustryLevelOneId() {
		return industryLevelOneId;
	}

	public void setIndustryLevelOneId(int industryLevelOneId) {
		this.industryLevelOneId = industryLevelOneId;
	}

	public int getIndustryLevelTwoId() {
		return industryLevelTwoId;
	}

	public void setIndustryLevelTwoId(int industryLevelTwoId) {
		this.industryLevelTwoId = industryLevelTwoId;
	}

	public String getLinkman() {
		return Linkman;
	}

	public void setLinkman(String linkman) {
		Linkman = linkman;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getEnterpriseAddress() {
		return enterpriseAddress;
	}

	public void setEnterpriseAddress(String enterpriseAddress) {
		this.enterpriseAddress = enterpriseAddress;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public int getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(int organizationType) {
		this.organizationType = organizationType;
	}

	public String getOrganizLocation() {
		return organizLocation;
	}

	public void setOrganizLocation(String organizLocation) {
		this.organizLocation = organizLocation;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	public String getServiceContentDescription() {
		return serviceContentDescription;
	}

	public void setServiceContentDescription(String serviceContentDescription) {
		this.serviceContentDescription = serviceContentDescription;
	}

	public String getRegiseAddress() {
		return regiseAddress;
	}

	public void setRegiseAddress(String regiseAddress) {
		this.regiseAddress = regiseAddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
}
