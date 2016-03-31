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
import com.zhiwei.wjgg.dao.HUserInfoWBDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.HUserInfoBD;
import com.zhiwei.wjgg.model.HUserInfoWB;

import test.object.ObjectTest;

/**
 * 
 * @ClassName:
 * @Description: TODO()
 * @author chenweitao
 * @date 2016年2月26日 上午11:37:28
 */
public class HUserInfoDaoTest extends ObjectTest
{
    @Resource
    HUserInfoWBDao dao;

    @Test
    public void findByIdTest()
    {
        try
        {
            System.out.println(dao.findOne("2803301701"));
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
        ids.add("2803301701");
        ids.add("1816011541");
        ids.add("1608574203");
        ids.add("1192329374");

        List<HUserInfoWB> a = dao.findByIdList(ids);
        for (HUserInfoWB hUserInfo : a)
        {
            System.out.println(hUserInfo);
        }
    }

}
