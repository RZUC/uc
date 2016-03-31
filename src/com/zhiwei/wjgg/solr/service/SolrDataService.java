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
package com.zhiwei.wjgg.solr.service;

import java.util.List;
import java.util.Map;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.zhiwei.wjgg.model.Event;

/**
 * @Description: solr的数据查询
 * @ClassName: SolrDataSerice
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
public interface SolrDataService
{
    
    /**
     * @Title: getSolrData
     * @Description: TODO(从Solr中获取数据)
     * @param @param startTime 开始时间
     * @param @param endTime 结束时间
     * @param @param wordList 关键词列表
     * @param @return 设定文件
     * @return SolrDocumentList 返回类型
     */
    SolrDocumentList getSolrData(Event event);
    
    /**
     * @Title: getTopByH 
     * @Description: TODO(根据影响力返回TOP数据) 
     * @param @param top
     * @param @return 设定文件 
     * @return SolrDocumentList 返回类型
     */
    List<Map<String, Object>> getTopByH(Event event,int top);
}
