/*** *************************************************** * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. * ***************************************************** *  **/package com.zhiwei.uc.system.dao.impl;import java.util.List;import org.springframework.data.mongodb.core.query.Criteria;import org.springframework.data.mongodb.core.query.Query;import org.springframework.stereotype.Component;import com.zhiwei.uc.system.dao.LogDao;import com.zhiwei.uc.system.exception.ZhiWeiException;import com.zhiwei.uc.system.model.Log;/** * @author 落花流水 * @date 2016-02-23 */@Componentpublic class LogDaoImpl extends GeneralDaoImpl implements LogDao{    @Override    public Log insert(Log ob)        throws ZhiWeiException    {        return null;    }        @Override    public Log findOne(String id)        throws ZhiWeiException    {        return mongoTemp.findById(id, Log.class);    }        @Override    public Log findOne(Log ob)        throws ZhiWeiException    {        return null;    }        @Override    public List<Log> findAll()        throws ZhiWeiException    {        return mongoTemp.findAll(Log.class);    }        @Override    public boolean removeOneById(String id)        throws ZhiWeiException    {        return false;    }        @Override    public boolean removeAll()        throws ZhiWeiException    {        mongoTemp.remove(new Query(), Log.class);        return true;    }        @Override    public boolean findAndModify(Log ob)        throws ZhiWeiException    {        return false;    }        @Override    public List<Log> findAllLogByType(String type)    {        List<Log> logs;        Query query = new Query();        query.addCriteria(Criteria.where("type").is(type));        try        {            logs = mongoTemp.find(query, Log.class);            return logs;        }        catch (Exception e)        {            e.printStackTrace();        }        return null;    }        @Override    public List<Log> findAllLogByTime(String startTime, String endTime)    {        List<Log> logs;        Query query = new Query();        query.addCriteria(Criteria.where("addTime").gte(startTime));        query.addCriteria(Criteria.where("addTime").lte(endTime));        try        {            logs = mongoTemp.find(query, Log.class);            return logs;        }        catch (Exception e)        {            e.printStackTrace();        }        return null;    }        @Override    public List<Log> findAllLogByTimeWithType(String startTime, String endTime, String type)    {        List<Log> logs;        Query query = new Query();        query.addCriteria(Criteria.where("addTime").gte(startTime));        query.addCriteria(Criteria.where("addTime").lte(endTime));        query.addCriteria(Criteria.where("type").is(type));        try        {            logs = mongoTemp.find(query, Log.class);            return logs;        }        catch (Exception e)        {            e.printStackTrace();        }        return null;    }        @Override    public void addLog(Log log)        throws ZhiWeiException    {        try        {            mongoTemp.save(log);        }        catch (Exception e)        {            throw new ZhiWeiException(e.getMessage());        }            }        @Override    public long findCont()        throws ZhiWeiException    {        // TODO Auto-generated method stub        return 0;    }    }