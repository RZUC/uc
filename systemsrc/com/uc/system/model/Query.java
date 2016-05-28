/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年4月19日
    * @version 1.00 
*/ 
package com.uc.system.model;

import java.util.Date;

/** 
 * @Description: 查询条件  
 * @ClassName: Query 
 * @author 落花流水 
 * @date 2016年4月19日 下午9:29:40  
 */
public class Query
{
    private String policyTypeId;
    private Date startTime;
    private Date endTime;
    private String locationId;
    private String industryId;
    private String word;
    public String getPolicyTypeId()
    {
        return policyTypeId;
    }
    public void setPolicyTypeId(String policyTypeId)
    {
        this.policyTypeId = policyTypeId;
    }
    public Date getStartTime()
    {
        return startTime;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }
    public Date getEndTime()
    {
        return endTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }
    public String getLocationId() 
    {
        return locationId;
    }
    public void setLocationId(String locationId)
    {
        this.locationId = locationId;
    }
    public String getIndustryId()
    {
        return industryId;
    }
    public void setIndustryId(String industryId)
    {
        this.industryId = industryId;
    }
    public String getWord()
    {
        return word;
    }
    public void setWord(String word)
    {
        this.word = word;
    }
}
