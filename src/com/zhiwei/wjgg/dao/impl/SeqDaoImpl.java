package com.zhiwei.wjgg.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.WriteResult;
import com.zhiwei.wjgg.dao.SeqDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Seq;

@Component
public class SeqDaoImpl extends GeneralDaoImpl implements SeqDao
{

    @Override
    public Seq insert(Seq ob) throws ZhiWeiException
    {
        try
        {
            mongoTemp.insert(ob);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return ob;
    }

    @Override
    public Seq findOne(String id) throws ZhiWeiException
    {
        Seq ob;
        try
        {
            ob = mongoTemp.findOne(new Query(Criteria.where("_id").is(id)),
                    Seq.class);
            return ob;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;}

    @Override
    public Seq findOne(Seq ob) throws ZhiWeiException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Seq> findAll() throws ZhiWeiException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long findCont() throws ZhiWeiException
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean removeOneById(String id) throws ZhiWeiException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll() throws ZhiWeiException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean findAndModify(Seq ob) throws ZhiWeiException
    {
        boolean result = false;
        WriteResult res;
        try
        {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(ob.getId()));
            Update update = new Update();
            update.set("seq", ob.getSeq());
            res = mongoTemp.upsert(query, update, Seq.class);
            // mongoTemplate.findAndModify(new
            // Query(Criteria.where("_id").is(ob.getId())),update, new
            // FindAndModifyOptions().upsert(true).returnNew(true),Carwler.class);
            result = true;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getNextId(String seq_name)
    {
        
        Seq ob = mongoTemp.findOne(new Query(Criteria.where("_id").is(seq_name)),
                Seq.class);
        long seq = ob.getSeq();
        
        ob.setSeq(seq+1);
        
        mongoTemp.save(ob);
        
        return seq+"";
    }

}
