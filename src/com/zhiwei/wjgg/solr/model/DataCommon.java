/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月3日
    * @version 1.00 
*/
package com.zhiwei.wjgg.solr.model;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: DataCommon
 * @author 落花流水
 * @date 2016年3月3日 上午11:10:16
 */
public class DataCommon
{
    @Field
    protected String content;
    
    @Field
    protected String title;
    
    @Field
    protected long savetime;
    
    @Field
    protected long rsid;
    
    @Field
    protected String username;
    
    @Field
    protected int vtype;
    
    @Field
    protected String user_id;
    
    @Field
    protected String text;
    
    @Field
    protected long _ts;
    
    @Field
    protected long rstime;
    
    @Field
    protected String ns;
    
    @Field
    protected int isForward;
    
    @Field
    protected String retweet_status_id;
    
    @Field
    protected String source;
    
    @Field
    protected Object time;
    
    @Field
    protected String roottext;
    
    @Field
    protected String id;
    
    @Field
    protected String pt;
    
    @Field
    protected String imgUrl;
    
    @Field
    protected String type;
    
    /**
     * @return the content
     */
    public String getContent()
    {
        return content;
    }
    
    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * @return the savetime
     */
    public long getSavetime()
    {
        return savetime;
    }
    
    /**
     * @return the rsid
     */
    public long getRsid()
    {
        return rsid;
    }
    
    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * @return the vtype
     */
    public int getVtype()
    {
        return vtype;
    }
    
    /**
     * @return the user_id
     */
    public String getUser_id()
    {
        return user_id;
    }
    
    /**
     * @return the text
     */
    public String getText()
    {
        return text;
    }
    
    /**
     * @return the _ts
     */
    public long get_ts()
    {
        return _ts;
    }
    
    /**
     * @return the rstime
     */
    public long getRstime()
    {
        return rstime;
    }
    
    /**
     * @return the ns
     */
    public String getNs()
    {
        return ns;
    }
    
    /**
     * @return the isForward
     */
    public int getIsForward()
    {
        return isForward;
    }
    
    /**
     * @return the retweet_status_id
     */
    public String getRetweet_status_id()
    {
        return retweet_status_id;
    }
    
    /**
     * @return the source
     */
    public String getSource()
    {
        return source;
    }
    
    /**
     * @return the time
     */
    public Object getTime()
    {
        return time;
    }
    
    /**
     * @return the roottext
     */
    public String getRoottext()
    {
        return roottext;
    }
    
    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * @return the pt
     */
    public String getPt()
    {
        return pt;
    }
    
    /**
     * @return the imgUrl
     */
    public String getImgUrl()
    {
        return imgUrl;
    }
    
    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * @param content the content to set
     */
    public void setContent(String content)
    {
        this.content = content;
    }
    
    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * @param savetime the savetime to set
     */
    public void setSavetime(long savetime)
    {
        this.savetime = savetime;
    }
    
    /**
     * @param rsid the rsid to set
     */
    public void setRsid(long rsid)
    {
        this.rsid = rsid;
    }
    
    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     * @param vtype the vtype to set
     */
    public void setVtype(int vtype)
    {
        this.vtype = vtype;
    }
    
    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }
    
    /**
     * @param text the text to set
     */
    public void setText(String text)
    {
        this.text = text;
    }
    
    /**
     * @param _ts the _ts to set
     */
    public void set_ts(long _ts)
    {
        this._ts = _ts;
    }
    
    /**
     * @param rstime the rstime to set
     */
    public void setRstime(long rstime)
    {
        this.rstime = rstime;
    }
    
    /**
     * @param ns the ns to set
     */
    public void setNs(String ns)
    {
        this.ns = ns;
    }
    
    /**
     * @param isForward the isForward to set
     */
    public void setIsForward(int isForward)
    {
        this.isForward = isForward;
    }
    
    /**
     * @param retweet_status_id the retweet_status_id to set
     */
    public void setRetweet_status_id(String retweet_status_id)
    {
        this.retweet_status_id = retweet_status_id;
    }
    
    /**
     * @param source the source to set
     */
    public void setSource(String source)
    {
        this.source = source;
    }
    
    /**
     * @param time the time to set
     */
    public void setTime(Object time)
    {
        this.time = time;
    }
    
    /**
     * @param roottext the roottext to set
     */
    public void setRoottext(String roottext)
    {
        this.roottext = roottext;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }
    
    /**
     * @param pt the pt to set
     */
    public void setPt(String pt)
    {
        this.pt = pt;
    }
    
    /**
     * @param imgUrl the imgUrl to set
     */
    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }
    
    /**
     * @Title: getTimeString
     * @Description: 返回时间类型的 时间字段
     * @param @return 设定文件
     * @return String 返回类型
     */
    public String getTimeString()
    {
        return "";
    }
    
    /**
     * 
     * @Title: getTimeDate
     * @Description: 返回时间类型的 时间字段
     * @param @return 设定文件
     * @return Date 返回类型
     */
    public Date getTimeDate()
    {
        return null;
    }
}
