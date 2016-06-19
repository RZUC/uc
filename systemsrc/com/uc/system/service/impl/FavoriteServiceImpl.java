package com.uc.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.uc.system.dao.FavoriteDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Favorite;
import com.uc.system.service.FavoriteService;

/**
 * @author Simple
 *
 */
@Component
public class FavoriteServiceImpl extends GeneralServiceImpl implements
		FavoriteService {

	@Resource
	private FavoriteDao dao;

	@Override
	public Favorite collect(Favorite favorite) {
		try {
			favorite = dao.insert(favorite);
			return favorite;
		} catch (ZhiWeiException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean unCollect(String favoriteId) {
		try {

			return dao.removeOneById(favoriteId);
		} catch (ZhiWeiException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Favorite> findFavoriteByUid(int uid) {
		List<Favorite> list = dao.findAllByUid(uid);
		return list;
	}

}