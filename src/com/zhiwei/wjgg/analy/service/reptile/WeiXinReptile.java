
package com.zhiwei.wjgg.analy.service.reptile;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mongodb.DBObject;
import com.zhiwei.wjgg.dao.BaiDuDao;
import com.zhiwei.wjgg.wechat.search.api.WechatSogouSearchBiz;
import com.zhiwei.wjgg.wechat.search.api.impl.WechatSogouSearchBizImpl;

/** 
 * @ClassName: WeiXinReptile 
 * @Description: TODO(微信数据爬虫采集类) 
 * @author zhiweizhang 
 * @date 2015年6月23日 上午8:37:05  
 */
public class WeiXinReptile implements Runnable{
	
	BaiDuDao bdDao;//将百度新闻采集回来的数据存入数据库
	
	private static Logger logger = LoggerFactory.getLogger(BaiDuReptile.class);
	private static WechatSogouSearchBiz wechatSearch = new WechatSogouSearchBizImpl(); //微信采集
	private List<String> wordList; //关键词
	private CountDownLatch countDown; //计数器
	
	
	public WeiXinReptile(List<String> wordList,BaiDuDao bdDao,CountDownLatch countDown)
    {
        super();
        this.bdDao = bdDao;
        this.wordList = wordList;
        this.countDown = countDown;
    }


    public WeiXinReptile(CountDownLatch countDown) 
	{
		this.countDown = countDown;
	}
	
	
	//抓取数据
		@Override
		public void run() 
		{
			for(String keyword : wordList)
			{
				List<DBObject> datalist = wechatSearch.wechatKeywordSearch(keyword);
				System.out.println("微信数据"+datalist.size());
				bdDao.addBaiduDataToMongo(datalist);
			}
			countDown.countDown();
			
		}

	public List<String> getWordList() {
		return wordList;
	}

	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}
}
