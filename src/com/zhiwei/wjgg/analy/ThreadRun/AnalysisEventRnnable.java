/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月16日
    * @version 1.00 
*/
package com.zhiwei.wjgg.analy.ThreadRun;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiwei.wjgg.model.Event;

/**
 * @Description: 事件解析类，为了可以完成多个事件的同步执行
 * @ClassName: AnalysisEvent
 * @author 落花流水
 * @date 2016年3月16日 上午8:39:17
 */
public class AnalysisEventRnnable implements Runnable
{
    public final static Logger log = LoggerFactory.getLogger(AnalysisEventRnnable.class);
    
    private Event event;
    
    private CountDownLatch countDown;
    
    private AnalysisEvent analysisEvent;
    
    @Override
    public void run()
    {
        try
        {
            String name = Thread.currentThread().getName();
            
            analysisEvent.analysis(event);
            
            log.info("解析【{}】事件-------------------------------完成", name);
        }
        catch (Exception e)
        {
            log.error("解析【{}】 出错：{}", e.getMessage());
        }
        finally
        {
            countDown.countDown();
        }
        
    }
    
    public AnalysisEventRnnable(Event event, CountDownLatch countDown, AnalysisEvent analysisEvent)
    {
        super();
        this.event = event;
        this.countDown = countDown;
        this.analysisEvent = analysisEvent;
    }
    
}
