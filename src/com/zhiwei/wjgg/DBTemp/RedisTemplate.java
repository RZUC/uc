package com.zhiwei.wjgg.DBTemp;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @deprecated:Redis数据库模板类
 * @author TangTou
 *
 */
public class RedisTemplate {
   
    protected static String redisIp= "115.236.59.91";
    protected static int redisPort = 6379;
    protected static JedisPool pool;
    protected static int maxActive = 2048;               //#最大分配的对象数  
    protected static int maxIdle = 200;                  //#最大能够保持idel状态的对象数  
    protected static long maxWait = 1000;                //#当池内没有返回对象时，最大等待时间 
    protected static boolean testOnBorrow = true;        //#当调用borrow Object方法时，是否进行有效性检查
    protected static boolean testOnReturn = true;        //#当调用return Object方法时，是否进行有效性检查  
    
    static{
        initRedisPool();
    }
    
    
    /**
     * @deprecated:获取redis连接的java实例连接Jedis
     * @return
     */
    public Jedis getJedisInstance()
    {
        Jedis jedis = null;
        try
        {
            jedis = pool.getResource();
        }catch(Exception e)
        {
            pool.returnBrokenResource(jedis);
            log.warn("Jedis pool get source error:"+e.getMessage());
        }
        return jedis;
    }
    
    
     /**
     * @deprecated:返还到连接池
     * @param jedis
     */
    public void returnJedisResource(Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }
    
    /**
     * @deprecated:初始化redis连接池
     * @author TangTou
     */
    private static void initRedisPool()
    {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWait(maxWait);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        pool = new JedisPool(config,redisIp,redisPort);
    }
    
    static Logger log = Logger.getLogger(RedisTemplate.class.getName());
    
}