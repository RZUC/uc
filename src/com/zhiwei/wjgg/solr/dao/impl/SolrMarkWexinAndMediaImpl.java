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
package com.zhiwei.wjgg.solr.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.stereotype.Component;

import com.zhiwei.wjgg.DBTemp.HttpSolrServerUtil;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.util.SolrTool;

/**
 * @Description: 舆情数据采集
 * @ClassName: SolrDataSerice
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
@Component("solrMarkOtherDao")
public class SolrMarkWexinAndMediaImpl extends CommonDao
{
    @Resource(name = "otherMarkSolr")
    HttpSolrServerUtil otherMarkSolr;
    
    @Override
    protected String getParam(List<String> wordList)
    {
        String param = SolrTool.processWordWX(wordList);
        return param;
    }
    
    @Override
    protected String getTimeType()
    {
        return TIMETYEP_STRING;
    }
    
    @Override
    protected HttpSolrServer getSolrServer()
    {
        return otherMarkSolr.getHttpSolrServer();
    }
    
    @Override
    protected String getFilterField()
    {
        return "emotionType,time,markGroup,mid";
    }
    
    @Override
    protected String getFilterRsid(long rsid)
    {
        return null;
    }
    
    @Override
    protected String getFilterByEvent(Event event)
    {
        String query = "markGroup:\"腾讯\"";
        log.debug("getFilterByEvent：{}", query);
        return query;
    }
}
