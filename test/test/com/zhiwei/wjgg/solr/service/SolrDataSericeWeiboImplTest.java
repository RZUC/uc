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

import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.HUserInfoWB;
import com.zhiwei.wjgg.solr.model.DataWeiBo;
import com.zhiwei.wjgg.solr.service.impl.SolrDataCommonService;
import com.zhiwei.wjgg.util.SolrDocumentToBeanUtil;

import test.object.ObjectTest;

/**
 * @Description: 微博实现
 * @ClassName: SolrDataSerice
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
public class SolrDataSericeWeiboImplTest extends ObjectTest
{
    @Resource(name = "solrWeiboService")
    SolrDataCommonService<HUserInfoWB> serviceWeibo;
    
    @Test
    public void getSolrDataTest()
        throws InterruptedException
    {
        Event event = new Event();
        event.setStartTime("2016-02-28 00:00:00");
        event.setEndTime("2016-02-29 23:59:59");
        event.setWord("提现");
        SolrDocumentList docList = serviceWeibo.getSolrDataWithHUserInfo(event);
        List<DataWeiBo> list = SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataWeiBo.class, docList);
        List<String> wordList2 = new ArrayList<String>();
        
        for (DataWeiBo media : list)
        {
             wordList2.add(media.getText());
             System.out.println("时间类型：" + media.getId());
             System.out.println("时间类型：" + media.getTimeDate());
             System.out.println("字符类型的时间：" + media.getTimeString());
             System.out.println("用戶数据：" + media.getHUserInfo());
             System.out.println("*********************************************************");
            
        }
        
    }
}
