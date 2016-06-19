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

import com.uc.system.model.Favorite;

public interface FavoriteDao extends CommonDao<Favorite> {

	List<Favorite> findAllByUid(int uid);
}
