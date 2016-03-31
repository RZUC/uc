package test.com.zhiwei.wjgg.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.dao.WeiBoDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;

import test.object.ObjectTest;

public class WeiBoDaoTest extends ObjectTest
{
    @Resource
    WeiBoDao dao;
    
    @Test
    public void findOneTest() throws ZhiWeiException
    {
        String id = "3784316464887449";
        System.out.println(dao.findOne(id));
    }
}
