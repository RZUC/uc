/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年2月29日
    * @version 1.00 
*/
package test.com.zhiwei.wjgg.solr.dao;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.solr.dao.SolrDao;
import com.zhiwei.wjgg.util.WordsCount;

import test.object.ObjectTest;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: SolrEventMediaDaoImplTest
 * @author 落花流水
 * @date 2016年2月29日 下午3:45:59
 */
public class SolrYuQingWeiXinDaoImplTest extends ObjectTest
{
    @Resource(name = "solrYuQingWeiXinDao")
    private SolrDao weixinSolrYuQing;
    
    @Test
    public void getDataTest()
    {
        Event event = new Event();
        event.setId("2");
        event.setStartTime("2015-01-25 00:00:00");
        event.setEndTime("2015-01-31 23:59:59");
        event.setWord("朋友圈+广告");
        
        SolrDocumentList lis = weixinSolrYuQing.getData(event, null);
        System.out.println(lis.size());
    }
}
