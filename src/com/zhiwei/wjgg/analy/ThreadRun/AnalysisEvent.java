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

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zhiwei.wjgg.analy.service.AnalyDataService;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.HUserInfoBD;
import com.zhiwei.wjgg.model.HUserInfoWB;
import com.zhiwei.wjgg.model.HUserInfoWX;
import com.zhiwei.wjgg.service.EventService;
import com.zhiwei.wjgg.service.EventTrendService;
import com.zhiwei.wjgg.service.NatureTrendService;
import com.zhiwei.wjgg.service.SimilarityService;
import com.zhiwei.wjgg.service.SolrSourceService;
import com.zhiwei.wjgg.solr.model.DataCommon;
import com.zhiwei.wjgg.solr.model.DataMark;
import com.zhiwei.wjgg.solr.model.DataMedia;
import com.zhiwei.wjgg.solr.model.DataWeiBo;
import com.zhiwei.wjgg.solr.model.DataWeiXin;
import com.zhiwei.wjgg.solr.service.SolrDataService;
import com.zhiwei.wjgg.solr.service.impl.SolrDataCommonService;
import com.zhiwei.wjgg.util.SolrDocumentToBeanUtil;
import com.zhiwei.wjgg.util.TimeUtil;

/**
 * @Description:具体的事件分析操作
 * @ClassName: AnalysisEvent
 * @author 落花流水
 * @date 2016年3月16日 上午8:39:17
 */
@Component
public class AnalysisEvent
{
    public final static Logger log = LoggerFactory.getLogger(AnalysisEvent.class);
    
    private static final String ANALYCOMPLETE = "0"; // 分析完成
    
    private static final String ANALYUNCOMPLETE = "1"; // 分析未完成
    
    @Resource(name = "analyServiceWeiBo")
    private AnalyDataService analyServiceWeiBo;
    
    @Resource(name = "analyServiceWeiXin")
    private AnalyDataService analyServiceWeiXin;
    
    @Resource(name = "analyServiceMedia")
    private AnalyDataService analyServiceMedia;
    
    @Resource(name = "analyServiceNature")
    private AnalyDataService analyService;
    
    @Resource(name = "solrMarkService")
    private SolrDataService serviceMarker;
    
    @Resource(name = "solrWeiboService")
    private SolrDataCommonService<HUserInfoWB> serviceWeibo;
    
    @Resource(name = "solrWeiXinService")
    private SolrDataCommonService<HUserInfoWX> serviceWeiXin;
    
    @Resource(name = "solrMediaService")
    private SolrDataCommonService<HUserInfoBD> serviceMedia;
    
    @Resource(name = "solrWeiboServiceImpl")
    private SolrSourceService eventDataWeibo;
    
    @Resource(name = "solrWeixinServiceImpl")
    private SolrSourceService eventDataWeixin;
    
    @Resource(name = "solrMediaServiceImpl")
    private SolrSourceService eventDataMedia;
    
    @Resource
    private EventTrendService eventTrendService;
    
    @Resource
    private SimilarityService similarityService;
    
    @Resource
    private EventService eventService;
    
    @Resource
    private NatureTrendService natureTrendService;
    
    public boolean analysis(Event event)
    {
        boolean flag = false;
        
        if (checkEventState(event))
        {
            setEventState(event);
            
            executionAlysis(event);
            
            flag = true;
        }
        
        return flag;
    }
    
    /**
     * @Title: excanalysis
     * @Description: 多线程分别执行该事件涉及的微博，微信，网媒数据的采集和分析<br>
     *               如果是相关事件那么还需要增加一个舆情数据的分析
     * @param @param event 设定文件
     * @return void 返回类型
     * @throws ZhiWeiException
     */
    private void executionAlysis(Event event)
    {
        try
        {
            CountDownLatch countDown = new CountDownLatch(3);
            
            AnalyWeiBo(event, countDown);
            
            AnalyWeiXin(event, countDown);
            
            AnalyMedia(event, countDown);
            
            if (event.isCorrelation())
            {
                AnalyNature(event);
            }
            
            countDown.await();
            
            eventTrendService.addAllByThree(event.getId()); // 添加总体趋势图
            
            eventService.updataInfulenceAndWord(event);// 更新事件热门分词和事件影响力
            
        }
        catch (Exception e)
        {
            log.error("{}事件更新出错------------------------------", event.getName());
        }
        
        log.debug("{}事件完成更新------------------------------", event.getName());
    }
    
