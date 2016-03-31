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
import com.zhiwei.wjgg.dao.HUserInfoWBDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.EventTrend;
import com.zhiwei.wjgg.model.HUserInfoWB;
import com.zhiwei.wjgg.solr.dao.SolrDao;
import com.zhiwei.wjgg.solr.model.DataMedia;
import com.zhiwei.wjgg.solr.model.DataWeiBo;
import com.zhiwei.wjgg.util.MidAurl;
import com.zhiwei.wjgg.util.RenH_Util;
import com.zhiwei.wjgg.util.SolrDocumentToBeanUtil;
import com.zhiwei.wjgg.util.UrlUtil;
import com.zhiwei.wjgg.util.WordsCount;

/**
 * @Description: 微博实现
 * @ClassName: SolrDataSerice
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
@Component("solrWeiboService")
public class SolrDataWeiboServiceImpl extends SolrDataCommonService<HUserInfoWB>
{
    @Resource(name = "solrWeiboDao")
    private SolrDao weiboSolr;
    
    @Resource
    private HUserInfoWBDao weiboUserInfoDao;// 媒介渠道
    
    @Override
    public SolrDocumentList getSolrData(Event event)
    {
        return delDuplication(weiboSolr.getData(event,
            getRsid(event)));
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
            EventTrend eventTrend = eventTrendDao.findOne(event.getId() + StaticParam.eventDataType.get("weibo"));
            l = eventTrend.getLastRsid();
        }
        catch (Exception e)
        {
            log.error("查询网媒RSID出错：", e.getMessage());
        }
        return l;
    }
    
    @Override
    protected Map<String, HUserInfoWB> getHUserinfoMap(SolrDocumentList docList)
    {
        Map<String, HUserInfoWB> map = new HashMap<String, HUserInfoWB>();
        
        List<HUserInfoWB> userList = weiboUserInfoDao.findByIdList(getWeiBoUserIds(docList));
        
        log.debug("获取到的微博用户数量：{}", userList.size());
        
        if (userList != null)
        {
            for (HUserInfoWB u : userList)
            {
                map.put(u.getId(), u);
            }
        }
        
        return map;
    }
    
    /**
     * @Title: getWeiBoUserIds
     * @Description: 提取id
     * @param @param docList
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    private List<String> getWeiBoUserIds(SolrDocumentList docList)
    {
        List<String> idList = new ArrayList<String>();
        for (SolrDocument doc : docList)
        {
            idList.add(doc.get("user_id").toString());
        }
        log.debug("用户去重--------------");
        return RenH_Util.quChong(idList);
    }
    
    @Override
    protected HUserInfoWB getHUserinfoByMap(SolrDocument doc, Map<String, HUserInfoWB> map)
    {
        HUserInfoWB user = map.get(doc.get("user_id").toString());
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
            String key = doc.get("_id").toString();
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
        
        log.debug("微博重复的数量:{}", replate);
        log.debug("微博数据返回的数量:{}", docList.size());
        
        return docList;
    }

    @Override
    public List<Map<String,Object>> getTopByH(Event event,int top)
    {
        SolrDocumentList doclist = getSolrDataWithHUserInfo(event);
        List<DataWeiBo> oblist = SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataWeiBo.class, doclist);
        
        
        List<Float> H_List = new ArrayList<Float>();// 未排序的h因子对象
        List<DataWeiBo> null_H_List = new ArrayList<DataWeiBo>();// 无h因子的对象

        Map<DataWeiBo, Float> s_h = new LinkedMap();//为取值
        
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        
        // 获取到H因子集合
        for (DataWeiBo doc : oblist)
        {
            HUserInfoWB h = doc.getHUserInfo();
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
            for (DataWeiBo data : oblist)
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
        for (DataWeiBo data : null_H_List)
        {
            s_h.put(data, (float) 0.0);
        }
        
        for (Entry<DataWeiBo, Float> en : s_h.entrySet())
        {
            Map<String,Object> map = new HashMap();
            Map<String,String> info = new HashMap();
            info.put("url", "http://weibo.com/" + en.getKey().getUser_id()+ "/"
                    + MidAurl.mid2url(en.getKey().getId()));
            info.put("time", en.getKey().getTimeString());
            info.put("username", en.getKey().getUsername());
            info.put("text", en.getKey().getText());
            
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
