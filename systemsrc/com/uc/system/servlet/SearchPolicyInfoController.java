package com.uc.system.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uc.system.model.Page;
import com.uc.system.model.PolicyInfo;
import com.uc.system.model.SearchQuery;
import com.uc.system.model.view.PolicyInfoView;
import com.uc.system.service.PolicyService;
import com.uc.system.solr.service.SolrDataService;
import com.uc.system.util.SolrDocumentToBeanUtil;

/**
 * @author Simple
 * 
 */
/**
 * @Description: 政策信息的维护<br>
 *               增加<br>
 *               删除<br>
 *               修改<br>
 *               推到头条<br>
 *               取消头条<br>
 *               查询：多个查询条件，类型，时间，区域，行业，关键词<br>
 * @ClassName: PolicyInfoController
 * @author 落花流水
 * @date 2016年4月19日 下午9:17:14
 */
@Controller
@RequestMapping(value = "/searchpolicyInfo")
public class SearchPolicyInfoController extends GeneralController {
	@Resource
	PolicyService service;

	@Resource
	SolrDataService solrservice;

	@RequestMapping(value = "/search")
	public ResponseEntity<Map<String, Object>> show(SearchQuery query)
			throws Exception {
		int totalPage = 0;

		System.out.println("关键词：" + query.getWord());
		System.out.println("开始时间：" + query.getStartTime());
		System.out.println("结束时间：" + query.getEndTime());
		System.out.println("类型：" + query.getPolicyTypeId());
		System.out.println("页码：" + query.getPageNum());
		System.out.println("每页条数：" + query.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Page page = new Page(query.getPageSize(), query.getPageNum());

			SolrDocumentList list = solrservice.getSolrData(query, page);
			for (SolrDocument doc : list) {
				doc.put("_id", Integer.valueOf(doc.get("_id").toString()));
			}
			List<PolicyInfoView> view = service
					.getViewList(SolrDocumentToBeanUtil
							.getDocumentObjectBinder().getBeans(
									PolicyInfo.class, list));

			totalPage = getTotalPage(page.getPageSize(), Integer.valueOf(String
					.valueOf(solrservice.getTotalCount(query, page))));

			map.put("message", "显示数据：" + query.getPolicyTypeId());
			map.put("state", true);
			map.put("data", view);
			map.put("totalPage", totalPage);
			map.put("currentPage", query.getPageNum());

		} catch (Exception e) {
			map.put("message", "数据失败");
			map.put("state", false);
			map.put("data", null);
			map.put("totalPage", totalPage);
			map.put("currentPage", query.getPageNum());
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	private int getTotalPage(int pageSize, int total) {
		return (total % pageSize == 0) ? total / pageSize
				: (total / pageSize + 1);
	}

}
