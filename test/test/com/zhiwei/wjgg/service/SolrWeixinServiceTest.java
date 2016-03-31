package test.com.zhiwei.wjgg.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.model.SolrWeixin;
import com.zhiwei.wjgg.service.SolrWeixinDataService;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: SolrMediaServiceTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenweitao
 * @date 2016年3月7日 下午2:18:26
 */
public class SolrWeixinServiceTest extends ObjectTest
{
    @Resource
    SolrWeixinDataService service;

    @Test
    public void saveTest()
    {
        SolrWeixin ob = new SolrWeixin();
        ob.setId("1");
        ob.setContent("有还是没有");
        ob.setPt("1233");
        ob.setRsid(1235);
        ob.setSavetime(13212313);
        ob.setSource("有推哦");
        ob.setTime(new Date());
        ob.setTitle("推哦");
        ob.setType("hjkl");
        System.out.println(service.add(ob));
    }
    
    @Test
    public void showTest(){
        List<SolrWeixin> obList = new ArrayList<SolrWeixin>();
        
        for (int i = 0; i < 20; i++)
        {
            SolrWeixin ob = new SolrWeixin();
            ob.setId(i+"");
            ob.setContent("有还是没有");
            ob.setPt("1233");
            ob.setRsid(1235);
            ob.setSavetime(13212313);
            ob.setSource("有推哦");
            ob.setTime(new Date());
            ob.setTitle("推哦");
            ob.setType("hjkl");
            obList.add(ob);
        }
        
        service.add(obList);
        
        System.out.println(service.findOneById("8"));
    }
}
