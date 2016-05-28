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

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description: 行业 （现在叫技术领域）
 * @ClassName: Industry
 * @author 落花流水
 * @date 2016年4月7日 上午11:46:32
 */
@Document(collection = "industry")
public class Industry {
	private String id;
	private int fatherId;
	private String name;// 行业名称（领域名称）

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the fatherId
	 */
	public int getFatherId() {
		return fatherId;
	}

	/**
	 * @Title: Industry
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @param fatherId
	 * @param @param name 设定文件
	 * @return
	 */
	public Industry(String id, int fatherId, String name) {
		super();
		this.id = id;
		this.fatherId = fatherId;
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param fatherId
	 *            the fatherId to set
	 */
	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Industry() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Industry [id=" + id + ", fatherId=" + fatherId + ", name="
				+ name + "]";
	}
}
