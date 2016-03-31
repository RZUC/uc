package com.zhiwei.wjgg.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.analy.fenci.Fenci;
import com.zhiwei.wjgg.dao.EventDao;
import com.zhiwei.wjgg.dao.EventTrendDao;
import com.zhiwei.wjgg.dao.SolrMediaDataDao;
import com.zhiwei.wjgg.dao.SolrWeiboDataDao;
import com.zhiwei.wjgg.dao.SolrWeixinDataDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.EventTrend;
import com.zhiwei.wjgg.model.EventUpate;
import com.zhiwei.wjgg.service.EventTrendService;
import com.zhiwei.wjgg.util.MapUtil;
import com.zhiwei.wjgg.util.TimeUtil;

/**
 * 
 * @ClassName: EventTrendServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenweitao
 * @date 2016年2月29日 下午3:36:36
 */
@Service
public class EventTrendServiceImpl extends GeneralServiceImpl implements
        EventTrendService
{

    private static SimpleDateFormat sdf_ss = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat sdf_HH = new SimpleDateFormat(
            "yyyy-MM-dd HH");

    private static final String MEDIA_TYPE = "media";

    private static final String WEIBO_TYPE = "weibo";

    private static final String WEIXIN_TYPE = "weixin";

    private static final String ALL_TYPE = "all";

    @Resource
    private EventDao eventDao;

    @Resource
    private EventTrendDao eventTrendDao;

    @Resource
    private SolrWeiboDataDao solrWeiboDataDao;

    @Resource
    private SolrWeixinDataDao solrWeiXDataindao;

    @Resource
    private SolrMediaDataDao solrMediaDatadao;

    @Override
    public boolean add(EventTrend ob)
    {

        try
        {
            if (null != eventTrendDao.findOne(ob))
            {
                return false;
            }
            EventTrend new_ob = eventTrendDao.insert(ob);
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
    public boolean updata(EventTrend ob)
    {
        boolean result = false;
        try
        {
            result = eventTrendDao.findAndModify(ob);
        }
        catch (ZhiWeiException e)
        {
            log.error("更新出错：{}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean delete(EventTrend ob)
    {
        boolean result = false;
        try
        {
            long cont = eventTrendDao.findCont();
            eventTrendDao.removeOneById(ob.getId());
            if (cont - eventTrendDao.findCont() > 0)
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
            long cont = eventTrendDao.findCont();
            eventTrendDao.removeOneById(id);
            if (cont - eventTrendDao.findCont() > 0)
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
    public boolean search(EventTrend ob)
    {
        boolean flag = false;
        try
        {
            if (null != eventTrendDao.findOne(ob))
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
    public EventTrend findOneById(String id)
    {
        EventTrend ob = null;
        try
        {
            ob = eventTrendDao.findOne(id);
        }
        catch (ZhiWeiException e)
        {
            log.error("查询信息出错：{}", e.getMessage());
        }
        return ob;
    }

    @Override
    public List<EventTrend> findAll(Object... ob)
    {
        return null;
    }

    @Override
    public EventTrend findByEventIdInTime(String eventId, String type)
    {
        EventTrend ob = null;

        try
        {
            ob = eventTrendDao.findByEventIdInTime(eventId, type);

            // timeLineIdListDebug(ob);
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

                    // 为完善表现层，填充无数据时间点
                    // 创建完整时间点
                    List<String> times = TimeUtil.getTimePointToNowOrEnd(start,
                            than);

                    Map<String, List> time_info = new LinkedMap();
                    for (String time : times)
                    {
                        if (ob.getTime_num().containsKey(time))
                        {
                            time_info.put(time, ob.getTime_num().get(time));
                        }
                        else
                        {
                            time_info.put(time, new ArrayList());
                        }
                    }
                    ob.setTime_num(time_info);

                }
            }

        }
        catch (Exception e)
        {
            log.error("查询信息出错：{}", e.getMessage());
        }

        return ob;
    }

    /**
     * @Title: timeLineIdListDebug
     * @Description:debug模式测试返回的数据List
     * @param @param ob 设定文件
     * @return void 返回类型
     */
    private void timeLineIdListDebug(EventTrend ob)
    {
        Map<String, List> timeMap = ob.getTime_num();
        for (String timeLine : timeMap.keySet())
        {
            List<String> keys = timeMap.get(timeLine);
            for (String key : keys)
            {
                log.debug("【{}】未排序ID：【{}】", timeLine, key);
            }
        }
    }

    @Override
    public boolean addInfoByOb(EventTrend ob)
    {
        boolean flag = false;
        try
        {
            EventTrend ob_old = eventTrendDao.findOne(ob);
            if (null == ob_old)
            {

                long cont = eventTrendDao.findCont();
                eventTrendDao.insert(ob);
                if (cont - eventTrendDao.findCont() < 0)
                {
                    flag = true;
                }
            }
            else
            {
                // 若新加的Time_num为空则过
                if (null != ob.getTime_num())
                {
                    List<Map<String, List>> time_numList = new ArrayList<Map<String, List>>();
                    time_numList.add(ob.getTime_num());
                    if (null != ob_old.getTime_num())
                    {
                        time_numList.add(ob_old.getTime_num());
                    }
                    ob_old.setTime_num(MapUtil.addMapByKeyByList(time_numList));
                }

                // 若新加的Source为空则过
                if (null != ob.getSource())
                {
                    List<Map<String, List>> sourceList = new ArrayList<Map<String, List>>();
                    sourceList.add(ob.getSource());
                    if (null != ob_old.getSource())
                    {
                        sourceList.add(ob_old.getSource());
                    }
                    ob_old.setSource(MapUtil.addMapByKeyByList(sourceList));
                }

                setEventRsidAndInfulenceAndDataCount(ob, ob_old);

                // 每个时间点的数据去重
                Map<String, List> map = new HashMap<String, List>();
                for (Entry<String, List> en : ob_old.getTime_num().entrySet())
                {
                    Set set = new HashSet();
                    set.addAll(en.getValue());
                    List list = new ArrayList();
                    list.addAll(set);
                    map.put(en.getKey(), list);
                }
                ob_old.setTime_num(map);

                flag = eventTrendDao.findAndModify(ob_old);
            }

        }
        catch (ZhiWeiException e)
        {
            log.error("更新信息出错：{}", e.getMessage());
        }
        return flag;
    }

    /**
     * @Title: setEventRsidAndInfulenceAndDataCount
     * @Description: 设置数据更新数据
     * @param @param ob
     * @param @param ob_old 设定文件
     * @return void 返回类型
     */
    private void setEventRsidAndInfulenceAndDataCount(EventTrend ob,
            EventTrend ob_old)
    {
        long newRsid = ob.getLastRsid();
        int dataCount = ob.getDataCount() + ob_old.getDataCount();
        double newInfulence = ob.getInfulence() + ob_old.getInfulence();

        if (newRsid != 0)
        {
            log.debug("\n当前线程：{} 原始RSID:{}\t新的Rsid:{}", Thread.currentThread()
                    .getName(), ob_old.getLastRsid(), ob.getLastRsid());
            ob_old.setLastRsid(newRsid);
        }

        if (ob.getDataCount() != 0)
        {
            log.debug("\n【当前线程：{} 原始DataCount:{}\t 新的DataCount:{}----更新后的：{}】",
                    Thread.currentThread().getName(), ob_old.getDataCount(),
                    ob.getDataCount(), dataCount);

            ob_old.setDataCount(dataCount);
        }
        if (ob.getInfulence() != 0)
        {
            log.debug(Thread.currentThread().getName()
                    + "\n【当前线程：{} 原始Infulence:{}\t新的Infulence:{}----更新后的：{}】",
                    Thread.currentThread().getName(), ob_old.getInfulence(),
                    ob.getInfulence(), newInfulence);
            ob_old.setInfulence(newInfulence);
        }
    }

    @Override
    public boolean addAllByThree(String eventId)
    {
        boolean flag = false;
        try
        {
            // 将三个平台的数据相加
            List<Map<String, List>> list = new ArrayList<Map<String, List>>();

            Map<String, List> weibo = eventTrendDao.findByEventIdInTime(
                    eventId, WEIBO_TYPE).getTime_num();
            Map<String, List> media = eventTrendDao.findByEventIdInTime(
                    eventId, MEDIA_TYPE).getTime_num();
            Map<String, List> meixin = eventTrendDao.findByEventIdInTime(
                    eventId, WEIXIN_TYPE).getTime_num();

            list.add(weibo);
            list.add(media);
            list.add(meixin);

            Map<String, List> time_num = MapUtil.addMapByKeyByList(list);
            // 存入
            EventTrend ob = new EventTrend();
            ob.setId(eventId+ALL_TYPE);
            ob.setEventId(eventId);
            ob.setType(ALL_TYPE);
            ob.setTime_num(MapUtil.treatOrderByKeyDescInStr(time_num));
            
            eventTrendDao.save(ob);
            flag = true;
                   
            log.debug(
                    "***********************************************************【{}：\t添加整体趋势】",
                    eventId);
            
        }
        catch (Exception e)
        {
            log.error("将三个平台求和出错：{}", e.getMessage());
        }

        return flag;
    }

    @Override
    public EventUpate findEventUpataByEvent(Event event)
    {
        Map<String, EventTrend> map = eventTrendDao
                .findEventUpataByEvent(event);
        List<String> textList = new ArrayList<String>();
        Double infulence = 0.0;
        int dataCount = 0;

        for (String type : map.keySet())
        {
            EventTrend eventTrend = map.get(type);
            if (null == eventTrend)
            {
                continue;
            }
            infulence += eventTrend.getInfulence();
            dataCount += eventTrend.getDataCount();
            textList.addAll(getDataTest(eventTrend, type));
        }

        Fenci fenci = new Fenci();

        EventUpate update = new EventUpate();
        update.setDataCount(dataCount);
        update.setInfulence(infulence);
        update.setWordMap(fenci.getFenCiWithStorp(textList));

        return update;
    }

    private List<String> getDataTest(EventTrend eventTrend, String type)
    {
        List<String> ids = getEventDataIds(eventTrend);
        List<String> textList = new ArrayList<String>();

        if ("weibo".equals(type))
        {
            textList = solrWeiboDataDao.findByIdList(ids);
        }
        if ("weixin".equals(type))
        {
            textList = solrWeiXDataindao.findByIdList(ids);
        }
        if ("media".equals(type))
        {
            textList = solrMediaDatadao.findByIdList(ids);
        }
        return textList;
    }

    private List<String> getEventDataIds(EventTrend eventTrend)
    {
        List<String> idList = new ArrayList<String>();

        for (Entry<String, List> ent : eventTrend.getSource().entrySet())
        {
            idList.addAll(ent.getValue());
        }

        return idList;
    }

    @Override
    public boolean deleteByEventId(String eventId)
    {
        boolean result = false;
        try
        {
            long cont = eventTrendDao.findCont();
            eventTrendDao.deleteByEventId(eventId);
            if (cont - eventTrendDao.findCont() > 0)
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