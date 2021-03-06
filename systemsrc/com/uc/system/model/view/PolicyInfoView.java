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
package com.uc.system.model.view;

import java.util.Map;

import com.uc.system.model.PolicyInfo;

/**
 * @Description: 政策信息 倒叙排列
 * @ClassName: PolicyInfo
 * @author 落花流水
 * @date 2016年4月7日 上午11:47:11
 */
public class PolicyInfoView {
	private String title;// 政策标题
	private String source;// 政策标题
	private String time;// 政策标题
	private String key;
	private int isTop; // 是否置顶

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public PolicyInfoView() {
		super();
	}

	public PolicyInfoView(PolicyInfo info,
			Map<Integer, String> departmentTypeMap,
			Map<Long, String> locationMap) {
		super();
		this.key = info.getId() + "";
		this.title = info.getTitle();
		String city = info.getCity() == -1 || info.getCity() == 0 ? locationMap
				.get(Long.valueOf(info.getProvince() + "")) : locationMap
				.get(Long.valueOf(info.getCity() + ""));
		if (null == city) {
			System.out.println(info);
		}
		this.source = city + " " + departmentTypeMap.get(info.getDepartment());
		if ("".equals(info.getReleaseTime()) || null == info.getReleaseTime()) {
			this.time = "";
		} else {
			this.time = info.getReleaseTime().split(" ")[0];
		}

		this.isTop = info.getTopState();
	}

	@Override
	public String toString() {
		return "PolicyInfoView [title=" + title + ", source=" + source
				+ ", time=" + time + "]";
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
