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

import javax.annotation.Resource;

import org.junit.Test;

import test.object.ObjectTest;

import com.zhiwei.wjgg.dao.UserDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.User;

/**
 * @Description: TODO(测试存储用户)
 * @ClassName: TestSaveUser
 * @author 落花流水
 * @date 2016年1月15日 上午11:06:52
 */
public class UserDaoTest extends ObjectTest
{
    @Resource
    UserDao dao;
    
    @Test
    public void saveTest()
    {
        User user = new User();
        user.setId("1");
        user.setName("admin");
        user.setPassword("1q2w3e4r");
        user.setPermissionid("1");
        try
        {
            dao.insert(user);
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void findOneTest() throws ZhiWeiException
    {
        
        User user = new User();
        user.setName("测试用户");
        user.setPassword("1q2w3e4r");
        user = dao.findOne(user);
        System.out.println(user);
//        System.out.println(user.getPermission().getPermission());
    }
    
    @Test
    public void findOneIDTest()
    {
        User user;
        try
        {
            user = dao.findOne("1");
            System.out.println("用户："+user);
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
        
    }
}
