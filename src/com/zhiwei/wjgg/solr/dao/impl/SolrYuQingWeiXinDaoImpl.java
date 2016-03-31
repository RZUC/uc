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
 * @Description: 舆情库的微信数据
 * @ClassName: SolrDataSerice
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
@Component("solrYuQingWeiXinDao")
public class SolrYuQingWeiXinDaoImpl extends CommonDao
{
    @Resource(name = "yuqingSolr")
    HttpSolrServerUtil weixinYuQingSolr;
    
    @Override
    protected String getParam(List<String> wordList)
    {
        String param = SolrTool.processWordWX(wordList);
        if (null != param && !"".equals(param))
        {
            return "("+param + ") AND pt:\"微信\"";
        }
        else
        {
            return param;
        }
    }
    
    @Override
    protected String getTimeType()
    {
        return TIMETYEP_Date;
    }
    
    @Override
    protected HttpSolrServer getSolrServer()
    {
        return weixinYuQingSolr.getHttpSolrServer();
    }
    
    @Override
    protected String getFilterField()
    {
        return "";
    }
}
