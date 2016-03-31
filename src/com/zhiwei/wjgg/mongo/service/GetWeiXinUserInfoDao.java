package com.zhiwei.wjgg.mongo.service;

import java.util.HashMap;
import java.util.List;

import org.apache.solr.common.SolrDocumentList;

/** 
 * @Package com.zhiwei.event.mongo.weixin.api
 * @ClassName: GetWeiXinDataByMongoApi
 * @Description: TODO(从mongo中读取微信帐号数据)
 * @author 志伟
 * @date 2015-5-25 下午4:25:44
 */
public interface GetWeiXinUserInfoDao {
	/**
	 * @deprecated:根据微信wxid在mongo中反查微信数据
	 * @param SolrDocumentList sdl
	 * 				微信的mid
	 * @return List<HashMap>
	 * **/
	List<HashMap> getWeixinUserInfoById(SolrDocumentList sdl);
	
	List<HashMap> getWeixinUserInfoByIdList(SolrDocumentList sdl);
}
