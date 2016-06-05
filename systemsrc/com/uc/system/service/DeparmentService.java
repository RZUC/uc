/**
 * ***************************************************
 * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
 *****************************************************
 * 类的详细说明 
 * 
 * @author 东临碣石
 * @Date 2016年4月21日
 * @version 1.00 
 */
package com.uc.system.service;

import java.util.List;

import com.uc.system.model.Department;

public interface DeparmentService {
	/**
	 * @Title: getDeparmentList
	 * @Description: 获取部门列表
	 * @param @return 设定文件
	 * @return List<Department> 返回类型
	 */
	List<Department> getDeparmentList();
}
