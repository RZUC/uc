/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月2日
    * @version 1.00 
*/
package com.zhiwei.wjgg.analy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.HUserInfoWX;
import com.zhiwei.wjgg.solr.model.DataWeiXin;
import com.zhiwei.wjgg.util.RenH_Util;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: AnalyServiceImpleWeiBo
 * @author 落花流水
 * @date 2016年3月2日 下午4:52:16
 */
@Component("analyServiceWeiXin")
public class AnalyServiceImpleWeiXin extends AnalyServiceGeneral
{
    /**
     * @Fields DEFAULH : 默认H因子数
     */
    private static Double DEFAULH = 0.0;
    
    @Override
    public Map<String, List<String>> analyTimeLine(List list, Event event)
    {
        List<DataWeiXin> dataWeiBoList = switchSelfClass(list);
        
        Map<String, List<String>> timeLine = new HashMap<String, List<String>>();
        List<String> timeLineList = null;
        for (DataWeiXin bean : dataWeiBoList)
        {
            String key = bean.getTimeString().split(":")[0];
            
            if (timeLine.containsKey(key))
            {
                timeLineList = timeLine.get(key);
                timeLineList.add(bean.getId());
                timeLine.put(key, timeLineList);
            }
            else
            {
                timeLineList = new ArrayList<String>();
                timeLineList.add(bean.getId());
                timeLine.put(key, timeLineList);
            }
        }
        return timeLine;
    }
    
    @Override
    public Map<String, List> analySpreadSource(List list)
    {
        Map<String, List> map = new HashMap<String, List>();
        List<DataWeiXin> dataWeiBoList = switchSelfClass(list);
        
        List<String> sourceList = null;
        for (DataWeiXin bean : dataWeiBoList)
        {
            String key = bean.getSource();
            key = null == key || "".equals(key) ? "未知" : key.replaceAll("\\.", "-");
            
            if (map.containsKey(key))
            {
                sourceList = map.get(key);
                sourceList.add(bean.getId());
                map.put(key, sourceList);
            }
            else
            {
                sourceList = new ArrayList<String>();
                sourceList.add(bean.getId());
                map.put(key, sourceList);
            }
        }
        
        return map;
    }
    
    @Override
    public Map<String, Integer> similarTitle(List list)
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<DataWeiXin> dataWeiBoList = switchSelfClass(list);
        
        for (DataWeiXin bean : dataWeiBoList)
        {
            String source = bean.getTitle().replaceAll("\\.", "-");
            RenH_Util.plusKey(map, source);
        }
        
        return map;
    }
    
    @Override
    public Double analyInfulence(List list)
    {
        List<DataWeiXin> dataWeiBoList = switchSelfClass(list);
        Double infulence = 0.0;
        int Null = 0;
        for (DataWeiXin bean : dataWeiBoList)
        {
            
            HUserInfoWX userinfo = bean.getHUserInfo();
            if (null != userinfo)
            {
                infulence += userinfo.getChannelIndex();
            }
            else
            {
                Null++;
                infulence += DEFAULH;
            }
        }
        
        log.debug("影响力指数分析完成*************************数据总量：{}---用户为空的数量：{}----渠道影响力：{}",
            dataWeiBoList.size(),
            Null,
            infulence);
        return infulence;
    }
    
    private List<DataWeiXin> switchSelfClass(List list)
    {
        List<DataWeiXin> selfClassList = list;
        return selfClassList;
    }
}