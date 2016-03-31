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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.solr.model.DataMark;
import com.zhiwei.wjgg.util.TimeUtil;

/**
 * @ClassName: AnalyServiceImpleWeiBo
 * @author 落花流水
 * @date 2016年3月2日 下午4:52:16
 */
@Component("analyServiceNature")
public class AnalyServiceImpleNature extends AnalyServiceGeneral
{
    
    private static final String ZHENGMIAN = "正面";
    
    private static final String FUMIAN = "负面";
    
    private static final String ZHONGXIN = "中性";
    
    @Override
    public Map analyTimeLine(List list, Event event)
    {
        List<DataMark> dataWeiBoList = switchSelfClass(list);
        Map<String, Map<String, Double>> timeLine = new HashMap<String, Map<String, Double>>();
        Map<String, List<String>> classifierMap = classifier(dataWeiBoList);
        
        Map<String, Double> timeLineV = null;
        for (Entry<String, List<String>> ent : classifierMap.entrySet())
        {
            String key = ent.getKey();
            timeLineV = new HashMap<String, Double>();
            if (ent.getValue().size() > 0)
            {
                timeLine.put(key, getTimeValue(ent.getValue()));
            }
        }
        
        return timeLine;
    }
    
    /**
     * @Title: getTimeValue
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param timeLineV
     * @param @return 设定文件
     * @return Map<String,String> 返回类型
     */
    private Map<String, Double> getTimeValue(List<String> list)
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(ZHENGMIAN, 0);
        map.put(FUMIAN, 0);
        map.put(ZHONGXIN, 0);
        for (String value : list)
        {
            int emotion = Integer.valueOf(value);
            if (emotion == 1)
            {
                map.put(ZHENGMIAN, map.get(ZHENGMIAN) + 1);
            }
            else if (emotion == 2)
            {
                map.put(FUMIAN, map.get(FUMIAN) + 1);
            }
            else
            {
                map.put(ZHONGXIN, map.get(ZHONGXIN) + 1);
            }
            
        }
        
        return percentMap(map);
    }
    
    @Override
    public Double analyInfulence(List list)
    {
        return null;
    }
    
    @Override
    public Map<String, List> analySpreadSource(List list)
    {
        return null;
    }
    
    @Override
    public Map<String, Integer> similarTitle(List list)
    {
        return null;
    }
    
    private Map<String, List<String>> classifier(List<DataMark> list)
    {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> sourceList = new ArrayList<String>();
        for (DataMark bean : list)
        {
            String key = bean.getTimeString().split(":")[0];
            String emotiom = bean.getEmotionTypeStr();
            if (map.containsKey(key))
            {
                sourceList = map.get(key);
                sourceList.add(emotiom);
                map.put(key, sourceList);
            }
            else
            {
                sourceList = new ArrayList<String>();
                sourceList.add(emotiom);
                map.put(key, sourceList);
            }
        }
        return map;
    }
    
    private List<DataMark> switchSelfClass(List list)
    {
        List<DataMark> selfClassList = list;
        
        return selfClassList;
    }
    
    /**
     * @Title: percentMap
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param map <String,Integer>
     * @return Map<String,String> 返回类型
     */
    private Map<String, Double> percentMap(Map<String, Integer> map)
    {
        
        Map<String, Double> newMap = new HashMap<String, Double>();
        Integer total = 0;
        for (Entry e : map.entrySet())
        {
            total += Integer.valueOf(e.getValue().toString());
        }
        
        for (String str : map.keySet())
        {
            double f = map.get(str).doubleValue() / total * 100;
            
            // 保留两位小数
            BigDecimal b = new BigDecimal(f);
            double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            
            newMap.put(str, f1);
        }
        return newMap;
    }
    
    /**
     * @Title: getTimeMap
     * @Description: 获取时间曲线，0-23，获取舆情数据
     * @param @param start
     * @param @param end
     * @param @return 设定文件
     * @return Map<String,List<String>> 返回类型
     */
    public synchronized Map<String, Map<String, Double>> getTimeMapNature(String start, String end)
    {
        
        Map<String, Map<String, Double>> map = new LinkedHashMap<String, Map<String, Double>>();
        try
        {
            Date baseTime = TimeUtil.parseTime(start);
            
            String startStr = start.split(":")[0];
            String endStr = end.split(":")[0];
            
            map.put(startStr, new HashMap<String, Double>());
            while (true)
            {
                Date time = TimeUtil.getSkipTime(baseTime, 0, 1, 0, 0);
                String now = TimeUtil.formatTime(time).split(":")[0];
                
                if (!now.equals(endStr))
                {
                    map.put(now, new HashMap<String, Double>());
                    baseTime = time;
                }
                else
                {
                    break;
                }
            }
            map.put(endStr, new HashMap<String, Double>());
        }
        catch (Exception e)
        {
            log.error("开始时间结束时间格式异常Message：{} 【开始时间：{}----结束时间：{}】", e.getMessage(), start, end);
        }
        
        return map;
    }
}