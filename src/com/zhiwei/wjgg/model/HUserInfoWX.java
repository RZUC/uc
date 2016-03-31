package com.zhiwei.wjgg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "HuserInfoWeixin")
public class HUserInfoWX extends HUserInfo
{
    @Id
    private String id;// 主键
    
    private String uid;// 用户ID
    
    private String username;// 用户昵称
    
    private String levelOne;// 一级分类
    
    private String levelTwo; // 二级分类
    
    private String openid; // openId
    
    private Integer avgReadCount; // 平均阅读数据
    
    private Integer avgLikeCount; // 平均点赞数
    
    private Integer H;// H因子
    
//    private Float channelIndex; // 微信网媒影响力指数
    
    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * @return the uid
     */
    public String getUid()
    {
        return uid;
    }
    
    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * @return the levelOne
     */
    public String getLevelOne()
    {
        return levelOne;
    }
    
    /**
     * @return the levelTwo
     */
    public String getLevelTwo()
    {
        return levelTwo;
    }
    
    /**
     * @return the openid
     */
    public String getOpenid()
    {
        return openid;
    }
    
    /**
     * @return the avgReadCount
     */
    public Integer getAvgReadCount()
    {
        return avgReadCount;
    }
    
    /**
     * @return the avgLikeCount
     */
    public Integer getAvgLikeCount()
    {
        return avgLikeCount;
    }
    
    /**
     * @return the h
     */
    public Integer getH()
    {
        return H;
    }
    
    /**
     * @return the channelIndex
     */
//    public Float getChannelIndex()
//    {
//        return channelIndex;
//    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }
    
    /**
     * @param uid the uid to set
     */
    public void setUid(String uid)
    {
        this.uid = uid;
    }
    
    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     * @param levelOne the levelOne to set
     */
    public void setLevelOne(String levelOne)
    {
        this.levelOne = levelOne;
    }
    
    /**
     * @param levelTwo the levelTwo to set
     */
    public void setLevelTwo(String levelTwo)
    {
        this.levelTwo = levelTwo;
    }
    
    /**
     * @param openid the openid to set
     */
    public void setOpenid(String openid)
    {
        this.openid = openid;
    }
    
    /**
     * @param avgReadCount the avgReadCount to set
     */
    public void setAvgReadCount(Integer avgReadCount)
    {
        this.avgReadCount = avgReadCount;
    }
    
    /**
     * @param avgLikeCount the avgLikeCount to set
     */
    public void setAvgLikeCount(Integer avgLikeCount)
    {
        this.avgLikeCount = avgLikeCount;
    }
    
    /**
     * @param h the h to set
     */
    public void setH(Integer h)
    {
        H = h;
    }
    
    /**
     * @param channelIndex the channelIndex to set
     */
//    public void setChannelIndex(Float channelIndex)
//    {
//        this.channelIndex = channelIndex;
//    }
    
    /*
     * (非 Javadoc) <p>Title:toString</p> <p>Description: </p>
     * 
     * @return
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "HUserInfoWX [id=" + id + ", uid=" + uid + ", username=" + username + ", levelOne=" + levelOne
            + ", levelTwo=" + levelTwo + ", openid=" + openid + ", avgReadCount=" + avgReadCount + ", avgLikeCount="
            + avgLikeCount + ", H=" + H + ", channelIndex=" + channelIndex + "]";
    }
}
