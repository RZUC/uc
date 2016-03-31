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

import com.zhiwei.wjgg.model.HUserInfoBD;
import com.zhiwei.wjgg.model.HUserInfoWX;
import com.zhiwei.wjgg.util.TimeUtil;

/**
 * @Description: SolrWeiBo数据
 * @ClassName: DataWeiBo
 * @author 落花流水
 * @date 2016年3月1日 下午5:21:34
 */
public class DataWeiXin extends DataCommon
{
    @Field("userinfo")
    private HUserInfoWX HUserInfo;
    
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
        String time = "";
        if (null != this.time)
        {
            time = TimeUtil.formatTime(getTimeDate());
        }
        else
        {
        
        }
        return time;
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
        Date time = null;
        if (null != this.time)
        {
            time = (Date)this.time;
        }
        return time;
    }
    
    /**
     * @return the hUserInfo
     */
    public HUserInfoWX getHUserInfo()
    {
        return HUserInfo;
    }
    
    /**
     * @param hUserInfo the hUserInfo to set
     */
    public void setHUserInfo(HUserInfoWX hUserInfo)
    {
        HUserInfo = hUserInfo;
    }
}
