package com.zhiwei.wjgg.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.dao.SolrWeixinDataDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.SolrWeixin;
import com.zhiwei.wjgg.service.SolrWeixinDataService;

/**
 * @author cwt
 * @date 2016-03-07
 */

@Service("solrWeixinServiceImpl")
public class SolrWeixinDataServiceImpl extends GeneralServiceImpl implements
        SolrWeixinDataService
{
    private static final SimpleDateFormat TIME_FORMATE = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    @Resource
    private SolrWeixinDataDao dao;

    @Override
    public boolean add(SolrWeixin ob)
    {
        try
        {
            SolrWeixin new_ob = dao.insert(ob);
            if (null != new_ob)
            {
                return true;
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrWeixin登记出错：{}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updata(SolrWeixin ob)
    {
        boolean flag = false;
        try
        {
            flag = dao.findAndModify(ob);
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrWeixin更新出错：{}", e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean delete(SolrWeixin ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(String id)
    {
        boolean result = false;
        try
        {
            long cont = dao.findCont();
            dao.removeOneById(id);
            if (cont - dao.findCont() > 0)
            {
                result = true;
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrWeixin删除出错：{}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean search(SolrWeixin ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public SolrWeixin findOneById(String id)
    {
        SolrWeixin ob = null;
        try
        {
            ob = dao.findOne(id);
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrWeixin查询出错：{}", e.getMessage());
        }
        return ob;
    }

    @Override
    public List<SolrWeixin> findAll(Object... ob)
    {
        List<SolrWeixin> list = null;
        try
        {
            list = dao.findAll();
        }
        catch (ZhiWeiException e)
        {
            list = new ArrayList<SolrWeixin>();
            log.error("SolrWeixin查询出错：{}", e.getMessage());
        }
        return list;
    }

    @Override
    public boolean add(List<SolrWeixin> obList)
    {
        List<SolrWeixin> new_ob;
        try
        {
            new_ob = dao.insert(obList);
            if (null != new_ob)
            {
                return true;
            }

        }
        catch (ZhiWeiException e)
        {
            log.error("SolrWeixin查询出错：{}", e.getMessage());
        }
        
        return false;
    }

    @Override
    public List<SolrWeixin> findInTime(String time) throws ParseException
    {
        String startTime = time +":00:00";
        String endTime = time +":59:59";
        
        Date start = TIME_FORMATE.parse(startTime);
        Date end = TIME_FORMATE.parse(endTime);
        List<SolrWeixin> oblist = null;
        try
        {
            oblist = dao.findInTime(start, end);
        }
        catch (ZhiWeiException e)
        {
            oblist = new ArrayList<SolrWeixin>();
            log.error("SolrWeixin查询出错：{}", e.getMessage());
        }
        return oblist;
    }

    @Override
    public List<SolrWeixin> findObByIdList(List<String> ids)
    {
        try
        {
            return dao.findObByIdList(ids);
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrWeixin查询出错：{}", e.getMessage());
        }
        return null;
    }

}