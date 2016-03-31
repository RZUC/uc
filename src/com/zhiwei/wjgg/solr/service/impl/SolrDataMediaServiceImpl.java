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
import com.zhiwei.wjgg.dao.HUserInfoBDDao;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.EventTrend;
import com.zhiwei.wjgg.model.HUserInfoBD;
import com.zhiwei.wjgg.solr.dao.SolrDao;
import com.zhiwei.wjgg.solr.model.DataMedia;
import com.zhiwei.wjgg.util.RenH_Util;
import com.zhiwei.wjgg.util.SolrDocumentToBeanUtil;

/**
 * @Description: 微博实现
 * @ClassName: SolrDataSerice
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
@Component("solrMediaService")
public class SolrDataMediaServiceImpl extends
        SolrDataCommonService<HUserInfoBD>
{
    @Resource(name = "solrYuQingMediaDao")
    private SolrDao mediaYuQing;

    @Resource
    private HUserInfoBDDao baiduUserInfoDao;// 媒介渠道

    @Override
    public SolrDocumentList getSolrData(Event event)
    {
        return delDuplication(mediaYuQing.getData(event, getRsid(event)));
    }

    @Override
    protected Map<String, HUserInfoBD> getHUserinfoMap(SolrDocumentList docList)
    {
        Map<String, HUserInfoBD> map = new HashMap<String, HUserInfoBD>();

        List<HUserInfoBD> userInfoListById = baiduUserInfoDao
                .findByIdList(getQuerList(docList));

        log.debug("获取到的百度用户数量：{}", userInfoListById.size());

        if (userInfoListById != null)
        {
            for (HUserInfoBD u : userInfoListById)
            {
                map.put(u.getId(), u);
            }
        }

        return map;
    }

    @Override
    protected HUserInfoBD getHUserinfoByMap(SolrDocument doc,
            Map<String, HUserInfoBD> map)
    {
        HUserInfoBD user = map.get(getMainUrl(doc));
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

        log.debug("网媒重复的数量:{}", replate);
        log.debug("网媒数据返回的数量:{}", docList.size());

        return docList;
    }

    /**
     * @Title: getMainUrl
     * @Description: 提取主域名--网媒数据补用户数据使用
     * @param @param doc
     * @param @return 设定文件
     * @return String 返回类型
     */
    private String getMainUrl(SolrDocument doc)
    {
        String url = doc.get("_id").toString();
        if (null != url && !"".equals(url) && url.contains("http://"))
            ;
        url = url.split(",")[0].split("http://")[1].split("/")[0];
        return url;
    }

    /**
     * @Title: getQuerList
     * @Description: 获取查询条件
     * @param @param docList
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    private List<String> getQuerList(SolrDocumentList docList)
    {
        List<String> idList = new ArrayList<String>();

        for (SolrDocument doc : docList)
        {
            String url = getMainUrl(doc);
            idList.add(url);
        }
        log.debug("网媒用户去重-------------");
        return RenH_Util.quChong(idList);
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
            EventTrend eventTrend = eventTrendDao.findOne(event.getId()
                    + StaticParam.eventDataType.get("media"));
            l = eventTrend.getLastRsid();
        }
        catch (Exception e)
        {
            log.error("查询网媒RSID出错：", e.getMessage());
        }
        return l;
    }

    @Override
    public List<Map<String,Object>> getTopByH(Event event, int top)
    {
        SolrDocumentList doclist = getSolrDataWithHUserInfo(event);
        List<DataMedia> oblist = SolrDocumentToBeanUtil.getDocumentObjectBinder().getBeans(DataMedia.class, doclist);
        
        
        List<Float> H_List = new ArrayList<Float>();// 未排序的h因子对象
        List<DataMedia> null_H_List = new ArrayList<DataMedia>();// 无h因子的对象

        Map<DataMedia, Float> s_h = new LinkedMap();//为取值
        
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        
        // 获取到H因子集合
        for (DataMedia doc : oblist)
        {
            HUserInfoBD h = doc.getHUserInfo();
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
            for (DataMedia dataMedia : oblist)
            {
                if (null!=dataMedia.getHUserInfo())
                {
                    if (float1==dataMedia.getHUserInfo().getChannelIndex())
                    {
                        s_h.put(dataMedia, float1);
                    }
                }
                
            }
        }
        
        //加入无h因子的
        for (DataMedia dataMedia : null_H_List)
        {
            s_h.put(dataMedia, (float) 0.0);
        }
        
        for (Entry<DataMedia, Float> en : s_h.entrySet())
        {
            Map<String,Object> map = new HashMap();
            map.put("h", en.getValue());
            
            Map<String,String> info = new HashMap();
            info.put("url", en.getKey().getId());
            info.put("title", en.getKey().getTitle());
            info.put("time", en.getKey().getTimeString());
            info.put("source", en.getKey().getSource());
            info.put("content", en.getKey().getContent());
            
            map.put("info", info);
           
            result.add(map);
            
            if (result.size()>=top)
            {
                break;
            }
        }
        
        return result;
    }

}
