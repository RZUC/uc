/**
 * ***************************************************
 * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
 *****************************************************
 * 类的详细说明 
 * 
 * @author 东临碣石
 * @Date 2016年1月15日
 * @version 1.00 
 */
package test.com.zhiwei.wjgg.analy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import com.zhiwei.wjgg.analy.service.AnalyDataService;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.solr.model.DataMark;
import com.zhiwei.wjgg.solr.model.DataWeiBo;
import com.zhiwei.wjgg.solr.service.SolrDataService;
import com.zhiwei.wjgg.util.SolrDocumentToBeanUtil;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: EventLevelDaoTest
 * @Description: TODO(测试事件危机等级)
 * @author chenweitao
 * @date 2016年2月26日 上午11:37:28
 */
public class AnalyServiceImpleNatureTest extends ObjectTest
{
    @Resource(name = "analyServiceNature")
    AnalyDataService analyService;
    
    @Resource(name = "solrMarkService")
    SolrDataService serviceMarker;
    
    @Test
    public void analyTest()
    {
        String statrTime = "2016-02-27 00:00:00";
        String endTime = "2016-02-29 23:59:59";
        Event event = new Event();
        
        event.setId("测试");
        event.setStartTime(statrTime);
        event.setEndTime(endTime);
        event.setWord("奶茶");
        SolrDocumentList docList = serviceMarker.getSolrData(event);
        List<DataMark> list = SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataMark.class, docList);
        
        System.out.println();
        Map<String, Map<String, Integer>> map =
            (Map<String, Map<String, Integer>>)analyService.analyData(list,event).get("timeLine");
            
        for (Entry entry : map.entrySet())
        {
            System.out.println("时间：" + entry.getKey());
            System.out.println("内容：" + entry.getValue());
        }
    }
}
