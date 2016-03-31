package com.zhiwei.wjgg.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.dao.SolrWeiboDataDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.SolrWeibo;
import com.zhiwei.wjgg.service.SolrWeiboDataService;

/**
 * @author cwt
 * @date 2016-03-07
 */

@Service("solrWeiboServiceImpl")
public class SolrWeiboDataServiceImpl extends GeneralServiceImpl implements
        SolrWeiboDataService
{

    @Resource
    private SolrWeiboDataDao dao;

    @Override
    public boolean add(SolrWeibo ob)
    {
        try
        {
            SolrWeibo new_ob = dao.insert(ob);
            if (null != new_ob)
            {
                return true;
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrWeibo登记出错：{}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updata(SolrWeibo ob)
    {
        boolean flag = false;
        try
        {
            flag = dao.findAndModify(ob);
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrWeibo更新出错：{}", e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean delete(SolrWeibo ob)
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
            if (cont - dao.findCont()> 0)
            {
                result = true;
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrWeibo删除出错：{}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean search(SolrWeibo ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public SolrWeibo findOneById(String id)
    {
        SolrWeibo ob = null;
        try
        {
            ob = dao.findOne(id);
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrWeibo查询出错：{}", e.getMessage());
        }
        return ob;
    }

    @Override
    public List<SolrWeibo> findAll(Object... ob)
    {
        List<SolrWeibo> list = null;
        try
        {
            list = dao.findAll();
        }
        catch (ZhiWeiException e)
        {
            list = new ArrayList<SolrWeibo>();
            log.error("SolrWeibo查询出错：{}", e.getMessage());
        }
        return list;
    }

    @Override
    public boolean add(List<SolrWeibo> obList)
    {
        List<SolrWeibo> new_ob;
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
            log.error("SolrWeibo查询出错：{}", e.getMessage());
        }
        
        return false;
    }

    @Override
    public List<SolrWeibo> findInTime(String time) throws ParseException
    {
        String startTime = time +":00:00";
        String endTime = time +":59:59";
        
        List<SolrWeibo> oblist = null;
        try
        {
            oblist = dao.findInTime(startTime, endTime);
        }
        catch (ZhiWeiException e)
        {
            oblist = new ArrayList<SolrWeibo>();
            log.error("SolrWeibo查询出错：{}", e.getMessage());
        }
        return oblist;
    }

    @Override
    public List<SolrWeibo> insert(List<SolrWeibo> obList)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SolrWeibo> findObByIdList(List<String> ids)
    {
        try
        {
            return dao.findObByIdList(ids);
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrWeibo查询出错：{}", e.getMessage());
        }
        return null;
    }

}