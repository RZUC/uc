package com.uc.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.uc.system.dao.LocationDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Location;
import com.uc.system.service.LocationService;

/**
 * @author cwt
 * @date 2016-04-06
 */
@Component
public class LocationServiceImpl extends GeneralServiceImpl implements
		LocationService {

	@Resource
	private LocationDao locationDao;

	@Override
	public List<Location> findLocationByFatherId(String fatherID) {
		List<Location> list;
		try {
			list = locationDao.findOneByFiled("fatherID", fatherID);
			return list;
		} catch (ZhiWeiException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Location> findProvince() {
		List<Location> list;
		try {
			list = locationDao.findOneByFiled("fatherID", "0");
			return list;
		} catch (ZhiWeiException e) {
			log.error("查询省份信息出错：{}", e.getCause());
		}
		return null;
	}

	@Override
	public boolean deleteById(String id) {
		return false;
	}

	@Override
	public List findAll(Object... ob) {
		return null;
	}

	@Override
	public Location add(Location ob) {
		try {
			return locationDao.insert(ob);
		} catch (ZhiWeiException e) {
			log.error("插入地域信息出错：{}", e.getMessage());
		}
		return null;
	}

	@Override
	public Location updata(Location ob) {
		return null;
	}

	@Override
	public boolean delete(Location ob) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean search(Location ob) {
		return false;
	}

	@Override
	public Location findOneById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}