/**
 * 
 */
package test.com.uc.system.solr.service;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Page;
import com.uc.system.model.SearchQuery;
import com.uc.system.solr.service.SolrDataService;

import test.object.ObjectTest;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: UserDaoTest
 * @author 落花流水
 * @date 2016年4月19日 下午9:15:28
 */
public class SolrPolicyService extends ObjectTest {

	@Resource
	SolrDataService service;

	@Test
	public void solrSearchTest() throws ZhiWeiException {
		Page page = null;
		SearchQuery query = new SearchQuery();
		query.setWord("浙江");
		query.setProvince("330000");
		query.setIndustryLeveOneId("7");
		SolrDocumentList list = service.getSolrData(query, page);
		System.out.println(list.size());
//		for (SolrDocument doc : list) {
//			System.out.println(doc);
//		}
	}
}
