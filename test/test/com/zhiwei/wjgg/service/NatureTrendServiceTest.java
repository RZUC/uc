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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.dao.EventLevelDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.EventKeyPoint;
import com.zhiwei.wjgg.model.EventLevel;
import com.zhiwei.wjgg.model.EventTrend;
import com.zhiwei.wjgg.model.NatureTrend;
import com.zhiwei.wjgg.service.EventKeyPointService;
import com.zhiwei.wjgg.service.EventLevelService;
import com.zhiwei.wjgg.service.EventTrendService;
import com.zhiwei.wjgg.service.NatureTrendService;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: EventTrendServiceTest
 * @Description: TODO(测试)
 * @author chenweitao
 * @date 2016年2月26日 上午11:37:28
 */
public class NatureTrendServiceTest extends ObjectTest
{
    @Resource
    NatureTrendService service;
    
    // @Test
    // public void saveTest()
    // {
    // NatureTrend ob = new NatureTrend();
    // Map<String, Integer> map = new HashMap<String, Integer>();
    //
    // map.put("2012-12-16 09", 2134);
    //// ob.setTime_num(map);
    // ob.setEventId("2");
    // ob.setType("中性");
    // System.out.println(service.add(ob));
    // }
    
    @Test
    public void showTest()
    {
        
        NatureTrend a = service.findByEventIdInTime("5", "1");
        System.out.println("=========" + a.getTime_num());
    }
    
    @Test
    public void remove()
    {
        System.out.println(service.deleteById(""));
    }
    
    @Test
    public void addInfoByObTest()
    {
        NatureTrend ob = new NatureTrend();
        ob.setEventId("addInfoByObTest");
        ob.setId("addInfoByObTest");
        ob.setType("addInfoByObTest");
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("2016-03-02 23", 1.0);
        map.put("2016-03-03 00", 2.0);
        map.put("2016-03-03 01", 3.0);
        map.put("2016-03-03 02", 4.0);
        map.put("2016-03-03 03", 5.0);
        map.put("2016-03-03 04", 5.0);
        map.put("2016-03-03 05", 5.0);
        map.put("2016-03-03 06", 5.0);
        map.put("2016-03-03 06", 6.6);
        map.put("2016-03-03 06", 6.0);
        ob.setTime_num(map);
        
        service.addInfoByOb(ob);
    }
}
