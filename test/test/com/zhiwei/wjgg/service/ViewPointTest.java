package test.com.zhiwei.wjgg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.model.ViewPoint;
import com.zhiwei.wjgg.service.ViewPointService;

import test.object.ObjectTest;

/**
 * @Description
 * 
 * @author 李自贤
 * @date 2016年2月29日
 */
public class ViewPointTest extends ObjectTest
{
    
    @Resource
    ViewPointService service;
    
    @Test
    public void saveTest()
    {
        ViewPoint ob = new ViewPoint();
        Map<String, Double> pointMap = new HashMap<String, Double>();
        pointMap.put("咱们的..", 1.0);
        ob.setId("11");
        ob.setType("lingxiu");
        ob.setPointMap(pointMap);
        service.add(ob);
    }
    
    @Test
    public void saveAllTest()
    {
        List<ViewPoint> viewPoints = new ArrayList<ViewPoint>();
        ViewPoint ob = new ViewPoint();
        ob.setEventId("1");
        Map<String, Double> point = new HashMap<String, Double>();
        point.put("咱们的", 10.0);
        point.put("咱们的事件", 10.0);
        point.put("咱们", 10.0);
        point.put("事件库", 10.0);
        point.put("的事件库", 20.0);
        // point.put("咱们的事件库", 0.0);
        point.put("件库", 40.0);
        ob.setPointMap(point);
        ob.setType("网民");
        viewPoints.add(ob);
        service.add(ob);
    }
    
    @Test
    public void findOneTest()
    {
        ViewPoint viewPoint = service.findOneById("nulllingxiu");
        System.out.println(viewPoint.getPointMap());
    }
    
    @Test
    public void findAllTest()
    {
        List<ViewPoint> viewPoint = service.findAll();
        
        System.out.println(viewPoint.size());
    }
    
    @Test
    public void deleteTest()
    {
        System.out.println(service.deleteById("123123"));
    }
}
