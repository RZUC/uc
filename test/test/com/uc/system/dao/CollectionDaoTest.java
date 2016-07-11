/**
 * 
 */
package test.com.uc.system.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.uc.system.dao.FavoriteDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Favorite;
import com.uc.system.model.SearchQuery;
import com.uc.system.util.Sequence;

import test.object.ObjectTest;

/**
 * @author Simple
 *
 */
public class CollectionDaoTest extends ObjectTest
{
    
    @Resource
    FavoriteDao dao;
    
    @Test
    public void addTest()
        throws ZhiWeiException
    {
        Favorite f = new Favorite(Integer.valueOf("17"), 97, "测试收藏");
        Sequence seq = new Sequence();
        for (int i = 0; i < 10; i++)
        {
            f.setId(Integer.valueOf(seq.getNextId("favorite")));
            dao.insert(f);
        }
    }
}
