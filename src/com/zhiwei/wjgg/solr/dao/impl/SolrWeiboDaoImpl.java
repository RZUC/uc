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
import com.zhiwei.wjgg.util.SolrTool;

/**
 * @Description: 微博Solr数据
 * @ClassName: SolrDataSerice
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
@Component("solrWeiboDao")
public class SolrWeiboDaoImpl extends CommonDao
{
    @Resource(name = "weiboSolr")
    HttpSolrServerUtil weiboSolr;
    
    @Override
    protected String getParam(List<String> wordList)
    {
        String param = SolrTool.processWordWB(wordList);
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
        return weiboSolr.getHttpSolrServer();
    }
    
    @Override
    protected String getFilterField()
    {
        return "";
    }
    
    @Override
    protected String getFilterRsid(long rsid)
    {
        String rsid2 = "rstime:{" + rsid + " TO  * ]";
        log.debug("RSID参数：{}", rsid2);
        return rsid2;
    }
}
