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

import com.uc.system.model.Industry;
import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.Query;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: IndustryService
 * @author 落花流水
 * @date 2016年4月21日 上午6:39:25
 */
public interface IndustryService {
	/**
	 * @Title: findList
	 * @Description: 根据查询条件和分页调节来查询信息
	 * @param @param query
	 * @param @param page
	 * @param @return 设定文件
	 * @return List<PolicyInfo> 返回类型
	 */
	List<Industry> findList(Query query, Page page);

	/**
	 * @Title: add
	 * @Description: 添加数据
	 * @param @param info
	 * @param @return 设定文件
	 * @return Message 返回类型
	 */
	Industry add(Industry industry);

	/**
	 * @Title: del
	 * @Description: 根据ID删除数据
	 * @param @param id
	 * @param @return 设定文件
	 * @return Message 返回类型
	 */
	Message del(String id);

	/**
	 * @Title: modify
	 * @Description: 更新数据
	 * @param @param info
	 * @param @return 设定文件
	 * @return List<Location> 返回类型
	 */
	Message modify(Industry industry);

	/**
	 * @Title: modify
	 * @Description: 更新数据
	 * @param @param info
	 * @param @return 设定文件
	 * @return List<Location> 返回类型
	 */
	List<Industry> findByPage(Page page);

	/**
	 * 查询一级行业
	 * 
	 * @param page
	 * @return
	 */
	List<Industry> findLevelOne();

	List<Industry> findByFatherID(String fatherid);
}
