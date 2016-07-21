/** * *************************************************** * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. * ***************************************************** *  * */package com.uc.system.dao.impl;import java.util.ArrayList;import java.util.List;import javax.annotation.Resource;import org.springframework.data.mongodb.core.MongoTemplate;import org.springframework.data.mongodb.core.query.Criteria;import org.springframework.data.mongodb.core.query.Query;import org.springframework.stereotype.Component;import com.mongodb.WriteConcern;import com.uc.system.dao.DepartmentDao;import com.uc.system.exception.ZhiWeiException;import com.uc.system.model.Department;@Componentpublic class DepartmentDaoImpl extends GeneralDaoImpl implements DepartmentDao {	@Resource	private MongoTemplate mongoTemp;	@Override	public Department insert(Department ob) throws ZhiWeiException {		try {			ob.setId(Integer.valueOf(Sequence.getNextId("deparment")));			mongoTemp.save(ob);		} catch (Exception e) {			ob = null;			System.out.println("新增数据出错");		}		return ob;	}	@Override	public Department findOne(Department ob) throws ZhiWeiException {		return null;	}	@Override	public List<Department> findAll() throws ZhiWeiException {		return mongoTemp.findAll(Department.class);	}	@Override	public long findCont() throws ZhiWeiException {		return 0;	}	@Override	public boolean removeOneById(String id) throws ZhiWeiException {		boolean flag = false;		Query query = new Query();		query.addCriteria(Criteria.where("_id").is(Integer.valueOf(id)));		try {			mongoTemp.setWriteConcern(WriteConcern.SAFE);			mongoTemp.remove(query, Department.class);			mongoTemp.setWriteConcern(WriteConcern.NORMAL);			flag = true;		} catch (Exception e) {			throw new ZhiWeiException(e.getMessage());		}		return flag;	}	@Override	public boolean removeAll() throws ZhiWeiException {		return false;	}	@Override	public boolean findAndModify(Department ob) throws ZhiWeiException {		boolean flag = false;		return flag;	}	@Override	public List<Department> findListWithLimitAndSkip(int skip, int limit)			throws ZhiWeiException {		List<Department> list = new ArrayList<Department>();		Query query = new Query();		query.skip(skip);		query.limit(limit);		list = mongoTemp.find(query, Department.class);		return list;	}}