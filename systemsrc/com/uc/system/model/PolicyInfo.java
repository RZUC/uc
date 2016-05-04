/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年4月7日
    * @version 1.00 
*/
package com.uc.system.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.TextScore;

/**
 * @Description: 政策信息 倒叙排列
 * @ClassName: PolicyInfo
 * @author 落花流水
 * @date 2016年4月7日 上午11:47:11
 */
public class PolicyInfo
{
    private String title;// 政策标题
    
    private String sourceUrl;// 发布来源ID
    
    private String department;// 发布部门
    
    private String policyType;// 政策类型
    
    private String industry;// 行业（技术领域）
    
    private String location;// 区域
    
    private int topState;// 置顶字段
    
    private int order;// 排序字段
    
    private int topStateEndTime;// 信息有效期，有效期到后自动消除置顶
    
    private String province;// 省
    
    private String city;// 市
    
    private String downtown;// 区
    
    private String content;// 内容
    
    private String releaseTime;// 发布时间
    
    private List<Resource> resourceList;// 资源文件
    
    private Date createTime;
    
    private Date lastUpdateTime;
    
    /**
     * @Title: PolicyInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param 设定文件
     * @return
     */
    public PolicyInfo()
    {
        super();
    }
    
    /** 
     * @Title: PolicyInfo
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param title
     * @param @param sourceUrl
     * @param @param department
     * @param @param policyType
     * @param @param industry
     * @param @param location
     * @param @param topState
     * @param @param order
     * @param @param topStateEndTime
     * @param @param province
     * @param @param city
     * @param @param downtown
     * @param @param content
     * @param @param releaseTime
     * @param @param resourceList
     * @param @param createTime
     * @param @param lastUpdateTime 设定文件 
     * @return  
     */ 
    public PolicyInfo(String title, String sourceUrl, String department, String policyType, String industry,
        String location, int topState, int order, int topStateEndTime, String province, String city, String downtown,
        String content, String releaseTime, List<Resource> resourceList, Date createTime, Date lastUpdateTime)
    {
        super();
        this.title = title;
        this.sourceUrl = sourceUrl;
        this.department = department;
        this.policyType = policyType;
        this.industry = industry;
        this.location = location;
        this.topState = topState;
        this.order = order;
        this.topStateEndTime = topStateEndTime;
        this.province = province;
        this.city = city;
        this.downtown = downtown;
        this.content = content;
        this.releaseTime = releaseTime;
        this.resourceList = resourceList;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }


    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * @return the sourceUrl
     */
    public String getSourceUrl()
    {
        return sourceUrl;
    }
    
    /**
     * @return the department
     */
    public String getDepartment()
    {
        return department;
    }
    
    /**
     * @return the policyType
     */
    public String getPolicyType()
    {
        return policyType;
    }
    
    /**
     * @return the industry
     */
    public String getIndustry()
    {
        return industry;
    }
    
    /**
     * @return the location
     */
    public String getLocation()
    {
        return location;
    }
    
    /**
     * @return the province
     */
    public String getProvince()
    {
        return province;
    }
    
    /**
     * @return the city
     */
    public String getCity()
    {
        return city;
    }
    
    /**
     * @return the downtown
     */
    public String getDowntown()
    {
        return downtown;
    }
    
    /**
     * @return the content
     */
    public String getContent()
    {
        return content;
    }
    
    /**
     * @return the releaseTime
     */
    public String getReleaseTime()
    {
        return releaseTime;
    }
    
    /**
     * @return the resourceList
     */
    public List<Resource> getResourceList()
    {
        return resourceList;
    }
    
    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * @param sourceUrl the sourceUrl to set
     */
    public void setSourceUrl(String sourceUrl)
    {
        this.sourceUrl = sourceUrl;
    }
    
    /**
     * @param 发布部门 the department to set
     */
    public void setDepartment(String department)
    {
        this.department = department;
    }
    
    /**
     * @param policyType the policyType to set
     */
    public void setPolicyType(String policyType)
    {
        this.policyType = policyType;
    }
    
    /**
     * @param industry the industry to set
     */
    public void setIndustry(String industry)
    {
        this.industry = industry;
    }
    
    /**
     * @param location the location to set
     */
    public void setLocation(String location)
    {
        this.location = location;
    }
    
    /**
     * @param province the province to set
     */
    public void setProvince(String province)
    {
        this.province = province;
    }
    
    /**
     * @param city the city to set
     */
    public void setCity(String city)
    {
        this.city = city;
    }
    
    /**
     * @param downtown the downtown to set
     */
    public void setDowntown(String downtown)
    {
        this.downtown = downtown;
    }
    
    /**
     * @param content the content to set
     */
    public void setContent(String content)
    {
        this.content = content;
    }
    
    /**
     * @param releaseTime the releaseTime to set
     */
    public void setReleaseTime(String releaseTime)
    {
        this.releaseTime = releaseTime;
    }
    
    /**
     * @param resourceList the resourceList to set
     */
    public void setResourceList(List<Resource> resourceList)
    {
        this.resourceList = resourceList;
    }
    
    /**
     * @return the topState
     */
    public int getTopState()
    {
        return topState;
    }
    
    /**
     * @return the order
     */
    public int getOrder()
    {
        return order;
    }
    
    /**
     * @return the topStateEndTime
     */
    public int getTopStateEndTime()
    {
        return topStateEndTime;
    }
    
    /**
     * @return the lastUpdateTime
     */
    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }
    
    /**
     * @param topState the topState to set
     */
    public void setTopState(int topState)
    {
        this.topState = topState;
    }
    
    /**
     * @param order the order to set
     */
    public void setOrder(int order)
    {
        this.order = order;
    }
    
    /**
     * @param topStateEndTime the topStateEndTime to set
     */
    public void setTopStateEndTime(int topStateEndTime)
    {
        this.topStateEndTime = topStateEndTime;
    }
    
    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    /**
     * @param lastUpdateTime the lastUpdateTime to set
     */
    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }
    
    /**
     * @return the createTime
     */
    public Date getCreateTime()
    {
        return createTime;
    }
    
}
