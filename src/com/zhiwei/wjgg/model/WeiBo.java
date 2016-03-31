/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月1日
    * @version 1.00 
*/
package com.zhiwei.wjgg.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description: 91Mongo对应的数据
 * @ClassName: WeiBo
 * @author 落花流水
 * @date 2016年3月1日 下午3:18:54
 */
@Document(collection = "status")
public class WeiBo
{
    @Id
    private String id;
    
    private String time;
    
    private String day;
    
    private String month;
    
    private String hour;
    
    private String text;
    
    private Long rstime;
    
    private String user_id;
    
    private String username;
    
    private String source;
    
    private Integer retweet_count;
    
    private Integer reply_count;
    
    private String url;
    
    private String img_url;
    
    private String weibo_img;
    
    private Integer vtype;
    
    private Integer fensi;
    
    private Integer guanzhu;
    
    private Integer weibo;
    
    private String description;
    
    private List<String> pic_urls;
    
    private Integer isForward;
    
    @Override
    public String toString()
    {
        return "WeiBo [id=" + id + ", time=" + time + ", day=" + day + ", month=" + month + ", hour=" + hour + ", text="
            + text + ", rstime=" + rstime + ", user_id=" + user_id + ", username=" + username + ", source=" + source
            + ", retweet_count=" + retweet_count + ", reply_count=" + reply_count + ", url=" + url + ", img_url="
            + img_url + ", weibo_img=" + weibo_img + ", vtype=" + vtype + ", fensi=" + fensi + ", guanzhu=" + guanzhu
            + ", weibo=" + weibo + ", description=" + description + ", pic_urls=" + pic_urls + ", isForward="
            + isForward + "]";
    }
    
    public WeiBo(String id, String time, String day, String month, String hour, String text, Long rstime,
        String user_id, String username, String source, Integer retweet_count, Integer reply_count, String url,
        String img_url, String weibo_img, Integer vtype, Integer fensi, Integer guanzhu, Integer weibo,
        String description, List<String> pic_urls, Integer isForward)
    {
        super();
        this.id = id;
        this.time = time;
        this.day = day;
        this.month = month;
        this.hour = hour;
        this.text = text;
        this.rstime = rstime;
        this.user_id = user_id;
        this.username = username;
        this.source = source;
        this.retweet_count = retweet_count;
        this.reply_count = reply_count;
        this.url = url;
        this.img_url = img_url;
        this.weibo_img = weibo_img;
        this.vtype = vtype;
        this.fensi = fensi;
        this.guanzhu = guanzhu;
        this.weibo = weibo;
        this.description = description;
        this.pic_urls = pic_urls;
        this.isForward = isForward;
    }
    
    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * @return the time
     */
    public String getTime()
    {
        return time;
    }
    
    /**
     * @return the day
     */
    public String getDay()
    {
        return day;
    }
    
    /**
     * @return the month
     */
    public String getMonth()
    {
        return month;
    }
    
    /**
     * @return the hour
     */
    public String getHour()
    {
        return hour;
    }
    
    /**
     * @return the text
     */
    public String getText()
    {
        return text;
    }
    
    /**
     * @return the rstime
     */
    public Long getRstime()
    {
        return rstime;
    }
    
    /**
     * @return the user_id
     */
    public String getUser_id()
    {
        return user_id;
    }
    
    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * @return the source
     */
    public String getSource()
    {
        return source;
    }
    
    /**
     * @return the retweet_count
     */
    public Integer getRetweet_count()
    {
        return retweet_count;
    }
    
    /**
     * @return the reply_count
     */
    public Integer getReply_count()
    {
        return reply_count;
    }
    
    /**
     * @return the url
     */
    public String getUrl()
    {
        return url;
    }
    
    /**
     * @return the img_url
     */
    public String getImg_url()
    {
        return img_url;
    }
    
    /**
     * @return the weibo_img
     */
    public String getWeibo_img()
    {
        return weibo_img;
    }
    
    /**
     * @return the vtype
     */
    public Integer getVtype()
    {
        return vtype;
    }
    
    /**
     * @return the fensi
     */
    public Integer getFensi()
    {
        return fensi;
    }
    
    /**
     * @return the guanzhu
     */
    public Integer getGuanzhu()
    {
        return guanzhu;
    }
    
    /**
     * @return the weibo
     */
    public Integer getWeibo()
    {
        return weibo;
    }
    
    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return the pic_urls
     */
    public List<String> getPic_urls()
    {
        return pic_urls;
    }
    
    /**
     * @return the isForward
     */
    public Integer getIsForward()
    {
        return isForward;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }
    
    /**
     * @param time the time to set
     */
    public void setTime(String time)
    {
        this.time = time;
    }
    
    /**
     * @param day the day to set
     */
    public void setDay(String day)
    {
        this.day = day;
    }
    
    /**
     * @param month the month to set
     */
    public void setMonth(String month)
    {
        this.month = month;
    }
    
    /**
     * @param hour the hour to set
     */
    public void setHour(String hour)
    {
        this.hour = hour;
    }
    
    /**
     * @param text the text to set
     */
    public void setText(String text)
    {
        this.text = text;
    }
    
    /**
     * @param rstime the rstime to set
     */
    public void setRstime(Long rstime)
    {
        this.rstime = rstime;
    }
    
    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }
    
    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     * @param source the source to set
     */
    public void setSource(String source)
    {
        this.source = source;
    }
    
    /**
     * @param retweet_count the retweet_count to set
     */
    public void setRetweet_count(Integer retweet_count)
    {
        this.retweet_count = retweet_count;
    }
    
    /**
     * @param reply_count the reply_count to set
     */
    public void setReply_count(Integer reply_count)
    {
        this.reply_count = reply_count;
    }
    
    /**
     * @param url the url to set
     */
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    /**
     * @param img_url the img_url to set
     */
    public void setImg_url(String img_url)
    {
        this.img_url = img_url;
    }
    
    /**
     * @param weibo_img the weibo_img to set
     */
    public void setWeibo_img(String weibo_img)
    {
        this.weibo_img = weibo_img;
    }
    
    /**
     * @param vtype the vtype to set
     */
    public void setVtype(Integer vtype)
    {
        this.vtype = vtype;
    }
    
    /**
     * @param fensi the fensi to set
     */
    public void setFensi(Integer fensi)
    {
        this.fensi = fensi;
    }
    
    /**
     * @param guanzhu the guanzhu to set
     */
    public void setGuanzhu(Integer guanzhu)
    {
        this.guanzhu = guanzhu;
    }
    
    /**
     * @param weibo the weibo to set
     */
    public void setWeibo(Integer weibo)
    {
        this.weibo = weibo;
    }
    
    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    /**
     * @param pic_urls the pic_urls to set
     */
    public void setPic_urls(List<String> pic_urls)
    {
        this.pic_urls = pic_urls;
    }
    
    /**
     * @param isForward the isForward to set
     */
    public void setIsForward(Integer isForward)
    {
        this.isForward = isForward;
    }
    
}
