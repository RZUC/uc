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
 * @author Simple
 *
 */
public class UserDaoTest extends ObjectTest {
	
	@Resource
	PolicyTypeDao dao;
	
	@Test
	public void addTest() throws ZhiWeiException {
		PolicyType type = new PolicyType();
		type.setId("5");
		type.setName("政策解读");
		dao.insert(type);
	}
	
	@Test
	public void delTest() throws ZhiWeiException {
//		PolicyType type = new PolicyType();
//		type.setId("1");
//		dao.insert(type);
	}
	
	@Test
	public void modelTest() throws ZhiWeiException {
		PolicyType type = new PolicyType();
		type.setId("5");
		type.setOrder(5);
		dao.findAndModify(type);
	}
}
