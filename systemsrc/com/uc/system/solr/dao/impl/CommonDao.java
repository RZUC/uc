/**
 * ***************************************************
 * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
 *****************************************************
 * 类的详细说明 
 * 
 * @author 东临碣石
 * @Date 2016年2月29日
 * @version 1.00 
 */
package com.uc.system.solr.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uc.system.model.Page;
import com.uc.system.model.SearchQuery;
import com.uc.system.solr.dao.SolrDao;
import com.uc.system.util.SolrTool;
import com.uc.system.util.WordsCount;

/**
 * @Description: policyInfo数据查询
 * @ClassName: CommonDao
 * @author 落花流水
 * @date 2016年2月29日 下午3:16:23
 */
public abstract class CommonDao implements SolrDao {
	public final static Logger log = LoggerFactory.getLogger(CommonDao.class);

	public static String TIMETYEP_STRING = "String";

	@Override
	public SolrDocumentList getData(SearchQuery query, Page page) {
		SolrDocumentList sdl = new SolrDocumentList();

		SolrQuery queryParams = new SolrQuery();

		queryParams.setQuery(getParam(WordsCount.wordsCount(query.getWord())));

		queryParams.addFilterQuery(getFilterTime(query.getStartTime(),
				query.getEndTime()));

		queryParams.addFilterQuery(getLocation(query.getProvince(),
				query.getCity(), query.getDowntown()));

		queryParams.addFilterQuery(getIndustry(query.getIndustryLeveOneId(),
				query.getIndustryLeveTwoeId()));

		queryParams.addFilterQuery(getPolicyType(query.getPolicyTypeId()));

		long resultcount = getTotalCount(getSolrServer(), queryParams);

		if (0 == resultcount) {
			return sdl;
		}

		setSortField(queryParams);// 设置排序，默认为Time

		// 设置高亮字段
		setHightLight(queryParams);

		setQueryStartAndEndRow(queryParams, resultcount, page);

		sdl = assembleSolrDocumentWithHightLight(getQuery(getSolrServer(),
				queryParams));

		return sdl;
	}

	/**
	 * @Title: setHightLight
	 * @Description: 设置高亮字段
	 * @param @param queryParams 设定文件
	 * @return void 返回类型
	 */
	private void setHightLight(SolrQuery queryParams) {
		queryParams.setFields(getFilterField());
		queryParams.setHighlight(true);
		queryParams.addHighlightField(getHighLightField());
		queryParams.setHighlightFragsize(300);
		queryParams.setHighlightSnippets(1);
		// queryParams
		// .setHighlightSimplePre("<B>");// 设置开头
		//
		// queryParams.setHighlightSimplePost("</B>");// 设置结尾
		queryParams
				.setHighlightSimplePre("<font style='font-weight:bold;font-size:15px' color='#ff330D'>");// 设置开头

		queryParams.setHighlightSimplePost("</font>");// 设置结尾
	}

	protected String getHighLightField() {
		return "";
	}

	/**
	 * @Title: setQueryStartAndEndRow
	 * @Description: 设置查询的分页
	 * @param @param queryParams
	 * @param @param resultcount
	 * @param @param page 设定文件
	 * @return void 返回类型
	 */
	private void setQueryStartAndEndRow(SolrQuery queryParams,
			long resultcount, Page page) {

		if (page.getPageNum() > 0 && page.getPageSize() > 0) {
			int start = (page.getPageNum() - 1) * page.getPageSize();
			int end = page.getPageSize();
			queryParams.setStart(start);
			queryParams.setRows(end);
		} else {
			queryParams.setStart(0);
			int row = Integer.valueOf(String.valueOf(resultcount));
			queryParams.setRows(row);
		}

	}

	private String getPolicyType(String policyTypeId) {
		String querypolicyType = "policyType:*";
		if (null != policyTypeId && !"".equals(policyTypeId)) {
			policyTypeId = "policyType:" + policyTypeId;
		}
		return querypolicyType;
	}

	private String getIndustry(String industryLeveOneId,
			String industryLeveTwoeId) {
		String queryIndustry = "industry:*";
		if (null != industryLeveTwoeId && !"".equals(industryLeveTwoeId)) {
			queryIndustry = "industry:" + industryLeveTwoeId;
		} else if (null != industryLeveOneId && !"".equals(industryLeveOneId)) {
			queryIndustry = "industry:" + industryLeveOneId;
		}
		log.debug("行业查询条件：[{}]", queryIndustry);
		return queryIndustry;
	}

	private String getLocation(String province, String city, String downtown) {
		String queryLocatio = "*:*";
		if (null != downtown && !"".equals(downtown)) {
			queryLocatio = "downtown:" + downtown;
		} else if (null != city && !"".equals(city)) {
			queryLocatio = "city:" + city;
		} else if (null != province && !"".equals(province)) {
			queryLocatio = "province:" + province;
		}
		log.debug("地域查询：[{}]", queryLocatio);
		return queryLocatio;
	}

	/**
	 * @Title: setSortField
	 * @Description: 设置排序方式
	 * @param @param queryParams 设定文件
	 * @return void 返回类型
	 */
	protected void setSortField(SolrQuery queryParams) {
		queryParams.setSortField("releaseTime", ORDER.desc); // 按时间排序
	}

