/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月15日
    * @version 1.00 
*/
package com.zhiwei.uc.system.timmer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhiwei.uc.system.model.Event;
import com.zhiwei.uc.system.util.TimeUtil;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: MyTask
 * @author 落花流水
 * @date 2016年3月15日 下午5:16:23
 */
public class MyTask extends TimerTask
{
    public static ApplicationContext ctx = new ClassPathXmlApplicationContext("application-config.xml");
    
    public final static Logger log = LoggerFactory.getLogger(MyTask.class);
    
    @Override
    public void run()
    {
        
        long start = System.currentTimeMillis();
        List<Event> list = getUnCompleteEvent();
        // CountDownLatch countDown = new CountDownLatch(list.size());
        // AnalysisEvent analysisEvent = (AnalysisEvent)ctx.getBean("analysisEvent");
        for (Event event : list)
        {
            // analysisEvent.analysis(event);
            // AnalysisEventRnnable r = new AnalysisEventRnnable(event, countDown, analysisEvent);
            // Thread t = new Thread(r, event.getName());
            // t.start();
        }
        // try
        // {
        // countDown.await();
        // }
        // catch (InterruptedException e)
        // {
        // e.printStackTrace();
        // }
        
        long end = System.currentTimeMillis();
        log.info("分析事件【开始时间:{},结束时间:{},总耗时:{}秒,完成任务数据量:{}】",
            TimeUtil.formatTime(new Date(start)),
            TimeUtil.formatTime(new Date(end)),
            (end - start) / 1000,
            list.size());
    }
    
    private List<Event> getUnCompleteEvent()
    {
        List<Event> list = new ArrayList<Event>();
        // EventDao eventDao = (EventDao)ctx.getBean("eventDaoImpl");
        // try
        // {
        // list = eventDao.findAliveEvent();
        // log.info("待分析的时间数量:{}", list.size());
        // }
        // catch (ZhiWeiException e)
        // {
        // log.error("查询待分析事件出错:{}", e.getMessage());
        // }
        
        return list;
    }
    
}
