/**
 * 
 */
package test.com.uc.system.dao;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.uc.system.dao.LocationDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Location;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import test.object.ObjectTest;

/**
 * @author Simple
 *
 */
public class LocationDaoTest extends ObjectTest
{
    
    @Resource
    LocationDao dao;
    
    @Test
    public void addTest()
        throws ZhiWeiException
    {
        
//        SimpeExcelReport simple = SimpeExcelReport.getInstance();
        JSONArray array = new JSONArray();
        Map<String, Object> map = null;//simple.readExcel(new File("E:\\areas.xls"), "areas");
        
        for (String sheetName : map.keySet())
        {
            Map<String, Object> sheet = (Map<String, Object>)map.get(sheetName);
            System.out.println("sheetName:" + sheetName);
            List<String> headList = (List<String>)sheet.get("head");
            List<Map<String, Object>> body = (List<Map<String, Object>>)sheet.get("body");
            for (String head : headList)
            {
                System.out.print(head + "\t");
            }
            System.out.println();
            for (Map<String, Object> map2 : body)
            {
                String id = "";
                
                String locationName = "";// 名称
                
                String fatherID = "";// 父级
                
                String abbreviation = "";// 简称
                
                Double longitude = 0.0;// 精度
                
                Double dimensionality = 0.0;// 维度
                
                int level = 0;// 等级
                
                int order = 0;// 排序
                
                for (String head : headList)
                {
                    JSONObject json = new JSONObject();
                    String value = map2.get(head).toString();
                    if ("编号".equals(head))
                    {
                        id = value;
                    }
                    else if ("名称".equals(head))
                    {
                        locationName = value;
                    }
                    else if ("父级".equals(head))
                    {
                        fatherID = value;
                    }
                    else if ("简称".equals(head))
                    {
                        abbreviation = value;
                    }
                    else if ("经度".equals(head))
                    {
                        longitude = Double.valueOf(value);
                    }
                    else if ("纬度".equals(head))
                    {
                        dimensionality = Double.valueOf(value);
                    }
                    else if ("等级".equals(head))
                    {
                        level = Integer.valueOf(value);
                    }
                    else if ("排序".equals(head))
                    {
                        order = Integer.valueOf(value);
                    }
                }
                
                Location location =
                    new Location(id, locationName, fatherID, abbreviation, longitude, dimensionality, level, order);
                dao.insert(location);
            }
        }
        
    }
    
    @Test
    public void findOneTest()
        throws ZhiWeiException
    {
        Location location = dao.findOneByFiled("_id", "1").get(0);
        System.out.println(location);
    }
}
