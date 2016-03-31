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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import com.zhiwei.wjgg.analy.ThreadRun.AnalysisDataNatureRun;
import com.zhiwei.wjgg.analy.service.AnalyDataService;
import com.zhiwei.wjgg.dao.EventDao;
import com.zhiwei.wjgg.dao.NatureTrendDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.service.NatureTrendService;
import com.zhiwei.wjgg.solr.model.DataMark;
import com.zhiwei.wjgg.solr.service.SolrDataService;
import com.zhiwei.wjgg.util.SolrDocumentToBeanUtil;

import test.object.ObjectTest;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: AnalysisRunTest
 * @author 落花流水
 * @date 2016年3月3日 下午5:10:44
 */
public class AnalysisNatureRunTest extends ObjectTest
{
    @Resource(name = "analyServiceNature")
    AnalyDataService analyService;
    
    @Resource(name = "solrMarkService")
    SolrDataService serviceMarker;
    
    @Resource
    NatureTrendService natureTrendService;
    
    @Resource
    EventDao eventdao;
    
    @Test
    public void analySisRunnTest()
        throws ZhiWeiException
    {
        
        long start = System.currentTimeMillis();
        
        Event event = eventdao.findOne("5");
        
        try
        {
            AnalyNature(event);
        }
        catch (InterruptedException e)
        {
            
            e.printStackTrace();
        }
        
        System.out.println("主线程分析完成：分析完成-----------------------------");
        
        long end = System.currentTimeMillis();
        
        System.out.println("运行时间：" + (end - start) / 1000 + "\t秒");
    }
    
    private void AnalyNature(Event event)
        throws InterruptedException
    {
        SolrDocumentList natureDocList = serviceMarker.getSolrData(event);
        List<DataMark> markList =
            SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataMark.class, natureDocList);
        AnalysisDataNatureRun weibo = new AnalysisDataNatureRun(analyService, markList, natureTrendService, event);
        
        Thread weiboThread = new Thread(weibo, "舆情");
        weiboThread.start();
        weiboThread.join();
    }
    
}
