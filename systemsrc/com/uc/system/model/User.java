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
	@Id
	private String id;

	@Indexed
	private String name;

	@Indexed
	private String password;
	private String createTime;// 创建时间
	private String whence;// 来源 导入，或者注册
	private String userTypeId;// 企业 服务机构
	private int province;// 省 （ID）
	private int city;// 市 （ID）
	private int downtown;// 区

	int industryLevelOneId;
	int industryLevelTwoId;

	/* 企业信息 */
	private String enterpriseName;// 企业名称
	private String enterpriseAddress;// 企业地址
	private String Linkman;// 联系人
	private String telephone;// 联系电话

	/* 服务机构 */
	// TODO:服务机构类别包括：政策咨询、管理咨询、知识产权、认证评估、科技服务、人力资源服务、会计/审计/税务服务、法律服务、金融服务、广告/媒体/会展、研发设计、IT服务
	String organizationName;// 机构名称
	int organizationType;// 机构类别
	int organizProvince;
	int organizCity;
	int organizDowntown;
	String url;
	String summary;//简介
	String serviceContent;//服务内容
//、网址、服务主题词、机构介绍、服务内容描述、注册地址、可提供服务、联系人姓名、手机、邮箱、固定电话、部门及职务。
	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", createTime=" + createTime + ", whence=" + whence + "]";
	}

	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public int getProvince() {
		return province;
	}

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

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @return the whence
	 */
	public String getWhence() {
		return whence;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @param whence
	 *            the whence to set
	 */
	public void setWhence(String whence) {
		this.whence = whence;
	}
}
