package com.zhiwei.wjgg.mongo.service.impl;

import java.util.List;

import com.mongodb.DBObject;
import com.zhiwei.wjgg.model.Status;

/** 
 * @ClassName: GetWeiboInfoDao 
 * @Description: TODO(获取微博详细信息) 
 * @author zhiweizhang 
 * @date 2015年7月25日 上午10:56:44  
 */
public interface GetWeiboInfoDao {
	
		/***
		 * @deprecated:根据mid查询库中转发此条微博的账号
		 * @param String mid
		 * 						微博mid
		 * @return String
		 * **/
	List<String> getWeiboRequest(String mid);
	
	
	/***
	 * 向微博中添加信息
	 * @param List<Status>
	 * 						微博数据集合
	 * @return
	 * **/
	 int addWeibo(List<Status> list);
	 
	 /**
	  * 根据微博mid查询微博数据
	  * @param String mid
	  * */
	DBObject showStatusByMid(String mid);
}
