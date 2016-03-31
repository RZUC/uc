package com.zhiwe.uc.system.DBTemp;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated:Solr模板
 * @author Man
 * @version 2013-11-8 下午5:34:38 by:TangTou
 */
public class HttpSolrServerUtil
{
    public static Logger log = LoggerFactory.getLogger(HttpSolrServerUtil.class);
    
    private HttpSolrServer solrServer;
    
    private String solrUrl;
    
    private int timeOut;
    
    protected int maxTryCount;
    
    public HttpSolrServerUtil()
    {
    }
    
    /**
     * @Title: HttpSolrServerUtil
     * @Description: 构造函数
     * @param @param solrUrl solr 地址
     * @param @param timeOut 超时时间
     * @param @param maxTryCount 连接尝试次数
     * @return
     */
    public HttpSolrServerUtil(String solrUrl, int timeOut, int maxTryCount)
    {
        super();
        this.solrUrl = solrUrl;
        this.maxTryCount = maxTryCount;
        if (timeOut > 0)
        {
            this.timeOut = timeOut;
        }
        else
        {
            this.timeOut = 20000;
        }
        log.debug("连接地址:{}\t超时时间：{} 毫秒\t最大尝试次数：{}", solrUrl, timeOut, maxTryCount);
    }
    
    
    
    /**
     * @Title: getHttpSolrServer
     * @Description: 获取solr连接
     * @param @return 设定文件
     * @return HttpSolrServer 返回类型
     */
    public HttpSolrServer getHttpSolrServer()
    {   
        if (solrServer == null)
        {   
            log.debug("当前solrServer为空，获取新的solrServer");
            solrServer = newSolrServer(maxTryCount);
        }
        
        return solrServer;
    }
    
    private HttpSolrServer newSolrServer(int tryCount)
    {
        HttpSolrServer solrServer = null;
        try
        {
            solrServer = new HttpSolrServer(solrUrl);
            solrServer.setConnectionTimeout(timeOut);
            solrServer.setSoTimeout(timeOut);
            solrServer.setMaxRetries(1);
            solrServer.setAllowCompression(true);
        }
        catch (Exception ex)
        {
            if (tryCount > 0)
            {
                newSolrServer(--tryCount);
            }
            else
            {
                log.error("连接服务器失败：[连接地址:{}\t超时时间：{} 毫秒 \t连接尝试次数：{}]", solrUrl, timeOut, maxTryCount);
                ex.printStackTrace();
            }
        }
        return solrServer;
    }
}
