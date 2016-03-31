/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年1月15日
    * @version 1.00 
*/
package com.zhiwei.wjgg.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.zhiwei.wjgg.dao.PermissionDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Permission;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: UserDaoImpl
 * @author 落花流水
 * @date 2016年1月15日 下午1:51:45
 */
@Component
public class PermissionDaoImpl extends GeneralDaoImpl implements PermissionDao
{
    
    /*
     * (非 Javadoc) <p>Title:insert</p> <p>Description: </p>
     * 
     * @param ob
     * 
     * @return
     * 
     * @throws ZhiWeiException
     * 
     * @see com.zhiwei.manager.dao.CommonDao#insert(java.lang.Object)
     */
    @Override
    public Permission insert(Permission psersion)
        throws ZhiWeiException
    {
        try
        {
            mongoTemp.save(psersion);
            return psersion;
        }
        catch (Exception e)
        {
            return null;
        }
        
    }
    
    /*
     * (非 Javadoc) <p>Title:findOne</p> <p>Description: </p>
     * 
     * @param id
     * 
     * @return
     * 
     * @throws ZhiWeiException
     * 
     * @see com.zhiwei.manager.dao.CommonDao#findOne(java.lang.String)
     */
    @Override
    public Permission findOne(String id)
        throws ZhiWeiException
    {
        try
        {
            return mongoTemp.findOne(new Query(Criteria.where("_id").is(id)), Permission.class);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    /*
     * (非 Javadoc) <p>Title:findOne</p> <p>Description: </p>
     * 
     * @param ob
     * 
     * @return
     * 
     * @throws ZhiWeiException
     * 
     * @see com.zhiwei.manager.dao.CommonDao#findOne(java.lang.Object)
     */
    @Override
    public Permission findOne(Permission ob)
        throws ZhiWeiException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (非 Javadoc) <p>Title:findAll</p> <p>Description: </p>
     * 
     * @return
     * 
     * @throws ZhiWeiException
     * 
     * @see com.zhiwei.manager.dao.CommonDao#findAll()
     */
    @Override
    public List<Permission> findAll()
        throws ZhiWeiException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public boolean removeOneById(String id)
        throws ZhiWeiException
    {
        return false;
    }
    
    @Override
    public boolean removeAll()
        throws ZhiWeiException
    {
        return false;
    }
    
    @Override
    public boolean findAndModify(Permission ob)
        throws ZhiWeiException
    {
        return false;
    }

	@Override
	public long findCont() throws ZhiWeiException {
		// TODO Auto-generated method stub
		return 0;
	}
}
