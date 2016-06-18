/** * *************************************************** * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. * ***************************************************** *  * */package com.uc.system.dao.impl;import java.util.ArrayList;import java.util.List;import javax.annotation.Resource;import org.springframework.data.mongodb.core.MongoTemplate;import org.springframework.data.mongodb.core.query.Criteria;import org.springframework.data.mongodb.core.query.Query;import org.springframework.data.mongodb.core.query.Update;import org.springframework.stereotype.Component;import com.mongodb.WriteConcern;import com.mongodb.WriteResult;import com.uc.system.dao.IndustryDao;import com.uc.system.exception.ZhiWeiException;import com.uc.system.model.Industry;/** * @Description: TODO(行业) * @ClassName: IndustryDaoImpl * @author 落花流水 * @date 2016年4月24日 下午3:47:45 */@Componentpublic class IndustryDaoImpl extends GeneralDaoImpl implements IndustryDao {	@Resource	private MongoTemplate mongoTemp;	@Override	public Industry insert(Industry ob) throws ZhiWeiException {		try {			ob.setId(Integer.valueOf(Sequence.getNextId("industry")));			mongoTemp.save(ob);		} catch (Exception e) {			ob = null;			System.out.println("新增数据出错");		}		return ob;	}	@Override	public Industry findOne(String id) throws ZhiWeiException {		// TODO Auto-generated method stub		return null;	}	@Override	public Industry findOne(Industry ob) throws ZhiWeiException {		// TODO Auto-generated method stub		return null;	}	@Override	public List<Industry> findAll() throws ZhiWeiException {		// TODO Auto-generated method stub		return null;	}	@Override	public long findCont() throws ZhiWeiException {		return 0;	}	@Override	public boolean removeOneById(String id) throws ZhiWeiException {		boolean flag = false;		Query query = new Query();		query.addCriteria(Criteria.where("_id").is(Integer.valueOf(id)));		try {			mongoTemp.setWriteConcern(WriteConcern.SAFE);			mongoTemp.remove(query, Industry.class);			mongoTemp.setWriteConcern(WriteConcern.NORMAL);			flag = true;		} catch (Exception e) {			throw new ZhiWeiException(e.getMessage());		}		return flag;	}	@Override	public boolean removeAll() throws ZhiWeiException {		// TODO Auto-generated method stub		return false;	}	@Override	public boolean findAndModify(Industry ob) throws ZhiWeiException {		boolean flag = false;		Query query = new Query();		query.addCriteria(Criteria.where("_id").is(ob.getId()));		Update update = new Update();		if (null != ob.getName() && !"".equals(ob.getName())) {			update.set("name", ob.getName());		}		update.set("fatherId", ob.getFatherId());		try {			mongoTemp.setWriteConcern(WriteConcern.SAFE);			WriteResult result = mongoTemp					.upsert(query, update, Industry.class);			if (result.getN() > 0) {				flag = true;			}			mongoTemp.setWriteConcern(WriteConcern.NORMAL);		} catch (Exception e) {			throw new ZhiWeiException(e.getMessage());		}		return flag;	}	@Override	public List<Industry> findListWithLimitAndSkip(int skip, int limit)			throws ZhiWeiException {		List<Industry> list = new ArrayList<Industry>();		Query query = new Query();		query.skip(skip);		query.limit(limit);		list = mongoTemp.find(query, Industry.class);		return list;	}	@Override	public List<Industry> findByFatherID(int fatherId) {		List<Industry> list = new ArrayList<Industry>();		Query query = new Query();		query.addCriteria(Criteria.where("fatherId").is(fatherId));		list = mongoTemp.find(query, Industry.class);		return list;	}}