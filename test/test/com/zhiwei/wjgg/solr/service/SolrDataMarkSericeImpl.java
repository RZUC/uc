/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年2月27日
    * @version 1.00 
*/
package test.com.zhiwei.wjgg.solr.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.springframework.stereotype.Component;

import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.solr.model.DataMark;
import com.zhiwei.wjgg.solr.service.SolrDataService;
import com.zhiwei.wjgg.util.SolrDocumentToBeanUtil;

import test.object.ObjectTest;

/**
 * @Description: 微信数据获取
 * @ClassName: SolrDataSerice
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
@Component("solrWeiXinService")
public class SolrDataMarkSericeImpl extends ObjectTest
{
    
    @Resource(name = "solrMarkService")
    SolrDataService serviceMark;
    
    @Test
    public void getSolrDataTest()
        throws InterruptedException
    {
        Event event = new Event();
        event.setId("2423423");
        event.setStartTime("2016-02-28 00:00:00");
        event.setEndTime("2016-02-29 23:59:59");
        event.setWord("马化腾");
        SolrDocumentList docList = serviceMark.getSolrData(event);
        List<DataMark> list = SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataMark.class, docList);
        for (DataMark media : list)
        {
            
            System.out.println("ID：" + media.getId());
            System.out.println("字符类型的时间：" + media.getTimeString());
            System.out.println("情感倾向：" + media.getEmotionTypeStr());
            System.out.println("情报组：" + media.getMarkGroup());
            System.out.println("*********************************************************");
        }
    }
    
}
