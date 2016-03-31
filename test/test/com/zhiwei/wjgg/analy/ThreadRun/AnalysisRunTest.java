/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月3日
    * @version 1.00 
*/
package test.com.zhiwei.wjgg.analy.ThreadRun;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.analy.ThreadRun.AnalysisEvent;
import com.zhiwei.wjgg.dao.EventDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Event;

import test.object.ObjectTest;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: AnalysisRunTest
 * @author 落花流水
 * @date 2016年3月3日 下午5:10:44
 */
public class AnalysisRunTest extends ObjectTest
{
    @Resource
    AnalysisEvent analysisEvent;
    
    @Resource
    EventDao dao;
    
    @Test
    public void analysisTest()
    {
        try
        {
            Event event = dao.findOne("5");
            analysisEvent.analysis(event);
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
    }
}
