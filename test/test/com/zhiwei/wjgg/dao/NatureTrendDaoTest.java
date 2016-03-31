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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.dao.EventTrendDao;
import com.zhiwei.wjgg.dao.NatureTrendDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.EventTrend;
import com.zhiwei.wjgg.model.NatureTrend;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: NatureTrendDaoTest
 * @Description: TODO(测试)
 * @author chenweitao
 * @date 2016年2月26日 上午11:37:28
 */
public class NatureTrendDaoTest extends ObjectTest
{
    @Resource
    NatureTrendDao dao;

    @Test
    public void saveTest()
    {
        NatureTrend ob = new NatureTrend();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("2012-12-16 09", 2131);
        // ob.setTime_num(map);
        ob.setEventId("1");
        ob.setType("全部");
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
            List<NatureTrend> a = dao.findAll();
            for (NatureTrend ob : a)
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
            System.out.println(dao.removeOneById("1"));
        }
        catch (ZhiWeiException e)
        {
            System.out.println(false);
        }
    }

    @Test
    public void findByEventIdInTime()
    {
        String eventId = "1";
        String start = "2012-12-16 05";
        String end = "2012-12-16 07";
        String type = "正面";

        NatureTrend ob = dao.findByEventIdInTime(eventId, type);

        System.out.println(ob.toString());
    }

}
