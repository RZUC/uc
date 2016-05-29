/**
 * 
 */
package test.com.uc.system.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.uc.system.dao.PolicyTypeDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.PolicyType;

import test.object.ObjectTest;

/** 
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @ClassName: UserDaoTest 
 * @author 落花流水 
 * @date 2016年4月19日 下午9:15:28  
 */
public class UserDaoTest extends ObjectTest
{
    
    @Resource
    PolicyTypeDao dao;
    
    @Test
    public void addTest()
        throws ZhiWeiException
    {
        PolicyType type = new PolicyType();
        type.setId(1);
        type.setName("政策解读");
        dao.insert(type);
    }
    
    @Test
    public void delTest()
        throws ZhiWeiException
    {
        // PolicyType type = new PolicyType();
        // type.setId("1");
        // dao.insert(type);
    }
    
    @Test
    public void modelTest()
        throws ZhiWeiException
    {
        PolicyType type = new PolicyType();
        type.setId(1);
        type.setOrder(5);
        dao.findAndModify(type);
    }
}
