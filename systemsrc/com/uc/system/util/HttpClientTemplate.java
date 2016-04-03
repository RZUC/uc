package com.uc.system.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.httpclient.HttpException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

/**
 * @Description: HttpClient模板类
 * @author Tou Tang
 * @date 2014-11-14 下午3:58:56
 */
public class HttpClientTemplate {
	private static PoolingHttpClientConnectionManager clientManager;
	private static IdleConnectionMonitorThread monitorThread;
	public HttpClientTemplate() {
		if (null == clientManager) {
			clientManager = initHttpClientPool();
			monitorThread = new IdleConnectionMonitorThread(clientManager);
			monitorThread.start();// 启动线程，20秒钟清空一次失效连接
		}
	}

	/**
	 * 
	 * @Description: 执行Request下载网页数据,不使用代理
	 * @param
	 * @return String
	 */
	public String executeHttpRequestGet(String url, String referer) {
		return executeRequestGet(url, referer, null);
	}

	/**
	 * 
	 * @Description: 执行Request下载网页数据,使用代理
	 * @param
	 * @return String
	 */
	public String executeHttpRequestGet(String url, String referer,
			String proxyIp, int proxyPort) {
		HttpHost proxy = new HttpHost(proxyIp, proxyPort);
		return executeRequestGet(url, referer, proxy);
	}
	
	
	/**
	 * 
	 * @Description: 执行Request下载网页数据,不使用代理
	 * @param
	 * @return String
	 */
	public String executeHttpRequestPost(String url, String referer,Map<String,String> params) {
		return executeRequestPost(url, referer, null,params);
	}

	/**
	 * 
	 * @Description: 执行Request下载网页数据,使用代理
	 * @param
	 * @return String
	 */
	public String executeHttpRequestPost(String url, String referer,
			String proxyIp, int proxyPort,Map<String,String> params) {
		HttpHost proxy = new HttpHost(proxyIp, proxyPort);
		return executeRequestPost(url, referer, proxy,params);
	}

	
	
	/**
	 * 
	 * @Description: 关闭连接管理线程
	 * @param
	 * @return void
	 */
	public void shutdown() {
		monitorThread.shutdown();
	}

	/**
	 * 
	 * @Description: 执行Request下载网页数据
	 * @param
	 * @return String
	 */
	private String executeRequestGet(String url, String referer, HttpHost proxy) {
		String result = null;
//		for(int i=1;i<=3;i++)  //针对连接超时的循环三次
//		{
			CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(clientManager).build();
			RequestConfig requestConfig;
			if (null == proxy) {
				requestConfig = RequestConfig.custom().setSocketTimeout(5000)
							.setConnectTimeout(5000).build();
			} else {
				requestConfig = RequestConfig.custom().setSocketTimeout(5000)
							.setConnectTimeout(5000).setProxy(proxy).build();
			}
				
			String userAgent = getUserAgent();
			HttpGet getMethod = new HttpGet(url);
			getMethod.setConfig(requestConfig);
			getMethod.setHeader("Referer", referer);
			getMethod.setHeader("User-Agent", userAgent);
				
			CloseableHttpResponse response = null;
			try {
				response = httpClient.execute(getMethod);
				int statusCode = response.getStatusLine().getStatusCode();
				if ( 200 == statusCode) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						InputStream in = entity.getContent();
						BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
						StringBuilder sb = new StringBuilder();
						String line = null;
						try {
							while ((line = reader.readLine()) != null) {
								sb.append(line);
							}
							result = sb.toString();
							reader.close();
							in.close();
							getMethod.releaseConnection();
//							//清楚浏览器缓存
							response.setHeader("Cache-Control","no-store");
							response.setHeader("Pragrma","no-cache");
							response.close();
//							break;
						} catch (Exception e) {
							e.printStackTrace();
							return null;
//							continue;
						}finally
						{
							reader.close();
							in.close();
							response.close();
						}
					}
				}
				response.close();
//				httpClient.close();
			}catch(HttpException e)
			{
				e.printStackTrace();
				return null;
//				continue;
			}catch (IOException e) {
				e.printStackTrace();
				return null;
//				continue;
			}
