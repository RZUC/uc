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
package com.uc.system.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.uc.system.dao.FavoriteDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Favorite;
import com.uc.system.util.MongoQuerBeanTO;

@Component
public class FavoriteDaoImple extends GeneralDaoImpl implements FavoriteDao
{
    
    @Override
    public Favorite insert(Favorite ob)
        throws ZhiWeiException
    {
        ob.setId(Integer.valueOf(Sequence.getNextId("favorite")));
        mongoTemp.save(ob);
        return ob;
    }
    
    @Override
    public Favorite findOne(Favorite ob)
        throws ZhiWeiException
    {
        DBObject obj = MongoQuerBeanTO.change(ob);
        
        Query query = new Query();
        
        for (String key : obj.keySet())
        {
            if (null != obj.get(key))
            {
                query.addCriteria(Criteria.where(key).is(obj.get(key)));
            }
        }
        Favorite f = mongoTemp.findOne(query, Favorite.class);
        return f;
    }
    
    @Override
    public List<Favorite> findAll()
        throws ZhiWeiException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public long findCont()
        throws ZhiWeiException
    {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public boolean removeOneById(String id)
        throws ZhiWeiException
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));
        mongoTemp.setWriteConcern(WriteConcern.SAFE);
        WriteResult result = mongoTemp.remove(query, Favorite.class);
        mongoTemp.setWriteConcern(WriteConcern.NORMAL);
        if (result.getN() == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public boolean removeAll()
        throws ZhiWeiException
    {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public boolean findAndModify(Favorite ob)
        throws ZhiWeiException
    {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public List<Favorite> findAllByUid(int uid)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("uid").is(uid));
        List<Favorite> list = mongoTemp.find(query, Favorite.class);
        return list;
    }
    
}
