package test.com.zhiwei.wjgg.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.model.SolrMedia;
import com.zhiwei.wjgg.service.SolrSourceService;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: SolrMediaServiceTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenweitao
 * @date 2016年3月7日 下午2:18:26
 */
public class SolrSourceServiceTest extends ObjectTest
{
    @Resource(name="solrMediaServiceImpl")
    SolrSourceService service;
    
    @Test
    public void addListTest()
    {
        SolrMedia ob;
        List<SolrMedia> obList = new ArrayList<SolrMedia>();
        for (int i = 0; i < 100; i++)
        {
            ob = new SolrMedia();
            ob.setId(String.valueOf(i));
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
    }
}
