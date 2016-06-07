/**
 * ***************************************************
 * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
 *****************************************************
 * 类的详细说明 
 * 
 * @author 东临碣石
 * @Date 2016年2月27日
 * @version 1.00 
 */
package com.uc.system.solr.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import com.uc.system.DBTemp.HttpSolrServerUtil;
import com.uc.system.model.Page;
import com.uc.system.model.SearchQuery;
import com.uc.system.util.SolrTool;
import com.uc.system.util.WordsCount;

/**
 * @ClassName: SolrPolicyInfoImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2016年6月2日 下午9:32:07
 */
@Component
public class SolrPolicyInfoImpl extends CommonDao {

	@Resource(name = "policySolor")
	HttpSolrServerUtil policySolr;

	@Override
	protected String getParam(List<String> wordList) {
		String param = SolrTool.processWord(wordList);
		return param;
	}

	@Override
	protected String getTimeType() {
		return TIMETYEP_STRING;
	}

	@Override
	protected HttpSolrServer getSolrServer() {
		return policySolr.getHttpSolrServer();
	}

	@Override
	protected String getFilterField() {
		return "_id,releaseTime,industry,department,title,content";
	}

	@Override
	protected String getHighLightField() {

		return "title,content";
	}

}
