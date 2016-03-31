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

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.dao.EventLevelDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.EventLevel;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: EventLevelDaoTest
 * @Description: TODO(测试事件危机等级)
 * @author chenweitao
 * @date 2016年2月26日 上午11:37:28
 */
public class EventLevelDaoTest extends ObjectTest {
    @Resource
    EventLevelDao dao;

    @Test
    public void saveTest() {
	EventLevel ob = new EventLevel();
	ob.setEventLevel("Ⅱ级");// Ⅰ,Ⅱ,Ⅲ,Ⅳ,Ⅴ,Ⅵ,Ⅶ
	try {
	    dao.insert(ob);
	} catch (ZhiWeiException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void showTest() {
	try {
	    List<EventLevel> a = dao.findAll();
	    for (EventLevel ob : a) {
		System.out.println(ob.getEventLevel());
	    }
	} catch (ZhiWeiException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void remove() {
	try {
	    System.out.println(dao.removeOneById("危机Ⅰ级"));
	} catch (ZhiWeiException e) {
	    System.out.println(false);
	}
    }

}
