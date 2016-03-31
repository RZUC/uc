/**
 * ***************************************************
 * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
 *****************************************************
 * 类的详细说明 
 * 
 * @author 东临碣石
 * @Date 2016年1月15日
 * @version 1.00 
 */
package test.com.zhiwei.wjgg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.EventTrend;
import com.zhiwei.wjgg.model.EventUpate;
import com.zhiwei.wjgg.service.EventTrendService;
import com.zhiwei.wjgg.util.MapUtil;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: EventTrendServiceTest
 * @Description: TODO(测试)
 * @author chenweitao
 * @date 2016年2月26日 上午11:37:28
 */
public class EventTrendServiceTest extends ObjectTest
{
    @Resource
    EventTrendService service;
    
    @Test
    public void saveTest()
    {
        EventTrend ob = new EventTrend();
        Map<String, List> map = new HashMap<String, List>();
        map.put("2012-12-16 09", new ArrayList());
        ob.setTime_num(map);
        ob.setEventId("1");
        ob.setType("all");
        System.out.println(service.add(ob));
    }
    
    @Test
    public void showByIdTest()
    {
        Map<String, List> map = service.findByEventIdInTime("1", "all").getTime_num();
        // s
        Map<String, List> map1 = MapUtil.treatOrderByKeyAsc(map);
        
        System.out.println(map1);
    }
    
    @Test
    public void showTest()
    {
        List<EventTrend> a = service.findAll();
        for (EventTrend ob : a)
        {
            System.out.println(ob.toString());
        }
    }
    
    @Test
    public void addTest()
    {
        service.addAllByThree("1");
    }
    
    @Test
    public void findEventUpataByEventTest()
    {
        String statrTime = "2016-02-27 00:00:00";
        String endTime = "2016-02-29 23:59:59";
        Event event = new Event();
        
        event.setId("测试");
        event.setStartTime(statrTime);
        event.setEndTime(endTime);
        event.setWord("奶茶");
        EventUpate e = service.findEventUpataByEvent(event);
        System.out.println(e);
    }
    
    @Test
    public void addInfoByObTest()
    {
        EventTrend ob = new EventTrend();
        Map<String, List> map = new HashMap<String, List>();
        List valueList = new ArrayList();
        for (int i = 0; i < 5; i++)
        {
            valueList.add("測試連接--" + i);
        }
        map.put("2012-12-31 23", valueList);
        map.put("2012-12-31 22", valueList);
        map.put("2012-12-31 21", valueList);
        ob.setTime_num(map);
        map.put("2012-12-31 20", valueList);
        ob.setSource(map);
        ob.setEventId("addInfoByObTest");
        ob.setType("addInfoByObTest");
        ob.setDataCount(10);
        ob.setInfulence(10.23);
        ob.setLastRsid(110);
        System.out.println(service.addInfoByOb(ob));
    }
    
}
