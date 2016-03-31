package com.zhiwei.uc.system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @ClassName: SolrWeibo 
 * @Description: TODO(来自Solr的微博实体类) 
 * @author chenweitao 
 * @date 2016年3月5日 上午10:07:49
 */
@Document
public class SolrWeibo
{
    @Id
    private String _id;
    
    private Integer vtypr;
    
    private String username;
    
    private String user_id;
    
    private String text;
    
    private long _ts;
    
    private long rstime;
    
    private String ns;
    
    private Integer isForward;
    
    private String retweet_status_id;
    
    private String source;
    
    private String time;
    
    private String roottext;

    public String get_id()
    {
        return _id;
    }

    public void set_id(String _id)
    {
        this._id = _id;
    }

    public Integer getVtypr()
    {
        return vtypr;
    }

    public void setVtypr(Integer vtypr)
    {
        this.vtypr = vtypr;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public long get_ts()
    {
        return _ts;
    }

    public void set_ts(long _ts)
    {
        this._ts = _ts;
    }

    public long getRstime()
    {
        return rstime;
    }

    public void setRstime(long rstime)
    {
        this.rstime = rstime;
    }

    public String getNs()
    {
        return ns;
    }

    public void setNs(String ns)
    {
        this.ns = ns;
    }

    public Integer getIsForward()
    {
        return isForward;
    }

    public void setIsForward(Integer isForward)
    {
        this.isForward = isForward;
    }

    public String getRetweet_status_id()
    {
        return retweet_status_id;
    }

    public void setRetweet_status_id(String retweet_status_id)
    {
        this.retweet_status_id = retweet_status_id;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getRoottext()
    {
        return roottext;
    }

    public void setRoottext(String roottext)
    {
        this.roottext = roottext;
    }
    
}
