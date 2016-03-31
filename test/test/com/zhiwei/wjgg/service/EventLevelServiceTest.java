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

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.dao.EventLevelDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.EventLevel;
import com.zhiwei.wjgg.service.EventLevelService;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: EventLevelDaoTest
 * @Description: TODO(测试事件危机等级)
 * @author chenweitao
 * @date 2016年2月26日 上午11:37:28
 */
public class EventLevelServiceTest extends ObjectTest
{
    @Resource
    EventLevelService service;

    @Test
    public void saveTest()
    {
        EventLevel ob = new EventLevel();
        ob.setEventLevel("Ⅰ级");
        service.add(ob);
    }

    @Test
    public void showTest()
    {

        List<EventLevel> a = service.findAll();
        for (EventLevel ob : a)
        {
            System.out.println(ob.getEventLevel());
        }
    }

    @Test
    public void remove()
    {

        System.out.println(service.deleteById("危机Ⅰ级"));
    }

    @Test
    public void updata()
    {
        EventLevel ob = new EventLevel();
        ob.setId("危机Ⅰ级");
        ob.setEventLevel("危机测试级");
        service.updata(ob);
    }
}
