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
	public void solrSearchTest() throws ZhiWeiException {
		Page page = null;
		SearchQuery query = new SearchQuery();
		query.setWord("浙江");
		query.setProvince("330000");
		query.setIndustryLeveOneId("7");
		SolrDocumentList list = dao.getData(query, page);
		System.out.println(list.size());
//		for (SolrDocument doc : list) {
//			System.out.println(doc);
//		}
	}
}
