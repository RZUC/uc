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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import com.zhiwei.wjgg.conf.StaticParam;
import com.zhiwei.wjgg.dao.HUserInfoWXDao;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.EventTrend;
import com.zhiwei.wjgg.model.HUserInfoWX;
import com.zhiwei.wjgg.solr.dao.SolrDao;
import com.zhiwei.wjgg.solr.model.DataMedia;
import com.zhiwei.wjgg.solr.model.DataWeiBo;
import com.zhiwei.wjgg.solr.model.DataWeiXin;
import com.zhiwei.wjgg.util.RenH_Util;
import com.zhiwei.wjgg.util.SolrDocumentToBeanUtil;

/**
 * @Description: 微信数据获取
 * @ClassName: SolrDataSerice
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
@Component("solrWeiXinService")
public class SolrDataWeiXinServiceImpl extends SolrDataCommonService<HUserInfoWX>
{
    private static final String MATCHKEY = "source";
    
    @Resource(name = "solrYuQingWeiXinDao")
    private SolrDao weixinSolrYuQing;
    
    @Resource
    private HUserInfoWXDao WxUserionfDao;
    
    @Override
    public SolrDocumentList getSolrData(Event event)
    {
        SolrDocumentList weixinSolrYuQingList = weixinSolrYuQing.getData(event, getRsid(event));
        return delDuplication(weixinSolrYuQingList);
    }
    
    @Override
    protected Map<String, HUserInfoWX> getHUserinfoMap(SolrDocumentList docList)
    {
        
        Map<String, HUserInfoWX> map = new HashMap<String, HUserInfoWX>();
        
        Map<String, List<String>> idListMap = getQuerList(docList);
        
        List<HUserInfoWX> userInfoListByOpenId = WxUserionfDao.findByIdList(idListMap.get("openId"));
        
        List<HUserInfoWX> userInfoListByOpenSource = WxUserionfDao.findBySourceList(idListMap.get("source"));
        
        log.debug("openID用户数量：{}", userInfoListByOpenId.size());
        log.debug("Source用户数量：{}", userInfoListByOpenSource.size());
        
        if (userInfoListByOpenId != null)
        {
            for (HUserInfoWX u : userInfoListByOpenId)
            {
                map.put(u.getId(), u);
            }
        }
        if (userInfoListByOpenSource != null)
        {
            for (HUserInfoWX u : userInfoListByOpenSource)
            {
                map.put(u.getUsername(), u);
            }
        }
        return map;
    }
    
    /**
     * @Title: getQuerList
     * @Description: 获取查询条件
     * @param @param docList
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    private Map<String, List<String>> getQuerList(SolrDocumentList docList)
    {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> idList = new ArrayList<String>();
        List<String> sourceList = new ArrayList<String>();
        
        for (SolrDocument doc : docList)
        {
            String openId = getOpenId(doc);
            if (null == openId)
            {
                sourceList.add(doc.get("source").toString());
            }
            else
            {
                idList.add(openId);
            }
            
        }
        
        log.debug("openID去重----------------");
        map.put("openId", RenH_Util.quChong(idList));
        log.debug("source去重----------------");
        map.put("source", RenH_Util.quChong(sourceList));
        
        return map;
    }
    
    /**
     * @Title: getOpenId
     * @Description: 获取OpenId
     * @param @param string
     * @param @return 设定文件
     * @return String 返回类型
     */
    private String getOpenId(SolrDocument doc)
    {
        String openid = null;
        try
        {
            openid = doc.get("type").toString().split(",")[1];
            openid = "http://weixin.sogou.com/gzh?openid=" + openid;
        }
        catch (Exception e)
        {
            // log.info("没有opendId:{}", doc.get("_id"));
        }
        return openid;
    }
    
    @Override
    protected HUserInfoWX getHUserinfoByMap(SolrDocument doc, Map<String, HUserInfoWX> map)
    {
        HUserInfoWX user = null;
        user = map.get(getOpenId(doc));
        
        if (null == user)
        {
            user = map.get(doc.get(MATCHKEY));
        }
        return user;
    }
    
    @Override
    protected SolrDocumentList delDuplication(SolrDocumentList list)
    {
        int replate = 0;
        Map<String, SolrDocument> map = new HashMap<String, SolrDocument>();
        SolrDocumentList docList = new SolrDocumentList();
        for (SolrDocument doc : list)
        {
            String key = null == doc.get("_id") ? doc.get("id").toString() : doc.get("_id").toString();
            if (map.containsKey(key))
            {
                replate++;
            }
            map.put(key, doc);
        }
        
        for (Entry<String, SolrDocument> entry : map.entrySet())
        {
            docList.add(entry.getValue());
        }
        
        log.debug("微信重复的数量:{}", replate);
        log.debug("微信数据返回的数量:{}", docList.size());
        
        return docList;
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
        Long l = null;
        try
        {
            EventTrend eventTrend = eventTrendDao.findOne(event.getId() + StaticParam.eventDataType.get("weixin"));
            l = eventTrend.getLastRsid();
        }
        catch (Exception e)
        {
            log.error("查询微信RSID出错：", e.getMessage());
        }
        return l;
    }

    @Override
    public List<Map<String,Object>> getTopByH(Event event,int top)
    {
        SolrDocumentList doclist = getSolrDataWithHUserInfo(event);
        List<DataWeiXin> oblist = SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataWeiXin.class, doclist);
        
        
        List<Float> H_List = new ArrayList<Float>();// 未排序的h因子对象
        List<DataWeiXin> null_H_List = new ArrayList<DataWeiXin>();// 无h因子的对象

        Map<DataWeiXin, Float> s_h = new LinkedMap();//为取值
        
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        
        // 获取到H因子集合
        for (DataWeiXin doc : oblist)
        {
            HUserInfoWX h = doc.getHUserInfo();
            if (h != null)
            {
                H_List.add(h.getChannelIndex());
            }
            else
            {
                null_H_List.add(doc);
            }

        }

        // 对h因子排序
        Collections.sort(H_List);
        Collections.reverse(H_List);
        //根据排序获取对象
        for (Float float1 : H_List)
        {
            for (DataWeiXin data : oblist)
            {
                if (null!=data.getHUserInfo())
                {
                    if (float1==data.getHUserInfo().getChannelIndex())
                    {
                        s_h.put(data, float1);
                    }
                }
                
            }
        }
        //加入无h因子的
        for (DataWeiXin data : null_H_List)
        {
            s_h.put(data, (float) 0.0);
        }
        
        for (Entry<DataWeiXin, Float> en : s_h.entrySet())
        {
            Map<String,Object> map = new HashMap();
            Map<String,String> info = new HashMap();
            info.put("url", en.getKey().getId());
            info.put("title", en.getKey().getTitle());
            info.put("time", en.getKey().getTimeString());
            info.put("source", en.getKey().getSource());
            info.put("content", en.getKey().getContent());
            
            map.put("info", info);
            map.put("h", en.getValue());
            result.add(map);
            
            if (result.size()>=top)
            {
                break;
            }
        }
        
        return result;
    }
}
