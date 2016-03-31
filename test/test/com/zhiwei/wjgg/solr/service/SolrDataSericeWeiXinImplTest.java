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
import com.zhiwei.wjgg.model.HUserInfoWX;
import com.zhiwei.wjgg.solr.model.DataWeiXin;
import com.zhiwei.wjgg.solr.service.impl.SolrDataCommonService;
import com.zhiwei.wjgg.util.SolrDocumentToBeanUtil;

import test.object.ObjectTest;

/**
 * @Description: 微博实现
 * @ClassName: SolrDataSerice
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
public class SolrDataSericeWeiXinImplTest extends ObjectTest
{
    @Resource(name = "solrWeiXinService")
    SolrDataCommonService<HUserInfoWX> serviceWeiXin;
    
    @Test
    public void getSolrDataTest()
        throws InterruptedException
    {
        long start = System.currentTimeMillis();
        
        Event event = new Event();
        event.setId("2423423");
        event.setStartTime("2016-02-28 00:00:00");
        event.setEndTime("2016-02-29 23:59:59");
        event.setWord("奶茶");
        
        SolrDocumentList docList = serviceWeiXin.getSolrDataWithHUserInfo(event);
        
        List<DataWeiXin> list = SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataWeiXin.class, docList);
        
        System.out.println(list.size());
        
        int i = 1;
        int x = 1;
        for (DataWeiXin media : list)
        {
            
            if (null != media.getHUserInfo())
            {
                // System.out.println("时间类型：" + media.getTimeDate());
                // System.out.println("字符类型的时间：" + media.getTimeString());
                // System.out.println("用戶数据：" + media.getHUserInfo());
                // System.out.println("*********************************************************编号：{}" + i);
                i++;
            }
            else
            {
                x++;
            }
            
        }
        System.out.println("没有用户信息的数据" + x);
        
        long end = System.currentTimeMillis();
        
        System.out.println("运行时间：" + (end - start) / 1000 + "\t秒");
    }
}
