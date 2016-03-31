package com.zhiwei.wjgg.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.analy.ThreadRun.AnalysisEvent;
import com.zhiwei.wjgg.analy.eventInfulence.EventInfulence;
import com.zhiwei.wjgg.dao.EventDao;
import com.zhiwei.wjgg.dao.SeqDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.EventUpate;
import com.zhiwei.wjgg.model.Seq;
import com.zhiwei.wjgg.service.DicService;
import com.zhiwei.wjgg.service.EventKeyPointService;
import com.zhiwei.wjgg.service.EventService;
import com.zhiwei.wjgg.service.EventTrendService;
import com.zhiwei.wjgg.service.NatureKeyPointService;
import com.zhiwei.wjgg.service.NatureTrendService;

/**
 * @author 落花流水
 * @date 2016-02-27
 */
@Service
public class EventServiceImpl extends GeneralServiceImpl implements EventService
{
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Resource
    private EventDao dao;
    
    @Resource
    private SeqDao seqDao;
    
    @Resource
    private NatureKeyPointService natureKeyPointService;
    
    @Resource
    private NatureTrendService natureTrendService;
    
    @Resource
    private EventKeyPointService eventKeyPointService;
    
    @Resource
    private EventTrendService eventTrendService;
    
    @Resource
    private AnalysisEvent analysisEvent;
    
    @Resource
    DicService service;
    
