package com.zhiwei.uc.system.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @ClassName: EventType
 * @Description: TODO(事件分类)
 * @author chenweitao
 * @date 2016年2月26日 上午9:51:10
 */
@Document
public class EventType
{
    /*
     * @file EventType.java
     */
    private String id;

    private String eventType;// 事件分类
    
    private String userId;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getEventType()
    {
        return eventType;
    }

    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    @Override
    public String toString()
    {
        return "EventType [id=" + id + ", eventType=" + eventType + "]";
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String user)
    {
        this.userId = user;
    }

}
