package com.zhiwei.wjgg.wechat.search.api;

import java.util.List;

import com.mongodb.DBObject;

/**
 * 
 * @ClassName: WechatSogouSearch
 * @Description: TODO(搜狗微信检索接口)
 * @author Abner Liu
 * @date 2015年6月19日 下午6:19:30
 */
public interface WechatSogouSearchBiz {
	/**
	 * 
	 * @Title: wechatKeywordSearch
	 * @Description: 根据关键词在搜狗微信搜索文章
	 * @param @param keyword 关键词组
	 * @param @return 设定文件
	 * @return List<DBObject> 返回类型
	 */
	public List<DBObject> wechatKeywordSearch(String keyword);

}
