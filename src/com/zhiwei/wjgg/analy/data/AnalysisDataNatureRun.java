/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月3日
    * @version 1.00 
*/
package com.zhiwei.wjgg.analy.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiwei.wjgg.analy.service.AnalyDataService;
import com.zhiwei.wjgg.conf.StaticParam;
import com.zhiwei.wjgg.dao.CommonDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.NatureTrend;
import com.zhiwei.wjgg.solr.model.DataCommon;

/**
 * @Description: 运行解析程序
 * @ClassName: AnalysisDataRun
 * @author 落花流水
 * @param <T>
 * @param <T>
 * @date 2016年3月3日 下午4:56:49
 */
public class AnalysisDataNatureRun<T extends DataCommon> implements Runnable
{
    private static final String ZHENGMIAN = "正面";
    
    private static final String FUMIAN = "负面";
    
    private static final String ZHONGXING = "中性";
    
    private static Logger log = LoggerFactory.getLogger(AnalysisDataNatureRun.class);
    
    private AnalyDataService analyService;
    
    private CommonDao dao;
    
    private List<T> dataList;
    
    private Event event;
    
    @Override
    public void run()
    {
        
        Map<String, Object> map = (Map<String, Object>)analyService.analyData(dataList,event).get("timeLine");
        Map<String, Double> zhengmianMap = new HashMap<String, Double>();
        Map<String, Double> fumianMap = new HashMap<String, Double>();
        Map<String, Double> zhongxingMap = new HashMap<String, Double>();
        
        Map<String, Map<String, Double>> natureMap = new HashMap<String, Map<String, Double>>();
        
        for (String key : map.keySet())
        {
            Map<String, Double> timeLineMap = (Map<String, Double>)map.get(key);
            zhengmianMap.put(key, timeLineMap.get(ZHENGMIAN));
            fumianMap.put(key, timeLineMap.get(FUMIAN));
            zhongxingMap.put(key, timeLineMap.get(ZHONGXING));
        }
        natureMap.put(ZHENGMIAN, zhengmianMap);
        natureMap.put(FUMIAN, fumianMap);
        natureMap.put(ZHONGXING, zhongxingMap);
        log.debug("舆情图整合后的数据：{}", natureMap);
        
        addEvent(natureMap, event);
    }
    
    /**
     * @param event2
     * @param natureMap
     * @Title: addEvent
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param 设定文件
     * @return void 返回类型
     */
    private void addEvent(Map<String, Map<String, Double>> natureMap, Event event2)
    {
        try
        {
            NatureTrend natureTrend = null;
            for (String type : natureMap.keySet())
            {
                natureTrend = new NatureTrend();
                natureTrend.setEventId(event2.getId());
                natureTrend.setId(event2.getId() + StaticParam.emotionType.get(type));
                natureTrend.setType(StaticParam.emotionType.get(type).toString());
                natureTrend.setTime_num(natureMap.get(type));
                dao.insert(natureTrend);
            }
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * @Title: AnalysisDataRun
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param analyService
     * @param @param dataList 设定文件
     * @return
     */
    public AnalysisDataNatureRun(AnalyDataService analyService, List<T> dataList, CommonDao dao, Event event)
    {
        super();
        this.analyService = analyService;
        this.dataList = dataList;
        this.dao = dao;
        this.event = event;
    }
}
