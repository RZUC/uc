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
import com.zhiwei.wjgg.model.HUserInfoWB;
import com.zhiwei.wjgg.solr.model.DataWeiBo;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: AnalyServiceImpleWeiBo
 * @author 落花流水
 * @date 2016年3月2日 下午4:52:16
 */
@Component("analyServiceWeiBo")
public class AnalyServiceImpleWeiBo extends AnalyServiceGeneral
{
    /**
     * @Fields DEFAULH : 默认H因子数
     */
    private static Double DEFAULH = 0.0;
    
    @Override
    public Map<String, List<String>> analyTimeLine(List list,Event event)
    {
        List<DataWeiBo> dataWeiBoList = switchSelfClass(list);
        
        Map<String, List<String>> timeLine = new HashMap<String, List<String>>();
        List<String> timeLineList = null;
        for (DataWeiBo bean : dataWeiBoList)
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
        List<DataWeiBo> dataWeiBoList = switchSelfClass(list);
        List<String> sourceList = null;
        for (DataWeiBo bean : dataWeiBoList)
        {
            String key = bean.getUsername();
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
        return null;
    }
    
    @Override
    public Double analyInfulence(List list)
    {
        List<DataWeiBo> dataWeiBoList = switchSelfClass(list);
        Double infulence = 0.0;
        int NULL = 0;
        for (DataWeiBo bean : dataWeiBoList)
        {
            HUserInfoWB userinfo = bean.getHUserInfo();
            if (null != userinfo)
            {
                
                infulence += userinfo.getChannelIndex();
            }
            else
            {
                NULL++;
                infulence += DEFAULH;// 默认影响力指数30
            }
        }
        log.debug("影响力指数分析完成*************************数据总量：{}---用户为空的数量：{}----渠道影响力：{}",
            dataWeiBoList.size(),
            NULL,
            infulence);
        return infulence;
    }
    
    private List<DataWeiBo> switchSelfClass(List list)
    {
        List<DataWeiBo> selfClassList = list;
        return selfClassList;
    }
}