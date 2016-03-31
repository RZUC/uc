/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月28日
    * @version 1.00 
*/
package test.com.zhiwei.wjgg.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.service.DicService;

import test.object.ObjectTest;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: DicEvetnServiceImplTest
 * @author 落花流水
 * @date 2016年3月28日 下午3:09:42
 */
public class DicEvetnServiceImplTest extends ObjectTest
{
    @Resource
    DicService service;
    
    @Test
    public void addWordTest()
    {
        List<String> list = new ArrayList<String>();
        list.add("(微信+提现+收费)|(微信+提现+手续费)");
        list.add("(微信+红包照片)|(朋友圈+红包照片)|(朋友圈+毛玻璃)|(红包+照片)");
        list.add("朋友圈+广告");
        list.add("(腾讯+利是)(马化腾+利是)");
        list.add("(微粒贷+征信查询)");
        list.add("(马云+扎克伯格)");
        list.add("(斗鱼+无下限)(斗鱼+造娃娃)(斗鱼+造人)(斗鱼+低俗)");
        
        for (String word : list)
        {
            System.out.println("【" + word + "】----" + service.addWrodList(word));
        }
    }
}
