package com.zhiwei.wjgg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.dao.NatureKeyPointDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.NatureKeyPoint;
import com.zhiwei.wjgg.service.NatureKeyPointService;
import com.zhiwei.wjgg.util.MapUtil;

/**
 * @author cwt
 * @date 2016-02-29
 */
@Service
public class NatureKeyPointServiceImpl extends GeneralServiceImpl implements
        NatureKeyPointService
{

    @Resource
    private NatureKeyPointDao dao;

    @Override
    public boolean add(NatureKeyPoint ob)
    {

        try
        {
            NatureKeyPoint new_ob = dao.insert(ob);
            if (null != new_ob)
            {
                return true;
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("登记出错：{}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updata(NatureKeyPoint ob)
    {
        boolean result = false;
        try
        {
            result = dao.findAndModify(ob);
        }
        catch (ZhiWeiException e)
        {
            log.error("更新出错：{}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean delete(NatureKeyPoint ob)
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
            log.error("删除出错：{}", e.getMessage());
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
            log.error("删除出错：{}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean search(NatureKeyPoint ob)
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
            log.error("查询出错：{}", e.getMessage());
        }
        return flag;
    }

    @Override
    public NatureKeyPoint findOneById(String id)
    {
        NatureKeyPoint ob = null;
        try
        {
            ob = dao.findOne(id);
        }
        catch (ZhiWeiException e)
        {
            log.error("查询出错：{}", e.getMessage());
        }
        return ob;
    }

    @Override
    public List<NatureKeyPoint> findAll(Object... ob)
    {
        try
        {
            return dao.findAll();
        }
        catch (ZhiWeiException e)
        {
            log.error("查询出错：{}", e.getMessage());
        }
        return null;
    }

    @Override
    public NatureKeyPoint findByEventIdInTime(String eventId, String type)
    {
        NatureKeyPoint ob = null;
        ob = dao.findByEventIdInTime(eventId, type);
        if (ob != null)
        {
            if (ob.getTime_content().size() > 0)
            {
                MapUtil.treatOrderByKeyDescInStr(ob.getTime_content());
            }
        }

        return ob;
    }

    @Override
    public boolean addInfoByOb(NatureKeyPoint ob)
    {
        boolean flag = false;
        try
        {
            NatureKeyPoint ob_old = dao.findOne(ob);
            if (null == ob_old)
            {
                dao.insert(ob);
                flag = true;
            }
            else
            {
                Map<String, List<String>> map = ob_old.getTime_content();
                for (Entry<String, List<String>> en : ob.getTime_content()
                        .entrySet())
                {
                    // 若源记录中存在该时间点的记录
                    if (map.containsKey(en.getKey()))
                    {
                        List<String> old_content = map.get(en.getKey());
                        old_content.addAll(en.getValue());
                        map.put(en.getKey(), old_content);
                    }
                    else
                    {
                        map.put(en.getKey(), en.getValue());
                    }

                }
                ob_old.setTime_content(map);
                dao.findAndModify(ob_old);
                flag = true;
            }

        }
        catch (ZhiWeiException e)
        {
            log.error("添加出错：{}", e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean deleteInfoByOb(NatureKeyPoint ob)
    {
        boolean flag = false;
        try
        {
            NatureKeyPoint ob_old = dao.findOne(ob);
            if (null != ob_old)
            {
                Map<String, List<String>> time_content = ob_old
                        .getTime_content();
                for (Entry<String, List<String>> en : ob.getTime_content()
                        .entrySet())
                {
                    // 获取源数据时间点里的内容列表
                    List<String> old_content = time_content.get(en.getKey());
                    // 要删除的内容在列表中的下标
                    List<Integer> removeIndex = new ArrayList<Integer>();
                    for (String content : old_content)
                    {
                        for (String remove_content : en.getValue())
                        {
                            if (content.equals(remove_content))
                            {
                                removeIndex.add(old_content
                                        .indexOf(remove_content));
                            }

                        }
                    }

                    // 删除该下标的内容
                    for (Integer index : removeIndex)
                    {
                        old_content.remove(old_content.get(index));
                    }
                    // 若删除后无内容则删除该时间点
                    if (0 == old_content.size())
                    {
                        time_content.remove(en.getKey());
                    }
                    else
                    {
                        Map<String, List<String>> map = new HashMap<String, List<String>>();
                        map.put(en.getKey(), old_content);
                        time_content.putAll(map);
                    }

                }
                ob_old.setTime_content(time_content);
                flag = dao.findAndModify(ob_old);
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("NatureKeyPoint删除信息出错：{}", e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean deleteByEventId(String eventId)
    {
        boolean result = false;
        try
        {
            long cont = dao.findCont();
            dao.deleteByEventId(eventId);
            if (cont - dao.findCont() > 0)
            {
                result = true;
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("删除出错：{}", e.getMessage());
        }
        return result;
    }

}