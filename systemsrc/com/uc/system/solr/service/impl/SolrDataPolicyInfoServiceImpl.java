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
package com.uc.system.solr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import com.uc.system.model.Page;
import com.uc.system.model.PolicyInfo;
import com.uc.system.model.SearchQuery;
import com.uc.system.solr.dao.SolrDao;
import com.uc.system.util.RenH_Util;
import com.uc.system.util.TimeUtil;

/**
 * @Description:
 * @ClassName: SolrDataSerice
 * @author 落花流水
 * @date 2016年2月27日 上午10:12:00
 */
@Component
public class SolrDataPolicyInfoServiceImpl extends
		SolrDataCommonService<PolicyInfo> {
	@Resource
	private SolrDao solr;

	@Override
	public SolrDocumentList getSolrData(SearchQuery query, Page page) {
		SolrDocumentList docList = solr.getData(query, page);
		return docList;
	}

	/**
	 * @Title: getQuerList
	 * @Description: 获取查询条件
	 * @param @param docList
	 * @param @return 设定文件
	 * @return List<String> 返回类型
	 */
	private Map<String, List<String>> getQuerList(SolrDocumentList docList) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> idList = new ArrayList<String>();
		List<String> sourceList = new ArrayList<String>();

		for (SolrDocument doc : docList) {
			String openId = getOpenId(doc);
			if (null == openId) {
				sourceList.add(doc.get("source").toString());
			} else {
				idList.add(openId);
			}

		}

		log.debug("openID去重----------------");
		map.put("openId", RenH_Util.quChong(idList));
		log.debug("source去重----------------");
		map.put("source", RenH_Util.quChong(sourceList));

		return map;
	}

	/**
	 * @Title: getOpenId
	 * @Description: 获取OpenId
	 * @param @param string
	 * @param @return 设定文件
	 * @return String 返回类型
	 */
	private String getOpenId(SolrDocument doc) {
		String openid = null;
		try {
			openid = doc.get("type").toString().split(",")[1];
			openid = "http://weixin.sogou.com/gzh?openid=" + openid;
		} catch (Exception e) {
			// log.info("没有opendId:{}", doc.get("_id"));
		}
		return openid;
	}

	@Override
	protected SolrDocumentList delDuplication(SolrDocumentList list) {
		int replate = 0;
		Map<String, SolrDocument> map = new HashMap<String, SolrDocument>();
		SolrDocumentList docList = new SolrDocumentList();
		for (SolrDocument doc : list) {

			String key = "";

			if (doc.containsKey("_id")) {
				key = null == doc.get("_id") ? "" : doc.get("_id").toString();
				key = doc.get("_id").toString();
			} else {
				key = null == doc.get("id") ? "" : doc.get("id").toString();
				doc.put("_id", key);
			}

			// TODO:来源，标题，时间来去重
			String source = null == doc.get("source") ? "" : doc.get("source")
					.toString();
			String title = null == doc.get("title") ? "" : doc.get("title")
					.toString();
			String time = "";
			if (null != doc.get("time")) {
				try {
					time = TimeUtil.formatTime((Date) doc.get("time"));
				} catch (Exception e) {
					log.error("获取数据的时间出错：{}", e.getMessage());
				}
			}

			key = source + title + time;
			if (map.containsKey(key)) {
				replate++;
			}
			map.put(key, doc);
		}

		for (Entry<String, SolrDocument> entry : map.entrySet()) {
			docList.add(entry.getValue());
		}

		log.debug("微信重复的数量:{}", replate);
		log.debug("微信数据返回的数量:{}", docList.size());

		return docList;
	}

	@Override
	protected Map<String, PolicyInfo> getHUserinfoMap(SolrDocumentList docList) {
		return null;
	}

	@Override
	protected PolicyInfo getHUserinfoByMap(SolrDocument doc,
			Map<String, PolicyInfo> map) {
		return null;
	}
}
