package com.zhiwei.wjgg.mongo.service;

import java.util.HashMap;
import java.util.List;

import org.apache.solr.common.SolrDocumentList;

/** 
 * @Package com.zhiwei.event.mongo.weibo.api
 * @ClassName: GetWeiboDataByMongo
 * @Description: TODO(从mongo中读取微博帐号数据)
 * @author 志伟
 * @date 2015-5-25 下午2:27:13
 */
public interface GetWeiboUserInfoDao {
	/**
	 * @deprecated:根据微博mid在mongo中反查微博数据
	 * @param SolrDocumentList sdl
	 * 						weibo的uid集合
	 * @return List<HashMap>
	 * **/
	List<HashMap> getWeiboUserInfoByUid(SolrDocumentList sdl);
	
	List<HashMap> getWeiboUserInfoByUidList(SolrDocumentList sdl);
}
