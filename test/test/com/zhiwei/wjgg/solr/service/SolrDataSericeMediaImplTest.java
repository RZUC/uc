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

import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.HUserInfoBD;
import com.zhiwei.wjgg.solr.model.DataMedia;
import com.zhiwei.wjgg.solr.service.impl.SolrDataCommonService;
import com.zhiwei.wjgg.util.SolrDocumentToBeanUtil;

import test.object.ObjectTest;

/**
 * @Description: 微博实现
 * @ClassName: SolrDataSerice
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
public class SolrDataSericeMediaImplTest extends ObjectTest
{
    @Resource(name = "solrMediaService")
    SolrDataCommonService<HUserInfoBD> serviceMedia;
    
    @Test
    public void getSolrDataTest()
        throws InterruptedException
    {
        Event event = new Event();
        event.setId("2423423");
        event.setStartTime("2016-02-28 00:00:00");
        event.setEndTime("2016-02-29 23:59:59");
        event.setWord("奶茶");
        SolrDocumentList docList = serviceMedia.getSolrDataWithHUserInfo(event);
        List<DataMedia> list = SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataMedia.class, docList);
        
    }
}
