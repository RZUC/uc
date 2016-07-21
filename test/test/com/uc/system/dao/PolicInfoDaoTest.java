/**
 * 
 */
package test.com.uc.system.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.uc.system.dao.UserDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.User;

import test.object.ObjectTest;

/**
 * @author Simple
 *
 */
public class PolicInfoDaoTest extends ObjectTest {

	@Resource
	UserDao dao;

	@Test
	public void addTest() throws ZhiWeiException {
		User user = new User();
		user.setName("小明");
		user.setPassword("123456");
		// user.setPermissionid("1");
		dao.insert(user);
		System.out.println("test dao");
	}

	@Test
	public void findOneTest() throws ZhiWeiException {
		User find = new User();
		find.setName("宁波优策");
		User user = dao.findOne(find);
		System.out.println(user);
	}
}
