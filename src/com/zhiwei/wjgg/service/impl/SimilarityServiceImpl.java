package com.zhiwei.wjgg.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.dao.SimilarityDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Similarity;
import com.zhiwei.wjgg.service.SimilarityService;
import com.zhiwei.wjgg.util.MapUtil;

/**
 * @author cwt
 * @date 2016-03-08
 */
@Service
public class SimilarityServiceImpl extends GeneralServiceImpl implements
        SimilarityService
{

    @Resource
    private SimilarityDao dao;

    @Override
    public boolean add(Similarity ob)
    {
        boolean result = false;
        try
        {
            long cont = dao.findCont();
            dao.insert(ob);
            if (cont - dao.findCont() < 0)
            {
                result = true;
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("Similarity添加出错：{}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean updata(Similarity ob)
    {
        boolean result = false;
        try
        {
            result = dao.findAndModify(ob);
        }
        catch (ZhiWeiException e)
        {
            log.error("Similarity更新出错：{}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean delete(Similarity ob)
    {
        boolean result = false;
        try
        {
            long cont = dao.findCont();
            dao.removeOneById(ob.getId());
            if (cont - dao.findCont() > 0)
            {
                result = true;
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("Similarity删除出错：{}", e.getMessage());
        }
        return result;
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
            log.error("Similarity删除出错：{}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean search(Similarity ob)
    {
        boolean flag = false;
        try
        {
            if (null != dao.findOne(ob))
            {
                flag = true;
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("Similarity查询出错：{}", e.getMessage());
        }
        return flag;
    }

    @Override
    public Similarity findOneById(String id)
    {
        Similarity ob = null;
        try
        {
            ob = dao.findOne(id);
        }
        catch (ZhiWeiException e)
        {
            log.error("Similarity查询出错：{}", e.getMessage());
        }
        return ob;
    }

    @Override
    public List<Similarity> findAll(Object... ob)
    {
        List<Similarity> list = null;
        try
        {
            list = dao.findAll();
        }
        catch (ZhiWeiException e)
        {
            list = new ArrayList<Similarity>();
            log.error("Similarity查询出错：{}", e.getMessage());
        }
        return list;
    }

    @Override
    public boolean addInfoByOb(Similarity ob)
    {
        boolean flag = false;
        try
        {
            Similarity old_ob = dao.findOne(ob.getId());

            if (null != old_ob)
            {
                List<Map<String, Integer>> meaidaList = new ArrayList<Map<String, Integer>>();
                List<Map<String, Integer>> weixinList = new ArrayList<Map<String, Integer>>();
                if (null != old_ob.getMedia())
                {
                    meaidaList.add(old_ob.getMedia());
                }
                if (null != ob.getMedia())
                {
                    meaidaList.add(ob.getMedia());
                }
                if (null != old_ob.getWeixin())
                {
                    weixinList.add(old_ob.getWeixin());
                }
                if (null != ob.getWeixin())
                {
                    weixinList.add(ob.getWeixin());
                }

                ob.setMedia(MapUtil.addMapByKeyForInteger(meaidaList));
                ob.setWeixin(MapUtil.addMapByKeyForInteger(weixinList));

                flag = dao.findAndModify(ob);
            }
            else
            {
                long cont = dao.findCont();
                dao.insert(ob);
                if (cont - dao.findCont() < 0)
                {
                    flag = true;
                }
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("Similarity添加出错：{}", e.getMessage());
        }
        return flag;
    }
}