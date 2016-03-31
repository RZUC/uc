/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月11日
    * @version 1.00 
*/
package test.com.zhiwei.wjgg.analy.eventInfulence;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import com.zhiwei.wjgg.analy.eventInfulence.EventInfulence;

import test.object.ObjectTest;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: EventInfulence
 * @author 落花流水
 * @date 2016年3月11日 下午3:20:40
 */
public class EventInfulenceTest extends ObjectTest
{
    @Resource
    EventInfulence eventInfulence;
    
    @Value("${EVENTRATIO}")
    static double value;
    @Test
    public void test()
    {   
        System.out.println("--------------------------------------------"+value);
        int correlationNum = 100;
        Double weixinInfulence = 38020.4384414554;
        Double weiboInfulence = 17721.6587494016;
        Double mediaInfulence = 42037.921333313;
        Integer totalDataCount = 1156+1466+10424;
        System.out.println(eventInfulence.calculateEventinfulence(correlationNum,
            weixinInfulence,
            weiboInfulence,
            mediaInfulence,
            totalDataCount));
    }
}
