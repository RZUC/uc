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
package com.zhiwei.wjgg.solr.dao;

import org.apache.solr.common.SolrDocumentList;

import com.zhiwei.wjgg.model.Event;

/**
 * @Description: solrDao的数据查询
 * @ClassName: CommonDao 公共Dao
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
public interface SolrCommonDao
{
    public SolrDocumentList getData(Event event, Long rsid);
}
