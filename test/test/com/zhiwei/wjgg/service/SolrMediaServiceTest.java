package test.com.zhiwei.wjgg.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.model.SolrMedia;
import com.zhiwei.wjgg.service.SolrMediaDataService;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: SolrMediaServiceTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenweitao
 * @date 2016年3月7日 下午2:18:26
 */
public class SolrMediaServiceTest extends ObjectTest
{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Resource
    SolrMediaDataService service;

    // @Test
    // public void saveTest()
    // {
    // SolrMedia ob = new SolrMedia();
    // ob.setId("1");
    // ob.setContent("有还是没有");
    // ob.setPt("1233");
    // ob.setRsid(1235);
    // ob.setSavetime(13212313);
    // ob.setSource("有推哦");
    // ob.setTime(new Date());
    // ob.setTitle("推哦");
    // ob.setType("hjkl");
    // System.out.println(service.add(ob));
    // }

    // @Test
    // public void showTest()
    // {
    // List<SolrMedia> obList = new ArrayList<SolrMedia>();
    //
    // for (int i = 0; i < 20; i++)
    // {
    // SolrMedia ob = new SolrMedia();
    // ob.setId(i + "");
    // ob.setContent("有还是没有");
    // ob.setPt("1233");
    // ob.setRsid(1235);
    // ob.setSavetime(13212313);
    // ob.setSource("有推哦");
    // ob.setTime(new Date());
    // ob.setTitle("推哦");
    // ob.setType("hjkl");
    // obList.add(ob);
    // }
    //
    // service.add(obList);
    //
    // System.out.println(service.findOneById("8"));
    // }

    @Test
    public void findInTime()
    {

        try
        {
//            List<SolrMedia> obList = service.findInTime("2016-02-27 03");
            List<SolrMedia> obList = service.findAll();

            System.out.println("obList:======" + obList.size());
            for (SolrMedia ob : obList)
            {
                System.out.println("=========" + sdf.format(ob.getTime()) +","+ ob.getTitle());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
