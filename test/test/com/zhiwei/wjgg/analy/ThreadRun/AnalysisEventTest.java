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
package test.com.zhiwei.wjgg.analy.ThreadRun;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import com.zhiwei.wjgg.analy.ThreadRun.AnalysisDataRun;
import com.zhiwei.wjgg.analy.service.AnalyDataService;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.HUserInfoBD;
import com.zhiwei.wjgg.model.HUserInfoWB;
import com.zhiwei.wjgg.model.HUserInfoWX;
import com.zhiwei.wjgg.service.EventService;
import com.zhiwei.wjgg.service.EventTrendService;
import com.zhiwei.wjgg.service.SimilarityService;
import com.zhiwei.wjgg.service.SolrSourceService;
import com.zhiwei.wjgg.solr.model.DataMedia;
import com.zhiwei.wjgg.solr.model.DataWeiBo;
import com.zhiwei.wjgg.solr.model.DataWeiXin;
import com.zhiwei.wjgg.solr.service.impl.SolrDataCommonService;
import com.zhiwei.wjgg.util.SolrDocumentToBeanUtil;

import test.object.ObjectTest;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: AnalysisRunTest
 * @author 落花流水
 * @date 2016年3月3日 下午5:10:44
 */
public class AnalysisEventTest extends ObjectTest
{
    @Resource(name = "analyServiceWeiBo")
    AnalyDataService analyServiceWeiBo;
    
    @Resource(name = "analyServiceWeiXin")
    AnalyDataService analyServiceWeiXin;
    
    @Resource(name = "analyServiceMedia")
    AnalyDataService analyServiceMedia;
    
    @Resource(name = "solrWeiboService")
    SolrDataCommonService<HUserInfoWB> serviceWeibo;
    
    @Resource(name = "solrWeiXinService")
    SolrDataCommonService<HUserInfoWX> serviceWeiXin;
    
    @Resource(name = "solrMediaService")
    SolrDataCommonService<HUserInfoBD> serviceMedia;
    
    @Resource(name = "solrWeiboServiceImpl")
    SolrSourceService eventDataWeibo;
    
    @Resource(name = "solrWeixinServiceImpl")
    SolrSourceService eventDataWeixin;
    
    @Resource(name = "solrMediaServiceImpl")
    SolrSourceService eventDataMedia;
    
    @Resource
    EventTrendService eventTrendService;
    
    @Resource
    SimilarityService similarityService;
    
    @Resource
    SimilarityService similarityService2;
    
    @Resource
    EventService eventService;
    
    @Resource
    EventService service;
    
    @Test
    public void analySisRunnTest()
    {
        long start = System.currentTimeMillis();
        
        Event event = eventService.findOneById("5");
        
        CountDownLatch countDown = new CountDownLatch(3);
        try
        {
            AnalyWeiBo(event, countDown);
            AnalyWeiXin(event, countDown);
            AnalyMedia(event, countDown);
            countDown.await();
            System.out.println("更新事件数据");
        }
        catch (InterruptedException e)
        {
            
            e.printStackTrace();
        }
        
        System.out.println("主线程分析完成：分析完成-----------------------------");
        
        System.out.println("总的趋势图：开始更新-----------------------------");
        eventTrendService.addAllByThree(event.getId());
        System.out.println("总的趋势图：更新完毕-----------------------------");
        System.out.println("更新事件数据：开始更新-----------------------------");
        service.updataInfulenceAndWord(event);
        System.out.println("更新事件数据：更新完毕-----------------------------");
        long end = System.currentTimeMillis();
        
        System.out.println("运行时间：" + (end - start) / 1000 + "\t秒");
    }
    
    
    private void AnalyWeiBo(Event event, CountDownLatch countDown)
        throws InterruptedException
    {
        SolrDocumentList weiboDocList = serviceWeibo.getSolrDataWithHUserInfo(event);
        List<DataWeiBo> weiboList =
            SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataWeiBo.class, weiboDocList);
        AnalysisDataRun weibo = new AnalysisDataRun(analyServiceWeiBo, weiboList, eventTrendService, eventDataWeibo,
            event, null, countDown);
            
        Thread weiboThread = new Thread(weibo, "weibo");
        weiboThread.start();
    }
    
    private void AnalyWeiXin(Event event, CountDownLatch countDown)
        throws InterruptedException
    {
        SolrDocumentList docList = serviceWeiXin.getSolrDataWithHUserInfo(event);
        List<DataWeiXin> weixinList =
            SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataWeiXin.class, docList);
        AnalysisDataRun<DataWeiXin> weixin = new AnalysisDataRun(analyServiceWeiXin, weixinList, eventTrendService,
            eventDataWeixin, event, similarityService, countDown);
        Thread weixinThread = new Thread(weixin, "weixin");
        weixinThread.start();
    }
    
    private void AnalyMedia(Event event, CountDownLatch countDown)
        throws InterruptedException
    {
        SolrDocumentList docList = serviceMedia.getSolrDataWithHUserInfo(event);
        List<DataMedia> mediaList = SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataMedia.class, docList);
        AnalysisDataRun<DataMedia> weixin = new AnalysisDataRun(analyServiceMedia, mediaList, eventTrendService,
            eventDataMedia, event, similarityService2, countDown);
        Thread weixinThread = new Thread(weixin, "media`");
        weixinThread.start();
    }
    
}
