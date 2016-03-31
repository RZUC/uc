package com.zhiwei.wjgg.mongo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/** 
 * @Package com.zhiwei.event.mongo.weixin.api.impl
 * @ClassName: GetWeiXinDataByMongoApiImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 志伟
 * @date 2015-5-25 下午4:31:15

 */
public class GetWeiXinUserInfoDaoImpl   implements
		GetWeiXinUserInfoDao {
	private static String collName = "wx_userInfo";
	private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public GetWeiXinUserInfoDaoImpl()
	{
	}
	
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HashMap> getWeixinUserInfoById(SolrDocumentList sdl) {
		List<HashMap> list = new ArrayList<HashMap>();
		
		for(SolrDocument sd : sdl)
		{
			HashMap userMap = new HashMap();
		
			String openid = "";
			String username ="";
			String url = "";
			
			//查询openid
			if(sd.get("source").toString().contains(","))
			{
				openid = sd.get("source").toString().split(",")[1];
				openid = "http://weixin.sogou.com/gzh?openid="+openid;
				username = sd.get("source").toString().split(",")[0];
			}else
			{
				username = sd.get("source").toString();
			}
			if(sd.get("type").toString().contains(","))
			{	
				openid = sd.get("type").toString().split(",")[1];
				openid = "http://weixin.sogou.com/gzh?openid="+openid;
			}
			//判断url
			if(sd.get("_id")!=null)
			{
				url = (String)sd.get("_id");
			}else
			{
				url = (String)sd.get("id");
			}
			
			userMap.put("url", url);
			userMap.put("title", sd.get("title"));
			userMap.put("username", username);
			userMap.put("content", sd.get("content"));
			userMap.put("time", sf.format(sd.get("time")));
			userMap.put("pt", "wechat");
			//查询用户信息
			DBObject query = new BasicDBObject();
			query.put("openid", openid);
			DBObject backDBO = new BasicDBObject();
			backDBO.put("img_url", 1);
			backDBO.put("H", 1);
			backDBO.put("avg_read", 1);
			DBObject doc = null;
			int H = 30;
			int avg_read = 0;
			if(doc != null)
			{
				userMap.put("img_url", doc.get("imgurl"));
				if(doc.get("H") != null)
				{
					H = Integer.valueOf(doc.get("H").toString());
				}
				if(doc.get("avg_read")!=null)
				{
					avg_read =  Integer.valueOf(doc.get("avg_read").toString());
				}
				userMap.put("H", H);
				userMap.put("avg_read", avg_read);
			}else   //需要查询用户信息
			{
				DBObject query2 = new BasicDBObject();
				query2.put("username", username);
				DBObject doc2 = null;
				if(doc2!=null)
				{
					userMap.put("img_url", doc2.get("imgurl"));
					if(doc2.get("H") != null)
					{
						H = Integer.valueOf(doc2.get("H").toString());
					}
					if(doc2.get("avg_read")!=null)
					{
						avg_read =  Integer.valueOf(doc2.get("avg_read").toString());
					}
					userMap.put("H", H);
					userMap.put("avg_read", avg_read);
				}else
				{
					userMap.put("img_url", "http://img01.sogoucdn.com/app/a/100520090/oIWsFt-O_GuORDFwn7D9RMG2kyCA");
					userMap.put("H", H);
					userMap.put("avg_read", 30);
				}
			}
			list.add(userMap);
		}
		return list;
	}

	
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HashMap> getWeixinUserInfoByIdList(SolrDocumentList sdl) {
		List<HashMap> list = new ArrayList<HashMap>(); //数据总集合
		
		HashMap<String,HashMap> openidMap = new HashMap<String,HashMap>(); //有openid的集合
		HashMap<String,HashMap> usernameMap = new HashMap<String,HashMap>();//有usernmae的集合
		
		List<String> openidList = new ArrayList();
		List<String> nameList = new ArrayList();
		for(SolrDocument sd : sdl)
		{
			String openid = null;  //openid
			String username = null; //用户昵称
			//查询openid
			if(sd.get("source").toString().contains(","))
			{
				openid = sd.get("source").toString().split(",")[1];
				username = sd.get("source").toString().split(",")[0];
			}else
			{
				username = sd.get("source").toString();
			}
			if(sd.get("type").toString().contains(","))
			{	
				openid = sd.get("type").toString().split(",")[1];
			}
			
			if(openid!=null)
			{
				openid = "http://weixin.sogou.com/gzh?openid="+openid;
				openidList.add(openid);
			}else
			{
				nameList.add(username);
			}
		}
		
		DBObject backDBObject = new BasicDBObject();
		backDBObject.put("openid", 1);
		backDBObject.put("username", 1);
		backDBObject.put("H", 1);
		backDBObject.put("imgurl", 1);
		backDBObject.put("avg_read", 1);
		
		DBObject query1 = new BasicDBObject();
		query1.put("openid", new BasicDBObject("$in",openidList));
		DBObject query2 = new BasicDBObject();
		query2.put("username", new BasicDBObject("$in",nameList));
		BasicDBList req = new BasicDBList();
		req.add(query1);
		req.add(query2);
		
		DBObject query = new BasicDBObject();
		query.put("$or", req);
		DBCursor cur = null;
		int i = 0;
		while(cur.hasNext())
		{
			DBObject doc = cur.next();
			String username = doc.get("username").toString();
			String openId = doc.get("openid").toString();
			int H = 30;
			int avg_read = 30;
			if(doc.get("H") != null)
			{
				H = Integer.valueOf(doc.get("H").toString());
			}
			if(doc.get("avg_read")!=null)
			{
				avg_read =  Integer.valueOf(doc.get("avg_read").toString());
			}
			
			if(!openidMap.containsKey(openId))
			{
				HashMap userMap = new HashMap();
				userMap.put("H", H);
				userMap.put("img_url", doc.get("imgurl"));
				userMap.put("avg_read", avg_read);
				openidMap.put(openId, userMap);
			}else if(!usernameMap.containsKey(username))
			{
				HashMap userMap = new HashMap();
				userMap.put("H", H);
				userMap.put("img_url", doc.get("imgurl"));
				userMap.put("avg_read", avg_read);
				usernameMap.put(username, userMap);
			}
		}
		cur.close();
		
		for(SolrDocument sd : sdl)
		{
			String openid = null;
			String username = null;
			String url = null;
			//查询openid
			if(sd.get("source").toString().contains(","))
			{
				openid = sd.get("source").toString().split(",")[1];
				username = sd.get("source").toString().split(",")[0];
			}else
			{
				username = sd.get("source").toString();
			}
			if(sd.get("type").toString().contains(","))
			{	
				openid = sd.get("type").toString().split(",")[1];
			}
			//判断url
			if(sd.get("_id")!=null)
			{
				url = (String)sd.get("_id");
			}else
			{
				url = (String)sd.get("id");
			}
			
			if(openid!=null)
			{
				openid = "http://weixin.sogou.com/gzh?openid="+openid;
			}
			
			if(openidMap.containsKey(openid))
			{
				HashMap map = openidMap.get(openid);
				HashMap dataMap = new HashMap();
				dataMap.put("H", map.get("H"));
				dataMap.put("img_url", map.get("img_url"));
				dataMap.put("avg_read", map.get("avg_read"));
				dataMap.put("url", url);
				dataMap.put("username", username);
				dataMap.put("content", sd.get("content"));
				dataMap.put("title", sd.get("title"));
				dataMap.put("time", sf.format(sd.get("time")));
				list.add(dataMap);
			}else if(usernameMap.containsKey(username))
			{
				HashMap map = usernameMap.get(username);
				HashMap dataMap = new HashMap();
				dataMap.put("H", map.get("H"));
				dataMap.put("img_url", map.get("img_url"));
				dataMap.put("avg_read", map.get("avg_read"));
				dataMap.put("url", url);
				dataMap.put("username", username);
				dataMap.put("content", sd.get("content"));
				dataMap.put("title", sd.get("title"));
				dataMap.put("time", sf.format(sd.get("time")));
				list.add(dataMap);
			}else
			{
				HashMap dataMap = new HashMap();
				dataMap.put("url", url);
				dataMap.put("username", username);
				dataMap.put("content", sd.get("content"));
				dataMap.put("title", sd.get("title"));
				dataMap.put("time", sf.format(sd.get("time")));
				dataMap.put("H", 30);
				dataMap.put("img_url", "http://img01.sogoucdn.com/app/a/100520090/oIWsFt-O_GuORDFwn7D9RMG2kyCA");
				dataMap.put("avg_read", 30);
				list.add(dataMap);
			}
		}
		
		return list;
	}
}
