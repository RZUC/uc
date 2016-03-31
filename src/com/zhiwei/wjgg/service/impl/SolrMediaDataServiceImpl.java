package com.zhiwei.wjgg.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.dao.SolrMediaDataDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.HUserInfoBD;
import com.zhiwei.wjgg.model.SolrMedia;
import com.zhiwei.wjgg.service.SolrMediaDataService;
import com.zhiwei.wjgg.solr.model.DataMedia;
import com.zhiwei.wjgg.solr.service.impl.SolrDataCommonService;
import com.zhiwei.wjgg.util.SolrDocumentToBeanUtil;

/**
 * @author cwt
 * @date 2016-03-07
 */
@Service("solrMediaServiceImpl")
public class SolrMediaDataServiceImpl extends GeneralServiceImpl implements
        SolrMediaDataService
{
    private static final SimpleDateFormat TIME_FORMATE = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    @Resource(name = "solrMediaService")
    SolrDataCommonService<HUserInfoBD> serviceMedia;
    
    @Resource
    private SolrMediaDataDao dao;

    @Override
    public boolean add(SolrMedia ob)
    {
        try
        {
            SolrMedia new_ob = dao.insert(ob);
            if (null != new_ob)
            {
                return true;
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrMedia登记出错：{}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean add(List<SolrMedia> obList)
    {
        List<SolrMedia> new_ob;
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
            log.error("SolrMedia多个登记出错：{}", e.getMessage());
        }
        

        return false;
    }

    @Override
    public boolean updata(SolrMedia ob)
    {
        boolean flag = false;
        try
        {
            flag = dao.findAndModify(ob);
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrMedia更新出错：{}", e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean delete(SolrMedia ob)
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
            log.error("SolrMedia删除出错：{}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean search(SolrMedia ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public SolrMedia findOneById(String id)
    {
        SolrMedia ob = null;
        try
        {
            ob = dao.findOne(id);
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrMedia查询出错：{}", e.getMessage());
        }
        return ob;
    }

    @Override
    public List<SolrMedia> findAll(Object... ob)
    {
        List<SolrMedia> list = null;
        try
        {
            list = dao.findAll();
        }
        catch (ZhiWeiException e)
        {
            list = new ArrayList<SolrMedia>();
            log.error("SolrMedia查询出错：{}", e.getMessage());
        }
        return list;
    }

    @Override
    public List<SolrMedia> findInTime(String time) throws ParseException
    {
        String startTime = time +":00:00";
        String endTime = time +":59:59";
        
        Date start = TIME_FORMATE.parse(startTime);
        Date end = TIME_FORMATE.parse(endTime);
        List<SolrMedia> oblist = null;
        try
        {
            oblist = dao.findInTime(start, end);
        }
        catch (ZhiWeiException e)
        {
            oblist = new ArrayList<SolrMedia>();
            log.error("SolrMedia查询出错：{}", e.getMessage());
        }
        return oblist;
    }

    @Override
    public List<SolrMedia> insert(List<SolrMedia> obList)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SolrMedia> findObByIdList(List<String> ids)
    {
        try
        {
            return dao.findObByIdList(ids);
        }
        catch (ZhiWeiException e)
        {
            log.error("SolrMedia查询出错：{}", e.getMessage());
        }
        return null;
    }

}