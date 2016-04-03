package com.uc.system.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @ClassName: Level
 * @Description: TODO(危机等级类)
 * @author chenweitao
 * @date 2016年2月26日 上午9:36:38
 */
@Document
public class EventLevel
{
    private String id;

    private String eventLevel;// 等级

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getEventLevel()
    {
        return eventLevel;
    }

    public void setEventLevel(String eventLevel)
    {
        this.eventLevel = eventLevel;
    }

    @Override
    public String toString()
    {
        return "EventLevel [id=" + id + ", eventLevel=" + eventLevel + "]";
    }

}