    @Override
    public boolean add(Event ob)
    {
        addWordFenci(ob);
        try
        {
            Date start = sdf.parse(ob.getStartTime());
            Date end = sdf.parse(ob.getEndTime());
            if (start.after(end))
            {
                System.out.println("事件开始时间在结束时间之后");
                return false;
            }
            long id = 0;
            // 自增
            Seq seq = seqDao.findOne("eventId");
            if (null == seq)
            {
                seq = new Seq();
                seq.setId("eventId");
                seq.setSeq(0);
                seqDao.insert(seq);
            }
            else
            {
                id = seq.getSeq();
                seq.setSeq(id + 1);
                seqDao.findAndModify(seq);
            }
            
            ob.setId(id + "");
            Event new_ob = dao.insert(ob);
            if (null != new_ob)
            {
                // analyTread(new_ob);
                analyTread(new_ob);
                return true;
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("事件登记出错：{}", e.getMessage());
        }
        catch (ParseException e)
        {
            log.error("事件登记时间格式出错：{}", e.getMessage());
        }
        return false;
    }
    
    /**
     * @Title: addWordFenci
     * @Description: 新增事件之前新增分词的关键词
     * @param @param ob 设定文件
     * @return void 返回类型
     */
    private void addWordFenci(Event ob)
    {
        try
        {
            boolean flag = service.addWrodList(ob.getWord());
            log.debug("添加字典：【{}----{}", ob.getWord(), flag);
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }
    
    /**
     * @Title: analyTread
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param new_ob 设定文件
     * @return void 返回类型
     */
    private void analyTread(final Event new_ob)
    {
        // 初始化事件
        Runnable analysisRunnable = new Runnable()
        {
            public void run()
            {
                log.debug(Thread.currentThread().getName());
                analysisEvent.analysis(new_ob);
            }
        };
        Thread analyTread = new Thread(analysisRunnable, "初始化解析事件：【" + new_ob.getName() + "】");
        analyTread.start();
    }
    
    @Override
    public boolean updata(Event ob)
    {
        boolean result = false;
        try
        {
            result = dao.findAndModify(ob);
        }
        catch (ZhiWeiException e)
        {
            log.error("事件更新出错：{}", e.getMessage());
        }
        return result;
    }
    
    @Override
    public boolean delete(Event ob)
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
            log.error("事件删除出错：{}", e.getMessage());
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
            
            natureKeyPointService.deleteByEventId(id);
            
            natureTrendService.deleteByEventId(id);
            
            eventKeyPointService.deleteByEventId(id);
            
            eventTrendService.deleteByEventId(id);
            
            if (cont - dao.findCont() > 0)
            {
                result = true;
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("事件删除出错：{}", e.getMessage());
        }
        return result;
    }
    
    @Override
    public boolean search(Event ob)
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
    public Event findOneById(String id)
    {
        Event ob = null;
        try
        {
            ob = dao.findOne(id);
        }
        catch (ZhiWeiException e)
        {
            log.error("事件查询出错：{}", e.getMessage());
        }
        return ob;
    }
    
    @Override
    public List<Event> findAll(Object... ob)
    {
        List<Event> lists = null;
        try
        {
            lists = dao.findAll();
        }
        catch (ZhiWeiException e)
        {
            log.error("事件查询所有出错：{}", e.getMessage());
        }
        
        return lists;
    }
    
    @Override
    public List<Event> findEventsByType(String eventType, String userId)
    {
        try
        {
            return dao.findEventsByType(eventType, userId);
        }
        catch (ZhiWeiException e)
        {
            log.error("事件查询出错：{}", e.getMessage());
        }
        return null;
    }
    
    @Override
    public List<Event> findEventsByLevel(String eventLevel, String user)
    {
        try
        {
            return dao.findEventsByLevel(eventLevel, user);
        }
        catch (ZhiWeiException e)
        {
            log.error("事件查询出错：{}", e.getMessage());
        }
        return null;
    }
    
    @Override
    public List<Event> findEventsByTypeAndLevel(String eventType, String eventLevel, String user)
    {
        try
        {
            return dao.findEventsByTypeAndLevel(eventType, eventLevel, user);
        }
        catch (ZhiWeiException e)
        {
            log.error("事件查询出错：{}", e.getMessage());
        }
        return null;
    }
    
    @Override
    public List<Event> findEventsByUser(String user)
    {
        try
        {
            return dao.findEventsByUser(user);
        }
        catch (ZhiWeiException e)
        {
            log.error("事件查询出错：{}", e.getMessage());
        }
        return null;
    }
    
    @Override
    public List<Event> findEventsByUserIsStart(String user)
    {
        try
        {
            return dao.findByUserIsStart(user);
        }
        catch (ZhiWeiException e)
        {
            log.error("事件查询出错：{}", e.getMessage());
        }
        return null;
    }
    
    @Override
    public long findContByUserIsStart(String user)
    {
        try
        {
            return dao.findContByUserIsStart(user);
        }
        catch (ZhiWeiException e)
        {
            log.error("事件查询出错：{}", e.getMessage());
        }
        return 0;
    }
    
    @Override
    public List<Event> fuzzyQueryByWord(String word, String userId)
    {
        try
        {
            return dao.fuzzyQueryByWord(word, userId);
        }
        catch (ZhiWeiException e)
        {
            log.error("事件查询出错：{}", e.getMessage());
        }
        return null;
    }
    
    @Override
    public List<Event> findEventsNearTime(String user)
    {
        try
        {
            return dao.findEventsNearTime(user);
        }
        catch (ZhiWeiException e)
        {
            log.error("事件查询出错：{}", e.getMessage());
        }
        return null;
    }
    
    @Override
    public boolean updataInfulenceAndWord(Event event)
    {
        // TODO:根据事件ID获取分词 以及数据数量 还有事件的影响力
        // TODO:1.eventTrend 获取数据
        // TODO:1.事件影响力，2.媒介渠道传播数量，3.事件相关性，4.影响力指数
        
        EventUpate eventUpate = eventTrendService.findEventUpataByEvent(event);
        event.setChannelNum(EventInfulence.nomelInfulence(eventUpate.getInfulence()));// 媒介渠道
        event.setSpread(EventInfulence.nomelDataCount(eventUpate.getDataCount()));
        event.setTopWord(eventUpate.getWordMap());
        event.setImpact(
            EventInfulence.integrated(event.getCorrelationNum(), eventUpate.getInfulence(), eventUpate.getDataCount()));
        
        try
        {
            dao.findAndModify(event);
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public List<Event> findCompleteEvent(String username, int maxCount)
    {
        
        try
        {
            return dao.findByUserIsComplete(username, maxCount);
        }
        catch (ZhiWeiException e)
        {
            return new ArrayList<Event>();
        }
    }
    
}