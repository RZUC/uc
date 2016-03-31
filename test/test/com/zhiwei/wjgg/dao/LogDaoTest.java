package test.com.zhiwei.wjgg.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.dao.LogDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.EventLevel;
import com.zhiwei.wjgg.model.Log;

import test.object.ObjectTest;

public class LogDaoTest  extends ObjectTest{
	@Resource
	LogDao dao;
	
	@Test
    public void saveTest()
    {
		Log ob = new Log();
    	ob.setContent("危机Ⅰ级");
        try
        {
            dao.addLog(ob);;
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
    }
}
