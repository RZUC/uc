package com.zhiwei.wjgg.mongo.service;

import java.util.HashMap;
import java.util.List;

import org.apache.solr.common.SolrDocumentList;

/**
 * @Package com.zhiwei.event.mongo.baidu.api
 * @ClassName: GetBaiduDataByMongoApi
 * @Description: TODO(从mongo中读取百度新闻帐号数据)
 * @author 志伟
 * @date 2015-5-26 上午11:18:24
 */
public interface GetBaiduUserInfoDao
{
    
    /**
     * @deprecated:根据网址在mongo中反查百度新闻帐号数据
     * @param SolrDocumentList sdl 百度新闻数据
     * @return List<HashMap>
     **/
    List<HashMap> getBaiduUserInfoById(SolrDocumentList sdl);
    
    List<HashMap> getBaiduUserInfoByIdList(SolrDocumentList sdl);
}
