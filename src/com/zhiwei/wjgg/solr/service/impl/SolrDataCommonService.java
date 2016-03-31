/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月7日
    * @version 1.00 
*/
package com.zhiwei.wjgg.solr.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiwei.wjgg.dao.EventTrendDao;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.solr.service.SolrDataService;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: SolrDataCommonService
 * @author 落花流水
 * @date 2016年3月7日 下午1:57:59
 */
public abstract class SolrDataCommonService<T> implements SolrDataService
{
    public final static Logger log = LoggerFactory.getLogger(SolrDataCommonService.class);
    
    @Resource
    EventTrendDao eventTrendDao;
    
    /**
     * @Title: matchingOtherDate
     * @Description: 获取匹配用户H因子的数据
     * @param @param sdl
     * @param @return 设定文件
     * @return SolrDocumentList 返回类型
     */
    public SolrDocumentList getSolrDataWithHUserInfo(Event event)
    {   
        SolrDocumentList docList = this.getSolrData(event);
        
        Map<String, T> map = getHUserinfoMap(docList);
        
        for (SolrDocument doc : docList)
        {
            T user = getHUserinfoByMap(doc, map);
            if (user != null)
            {
                doc.put("userinfo", user);
            }
        }
        
        return docList;
    }
    
    /**
     * @Title: HUserInfoListByIds
     * @Description: 根据DOC文档获取文档中H因子的用户信息
     * @param @param queryParams
     * @param @return 设定文件
     * @return Map<String,BdUserInfo> 返回类型
     */
    protected abstract Map<String, T> getHUserinfoMap(SolrDocumentList docList);
    
    /**
     * @Title: getHUserinfo
     * @Description: 根据doc中的Key 从Map中获取数据
     * @param @param doc
     * @param @return 设定文件
     * @return T 返回类型
     */
    protected abstract T getHUserinfoByMap(SolrDocument doc, Map<String, T> map);
    
    /**
     * @Title: 数据去重
     * @Description: 删除相同的数据
     * @param @param list
     * @param @return 设定文件
     * @return SolrDocumentList 返回类型
     */
    protected abstract SolrDocumentList delDuplication(SolrDocumentList list);
    
}
