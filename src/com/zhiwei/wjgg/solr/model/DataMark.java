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
package com.zhiwei.wjgg.solr.model;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

import com.zhiwei.wjgg.util.TimeUtil;

/**
 * @Description: 全网标注数据，京东和腾讯
 * @ClassName: DataWeiBo
 * @author 落花流水
 * @date 2016年3月1日 下午5:21:34
 */
public class DataMark extends DataCommon
{
    @Field("emotionType")
    private Object emotionType;
    
    @Field("mid")
    private String mid;
    
    @Field("markGroup")
    private String markGroup;
    
    @Field("_id")
    public void set_id(String id)
    {
        this.id = id;
    }
    
    /**
     * @Title: getTimeString
     * @Description: 返回时间类型的 时间字段
     * @param @return 设定文件
     * @return String 返回类型
     */
    public String getTimeString()
    {
        String times = "";
        if (time instanceof Date)
        {
            times = TimeUtil.formatDate(getTimeDate());
        }
        else
        {
            times = time.toString();
        }
        
        return times;
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
        Date times = null;
        if (time instanceof Date)
        {
            if (null != this.time)
            {
                times = (Date)this.time;
            }
        }
        else
        {
            times = TimeUtil.parseTime(time.toString());
        }
        
        return times;
    }
    
    /**
     * @return the emotionType
     */
    public Integer getEmotionTypeInt()
    {
        return Integer.valueOf(emotionType.toString());
    }
    
    /**
     * @return the emotionType
     */
    public String getEmotionTypeStr()
    {
        return emotionType.toString();
    }
    
    /**
     * @return the mid
     */
    public String getMid()
    {
        return mid;
    }
    
    /**
     * @return the markGroup
     */
    public String getMarkGroup()
    {
        return markGroup;
    }
    
    /**
     * @param emotionType the emotionType to set
     */
    public void setEmotionType(Object emotionType)
    {
        this.emotionType = emotionType;
    }
    
    /**
     * @param mid the mid to set
     */
    public void setMid(String mid)
    {
        this.mid = mid;
    }
    
    /**
     * @param markGroup the markGroup to set
     */
    public void setMarkGroup(String markGroup)
    {
        this.markGroup = markGroup;
    }
    
}
