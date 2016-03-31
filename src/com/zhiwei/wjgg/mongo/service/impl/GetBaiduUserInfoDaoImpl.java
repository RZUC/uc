package com.zhiwei.wjgg.mongo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.zhiwei.wjgg.mongo.service.GetBaiduUserInfoDao;

/** 
 * @Package com.zhiwei.event.mongo.baidu.api
 * @ClassName: GetBaiduDataByMongoApiImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 志伟
 * @date 2015-5-26 上午11:24:38
 */
public class GetBaiduUserInfoDaoImpl implements
		GetBaiduUserInfoDao {
	private static String collName = "bd_userInfo";
	private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public GetBaiduUserInfoDaoImpl()
	{
	}
	
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@Override
	public List<HashMap> getBaiduUserInfoById(SolrDocumentList sdl) {
		List<HashMap> list = new ArrayList<HashMap>();
		for(SolrDocument sd : sdl)
		{
			HashMap dataMap = new HashMap();
			dataMap.put("url", sd.get("id"));
			dataMap.put("source", sd.get("source"));
			dataMap.put("title", sd.get("title"));
			dataMap.put("content", sd.get("content"));
			dataMap.put("time", sf.format(sd.get("time")));
			dataMap.put("pt", "media");
			String url = sd.get("id").toString();
			
			DBObject obj = null;
			try {
				url = url.split(",")[0].split("http://")[1].split("/")[0];
				DBObject query = new BasicDBObject();
				query.put("_id", url);
				
				DBObject backDBO = new BasicDBObject("H",1);
			} catch (Exception e) {
				continue;
			}
			if(obj != null)
			{
				dataMap.put("H", obj.get("H"));
			}else
			{
				dataMap.put("H", 30);
			}
			list.add(dataMap);
		}
		return list;
	}
	
	
	
	@SuppressWarnings({ "rawtypes",  "unchecked" })
	public List<HashMap> getBaiduUserInfoByIdList(SolrDocumentList sdl) {
		List<HashMap> list = new ArrayList<HashMap>(); //最后总的数据集合
		List<String> uidList = new ArrayList<String>(); //用户id集合用于批量查询
		HashMap<String,HashMap> userMap = new HashMap<String,HashMap>(); //用户集合
		for(SolrDocument sd : sdl)
		{
			String url = sd.get("id").toString();
			try {
				url = url.split(",")[0].split("http://")[1].split("/")[0];
				uidList.add(url);
			} catch (Exception e) {
				continue;
			}
		}
		//批量查询用户信息
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new BasicDBObject("$in",uidList));
		BasicDBObject backDBO = new BasicDBObject();
		backDBO.put("H", 1);
		backDBO.put("_id", 1);
		DBCursor cur = null;
		while(cur.hasNext())
		{
			DBObject doc = cur.next();
			String url = doc.get("_id").toString();
			int H = 30;
			if(doc.get("H")!=null)
			{
				H = Integer.valueOf(doc.get("H").toString());
			}
			HashMap dataMap = new HashMap();
			dataMap.put("H", H);
			userMap.put(url, dataMap);
		}
		cur.close();
		//整合数据
		for(SolrDocument sd : sdl)
		{
			String url = sd.get("id").toString();
			try {
				url = url.split(",")[0].split("http://")[1].split("/")[0];
				if(userMap.containsKey(url))
				{
					HashMap map = userMap.get(url);
					HashMap dataMap = new HashMap();
					dataMap.put("url", sd.get("id"));
					dataMap.put("H", map.get("H"));
					dataMap.put("source", sd.get("source"));
					dataMap.put("title", sd.get("title"));
					dataMap.put("content", sd.get("content"));
					dataMap.put("time", sf.format(sd.get("time")));
					list.add(dataMap);
				}else
				{
					HashMap dataMap = new HashMap();
					dataMap.put("url", sd.get("id"));
					dataMap.put("source", sd.get("source"));
					dataMap.put("title", sd.get("title"));
					dataMap.put("content", sd.get("content"));
					dataMap.put("time", sf.format(sd.get("time")));
					dataMap.put("H", 30);
					list.add(dataMap);
				}
			} catch (Exception e) {
				continue;
			}
		}
		return list;
	}
}
