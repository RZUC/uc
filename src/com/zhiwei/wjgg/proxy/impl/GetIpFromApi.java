/** 
 * @Title: GetIpFromApi.java 
 * @Package com.zhiwei.eventFulRun.proxy.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author Abner Liu
 * @date 2015年6月18日 下午5:59:45 
 * @version V1.0 
 */
/**
 * 
 */
package com.zhiwei.wjgg.proxy.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.zhiwei.wjgg.DBTemp.RedisTemplate;

import redis.clients.jedis.Jedis;


/**
 * @ClassName: GetIpFromApi
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Abner Liu
 * @date 2015年6月18日 下午5:59:45
 */
public class GetIpFromApi extends RedisTemplate implements Runnable {

	@Override
	public void run() {

		List<String> iplist = new ArrayList<String>();
		while (true) {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(
					"http://revx.daili666.com/ip/?tid=557815486629706&num=50&filter=on&foreign=none&delay=1");
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(2000).setConnectTimeout(2000)
					.setSocketTimeout(2000).build();
			httpGet.setConfig(requestConfig);
			try {
				HttpResponse response = httpClient.execute(httpGet);
				String ips = EntityUtils.toString(response.getEntity());
				String ipArray[] = ips.split("\r\n");

				for (int i = 0; i < ipArray.length; i++) {
					httpGet = new HttpGet(
							"http://news.baidu.com/ns?ct=1&rn=20&ie=utf-8&bs=list.toarray&rsv_bp=1&sr=0&cl=2&f=8&prevct=no&tn=news&word=toarray&rsv_sug3=1&rsv_sug4=35&rsv_sug2=0&inputT=649");
					requestConfig = RequestConfig
							.custom()
							.setProxy(
									new HttpHost(ipArray[i].split(":")[0],
											Integer.valueOf(ipArray[i]
													.split(":")[1])))
							.setConnectionRequestTimeout(1000)
							.setConnectTimeout(1000).setSocketTimeout(1000)
							.build();
					httpGet.setConfig(requestConfig);
					try {
						response = httpClient.execute(httpGet);
						iplist.add(ipArray[i]);
						// System.out.println(true);
					} catch (Exception e) {
						// System.out.println("err");
					}

				}

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//
			if (iplist.size() > 20) {
				break;
			}
			httpClient.getConnectionManager().shutdown();
		}
		Jedis jedis = this.getJedisInstance();
		String[] result = new String[iplist.size()];
		for (int i = 0; i < iplist.size(); i++) {
			result[i] = iplist.get(i);
		}
		jedis.rpush("ef_proxyPool", result);
		System.out.println(jedis.llen("ef_proxyPool"));
		this.returnJedisResource(jedis);

	}

	@Test
	public void UnitTest() {
		run();
	}

}
