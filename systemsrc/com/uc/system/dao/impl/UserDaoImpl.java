/*** *************************************************** * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. * ***************************************************** *  **/

package com.uc.system.dao.impl;

import java.util.List;import javax.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.uc.system.dao.UserDao;import com.uc.system.exception.ZhiWeiException;import com.uc.user.model.User;

/**
 * @author cwt
 * @date 2016-04-03
 */
public class UserDaoImpl implements UserDao
{
    @Resource
    private MongoTemplate mongoTemp;	/* (non-Javadoc)	 * @see com.uc.system.dao.CommonDao#insert(java.lang.Object)	 */	@Override	public User insert(User ob) throws ZhiWeiException {		// TODO Auto-generated method stub		return null;	}	/* (non-Javadoc)	 * @see com.uc.system.dao.CommonDao#findOne(java.lang.String)	 */	@Override	public User findOne(String id) throws ZhiWeiException {		// TODO Auto-generated method stub		return null;	}	/* (non-Javadoc)	 * @see com.uc.system.dao.CommonDao#findOne(java.lang.Object)	 */	@Override	public User findOne(User ob) throws ZhiWeiException {		// TODO Auto-generated method stub		return null;	}	/* (non-Javadoc)	 * @see com.uc.system.dao.CommonDao#findAll()	 */	@Override	public List<User> findAll() throws ZhiWeiException {		// TODO Auto-generated method stub		return null;	}	/* (non-Javadoc)	 * @see com.uc.system.dao.CommonDao#findCont()	 */	@Override	public long findCont() throws ZhiWeiException {		// TODO Auto-generated method stub		return 0;	}	/* (non-Javadoc)	 * @see com.uc.system.dao.CommonDao#removeOneById(java.lang.String)	 */	@Override	public boolean removeOneById(String id) throws ZhiWeiException {		// TODO Auto-generated method stub		return false;	}	/* (non-Javadoc)	 * @see com.uc.system.dao.CommonDao#removeAll()	 */	@Override	public boolean removeAll() throws ZhiWeiException {		// TODO Auto-generated method stub		return false;	}	/* (non-Javadoc)	 * @see com.uc.system.dao.CommonDao#findAndModify(java.lang.Object)	 */	@Override	public boolean findAndModify(User ob) throws ZhiWeiException {		// TODO Auto-generated method stub		return false;	}

}