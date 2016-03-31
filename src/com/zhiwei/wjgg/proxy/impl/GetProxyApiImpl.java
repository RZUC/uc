/** 
 * @Title: GetProxyApiImpl.java 
 * @Package com.zhiwei.eventFulRun.proxy.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author Abner Liu
 * @date 2015年6月18日 下午5:57:40 
 * @version V1.0 
 */
/**
 * 
 */
package com.zhiwei.wjgg.proxy.impl;

import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.zhiwei.wjgg.DBTemp.RedisTemplate;
import com.zhiwei.wjgg.proxy.GetProxyApi;

/**
 * @ClassName: GetProxyApiImpl
 * @Description: TODO(代理IP操作实现类)
 * @author Abner Liu
 * @date 2015年6月18日 下午5:57:40
 */
public class GetProxyApiImpl extends RedisTemplate implements GetProxyApi {
	private static Long ipIndex = (long) 0;

	@Override
	public String getProxy() {
		try {
			Jedis jedis = this.getJedisInstance();
			if (ipIndex == 0) {
				long count = jedis.llen("ef_proxyPool");
				ipIndex = (long) (count - 1);
			}
			String ip = jedis.lindex("ef_proxyPool", ((long) ipIndex--));
			if (jedis.llen("ef_proxyPool") < 50) {
				Thread thread = new Thread(new GetIpFromApi());
				thread.run();
			}
			this.returnJedisResource(jedis);
			return ip;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void destoryProxy(String proxy){
		Jedis jedis = this.getJedisInstance();
		long destoryCount = jedis.lrem("ef_proxyPool", 0, proxy);
		this.returnJedisResource(jedis);

	}

	@Test
	public void UnitTest() {
		for (int i = 0; i < 5000000; i++) {
			String ip = getProxy();
			if (i % 2 == 1) {
				destoryProxy(ip);
			}

		}
	}
}
