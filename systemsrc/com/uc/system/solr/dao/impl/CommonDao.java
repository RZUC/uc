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

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uc.system.model.Page;
import com.uc.system.model.Query;
import com.uc.system.solr.dao.SolrDao;
import com.uc.system.util.SolrTool;
import com.uc.system.util.TimeUtil;
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
	public SolrDocumentList getData(Query query, Page page) {
		SolrDocumentList sdl = new SolrDocumentList();

		SolrQuery queryParams = new SolrQuery();

		queryParams.setQuery(getParam(WordsCount.wordsCount(query.getWord())));

		queryParams.addFilterQuery(getFilterTime(
				TimeUtil.formatTime(query.getStartTime()),
				TimeUtil.formatTime(query.getEndTime())));

		// String eventFilter = getFilterByEvent(event);
		//
		// if (null != eventFilter && !"".equals(eventFilter))
		// {
		// queryParams.addFilterQuery(eventFilter);
		// }

		long resultcount = getTotalCount(getSolrServer(), queryParams);

		if (0 == resultcount) {
			return sdl;
		}

		setSortField(queryParams);// 设置排序，默认为Time

		queryParams.setFields(getFilterField());

		queryParams.setStart(0);

		int row = Integer.valueOf(String.valueOf(resultcount));

		queryParams.setRows(row);

		sdl = getQuery(getSolrServer(), queryParams).getResults();

		return sdl;
	}

	/**
	 * @Title: setSortField
	 * @Description: 设置排序方式
	 * @param @param queryParams 设定文件
	 * @return void 返回类型
	 */
	protected void setSortField(SolrQuery queryParams) {
		queryParams.setSortField("time", ORDER.desc); // 按时间排序
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
				QueryResponse response = solrServer.query(params, METHOD.POST);
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
	 * @Description: 获取数据时间参数
	 * @param @param key
	 * @param @param startTime
	 * @param @param endTime
	 * @param @param type 时间的格式类型<br>
	 *        1.String <br>
	 *        2.date <br>
	 * @param @return 设定文件
	 * @return String 返回类型
	 */
	protected String getFilterTime(String startTime, String endTime) {
		String time = "";

		if (TIMETYEP_STRING.equals(getTimeType())) {
			time = SolrTool.processTimeStr(startTime, endTime);
			log.debug("String 时间 :{}", time);
		}
		return time;
	}

}
