/**
 * 
 */
package test.com.uc.system.dao;

import javax.annotation.Resource;

import org.junit.Test;

import test.object.ObjectTest;

import com.uc.system.dao.FavoriteDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Favorite;
import com.uc.system.util.Sequence;

/**
 * @author Simple
 *
 */
public class CollectionDaoTest extends ObjectTest {

	@Resource
	FavoriteDao dao;
	@Resource
	Sequence seq;

	@Test
	public void addTest() throws ZhiWeiException {
		Favorite f = new Favorite(Integer.valueOf("17"), 97, "测试收藏");
		for (int i = 0; i < 10; i++) {
			f.setId(Integer.valueOf(seq.getNextId("favorite")));
			dao.insert(f);
		}
	}
}
