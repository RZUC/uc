/** * *************************************************** * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. * ***************************************************** *  * */package com.uc.system.dao.impl;import java.util.ArrayList;import java.util.List;import javax.annotation.Resource;import org.springframework.data.mongodb.core.MongoTemplate;import org.springframework.data.mongodb.core.query.Criteria;import org.springframework.data.mongodb.core.query.Query;import org.springframework.data.mongodb.core.query.Update;import org.springframework.stereotype.Component;import com.mongodb.WriteConcern;import com.mongodb.WriteResult;import com.uc.system.dao.PolicyTypeDao;import com.uc.system.exception.ZhiWeiException;import com.uc.system.model.PolicyType;import com.uc.system.util.Sequence;/** * @author Simple * @date 2016-04-18 */@Componentpublic class PolicyTypeDaoImpl implements PolicyTypeDao {	@Resource	private MongoTemplate mongoTemp;	@Override	public PolicyType insert(PolicyType ob) throws ZhiWeiException {		try {			ob.setId(Sequence.getNextId("policyType"));			mongoTemp.save(ob);		} catch (Exception e) {			throw new ZhiWeiException(e.getMessage());		}		return ob;	}	@Override	public PolicyType findOne(String id) throws ZhiWeiException {		// TODO Auto-generated method stub		return null;	}	@Override	public PolicyType findOne(PolicyType ob) throws ZhiWeiException {		// TODO Auto-generated method stub		return null;	}	@Override	public List<PolicyType> findAll() throws ZhiWeiException {		// TODO Auto-generated method stub		return null;	}	@Override	public long findCont() throws ZhiWeiException {		// TODO Auto-generated method stub		return 0;	}	@Override	public boolean removeOneById(String id) throws ZhiWeiException {		Query query = new Query();		query.addCriteria(Criteria.where("_id").is(id));		try {			mongoTemp.setWriteConcern(WriteConcern.SAFE);			mongoTemp.remove(query, PolicyType.class);			mongoTemp.setWriteConcern(WriteConcern.NORMAL);		} catch (Exception e) {			throw new ZhiWeiException(e.getMessage());		}		return false;	}	/*	 * (non-Javadoc)	 * 	 * @see com.uc.system.dao.CommonDao#removeAll()	 */	@Override	public boolean removeAll() throws ZhiWeiException {		// TODO Auto-generated method stub		return false;	}	@Override	public boolean findAndModify(PolicyType ob) throws ZhiWeiException {		Query query = new Query();		query.addCriteria(Criteria.where("_id").is(ob.getId()));		Update update = new Update();		if (null != ob.getName() && !"".equals(ob.getName())) {			update.set("name", ob.getName());		}		if (0 != ob.getOrder()) {			update.set("order", ob.getOrder());		}		try {			mongoTemp.setWriteConcern(WriteConcern.SAFE);			WriteResult result = mongoTemp.upsert(query, update,					PolicyType.class);			if (result.getN() > 0) {				return true;			}			mongoTemp.setWriteConcern(WriteConcern.NORMAL);		} catch (Exception e) {			throw new ZhiWeiException(e.getMessage());		}		return false;	}	@Override	public List<PolicyType> findListWithLimitAndSkip(int skip, int limit)			throws ZhiWeiException {		List<PolicyType> list = new ArrayList<PolicyType>();		Query query = new Query();		query.skip(skip);		query.limit(limit);		list = mongoTemp.find(query, PolicyType.class);		return list;	}}