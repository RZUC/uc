/**
 * 
 */
package test.com.uc.system.dao;

import javax.annotation.Resource;

import org.junit.Test;

import test.object.ObjectTest;

import com.uc.system.dao.UserDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.User;
import com.uc.system.util.TimeUtil;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: UserDaoTest
 * @author 落花流水
 * @date 2016年4月19日 下午9:15:28
 */
public class UserDaoTest extends ObjectTest {

	@Resource
	UserDao dao;

	@Test
	public void addTest() throws ZhiWeiException {
		User u = new User("", "宁波优策", "123456", TimeUtil.getCurrentTimeStr(),
				"platform", "1", 0, 0, 0);
		dao.insert(u);
	}
}
