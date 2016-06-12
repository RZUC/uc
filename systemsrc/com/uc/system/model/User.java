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

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description: 用户实体
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

	private String password;

	private String createTime;// 创建时间

	private String whence;// 来源 导入，或者注册

	private List<String> save;// 收藏夹；

	private String userTypeId;// 企业 服务机构

	/**
	 * @Title: User
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return
	 */
	public User() {
		super();
	}

	/**
	 * @Title: User
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @param name
	 * @param @param password
	 * @param @param createTime
	 * @param @param whence 设定文件
	 * @return
	 */
	public User(String id, String name, String password, String createTime,
			String whence) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.createTime = createTime;
		this.whence = whence;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", createTime=" + createTime + ", whence=" + whence + "]";
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
