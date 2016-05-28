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

import java.util.Map;

import com.uc.system.util.TimeUtil;

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

	public String getTitle() {
		return title;
	}

	public PolicyInfoView() {
		super();
	}

	public PolicyInfoView(PolicyInfo info, Map<String, String> departmentTypeMap) {
		super();
		this.title = info.getTitle();
		this.source = info.getCity() + " "
				+ departmentTypeMap.get(info.getDepartment());
		if("".equals(info.getReleaseTime())||null==info.getReleaseTime()){
			this.time = "";
		}else{
			this.time =  info.getReleaseTime().split(" ")[0];
		}
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
