/**
 * 
 */
package com.zhiwei.wjgg.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.zhiwei.wjgg.dao.ViewPointDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.SolrMedia;
import com.zhiwei.wjgg.model.ViewPoint;

/**
 * @Description
 * 
 * @author 李自贤
 * @date 2016年2月29日
 */
@Component
public class ViewPointDaoImpl extends GeneralDaoImpl implements ViewPointDao
{
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.dao.CommonDao#insert(java.lang.Object)
     */
    @Override
    public ViewPoint insert(ViewPoint ob)
        throws ZhiWeiException
    {
        ob.setId(ob.getEventId() + ob.getType());
        ob.setSaveTime(sdf1.format(new Date()));
        mongoTemp.insert(ob);
        return ob;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.dao.CommonDao#findOne(java.lang.String)
     */
    @Override
    public ViewPoint findOne(String id)
        throws ZhiWeiException
    {
        ViewPoint ob;
        try
        {
            ob = mongoTemp.findOne(new Query(Criteria.where("_id").is(id)), ViewPoint.class);
            return ob;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.dao.CommonDao#findOne(java.lang.Object)
     */
    @Override
    public ViewPoint findOne(ViewPoint ob)
        throws ZhiWeiException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.dao.CommonDao#findAll()
     */
    @Override
    public List<ViewPoint> findAll()
        throws ZhiWeiException
    {
        List<ViewPoint> list = null;
        try
        {
            list = mongoTemp.find(null, ViewPoint.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.dao.CommonDao#findCont()
     */
    @Override
    public long findCont()
        throws ZhiWeiException
    {
        return mongoTemp.count(null, ViewPoint.class);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.dao.CommonDao#removeOneById(java.lang.String)
     */
    @Override
    public boolean removeOneById(String id)
        throws ZhiWeiException
    {
        boolean result = false;
        try
        {
            mongoTemp.remove(new Query(Criteria.where("_id").is(id)), ViewPoint.class);
            result = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.dao.CommonDao#removeAll()
     */
    @Override
    public boolean removeAll()
        throws ZhiWeiException
    {
        return false;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.dao.CommonDao#findAndModify(java.lang.Object)
     */
    @Override
    public boolean findAndModify(ViewPoint ob)
        throws ZhiWeiException
    {
        boolean result = false;
        WriteResult res;
        try
        {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(ob.getId()));
            Update update = new Update();
            update.set("eventId", ob.getEventId());
            update.set("pointMap", ob.getPointMap());
            update.set("saveTime", sdf1.format(new Date()));
            update.set("type", ob.getType());
            
            res = mongoTemp.upsert(query, update, ViewPoint.class);
            result = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public ViewPoint findOneByEventIdAndType(String eventId, String type)
    {
        ViewPoint ob;
        try
        {
            Query query = new Query();
            query.addCriteria(Criteria.where("eventId").is(eventId));
            query.addCriteria(Criteria.where("type").is(type));
            ob = mongoTemp.findOne(query, ViewPoint.class);
            return ob;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
}
