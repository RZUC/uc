/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月3日
    * @version 1.00 
*/
package com.zhiwei.wjgg.analy.ThreadRun;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiwei.wjgg.analy.service.AnalyDataService;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.EventTrend;
import com.zhiwei.wjgg.model.Similarity;
import com.zhiwei.wjgg.service.EventTrendService;
import com.zhiwei.wjgg.service.SimilarityService;
import com.zhiwei.wjgg.service.SolrSourceService;
import com.zhiwei.wjgg.solr.model.DataCommon;

/**
 * @Description: 解析具体的数据（微博，微信，网媒）
 * @ClassName: AnalysisDataRun
 * @author 落花流水
 * @param <T>
 * @param <T>
 * @date 2016年3月3日 下午4:56:49
 */
public class AnalysisDataRun<T extends DataCommon> implements Runnable
{
    private static Logger log = LoggerFactory.getLogger(AnalysisDataRun.class);
    
    private static final String WEIBO = "weibo";
    
    private static final String WEIXIN = "weixin";
    
    private static final String MEDIA = "media";
    
    private AnalyDataService analyService;
    
    @Resource
    EventTrendService eventTrendService;
    
    private SimilarityService similarityservice;
    
    private SolrSourceService service;
    
    private List<T> dataList;
    
    private Event event;
    
    private CountDownLatch countDown;
    
    private EventTrend eventTrend = new EventTrend();
    
    @Override
    public void run()
    {
        try
        {
            String type = Thread.currentThread().getName();
            
            initEventThread(type);
            
            Map<String, Object> map = analyService.analyData(dataList, event); // 解析数据以Map形式返回
            
            // 保存原始数据
            if (saveEventData(dataList))
            {
                log.debug("保存数据成功 数据条数：{}", dataList.size());
            }
            // 添加数据趋势图
            AddEventTrend(map);
            
            // 添加共同标题
            addSimilarity(type, map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            countDown.countDown();
        }
        
    }
    
    /**
     * @Title: addEvent
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param 设定文件
     * @return void 返回类型
     */
    private void addEvent()
    {
        log.debug("当前线程:{},EventTrend:{}", Thread.currentThread().getName(), eventTrend.getInfulence());
        try
        {
            eventTrendService.addInfoByOb(eventTrend);
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
        }
    }
    
    private void addSimilarity(String type, Map map)
    {
        
        if (WEIBO.equals(type))
        {
            return;
        }
        Map<String, Integer> similarityMap = (Map<String, Integer>)map.get("similarTitle");
        log.debug("-----------------------共同标题线程：【{}】，类型：【{}】，大小：【{}】",
            Thread.currentThread().getName(),
            type,
            similarityMap.size());
        Similarity similarity = new Similarity();
        
        similarity.setId(event.getId());
        
        if (WEIXIN.equals(type))
        {
            similarity.setWeixin(similarityMap);
        }
        else
        {
            similarity.setMedia(similarityMap);
        }
        similarityservice.addInfoByOb(similarity);
    }
    
    /**
     * @Title: setEventTrend
     * @Description: 添加事件趋势
     * @param @param map 设定文件
     * @return void 返回类型
     */
    private void AddEventTrend(Map<String, Object> map)
    {
        eventTrend.setTime_num((Map<String, List>)map.get("timeLine"));
        eventTrend.setSource((Map<String, List>)map.get("spredSource"));
        eventTrend.setInfulence(Double.valueOf(map.get("infulence").toString()));
        eventTrend.setDataCount(Integer.valueOf(map.get("dataCount").toString()));
        eventTrend.setLastRsid((Long)map.get("lasrsid"));
        addEvent();
    }
    
    /**
     * @Title: initEventThread
     * @Description: 初始化事件数据
     * @param 设定文件
     * @return void 返回类型
     */
    private void initEventThread(String type)
    {
        eventTrend.setType(getTypeStr(type));
        eventTrend.setEventId(event.getId());
    }
    
    public AnalysisDataRun(AnalyDataService analyService, List<T> dataList, EventTrendService eventTrendService,
        SolrSourceService service, Event event, SimilarityService similarityservice, CountDownLatch countDown)
    {
        super();
        this.analyService = analyService;
        this.dataList = dataList;
        this.eventTrendService = eventTrendService;
        this.service = service;
        this.event = event;
        this.similarityservice = similarityservice;
        this.countDown = countDown;
    }
    
    private String getTypeStr(String t)
    {
        String type = "";
        if (t.contains(WEIBO))
        {
            type = WEIBO;
        }
        if (t.contains(WEIXIN))
        {
            type = WEIXIN;
        }
        if (t.contains(MEDIA))
        {
            type = MEDIA;
        }
        
        return type;
    }
    
    private boolean saveEventData(List<T> dataList)
    {
        return service.add(dataList);
    }
}
