/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年4月6日
    * @version 1.00 
*/
package com.uc.system.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description: 地理位置
 * @ClassName: Location
 * @author 落花流水
 * @date 2016年4月6日 下午4:25:51
 */
@Document(collection = "location")
public class Location
{
    private String id;// 编号
    
    private String locationName;// 名称
    
    private String fatherID;// 父级
    
    private String abbreviation;// 简称
    
    private Double longitude;// 精度
    
    private Double dimensionality;// 维度
    
    private int level;// 等级
    
    private int order;// 排序
    
    public Location()
    {
        super();
    }
    /**
     * @Title: Location
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param id
     * @param @param locationName
     * @param @param fatherID
     * @param @param abbreviation
     * @param @param longitude
     * @param @param dimensionality
     * @param @param level
     * @param @param order 设定文件
     * @return
     */
    public Location(String id, String locationName, String fatherID, String abbreviation, Double longitude,
        Double dimensionality, int level, int order)
    {
        super();
        this.id = id;
        this.locationName = locationName;
        this.fatherID = fatherID;
        this.abbreviation = abbreviation;
        this.longitude = longitude;
        this.dimensionality = dimensionality;
        this.level = level;
        this.order = order;
    }
    
    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * @return the locationName
     */
    public String getLocationName()
    {
        return locationName;
    }
    
    /**
     * @return the fatherID
     */
    public String getFatherID()
    {
        return fatherID;
    }
    
    /**
     * @return the abbreviation
     */
    public String getAbbreviation()
    {
        return abbreviation;
    }
    
    /**
     * @return the longitude
     */
    public Double getLongitude()
    {
        return longitude;
    }
    
    /**
     * @return the dimensionality
     */
    public Double getDimensionality()
    {
        return dimensionality;
    }
    
    /**
     * @return the level
     */
    public int getLevel()
    {
        return level;
    }
    
    /**
     * @return the order
     */
    public int getOrder()
    {
        return order;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }
    
    /**
     * @param locationName the locationName to set
     */
    public void setLocationName(String locationName)
    {
        this.locationName = locationName;
    }
    
    /**
     * @param fatherID the fatherID to set
     */
    public void setFatherID(String fatherID)
    {
        this.fatherID = fatherID;
    }
    
    /**
     * @param abbreviation the abbreviation to set
     */
    public void setAbbreviation(String abbreviation)
    {
        this.abbreviation = abbreviation;
    }
    
    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }
    
    /**
     * @param dimensionality the dimensionality to set
     */
    public void setDimensionality(Double dimensionality)
    {
        this.dimensionality = dimensionality;
    }
    
    /**
     * @param level the level to set
     */
    public void setLevel(int level)
    {
        this.level = level;
    }
    
    /**
     * @param order the order to set
     */
    public void setOrder(int order)
    {
        this.order = order;
    }
    
    @Override
    public String toString()
    {
        return "Location [id=" + id + ", locationName=" + locationName + ", fatherID=" + fatherID + ", abbreviation="
            + abbreviation + ", longitude=" + longitude + ", dimensionality=" + dimensionality + ", level=" + level
            + ", order=" + order + "]";
    }
}
