package com.zhiwei.wjgg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.dao.EventTypeDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.EventType;
import com.zhiwei.wjgg.service.EventService;
import com.zhiwei.wjgg.service.EventTypeService;

/**
 * @author 落花流水
 * @date 2016-02-26
 */

@Service
public class EventTypeServiceImpl extends GeneralServiceImpl implements
        EventTypeService
{

    @Resource
    private EventTypeDao dao;
    
    @Resource
    EventService eventService;

    @Override
    public boolean add(EventType ob)
    {
        try
        {
            EventType new_ob = dao.insert(ob);
            if (null != new_ob)
            {
                return true;
            }
        } catch (ZhiWeiException e)
        {
            log.error("事件类型登记出错：{}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updata(EventType ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(EventType ob)
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
            log.error("事件类型删除出错：{}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteById(String id,String userId)
    {
        boolean result = false;
        try
        {
            long cont = dao.findCont();
            EventType eventType= dao.findOne(id);
            dao.removeOneById(id);
            
            List<Event> eventsList = eventService.findEventsByType(eventType.getEventType(),userId);
            for (Event event : eventsList)
            {
                eventService.deleteById(event.getId());
            }
            
            if (cont - dao.findAll().size() > 0)
            {
                result = true;
            }
        } catch (ZhiWeiException e)
        {
            log.error("事件类型删除出错：{}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean search(EventType ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public EventType findOneById(String id)
    {
        EventType ob = null;
        try
        {
            ob = dao.findOne(id);
        } catch (ZhiWeiException e)
        {
            log.error("事件类型查询出错：{}", e.getMessage());
        }
        return ob;
    }

    @Override
    public List<EventType> findAll(Object... ob)
    {
        List<EventType> lists = null;
        try
        {
            lists = dao.findAll();
        } catch (ZhiWeiException e)
        {
            log.error("事件类型查询所有出错：{}", e.getMessage());
        }

        return lists;
    }

    @Override
    public boolean deleteById(String id)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<EventType> findByUser(String user)
    {
        List<EventType> lists = null;
        try
        {
            lists = dao.findByUser(user);
        } catch (Exception e)
        {
            log.info("事件类型查询所有出错：{}", e.getMessage());
        }

        return lists;
    }

}