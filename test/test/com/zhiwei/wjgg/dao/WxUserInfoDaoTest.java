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
import com.zhiwei.wjgg.dao.HUserInfoWXDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.HUserInfoBD;
import com.zhiwei.wjgg.model.HUserInfoWX;

import test.object.ObjectTest;

/**
 * 
 * @ClassName:
 * @Description: TODO()
 * @author chenweitao
 * @date 2016年2月26日 上午11:37:28
 */
public class WxUserInfoDaoTest extends ObjectTest
{
    @Resource
    HUserInfoWXDao dao;

    @Test
    public void findByIdTest()
    {
        try
        {
            System.out.println(dao.findOne("http://weixin.sogou.com/gzh?openid=oIWsFt8_jYUmdw1PQgNVhH9vOEvI"));
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    public void findByIdListTest()
    {   
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        List<String> ids = new ArrayList<String>();
        ids.add("http://weixin.sogou.com/gzh?openid=oIWsFt8_jYUmdw1PQgNVhH9vOEvI");
        ids.add("http://weixin.sogou.com/gzh?openid=oIWsFt0KxjSCnBevmRC3qlOv2RYg");
        ids.add("http://weixin.sogou.com/gzh?openid=oIWsFt21lxu6mV64ZRiAGbNdV3Ew");

        List<HUserInfoWX> a = dao.findByIdList(ids);
        for (HUserInfoWX wxUserInfo : a)
        {
            System.out.println(wxUserInfo);
        }
    }

}
