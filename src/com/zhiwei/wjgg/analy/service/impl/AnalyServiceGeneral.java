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
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zhiwei.wjgg.analy.service.AnalyDataService;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.solr.model.DataCommon;
import com.zhiwei.wjgg.solr.model.DataWeiBo;
import com.zhiwei.wjgg.util.TimeUtil;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: AnalyServiceImpleWeiBo
 * @author 落花流水
 * @date 2016年3月2日 下午4:52:16
 */
@Component("analyService")
public abstract class AnalyServiceGeneral implements AnalyDataService
{
    public final static Logger log = LoggerFactory.getLogger(AnalyServiceGeneral.class);
    
    @Override
    public Map<String, Object> analyData(List list, Event event)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("timeLine", analyTimeLine(list, event));
        map.put("spredSource", analySpreadSource(list));
        map.put("similarTitle", similarTitle(list));
        map.put("infulence", analyInfulence(list)); // 平台影响力
        map.put("dataCount", list.size());
        map.put("lasrsid", getListRsid(list));
        return map;
    }
    
    /**
     * @Title: getListRsid
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param list
     * @param @return 设定文件
     * @return Object 返回类型
     */
    private <T extends DataCommon> long getListRsid(List<T> list)
    {
        // TODO Auto-generated method stub
        long maxRsid = 0;
        
        for (DataCommon bean : list)
        {
            if (bean instanceof DataWeiBo)
            {
                
                maxRsid = Math.max(maxRsid, bean.getRstime());
            }
            maxRsid = Math.max(maxRsid, bean.getRsid());
        }
        return maxRsid;
    }
    
    /**
     * @Title: analyInfulence
     * @Description: 计算影响力
     * @param @param list
     * @param @return 设定文件
     * @return Object 返回类型
     */
    public abstract Double analyInfulence(List list);
    
    /**
     * @param event
     * @Title: analyTimeLine
     * @Description: 解析时间线
     * @param @param list
     * @param @return 设定文件
     * @return Map<String,Integer> 返回类型
     */
    public abstract Map<String, List<String>> analyTimeLine(List list, Event event);
    
    /**
     * @Title: analySpreadSource
     * @Description: 解析时间来源
     * @param @param list
     * @param @return 设定文件
     * @return Map<String,List> 返回类型
     */
    public abstract Map<String, List> analySpreadSource(List list);
    
    /**
     * @Title: similarTitle
     * @Description: 解析数据相似标题<b>只有网媒和转发有</b>
     * @param @param list
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    public abstract Map<String, Integer> similarTitle(List list);
    
    /**
     * @Title: getTimeMap
     * @Description: 获取时间曲线，0-23，若输入的日期格式异常责返回new
     * @param @param start
     * @param @param end
     * @param @return 设定文件
     * @return Map<String,List<String>> 返回类型
     */
    public synchronized Map<String, List<String>> getTimeMap(String start, String end)
    {
        
        Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
        try
        {
            Date baseTime = TimeUtil.parseTime(start);
            
            String startStr = start.split(":")[0];
            String endStr = end.split(":")[0];
            
            map.put(startStr, new ArrayList<String>());
            while (true)
            {
                Date time = TimeUtil.getSkipTime(baseTime, 0, 1, 0, 0);
                String now = TimeUtil.formatTime(time).split(":")[0];
                
                if (!now.equals(endStr))
                {
                    map.put(now, new ArrayList<String>());
                    baseTime = time;
                }
                else
                {
                    break;
                }
            }
            map.put(endStr, new ArrayList<String>());
        }
        catch (Exception e)
        {
            log.error("开始时间结束时间格式异常Message：{} 【开始时间：{}----结束时间：{}】", e.getMessage(), start, end);
        }
        
        return map;
    }
    
}
