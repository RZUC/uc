package com.zhiwei.wjgg.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.dao.EventDao;
import com.zhiwei.wjgg.dao.NatureTrendDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.NatureTrend;
import com.zhiwei.wjgg.service.NatureTrendService;
import com.zhiwei.wjgg.util.MapUtil;
import com.zhiwei.wjgg.util.TimeUtil;

/**
 * @author cwt
 * @date 2016-02-29
 */
@Service
public class NatureTrendServiceImpl extends GeneralServiceImpl implements
        NatureTrendService
{

    private static SimpleDateFormat sdf_ss = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat sdf_HH = new SimpleDateFormat(
            "yyyy-MM-dd HH");

    @Resource
    private EventDao eventDao;

    @Resource
    private NatureTrendDao dao;

    @Override
    public boolean add(NatureTrend ob)
    {

        try
        {
            if (null != dao.findOne(ob))
            {
                return false;
            }
            NatureTrend new_ob = dao.insert(ob);
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
    public boolean updata(NatureTrend ob)
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
    public boolean delete(NatureTrend ob)
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
    public boolean search(NatureTrend ob)
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
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public NatureTrend findOneById(String id)
    {
        NatureTrend ob = null;
        try
        {
            ob = dao.findOne(id);
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
        return ob;
    }

    @Override
    public List<NatureTrend> findAll(Object... ob)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NatureTrend findByEventIdInTime(String eventId, String type)
    {
        NatureTrend ob = null;
        try
        {
            ob = dao.findByEventIdInTime(eventId, type);
            if (ob != null)
            {
                if (ob.getTime_num() != null)
                {
                    // 获取到事件，为获取开始时间与结束时间
                    Event event = eventDao.findOne(eventId);

                    long start = sdf_ss.parse(event.getStartTime()).getTime();
                    long end = sdf_ss.parse(event.getEndTime()).getTime();
                    long now = System.currentTimeMillis();
                    long than = now < end ? now : end;
                    //创建完整时间点
                    List<String> times = TimeUtil.getTimePointToNowOrEnd(start, than);
                    
                    Map<String,Double> time_info = new LinkedMap();
                    for (String time : times)
                    {
                        if (ob.getTime_num().containsKey(time))
                        {
                            time_info.put(time, ob.getTime_num().get(time));
                        }
                        else
                        {
                            time_info.put(time,0.0);
                        }
                    }
                    ob.setTime_num(time_info);
                }

            }
        }
        catch (Exception e)
        {
        }

        return ob;
    }

    @Override
    public boolean addInfoByOb(NatureTrend ob)
    {
        boolean flag = false;

        try
        {
            dao.findAndModify(ob);
            flag = true;
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
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