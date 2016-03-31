package com.zhiwei.wjgg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.dao.EventLevelDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.EventLevel;
import com.zhiwei.wjgg.service.EventLevelService;

/**
 * @author 落花流水
 * @date 2016-02-26
 */

@Service
public class EventLevelServiceImpl extends GeneralServiceImpl implements
        EventLevelService
{

    @Resource
    private EventLevelDao dao;

    @Override
    public boolean add(EventLevel ob)
    {
        try
        {
            EventLevel new_ob = dao.insert(ob);
            if (null != new_ob)
            {
                return true;
            }
        } catch (ZhiWeiException e)
        {
            log.error("事件危机等级登记出错：{}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updata(EventLevel ob)
    {
        boolean result = false;
        try
        {
            result = dao.findAndModify(ob);
        } catch (ZhiWeiException e)
        {
            log.error("事件危机等级更新出错：{}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean delete(EventLevel ob)
    {
        boolean result = false;
        try
        {
            long cont = dao.findCont();
            dao.removeOneById(ob.getId());
            if (cont - dao.findAll().size() > 0)
            {
                result = true;
            }
        } catch (ZhiWeiException e)
        {
            log.error("事件危机等级出错：{}", e.getMessage());
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
            if (cont - dao.findAll().size() > 0)
            {
                result = true;
            }
        } catch (ZhiWeiException e)
        {
            log.error("事件危机等级删除出错：{}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean search(EventLevel ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public EventLevel findOneById(String id)
    {
        EventLevel ob = null;
        try
        {
            ob = dao.findOne(id);
        } catch (ZhiWeiException e)
        {
            log.error("事件危机等级查询出错：{}", e.getMessage());
        }
        return ob;
    }

    @Override
    public List<EventLevel> findAll(Object... ob)
    {
        List<EventLevel> lists = null;
        try
        {
            lists = dao.findAll();
        } catch (ZhiWeiException e)
        {
            log.error("事件危机等级查询所有出错：{}", e.getMessage());
        }

        return lists;
    }

}