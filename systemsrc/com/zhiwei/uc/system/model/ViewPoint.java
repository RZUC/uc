package com.zhiwei.uc.system.model;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description 媒体意见领袖的观点和占比
 *              
 * @author 李自贤
 * @date 2016年2月29日
 */
@Document
public class ViewPoint
{
    
    private String id;
    
    private String saveTime;// 观点添加时间
    
    private String type; // 观点的类型
    
    private String eventId; // 事件id
    
    private Map<String, Double> pointMap; // 观点的map
    
    public String getEventId()
    {
        return eventId;
    }
    
    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getSaveTime()
    {
        return saveTime;
    }
    
    public void setSaveTime(String saveTime)
    {
        this.saveTime = saveTime;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    @Override
    public String toString()
    {
        return "Viewpoint [id" + id + ", pointMap" + pointMap + ", type" + type + "]";
    }

    /**
     * @return the pointMap
     */
    public Map<String, Double> getPointMap()
    {
        return pointMap;
    }

    /**
     * @param pointMap the pointMap to set
     */
    public void setPointMap(Map<String, Double> pointMap)
    {
        this.pointMap = pointMap;
    }
    
}
