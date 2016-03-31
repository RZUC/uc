/** 
 * @Title: WechatSogouSearchBizImpl.java 
 * @Package com.zhiwei.eventFulRun.wechat.search.api.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author Abner Liu
 * @date 2015年6月19日 下午6:21:35 
 * @version V1.0 
 */
/**
 * 
 */
package com.zhiwei.wjgg.wechat.search.api.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zhiwei.wjgg.wechat.search.api.WechatSogouSearchBiz;

/**
 * @ClassName: WechatSogouSearchBizImpl
 * @Description: TODO(搜狗微信检索实现类)
 * @author Abner Liu
 * @date 2015年6月19日 下午6:21:35
 */
public class WechatSogouSearchBizImpl implements WechatSogouSearchBiz {
	@Test
	public void UnitTest() {

		wechatKeywordSearch("高考+成绩");
	}

	@Override
	public List<DBObject> wechatKeywordSearch(String keyword) {
		List<DBObject> result = new ArrayList<DBObject>();
		// 关键词处理
//		keyword = keyword.replaceAll("\\(", "").replaceAll("\\)", "")
//				.replaceAll("\\+", " ");
		// 实例新的容器
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 容器配置
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(2000).setConnectTimeout(2000)
				.setSocketTimeout(2000).build();
		HttpGet httpGet;
		int page = 1;// 总页数
		for (int i = 0; i < page; i++) {
			String url = "http://weixin.sogou.com/weixin?query="
					+ URLEncoder.encode(keyword)
					+ "&fr=sgsearch&sut=2345&lkt=1%2C1434709933110%2C1434709933110&type=2&sst0="
					+ System.currentTimeMillis() + "&page=" + (i + 1)
					+ "&ie=utf8&w=01019900&dr=1&tsn=1";
			
			System.out.println("微信=="+url);
			httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			try {
				HttpResponse response = httpClient.execute(httpGet);
				// 如果状态码正常则进入解析阶段
				if (response.getStatusLine().getStatusCode() == 200) {
					String html = EntityUtils.toString(response.getEntity());
					Document document = Jsoup.parse(html);
					
					Elements wx = document.select("[class=results]").select("div.wx-rb3");
					for (Element element : wx) {
						DBObject object = new BasicDBObject();
						String content = element.select("[class=txt-box]>p")
								.text().replaceAll("\r", "")
								.replaceAll("\n", "");
						object.put("content", content);
						String id = element.select("[class=txt-box]>h4>a")
								.attr("href");
						object.put("_id", id);
						object.put("pt", "微信");
						String source = element.select("[class=s-p]>a").attr(
								"title");
						String openid = element.select("[class=s-p]>a").attr("i");
						object.put("source", source);
						String time = element.select("[class=s-p]").attr("t");
						object.put("time", new Date(Long.valueOf(time) * 1000L));
						String title = element.select("[class=txt-box]>h4>a")
								.text();
						object.put("title", title);
						object.put("type", "微信"+","+openid);
						object.put("savetime", new Date().getTime());
//						System.out.println("微信数据title："+title);
						result.add(object);
					}
					// 解析最大可寻页码
					Elements elements = document
							.select("[id=pagebar_container]>a");
					page = elements.size();
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				continue;
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
		System.out.println(result.size());
		httpClient.getConnectionManager().shutdown();
		return result;
	}
}
