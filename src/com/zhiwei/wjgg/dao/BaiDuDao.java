package com.zhiwei.wjgg.dao;

import java.util.List;

import com.mongodb.DBObject;

/** 
 * @Package com.zhiwei.event.dao
 * @ClassName: BaiDuDao
 * @Description: TODO(将百度新闻抓取回来的数据存入数据库)
 * @author 志伟
 * @date 2015-5-27 上午10:22:09

 */
public interface BaiDuDao {
	
	/***
	 * @deprecated:将百度新闻抓取回来的消息存入数据库
	 * @param List<DBOject> datalist
	 * 					采集回来的数据集合
	 * @return int 
	 * **/
	void addBaiduDataToMongo(List<DBObject> datalist);
	
	
	/**
	 * @deprecated：向solr中添加数据
	 * @param List<DBOject> datalist
	 * 					采集回来的数据集合
	 * @return int
	 * **/
	void addBaiduDataToSolr(DBObject doc);
}
