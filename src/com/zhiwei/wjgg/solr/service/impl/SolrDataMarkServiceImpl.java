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
package com.zhiwei.wjgg.solr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import com.zhiwei.wjgg.conf.StaticParam;
import com.zhiwei.wjgg.dao.NatureTrendDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.EventTrend;
import com.zhiwei.wjgg.model.HUserInfoWX;
import com.zhiwei.wjgg.solr.dao.SolrDao;
import com.zhiwei.wjgg.util.WordsCount;

/**
 * @Description: 微信数据获取
 * @ClassName: SolrDataSerice
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
@Component("solrMarkService")
public class SolrDataMarkServiceImpl extends SolrDataCommonService<HUserInfoWX>
{
    
    @Resource(name = "solrMarkWeiboDao")
    private SolrDao markSolrWeibo;
    
    @Resource(name = "solrMarkOtherDao")
    private SolrDao markSolrOther;
    
    @Resource
    private NatureTrendDao natureTrend;
    
    @Override
    public SolrDocumentList getSolrData(Event event)
    {
        
        SolrDocumentList list = new SolrDocumentList();
        
        SolrDocumentList weiboSolrList = markSolrWeibo.getData(event, null);
        
        SolrDocumentList solrMarkOtherList = markSolrOther.getData(event, null);
        
        if (null != weiboSolrList)
        {
            list.addAll(weiboSolrList);
        }
        if (null != solrMarkOtherList)
        {
            list.addAll(solrMarkOtherList);
        }
        
        return delDuplication(list);
    }
    
    /**
     * @Title: getRsid
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param event
     * @param @return 设定文件
     * @return Long 返回类型
     */
    private Long getRsid(Event event)
    {
        return null;
    }
    
    @Override
    protected Map<String, HUserInfoWX> getHUserinfoMap(SolrDocumentList docList)
    {
        return null;
    }
    
    @Override
    protected HUserInfoWX getHUserinfoByMap(SolrDocument doc, Map<String, HUserInfoWX> map)
    {
        return null;
    }
    
    @Override
    protected SolrDocumentList delDuplication(SolrDocumentList list)
    {
        int replate = 0;
        Map<String, SolrDocument> map = new HashMap<String, SolrDocument>();
        SolrDocumentList docList = new SolrDocumentList();
        for (SolrDocument doc : list)
        {
            String mid = doc.get("mid").toString();
            String key = mid;
            if (map.containsKey(key))
            {
                log.debug("已存在该记录：{}", doc);
                replate++;
            }
            map.put(key, doc);
        }
        
        for (Entry<String, SolrDocument> entry : map.entrySet())
        {
            docList.add(entry.getValue());
        }
        log.debug("重复个数:{}", replate);
        return list;
    }

    @Override
    public List<Map<String, Object>> getTopByH(Event event,int top)
    {
        // TODO Auto-generated method stub
        return null;
    }


}
