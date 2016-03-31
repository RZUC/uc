package com.zhiwei.wjgg.mongo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.zhiwei.wjgg.model.Status;
import com.zhiwei.wjgg.model.User;
import com.zhiwei.wjgg.util.TimeUtil;

/** 
 * @ClassName: GetWeiboInfoDaoImpl 
 * @Description: TODO(微博详细信息) 
 * @author zhiweizhang 
 * @date 2015年7月25日 上午10:59:10  
 */
public class GetWeiboInfoDaoImpl implements
		GetWeiboInfoDao {

	private  Logger logger = LoggerFactory.getLogger(GetWeiboInfoDaoImpl.class);
	private String collName = "status";
	private String dbName = "NetWork";
	public GetWeiboInfoDaoImpl() 
	{
		super();
	}

	@Override
	public List<String> getWeiboRequest(String mid) 
	{
		
		List<String> list = new ArrayList<String>();
		
		DBObject query = new BasicDBObject();
		query.put("retweet_status_id", mid);
		DBCursor cur = null;
		while(cur.hasNext())
		{
			DBObject doc = cur.next();
			if(doc!=null)
			{
				String username = doc.get("username").toString();
				int fensi = Integer.valueOf(doc.get("fensi").toString());
				int vtype = Integer.valueOf(doc.get("vtype").toString());
				if(fensi>=50000)
				{
					if(vtype==0||vtype==-1)
					{
						if(!list.contains(username))
						{
							list.add(username);
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public int addWeibo(List<Status> list) {
		for(Status status : list){
			DBObject doc = new BasicDBObject();
			String time = TimeUtil.formatDate(status.getCreatedAt(), "yyyy-MM-dd HH:mm");
			doc.put("_id", status.getMid());
			doc.put("time", time);
			doc.put("day", time.split(" ")[0]);
			doc.put("month", time.substring(0,7));
			doc.put("hour", time.substring(8,10));
			doc.put("text", status.getText());
//			doc.put("rstime", SequenceToWeibo.getNextId());
//			doc.put("source", status.getSource().getName());
			doc.put("pic_urls", status.getPic_urls());
			doc.put("weibo_img", status.getOriginalPic());
			if(status.getUser() != null)
			{
				User user = status.getUser();
				doc.put("user_id", user.getId());
//				doc.put("username", user.getScreenName());
//				String url = "http://weibo.com/"+user.getId()+"/"+MidAurl.mid2url(status.getMid());
//				doc.put("url", url);
//				doc.put("img_url", user.getAvatarLarge());
//				doc.put("vtype", user.getVerifiedType());
//				doc.put("fensi", user.getFollowersCount());
//				doc.put("guanzhu", user.getFriendsCount());
//				doc.put("weibo", user.getStatusesCount());
//				doc.put("description", user.getDescription());
//				doc.put("sex", user.getGender());
//				doc.put("location", user.getLang());
			}else
			{
				doc.put("user_id", null);
				doc.put("img_url", null);
				doc.put("username", null);
				doc.put("url", null);
				doc.put("img_url", null);
				doc.put("vtype", null);
				doc.put("fensi", null);
				doc.put("guanzhu", null);
				doc.put("weibo", null);
				doc.put("description", null);
				doc.put("sex", null);
				doc.put("location", null);
			}
			
			int isForward = 0;
			if(status.getRetweetedStatus() != null)
			{
				isForward = 1;
				Status root = status.getRetweetedStatus();
				String root_time = TimeUtil.formatDate(root.getCreatedAt(), "yyyy-MM-dd HH:mm");
				doc.put("roottext", root.getText());
				doc.put("retweet_status_id", root.getMid());
				doc.put("root_time", root_time);
//				doc.put("rootsource", root.getSource().getName());
				doc.put("root_repost", root.getRepostsCount());
				doc.put("root_reply", root.getCommentsCount());
				
				if(root.getUser() != null)
				{
					User rootUser = root.getUser();
//					doc.put("rootLocation", rootUser.getLocation());
					doc.put("rootuid", rootUser.getId());
//					doc.put("rootname", rootUser.getScreenName());
//					String rooturl = "http://weibo.com/"+rootUser.getId()+"/"+MidAurl.mid2url(root.getMid());
//					doc.put("rooturl", rooturl);
				}else
				{
					doc.put("rootLocation", null);
					doc.put("rootuid", null);
					doc.put("rootname", null);
					doc.put("rooturl",null);
				}

			}
			doc.put("isForward", isForward);
		}
		return 0;
	}
	
	
	
	
	public DBObject showStatusByMid(String mid)
	{
		DBObject query = new BasicDBObject();
		query.put("_id", mid);
		
		DBObject back = new BasicDBObject();
		back.put("retweet_count", 1);
		back.put("reply_count", 1);
		back.put("username", 1);
		back.put("u", 1);
		return null;
//		return this.getReadColl().findOne(query, back);
	}

}
