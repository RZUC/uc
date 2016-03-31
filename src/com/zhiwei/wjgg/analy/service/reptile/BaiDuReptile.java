package com.zhiwei.wjgg.analy.service.reptile;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zhiwei.wjgg.dao.BaiDuDao;
import com.zhiwei.wjgg.proxy.GetProxyApi;
import com.zhiwei.wjgg.proxy.impl.GetProxyApiImpl;
import com.zhiwei.wjgg.util.HttpClientTemplate;
import com.zhiwei.wjgg.util.ReplaceSymbol;
import com.zhiwei.wjgg.util.TreatTime;

/** 
 * @Package com.zhiwei.event.reptile
 * @ClassName: BaiDuReptile
 * @Description: TODO(通过关键词抓取百度新闻数据)
 * @author 志伟
 * @date 2015-5-25 下午6:59:07
 */
public class BaiDuReptile implements Runnable{
	
    BaiDuDao bdDao;
    
	private static Logger logger = LoggerFactory.getLogger(BaiDuReptile.class);
//	private static BaiDuDao bdDao = new BaiDuDaoImpl();  //将百度新闻采集回来的数据存入数据库
	private static HttpClientTemplate httpClient = new HttpClientTemplate(); //抓取网页信息方法
	private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //精确到秒的时间处理方法
	private static String type = "百度新闻";
	private static boolean f = true;  //判断是否有下一页
	private Date startTime ;
	private Date endTime ;
	private List<String> wordList;
	private static boolean useProxy;
	private CountDownLatch countDown;

	
	public BaiDuReptile(List<String> wordList,BaiDuDao bdDao,CountDownLatch countDown)
    {
        super();
        this.bdDao = bdDao;
        this.wordList = wordList;
        this.countDown = countDown;
    }

    public BaiDuReptile(CountDownLatch countDown)
	{
		this.countDown = countDown;
	}
	
	//抓取数据
	@Override
	public void run() 
	{
		List<String> timeList = new ArrayList<String>();
		if(this.isUseProxy())
		{
			timeList = TreatTime.calTimeDiff(startTime, endTime);
		}else
		{
			String time = sf.format(startTime)+","+sf.format(endTime);
			timeList.add(time);
		}
		
		
		for(String words : wordList)
		{
			getHtmlBody(words, timeList,useProxy);
		}
		
		countDown.countDown();
		
	}
	
	/**
	 * @deprecated:根据关键词和时间采集百度新闻相关信息,获取网页信息
	 * @param List<String> wordList
	 * 				关键词 集合
	 * @param List<String> timeList
	 * 				采集时间 集合
	 * @return List<HashMap>
	 * **/
	public int getHtmlBody(String words,List<String> timeList,boolean useProxy)
	{
		List<HashMap> list = new ArrayList<HashMap>();
		int count = 0;
		for(String time : timeList)
		{
			f = true;
			//对采集时间进行处理
			String startTime = time.split(",")[0];
			String endTime = time.split(",")[1];
			System.out.println(startTime+"==========="+endTime);
			
			String timeLong = TreatTime.changeDateFromString(startTime, endTime);
			String bt = timeLong.split(",")[0];
			String et = timeLong.split(",")[1];
			//处理关键词
			String word = URLEncoder.encode(words);
			//拼接百度新闻查询链接
			int page = 0;
			while(f)
			{
				String url = "http://news.baidu.com/ns?from=news&cl=2&bt=" + bt
						+ "&et=" + et + "&q1="+word+"&q3=&q4=&tn=newsdy"
						+ "&ct=0&rn=50&clk=sortbytime&q6=&pn=" + page* 50;
				//通过httpClient下载相关数据
				System.out.println(url);
				String htmlBody = this.getHtmlBodyPrivate(url);
				if(htmlBody != null)
				{
					//解析并获取百度新闻数据,
					List<DBObject> docsList = analysisXML(htmlBody);
					count = count+docsList.size();
					System.out.println("第"+page+"页的数据个数==========="+docsList.size());
					bdDao.addBaiduDataToMongo(docsList);
				}else
				{
					logger.error("百度采集出错！！！！！！！！！！");
				}
				page++;
			}
		}
		
		System.out.println("百度新闻的总数："+count);
		return 2;
	}
	
	/**
	 * @deprecated:解析数据
	 * @param String htmlBody
	 * 				要解析的网页
	 * @return 
	 * 
	 * **/
	private  List<DBObject> analysisXML(String htmlBody)
	{
		
		Map<String,Integer> otherLinkMap = new HashMap<String,Integer>();
		/** 解析页面 */
		Document document = Jsoup.parse(htmlBody);
		/**判断是否有下一页**/
		if(document.select("p#page") == null)
		{
			f = false;
		}else
		{
			if(!document.select("p#page").text().contains("下一页"))
			{
				f = false;
			}
		}
		
		List list = this.getDataInfo(document, otherLinkMap);
		
		/**添加相同新闻**/
		for(Entry<String, Integer> entry : otherLinkMap.entrySet())
		{
			String otherLink = entry.getKey();
			//通过httpClient下载相关数据
			String htmlBodyOther = this.getHtmlBodyPrivate(otherLink);
			Document documentOther = null;
			if(htmlBodyOther != null)
			{
				documentOther = Jsoup.parse(htmlBodyOther);
				List<DBObject> otherList = getDataInfo(documentOther,null);
				list.addAll(otherList);
			}else
			{
				logger.error("百度采集出错！！！！！！！！！！");
			}
		}
		return list;
	}
	
	
	/**
	 * @deprecated：根据时间和关键词采集共有多少条新闻
	 * @param String htmlBody
	 * 				需要解析的页面
	 * @return int count
	 * **/
	
