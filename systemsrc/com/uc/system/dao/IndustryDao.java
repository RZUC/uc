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
package com.uc.system.dao;

import java.util.List;

import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Industry;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: IndustryDao
 * @author 落花流水
 * @date 2016年4月21日 上午6:41:55
 */
public interface IndustryDao extends CommonDao<Industry> {

	/**
	 * @Title: findAll
	 * @Description: 分页查询数据
	 * @param @param query
	 * @param @param page
	 * @param @return 设定文件
	 * @return List<PolicyInfo> 返回类型
	 */
	List<Industry> findListWithLimitAndSkip(int skip, int limit)
			throws ZhiWeiException;

	/**
	 * 根据ID查询行业
	 * 
	 * @param fatherId
	 * @return
	 */
	List<Industry> findByFatherID(String fatherId);

}
