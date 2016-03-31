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
package test.com.zhiwei.wjgg.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.dao.EventDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Event;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: EventDaoTest
 * @Description: TODO(测试事件危机等级)
 * @author chenweitao
 * @date 2016年2月26日 上午11:37:28
 */
public class EventDaoTest extends ObjectTest
{
    @Resource
    EventDao dao;
    
    @Test
    public void saveTest()
    {
        Event ob = new Event();
        Map<String, Integer> topWord = new HashMap<String, Integer>();
        topWord.put("马云", 32);
        topWord.put("马云", 21);
        topWord.put("马云", 12);
        topWord.put("马云", 211);
        topWord.put("马云", 123);
        topWord.put("马云", 43);
        topWord.put("马云", 65);
        topWord.put("马云", 98);
        topWord.put("马云", 25);
        topWord.put("马云", 43);
        
        ob.setTopWord(topWord);
        ob.setId("2");
        ob.setUser("tao");
        ob.setName("我叫柯南");
        ob.setContent("走到哪死到哪");
        ob.setState("1");
        ob.setCorrelation(true);
        ob.setCorrelationNum(85.00);
        ob.setChannelNum(45.00);
        ob.setImpact(75.00);
        ob.setSpread(80.00);
        ob.setWord("微信+微博");
        
        ob.setEventType("死神");
        ob.setStartTime("1992-03-10 06");
        ob.setEndTime("2016-02-29 09");
        ob.setEventLevel("Ⅰ级");
        try
        {
            dao.insert(ob);
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void showTest()
    {
        try
        {
            List<Event> a = dao.findAll();
            for (Event ob : a)
            {
                System.out.println(ob);
            }
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void remove()
    {
        try
        {
            System.out.println(dao.removeOneById("Ⅰ级"));
        }
        catch (ZhiWeiException e)
        {
            System.out.println(false);
        }
    }
    
    @Test
    public void findByTypeTest()
    {
        try
        {
            List<Event> obList = dao.findEventsByType("腾讯","");
            for (Event ob : obList)
            {
                System.out.println(ob.getName());
            }
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void findAliveEventTest()
    {
        try
        {
            List<Event> obList = dao.findAliveEvent();
            for (Event ob : obList)
            {
                System.out.println(ob.getName());
            }
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
    }
    
}
