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

import com.zhiwei.wjgg.dao.EventLevelDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.EventKeyPoint;
import com.zhiwei.wjgg.model.EventLevel;
import com.zhiwei.wjgg.model.NatureKeyPoint;
import com.zhiwei.wjgg.service.EventKeyPointService;
import com.zhiwei.wjgg.service.EventLevelService;
import com.zhiwei.wjgg.service.NatureKeyPointService;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: NaturePointServiceTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenweitao 
 * @date 2016年2月29日 下午5:18:16
 */
public class NatureKeyPointServiceTest extends ObjectTest
{
    @Resource
    NatureKeyPointService service;
    
//    @Test
//    public void saveTest()
//    {
//    	NatureKeyPoint ob = new NatureKeyPoint();
//    	Map<String, List<String>> map = new HashMap<String, List<String>>();
//
//        List<String> list = new ArrayList<String>();
//        list.add("213131");
//        
//        map.put("2012-12-16 09", list);
//    	ob.setEventId("1");
//    	ob.setType("正面");
//    	System.out.println(service.add(ob));
//    }
    
    @Test
    public void showTest(){
			
    	NatureKeyPoint a = service.findOneById("51");
    	
    	System.out.println(a);
    }
    
}
