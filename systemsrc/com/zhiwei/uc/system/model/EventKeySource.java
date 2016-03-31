package com.zhiwei.uc.system.model;

import java.util.List;

/**
 * 
 * @ClassName: Event
 * @Description: TODO(事件实体类)
 * @author chenweitao
 * @date 2016年2月27日 上午10:27:19
 */
public class EventKeySource
{
    private String eventId;
    
    private String type;
    
    private List<String> ids;
    
    /**
     * @return the eventId
     */
    public String getEventId()
    {
        return eventId;
    }
    
    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * @return the ids
     */
    public List<String> getIds()
    {
        return ids;
    }
    
    /**
     * @param eventId the eventId to set
     */
    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }
    
    /**
     * @param ids the ids to set
     */
    public void setIds(List<String> ids)
    {
        this.ids = ids;
    }
    
    @Override
    public String toString()
    {
        return "EventKeySource [eventId=" + eventId + ", type=" + type + ", ids=" + ids + "]";
    }
}
