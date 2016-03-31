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

/**
 * @Package com.zhiwei.event.mongo.weibo.api.impl
 * @ClassName: GetWeiboDataByMongoApiImpl
 * @Description: TODO(从mongo中读取微博帐号数据实现类)
 * @author 志伟..
 * @date 2015-5-25 下午2:30:43
 */
public class GetWeiboUserInfoDaoImpl  implements
		GetWeiboUserInfoDao {
	private static String collName = "H_userinfo";
	private static SimpleDateFormat sf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public GetWeiboUserInfoDaoImpl() {
	}

	// 根据微博mid在mongo中反查微博数据
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> getWeiboUserInfoByUid(SolrDocumentList sdl) {

		List<HashMap> list = new ArrayList<HashMap>();

		for (SolrDocument sd : sdl) {
			HashMap userMap = new HashMap();
			DBObject query = new BasicDBObject();
			query.put("_id", sd.get("user_id"));

			DBObject backDBO = new BasicDBObject();
			backDBO.put("username", 1);
			backDBO.put("fensi", 1);
			backDBO.put("H", 1);
			backDBO.put("img_url", 1);
			DBObject doc = null;
			if (doc != null) {
//				String url = "http://weibo.com/" + sd.get("user_id") + "/"
//						+ MidAurl.mid2url(sd.get("_id").toString());
				String img_url = doc.get("img_url").toString();
				if (img_url.contains("180")) {
					img_url = img_url.replaceAll("/180/", "/50/");
				}

				userMap.put("mid", sd.get("_id"));
				userMap.put("uid", sd.get("user_id"));
				userMap.put("username", doc.get("username"));
				userMap.put("fensi", doc.get("fensi"));
				userMap.put("img_url", img_url);
				userMap.put("isforward", sd.get("isForward"));
				userMap.put("content", sd.get("text"));
				userMap.put("vtype", sd.get("vtype"));
				userMap.put("time", sd.get("time") + ":00");
//				userMap.put("url", url);
				userMap.put("pt", "weibo");
				int H = 1;
				if (doc.get("H") != null) {
					H = Integer.valueOf(doc.get("H").toString());
				}
				userMap.put("H", H);
				list.add(userMap);
			}
		}
		return list;
	}

	// 根据微博mid在mongo中反查微博数据
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> getWeiboUserInfoByUidList(SolrDocumentList sdl) {

		List<HashMap> list = new ArrayList<HashMap>();
		List<String> uidList = new ArrayList<String>();
		HashMap<String, HashMap> userMap = new HashMap<String, HashMap>();
		// 存储用户id用于查询
		for (SolrDocument sd : sdl) {
			uidList.add(sd.get("user_id").toString());
		}

		// 批量查询用户
		DBObject query = new BasicDBObject();
		query.put("_id", new BasicDBObject("$in", uidList));
		DBObject backDBO = new BasicDBObject();
		backDBO.put("_id", 1);
		backDBO.put("fensi", 1);
		backDBO.put("img_url", 1);
		DBCursor cur = null;
		while (cur.hasNext()) {
			DBObject doc = cur.next();
			HashMap dataMap = new HashMap();
			String uid = doc.get("_id").toString();
			String img_url = doc.get("img_url").toString();
			int H = 1;
			if (doc.get("H") != null) {
				H = Integer.valueOf(doc.get("H").toString());
			}
			if (img_url.contains("180")) {
				img_url = img_url.replaceAll("/180/", "/50/");
			}
			dataMap.put("H", H);
			dataMap.put("fensi", doc.get("fensi"));
			dataMap.put("img_url", img_url);
			userMap.put(uid, dataMap);
		}
		cur.close();
		// 整合数据
		for (SolrDocument sd : sdl) {
//			String url = "http://weibo.com/" + sd.get("user_id") + "/"
//					+ MidAurl.mid2url(sd.get("_id").toString());
			if (userMap.containsKey(sd.get("user_id").toString())) {
				
				HashMap map = userMap.get(sd.get("user_id"));
				HashMap dataMap = new HashMap();
				dataMap.put("mid", sd.get("_id"));
				dataMap.put("H", map.get("H"));
				dataMap.put("img_url", map.get("img_url"));
				dataMap.put("fensi", map.get("fensi"));
				dataMap.put("uid", sd.get("user_id"));
				dataMap.put("username", sd.get("username"));
				dataMap.put("isforward", sd.get("isForward"));
				dataMap.put("content", sd.get("text"));
				dataMap.put("vtype", sd.get("vtype"));
				dataMap.put("time", sd.get("time")+":00");
//				dataMap.put("url", url);
				dataMap.put("retweet_status_id", sd.get("retweet_status_id"));
				list.add(dataMap);
			} else {
				HashMap dataMap = new HashMap();
				dataMap.put("mid", sd.get("_id"));
				dataMap.put("uid", sd.get("user_id"));
				dataMap.put("username", sd.get("username"));
				dataMap.put("isforward", sd.get("isForward"));
				dataMap.put("content", sd.get("text"));
				dataMap.put("vtype", sd.get("vtype"));
				dataMap.put("time", sd.get("time") + ":00");
//				dataMap.put("url", url);
				dataMap.put("H", 30);
				dataMap.put("fensi", 30);
				dataMap.put("retweet_status_id", sd.get("retweet_status_id"));
				dataMap.put("img_url", "http://tp1.sinaimg.cn/1618051664/50/5735009977/0");
				list.add(dataMap);
			}
		}
		return list;
	}

}
