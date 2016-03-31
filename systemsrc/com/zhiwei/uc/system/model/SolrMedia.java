package com.zhiwei.uc.system.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @ClassName: SolrMedia 
 * @Description: TODO(来自Solr的网媒实体类) 
 * @author chenweitao 
 * @date 2016年3月5日 上午9:18:01
 */
@Document
public class SolrMedia
{
    private String id;
    
    private String content;
    
    private String pt;
    
    private String title;
    
    private String source;
    
    private Date time;
    
    private long savetime;
    
    private long rsid;
    
    private String type;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getPt()
    {
        return pt;
    }

    public void setPt(String pt)
    {
        this.pt = pt;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }

    public long getSavetime()
    {
        return savetime;
    }

    public void setSavetime(long savetime)
    {
        this.savetime = savetime;
    }

    public long getRsid()
    {
        return rsid;
    }

    public void setRsid(long rsid)
    {
        this.rsid = rsid;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    
    
}
