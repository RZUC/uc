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
import com.zhiwei.wjgg.dao.EventTypeDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.EventLevel;
import com.zhiwei.wjgg.model.EventType;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: EventLevelDaoTest
 * @Description: TODO(测试事件危机等级)
 * @author chenweitao
 * @date 2016年2月26日 上午11:37:28
 */
public class EventTypeDaoTest extends ObjectTest {
    @Resource
    EventTypeDao dao;

    @Test
    public void saveTest() {
	EventType ob = new EventType();
	ob.setEventType("QQ");
	try {
	    dao.insert(ob);
	} catch (ZhiWeiException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void showTest() {
	try {
	    List<EventType> a = dao.findAll();
	    for (EventType ob : a) {
		System.out.println(ob.toString());
	    }
	} catch (ZhiWeiException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void remove() {
	try {
	    System.out.println(dao.removeOneById("腾讯"));
	} catch (ZhiWeiException e) {
	    System.out.println(false);
	}
    }

}