    /**
     * @Title: checkEventState
     * @Description: 检查事件状态，事件不等于空，切结束时间大于当前时间
     * @param @param event
     * @param @return 设定文件
     * @return boolean 返回类型
     */
    private boolean checkEventState(Event event)
    {
        boolean NULL = null != event;
        boolean isComplete = ANALYUNCOMPLETE.equals(event.getState());
        return NULL && isComplete;
    }
    
    /**
     * @Title: setEventState
     * @Description: 设置时间的状态，以及最后更新的时间 当结束时间小于当前时间的时候，设置线程分析以及完成
     * @param @param event 设定文件
     * @return void 返回类型
     */
    private void setEventState(Event event)
    {
        
        try
        {
            Date lastupdateTime = TimeUtil.getCurrentTime();
            event.setLastUpateTime(TimeUtil.formatTime(lastupdateTime));
            if (TimeUtil.parseTime(event.getEndTime()).getTime()
                - TimeUtil.parseTime(event.getLastUpateTime()).getTime() <= 0)
            {
                event.setState(ANALYCOMPLETE);
            }
        }
        catch (Exception e)
        {
            log.error("设置事件更新状态出错：{}", e.getMessage());
        }
        
    }
    
    private void AnalyNature(Event event)
        throws InterruptedException
    {
        SolrDocumentList natureDocList = serviceMarker.getSolrData(event);
        
        List<DataMark> markList = changeClas(DataMark.class, natureDocList);
        
        // SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataMark.class, natureDocList);
        
        AnalysisDataNatureRun weibo = new AnalysisDataNatureRun(analyService, markList, natureTrendService, event);
        Thread Thread = new Thread(weibo, "舆情");
        Thread.start();
        Thread.join();
    }
    
    private void AnalyWeiBo(Event event, CountDownLatch countDown)
        throws InterruptedException
    {
        SolrDocumentList weiboDocList = serviceWeibo.getSolrDataWithHUserInfo(event);
        List<DataWeiBo> weiboList = changeClas(DataWeiBo.class, weiboDocList);
        // List<DataWeiBo> weiboList =
        // SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataWeiBo.class, weiboDocList);
        AnalysisDataRun weibo = new AnalysisDataRun(analyServiceWeiBo, weiboList, eventTrendService, eventDataWeibo,
            event, null, countDown);
        
        Thread weiboThread = new Thread(weibo, "weibo");
        weiboThread.start();
    }
    
    private void AnalyWeiXin(Event event, CountDownLatch countDown)
        throws InterruptedException
    {
        SolrDocumentList docList = serviceWeiXin.getSolrDataWithHUserInfo(event);
        List<DataWeiXin> weixinList = changeClas(DataWeiXin.class, docList);
        // List<DataWeiXin> weixinList =
        // SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataWeiXin.class, docList);
        
        AnalysisDataRun<DataWeiXin> weixin = new AnalysisDataRun(analyServiceWeiXin, weixinList, eventTrendService,
            eventDataWeixin, event, similarityService, countDown);
        Thread weixinThread = new Thread(weixin, "weixin");
        weixinThread.start();
    }
    
    private void AnalyMedia(Event event, CountDownLatch countDown)
        throws InterruptedException
    {
        SolrDocumentList docList = serviceMedia.getSolrDataWithHUserInfo(event);
        List<DataMedia> mediaList = changeClas(DataMedia.class, docList);
        
        // List<DataMedia> mediaList = SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataMedia.class,
        // docList);
        
        AnalysisDataRun<DataMedia> weixin = new AnalysisDataRun(analyServiceMedia, mediaList, eventTrendService,
            eventDataMedia, event, similarityService, countDown);
        Thread weixinThread = new Thread(weixin, "media");
        weixinThread.start();
    }
    
    private synchronized <T extends DataCommon> List<T> changeClas(Class<T> t, SolrDocumentList docList)
    {
        List<T> list = SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(t, docList);
        return list;
    }
}
