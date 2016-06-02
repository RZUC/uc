/**
 * 
 */
package test.com.uc.system.solr.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import test.object.ObjectTest;

import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Page;
import com.uc.system.model.SearchQuery;
import com.uc.system.solr.dao.SolrDao;
import com.uc.system.util.SolrTool;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: UserDaoTest
 * @author 落花流水
 * @date 2016年4月19日 下午9:15:28
 */
public class SolrPolicyInfoImplTest extends ObjectTest {

	@Resource
	SolrDao dao;

	@Test
	public void addTest() throws ZhiWeiException {
		Page page = null;
		SearchQuery query = new SearchQuery();
		query.setWord("浙江");
		SolrDocumentList list = dao.getData(query, page);
		for (SolrDocument doc : list) {
			System.out.println(doc);
		}
	}
}
