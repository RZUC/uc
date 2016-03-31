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

import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.service.EventService;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: EventLevelDaoTest
 * @Description: TODO(测试事件危机等级)
 * @author chenweitao
 * @date 2016年2月26日 上午11:37:28
 */
public class EventServiceTest extends ObjectTest
{
    @Resource
    EventService service;
    
    @Test
    public void saveTest()
    {
        Event ob = new Event();
        Map<String, Integer> topWord = new HashMap<String, Integer>();
        topWord.put("马云", 32);
        topWord.put("小龙", 21);
        topWord.put("硬盘", 12);
        topWord.put("机械", 211);
        topWord.put("光驱", 123);
        topWord.put("雷丘", 43);
        topWord.put("棒球啊", 65);
        topWord.put("就会快乐", 98);
        topWord.put("腾讯", 25);
        topWord.put("QQ", 43);
        
        ob.setTopWord(topWord);
        ob.setId("3");
        ob.setName("需徐");
        ob.setUser("tao");
        ob.setContent("走到哪死到哪");
        ob.setState("0");
        ob.setCorrelation(true);
        // ob.setCorrelationNum(85);
        ob.setCorrelationNum(85.00);
        ob.setChannelNum(45.00);
        ob.setImpact(75.00);
        ob.setSpread(80.00);
        ob.setWord("微信+微博");
        ob.setEventType("腾讯");
        ob.setStartTime("1992-03-10 06");
        ob.setEndTime("2016-02-29 09");
        ob.setEventLevel("Ⅰ级");
        System.out.println(service.add(ob));
    }
    
    @Test
    public void showTest()
    {
        
        List<Event> a = service.findAll();
        for (Event ob : a)
        {
            System.out.println(ob.toString());
        }
    }
    
    @Test
    public void remove()
    {
        
        System.out.println(service.deleteById("1"));
    }
    
    @Test
    public void updata()
    {
        Event ob = new Event();
        Map<String, Integer> topWord = new HashMap<String, Integer>();
        topWord.put("马云", 32);
        topWord.put("小龙", 21);
        topWord.put("硬盘", 12);
        topWord.put("机械", 211);
        topWord.put("光驱", 123);
        topWord.put("雷丘", 43);
        topWord.put("棒球啊", 65);
        topWord.put("就会快乐", 98);
        topWord.put("腾讯", 25);
        topWord.put("QQ", 43);
        
        ob.setTopWord(topWord);
        
        ob.setId("1");
        ob.setName("我叫工藤新一");
        ob.setUser("tao");
        ob.setContent("走到哪死到哪");
        ob.setState("1");
        ob.setCorrelation(true);
        // ob.setCorrelationNum(85);
        ob.setCorrelationNum(85.00);
        ob.setChannelNum(45.00);
        ob.setImpact(75.00);
        ob.setSpread(80.00);
        ob.setWord("微信+微博");
        ob.setEventType("死神");
        ob.setStartTime("1992-03-10 06");
        ob.setEndTime("2016-02-29 09");
        ob.setEventLevel("危机Ⅰ级");
        service.updata(ob);
    }
    
    @Test
    public void updataInfulenceAndWordTest()
    {
        Event event = service.findOneById("5");
        if (null != event)
        {
            service.updataInfulenceAndWord(event);
        }
    }
    
    @Test
    public void findCompleteEventTest()
    {
        for (Event e : service.findCompleteEvent("测试用户", 0))
        {
            System.out.println("事件名称：" + e.getName());
        }
    }
}
