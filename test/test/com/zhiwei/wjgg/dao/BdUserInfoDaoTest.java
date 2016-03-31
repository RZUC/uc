/**
 * ***************************************************
 * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
 *****************************************************
 * 类的详细说明 
 * 
 * @author 东临碣石
 * @Date 2016年1月15日
 * @version 1.00 
 */
package test.com.zhiwei.wjgg.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.dao.HUserInfoBDDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.HUserInfoBD;

import test.object.ObjectTest;

/**
 * 
 * @ClassName:
 * @Description: TODO()
 * @author chenweitao
 * @date 2016年2月26日 上午11:37:28
 */
public class BdUserInfoDaoTest extends ObjectTest
{
    @Resource
    HUserInfoBDDao dao;
    
    @Test
    public void findByIdTest()
    {
        try
        {
            String id = "expo.people.com.cn";
            HUserInfoBD ob = dao.findOne(id);
            System.out.println(ob);
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
        
    }
    
    @Test
    public void findByIdListTest()
    {
        List<String> ids = new ArrayList<String>();
        ids.add("printer.thethirdmedia.com");
        ids.add("yangjiang.house.sina.com.cn");
        ids.add("rizhao.sdchina.com");
        ids.add("gd.huatu.com");
        
        List<HUserInfoBD> a = dao.findByIdList(ids);
        for (HUserInfoBD bdUserInfo : a)
        {
            System.out.println(bdUserInfo);
        }
    }
    
}
