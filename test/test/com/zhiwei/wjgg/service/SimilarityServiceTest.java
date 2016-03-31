package test.com.zhiwei.wjgg.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.model.Similarity;
import com.zhiwei.wjgg.service.SimilarityService;

import test.object.ObjectTest;

/**
 * 
 * @ClassName: SolrMediaServiceTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenweitao
 * @date 2016年3月7日 下午2:18:26
 */
public class SimilarityServiceTest extends ObjectTest
{
    @Resource
    SimilarityService service;

    @Test
    public void saveTest()
    {
        Similarity ob = new Similarity();
        Map<String, Integer> media = new HashMap<String, Integer>();
        Map<String, Integer> weixin = new HashMap<String, Integer>();
        media.put("有吗", 456);
        weixin.put("有的吧", 78);

        ob.setId("1");
        ob.setMedia(media);
        ob.setWeixin(weixin);
        System.out.println(service.add(ob));
    }

    @Test
    public void addTest()
    {
        Similarity ob = new Similarity();
        Map<String, Integer> media = new HashMap<String, Integer>();
        Map<String, Integer> weixin = new HashMap<String, Integer>();
        media.put("网媒", 12);
        media.put("网媒2", 12);
        media.put("每日新闻早报：新华社评微信提现收费，三星手机跌出中国前五 ...", 12);
        weixin.put("微信", 12);
        weixin.put("每日新闻早报：新华社评微信提现收费，三星手机跌出中国前五 ...", 12);
        ob.setId("110");
        ob.setMedia(media);
        ob.setWeixin(weixin);

        service.addInfoByOb(ob);
    }

    @Test
    public void deleteTest()
    {
        service.deleteById("2");
    }

    @Test
    public void findAll(){
        List<Similarity> list = service.findAll();
        for (Similarity ob : list)
        {
            System.out.println("id:"+ob.getId()+",media:"+ob.getMedia()+",weixin:"+ob.getWeixin());
        }
    }
    
    @Test
    public void findoneTest(){
        Similarity ob = service.findOneById("2");
        System.out.println("id:"+ob.getId()+",media:"+ob.getMedia()+",weixin:"+ob.getWeixin());
    }
}
