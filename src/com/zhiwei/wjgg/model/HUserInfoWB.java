/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月4日
    * @version 1.00 
*/
package com.zhiwei.wjgg.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description: 微博用户H因子表
 * @ClassName: HUserInfo
 * @author 落花流水
 * @date 2016年3月4日 上午10:17:16
 */
@Document(collection = "HuserInfoWeibo")
public class HUserInfoWB extends HUserInfo
{
    private String id; // 用户ID
    
    private String username; // 用户昵称
    
    private Integer followsCount;// 粉丝数
    
    private Integer weiboCount;// 微博数
    
    private Integer friendsCount;// 关注数
    
    private String vType;// 认证类型
    
    private Integer H;// H因子
    
//    private Float channelIndex;// 渠道影响力
    
    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * @return the followsCount
     */
    public Integer getFollowsCount()
    {
        return followsCount;
    }
    
    /**
     * @return the weiboCount
     */
    public Integer getWeiboCount()
    {
        return weiboCount;
    }
    
    /**
     * @return the friendsCount
     */
    public Integer getFriendsCount()
    {
        return friendsCount;
    }
    
    /**
     * @return the vType
     */
    public String getvType()
    {
        return vType;
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
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     * @param followsCount the followsCount to set
     */
    public void setFollowsCount(Integer followsCount)
    {
        this.followsCount = followsCount;
    }
    
    /**
     * @param weiboCount the weiboCount to set
     */
    public void setWeiboCount(Integer weiboCount)
    {
        this.weiboCount = weiboCount;
    }
    
    /**
     * @param friendsCount the friendsCount to set
     */
    public void setFriendsCount(Integer friendsCount)
    {
        this.friendsCount = friendsCount;
    }
    
    /**
     * @param vType the vType to set
     */
    public void setvType(String vType)
    {
        this.vType = vType;
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
        return "HUserInfoWB [id=" + id + ", username=" + username + ", followsCount=" + followsCount + ", weiboCount="
            + weiboCount + ", friendsCount=" + friendsCount + ", vType=" + vType + ", H=" + H + ", channelIndex="
            + channelIndex + "]";
    }

    /**
     * @return the h
     */
    public Integer getH()
    {
        return H;
    }

    /**
     * @param h the h to set
     */
    public void setH(Integer h)
    {
        H = h;
    }
}
