/** * *************************************************** * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. * ***************************************************** *  * */package com.uc.system.dao.impl;import java.util.ArrayList;import java.util.List;import java.util.Set;import org.springframework.data.mongodb.core.query.Criteria;import org.springframework.data.mongodb.core.query.Query;import org.springframework.stereotype.Component;import com.uc.system.dao.LocationDao;import com.uc.system.exception.ZhiWeiException;import com.uc.system.model.Location;import com.uc.system.util.Sequence;/** * @author cwt * @date 2016-04-06 */@Componentpublic class LocationDaoImpl extends GeneralDaoImpl implements LocationDao {	@Override	public Location insert(Location ob) throws ZhiWeiException {		ob.setId(Integer.valueOf(Sequence.getNextId("location")));		mongoTemp.save(ob);		return ob;	}	@Override	public Location findOne(String id) throws ZhiWeiException {		// TODO Auto-generated method stub		return null;	}	@Override	public Location findOne(Location ob) throws ZhiWeiException {		// TODO Auto-generated method stub		return null;	}	@Override	public List<Location> findAll() throws ZhiWeiException {		List<Location> list = mongoTemp.findAll(Location.class);		return list;	}	@Override	public long findCont() throws ZhiWeiException {		// TODO Auto-generated method stub		return 0;	}	@Override	public boolean removeOneById(String id) throws ZhiWeiException {		Query query = new Query();		query.addCriteria(Criteria.where("_id").is(Integer.valueOf(id)));		try {			mongoTemp.remove(query, Location.class);			return true;		} catch (Exception e) {			log.error("dao删除location失败：{}", e.getMessage());		}		return false;	}	@Override	public boolean removeAll() throws ZhiWeiException {		// TODO Auto-generated method stub		return false;	}	@Override	public boolean findAndModify(Location ob) throws ZhiWeiException {		// TODO Auto-generated method stub		return false;	}	@Override	public List<Location> findOneByFiled(String field, Object value)			throws ZhiWeiException {		List<Location> list = new ArrayList<Location>();		if (value instanceof Set) {			Query query = new Query(Criteria.where(field).in(((Set) value).toArray()));			System.out.println(query.getQueryObject());			list = mongoTemp.find(query,					Location.class);		} else {			list = mongoTemp.find(new Query(Criteria.where(field).is(value)),					Location.class);		}		return list;	}	@Override	public Location updateLocation(Location ob) throws ZhiWeiException {		try {			mongoTemp.save(ob);			return ob;		} catch (Exception e) {			throw new ZhiWeiException("dao更新location失败：" + e.getMessage());		}	}}