	/**
	 * @Title: getParam
	 * @Description: 通过关键词获取查询语句
	 * @param @param wordList
	 * @param @return 设定文件
	 * @return String 返回类型
	 */
	protected abstract String getParam(List<String> wordList);

	/**
	 * @Title: getFilter
	 * @Description: 添加显示的字段
	 * @param @param filter
	 * @param @return 设定文件
	 * @return String 返回类型
	 */
	protected abstract String getFilterField();

	/**
	 * @Title: getTimeType
	 * @Description: 获取时间条件的类型
	 * @param @return 设定文件
	 * @return String 返回类型
	 */
	protected abstract String getTimeType();

	/**
	 * @Title: getSolrServer
	 * @Description: 获取Solr数据库连接
	 * @param 设定文件
	 * @return void 返回类型
	 */
	protected abstract HttpSolrServer getSolrServer();

	/**
	 * 通过查询条件查询数据
	 * 
	 * @Title: getQuery
	 * @param @param params
	 * @return QueryResponse 返回类型
	 */
	protected QueryResponse getQuery(HttpSolrServer solrServer, SolrQuery params) {
		for (int i = 1; i <= 3; i++) {
			try {
				QueryResponse response = solrServer.query(params, METHOD.GET);
				log.debug("查询参数:[Url:{}]\t查询参数:{}", solrServer.getBaseURL(),
						params.getQuery());
				return response;
			} catch (SolrServerException e) {
				log.error(
						"查询出错:[Url:{}]\t[重试次数：{}]\t[ERRORMessage:{}]\t[param:{}]",
						solrServer.getBaseURL(), i, e.getMessage(),
						params.getQuery());
			}
		}
		return null;
	}

	/**
	 * 查询数据总数
	 * 
	 * @Title: getQuery
	 * @param @param params
	 * @return QueryResponse 返回类型
	 */
	protected long getTotalCount(HttpSolrServer solrServer, SolrQuery params) {
		params.setStart(0);
		params.setRows(1);
		QueryResponse response = getQuery(solrServer, params);
		long totalCount = 0;
		if (null != response) {
			totalCount = response.getResults().getNumFound();
		}
		log.debug("查询到的记录总数:{}", totalCount);
		return totalCount;
	}

	/**
	 * @Title: getFilterTime
	 * @Description: 获取查询时间的参数
	 * @param @param startTime
	 * @param @param endTime
	 * @param @return 设定文件
	 * @return String 返回类型
	 */
	protected String getFilterTime(String startTime, String endTime) {
		String time = "";
		String key = "releaseTime";
		if (TIMETYEP_STRING.equals(getTimeType())) {
			time = SolrTool.processTimeStr(startTime, endTime, key);
			log.debug("String 时间 :[{}]", time);
		}
		return time;
	}

	private SolrDocumentList assembleSolrDocumentWithHightLight(
			QueryResponse response) {
		SolrDocumentList sdl = response.getResults();
		Map<String, Map<String, List<String>>> highlight = response
				.getHighlighting();
		for (SolrDocument solrDocument : sdl) {
			String title = "";
			String content = "";

			String content1 = solrDocument.get("title") == null ? ""
					: solrDocument.get("title").toString();
			String content2 = solrDocument.get("content") == null ? ""
					: solrDocument.get("content").toString();
			log.debug("***********原始内容***********\nQ:{}\nA:{}", content1,
					content2);

			try {
				List<String> titlelist = highlight.get(solrDocument.get("_id"))
						.get("title");
				List<String> questionContentlist = highlight.get(
						solrDocument.get("_id")).get("content");

				if (titlelist != null && titlelist.size() > 0) {
					for (String string : titlelist) {
						title = title + string;
					}
				}

				if (questionContentlist != null
						&& questionContentlist.size() > 0) {
					for (String string : questionContentlist) {
						content = content + string;
					}
				}

				if (title.isEmpty()) {
					title = String.valueOf(solrDocument.get("title"));

					if (title.length() > 300) {
						title = title.substring(0, 300);
					}
				}

				solrDocument.put("title", content);

				log.debug("***********高亮内容***********\nT:{}\nQ:{}\nA:{}",
						new Object[] { solrDocument.get("title").toString(),
								solrDocument.get("content").toString() });
				log.debug("***********这条结束***********");
			} catch (Exception e) {
				log.error("匹配高亮出错：{}", solrDocument);
			}

		}
		return sdl;
	}

	@Override
	public long getTotalCount(SearchQuery query, Page page) {
		SolrDocumentList sdl = new SolrDocumentList();

		SolrQuery queryParams = new SolrQuery();

		queryParams.setQuery(getParam(WordsCount.wordsCount(query.getWord())));

		queryParams.addFilterQuery(getFilterTime(query.getStartTime(),
				query.getEndTime()));

		queryParams.addFilterQuery(getLocation(query.getProvince(),
				query.getCity(), query.getDowntown()));

		queryParams.addFilterQuery(getIndustry(query.getIndustryLeveOneId(),
				query.getIndustryLeveTwoeId()));

		queryParams.addFilterQuery(getPolicyType(query.getPolicyTypeId()));

		long resultcount = getTotalCount(getSolrServer(), queryParams);
		return resultcount;
	}

}