	public static int getCount(String htmlBody,String words) {
		int count  = 0;
		Document document = Jsoup.parse(htmlBody);
		/*** 采集共有多少条数据 **/
		if (document.select("span.nums") != null) {
			String nums = document.select("span.nums").text().split("新闻")[1]
					.split("篇")[0];
			count = Integer.valueOf(nums);
		}else
		{
			System.out.println("此时间段没有相关新闻");
			logger.error("此时间段没有与”"+words+"“相关新闻");
		}
		return count;
	}
	
	/**
	 * @deprecated：解析数据
	 * @param Document document
	 * 						要解析的数据结构
	 * @param HashMap otherLinkMap
	 * 						相同新闻地址map
	 * @return List<DBObject>
	 * **/
	private  List<DBObject> getDataInfo(Document document,Map<String, Integer> otherLinkMap)
	{
		
		List<DBObject> list = new ArrayList<DBObject>();
		/** 解析数据 */
		Elements elm = document.select("div.result");
		String time = "";
		String source = "";
		/** 判断相同数据是否为同一条数据 **/
		Map<String, DBObject> docsMap = new HashMap<String, DBObject>();
		for (Element el : elm) 
		{
			try {
				String link = el.select("h3.c-title").select("a").attr("href");
				// System.out.println(link);
				String title = el.select("h3.c-title").select("a").text();
//				System.out.println(title);
				String soure_time = el.select("div.c-row").select("p.c-author").html();
				/** 截取时间 */
				if (soure_time.contains("&nbsp;&nbsp;")) {
					String tim[] = soure_time.split("&nbsp;&nbsp;");
					time = tim[1];
					source = tim[0];
				} else {
					time = el.select("div.c-row").select("p.c-author").text();
				}
				// 处理文章简介
				String des = el.select("div.c-row").text();
				String s = el.select("div.c-row").select("p.c-author").text();
				String a = el.select("div.c-row").select("div.c-span-last").select("a").text();
				String asd = des.substring(s.length(), des.length());
				Pattern pattern = Pattern.compile("\\d*条相同新闻");
				Matcher matcher = pattern.matcher(asd);
				String aaaa = matcher.replaceAll("");
				String content = aaaa.replaceAll("-", "").replaceAll("百度快照", "");
				/** 文章发布时间处理 **/
				Date date = TreatTime.conversionTime(time);
				/** 采集相同新闻数量 **/
				int otherCount = 0;
				String otherMore = el.select("span.c-info").text();
				if (otherMore.contains("条相同新闻")) 
				{
					otherCount = Integer.valueOf(otherMore.split("条相同新闻")[0]);
				}
				
				//判断是否采集相同新闻
				if(null != otherLinkMap&&otherLinkMap.size()>=0)
				{
					/**采集相同新闻链接**/
					if(el.select("div.c-row").select("a.c-more_link")!=null)
					{
						String otherLink="http://news.baidu.com"+el.select("div.c-row").select("a.c-more_link").attr("href");
						//判断之前的链接中是否存在这个key
						otherLinkMap.put(otherLink, 1);
					}
				}
				
				// 向数据库添加
				DBObject obj = new BasicDBObject();
				link = ReplaceSymbol.ReplaceSymbol(link);
				obj.put("_id", link);
				obj.put("title", title);
				obj.put("content", content);
				obj.put("time", date);
				obj.put("source", source);
				obj.put("type", type);
				obj.put("savetime", new Date().getTime());
				obj.put("pt", type);
				obj.put("otherCount", otherCount);
				
				docsMap.put(title+","+source, obj);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("解析数据出现bug");
				continue;
			}
		}
		
		list = new ArrayList(docsMap.values());
		return list;
	}
	
	
	
	
	
	
	
	
	/**
	 * @deprecated：根据关键词和时间获取原始数据
	 * @param String url
	 * 						采集链接
	 * @return String
	 * **/
	private static String getHtmlBodyPrivate(String url)
	{
		String htmlBody = null;
		for(int i = 1;i<=3;i++)
		{
			if(useProxy)
			{
				GetProxyApi getProxy = new GetProxyApiImpl();
				String proxy = getProxy.getProxy();
				String[] ip =null;
				if(proxy!=null)
				{
					ip = proxy.split(":");
				}else
				{
					try {
						getProxy.destoryProxy(proxy);
					} catch (Exception e) {
						getProxy.destoryProxy(proxy);
						continue;
					}
					proxy = getProxy.getProxy();
					if(null != proxy)
					{
						ip = proxy.split(":");
					}
				}
				
				if(ip!=null)
				{
					htmlBody = httpClient.executeHttpRequestGet(url, null,ip[0],Integer.parseInt(ip[1]));
					if(htmlBody!=null)
					{
//						System.out.println(htmlBody);
						break;
					}else
					{
						getProxy.destoryProxy(proxy);
						proxy = getProxy.getProxy();
						if(null != proxy)
						{
							ip = proxy.split(":");
						}
						continue;
					}
				}else
				{
					System.out.println("ip===="+ip);
				}
			}else
			{
				htmlBody = httpClient.executeHttpRequestGet(url, null);
				if(htmlBody!=null)
				{
					break;
				}else
				{
					continue;
				}
			}
		}
		return htmlBody;
	}

	

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<String> getWordList() {
		return wordList;
	}

	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}

	public boolean isUseProxy() {
		return useProxy;
	}

	public void setUseProxy(boolean useProxy) {
		this.useProxy = useProxy;
	}

}