//		}
		return result;
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * @Description: 执行RequestPost下载网页数据
	 * @param
	 * @return String
	 */
	private String executeRequestPost(String url,String referer ,HttpHost proxy,Map<String,String> params) {
		String result = null;
		for(int i=1;i<=3;i++)  //针对连接超时的循环三次
		{
			CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(clientManager).build();
			RequestConfig requestConfig;
			if (null == proxy) {
				requestConfig = RequestConfig.custom().setSocketTimeout(5000)
							.setConnectTimeout(5000).build();
			} else {
				requestConfig = RequestConfig.custom().setSocketTimeout(5000)
							.setConnectTimeout(5000).setProxy(proxy).build();
			}
				
			String userAgent = getUserAgent();
			HttpPost postMethod = new HttpPost(url);
			postMethod.setConfig(requestConfig);
			postMethod.setHeader("Referer", referer);
			postMethod.setHeader("User-Agent", userAgent);
			
			 List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
	          
		      Set<String> keySet = params.keySet();  
		      for(String key : keySet) 
		      {  
		           nvps.add(new BasicNameValuePair(key, params.get(key)));  
		      }  
		      
		      try {  
		            postMethod.setEntity(new UrlEncodedFormEntity( nvps, HTTP.UTF_8));  
		        } catch (UnsupportedEncodingException e) {  
		            e.printStackTrace();  
		      }  
		      
			CloseableHttpResponse response = null;
			try {
				response = httpClient.execute(postMethod);
				int statusCode = response.getStatusLine().getStatusCode();
				if ( 200 == statusCode) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						InputStream in = entity.getContent();
						BufferedReader reader = new BufferedReader(new InputStreamReader(in));
						StringBuilder sb = new StringBuilder();
						String line = null;
						try {
							while ((line = reader.readLine()) != null) {
								sb.append(line);
							}
							result = sb.toString();
							reader.close();
							in.close();
							postMethod.releaseConnection();
//							//清楚浏览器缓存
							response.setHeader("Cache-Control","no-store");
							response.setHeader("Pragrma","no-cache");
							response.close();
							break;
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
					}
				}
			}catch(HttpException e)
			{
				e.printStackTrace();
				continue;
			}catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
		return result;
	}
	
	
	
	
	
	
	/**
	 * @Deprecated User-Agent 参数列队
	 * @author 志伟
	 * */
	public String getUserAgent() {
		
		List<String> agentlist = new ArrayList<String>();
		agentlist.add("Mozilla/4.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.137 Safari/537.36");
		agentlist.add("Mozilla/5.0 (Windows NT 6.3;) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36 SE 2.X MetaSr 1.0");// 搜狗浏览器
		agentlist.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)");// 在32位Windows上的32位IE10
		agentlist.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.2; WOW64; Trident/6.0)");// 在32位Windows上的32位IE9.0
		agentlist.add("Mozilla/5.0 (compatible; MSIE 11.0; Windows NT 6.2; WOW64; Trident/6.0)");// 在32位Windows上的32位IE11.0
		agentlist.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)");// 在64位Windows上的32位IE10.0
		agentlist.add("Mozilla/5.0 (compatible; MSIE 11.0; Windows NT 6.2; Trident/6.0)");// 在64位Windows上的32位IE11.0
		agentlist.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64; Trident/6.0)");// 在64位Windows上的64位IE10
		agentlist.add("Mozilla/5.0 (compatible; MSIE 11.0; Windows NT 6.2; Win64; x64; Trident/6.0)");// 在64位Windows上的64位IE11
		agentlist.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; ARM; Trident/6.0)");// 在Windows																						// RT上的IE10
		agentlist.add("Mozilla/5.0 (compatible; MSIE 11.0; Windows NT 6.2; ARM; Trident/6.0)");// 在Windows																						// RT上的IE11
		agentlist.add("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0)");// Firefox														// 31.0
		agentlist.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20120101 Firefox/29.0");// Firefox																									// 29.0
		agentlist.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20120101 Firefox/29.0");// Firefox
																									// 29.0
		return agentlist.get(new Random().nextInt(agentlist.size()));
	}
	

	/**
	 * 
	 * @Description: 初始化HttpClientConnectionManager
	 * @param
	 * @return PoolingHttpClientConnectionManager
	 */
	private PoolingHttpClientConnectionManager initHttpClientPool() {
		PoolingHttpClientConnectionManager clientManager = new PoolingHttpClientConnectionManager();
		clientManager.setMaxTotal(200); // 设置最大连接数
		clientManager.setDefaultMaxPerRoute(20);// 设置每个路由默认连接数
		HttpHost host = new HttpHost("115.239.210.52", 80);// 设置目标主机
		clientManager.setMaxPerRoute(new HttpRoute(host), 50);// 每个路由器对每个服务器允许最大并发访问
		return clientManager;
	}

}
