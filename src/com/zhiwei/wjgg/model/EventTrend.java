package com.zhiwei.wjgg.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.zhiwei.wjgg.util.MapUtil;

/**
 * 
 * @ClassName: EventTrend
 * @Description: TODO(事件趋势)
 * @author chenweitao
 * @date 2016年2月29日 下午2:13:49
 */
@Document
public class EventTrend
{
    @Id
    private String id;
    
    private String eventId;// 事件id
    
    private String type;// 整体
    
    private Map<String, List> time_num;
    
    private Map<String, List> source;
    
    private Double infulence;
    
    private Integer dataCount;// 事件数量
    
    private long lastRsid;// 事件的最后一个数据
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
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
    
    public Map<String, List> getTime_num()
    {
        return MapUtil.treatOrderByKeyDescInStr(time_num);
    }
    
    public void setTime_num(Map<String, List> time_num)
    {
        this.time_num = time_num;
    }
    
    @Override
    public String toString()
    {
        return "EventTrend [id=" + id + ", eventId=" + eventId + ", type=" + type + ", time_num=" + time_num + "]";
    }
    
    /**
     * @return the infulence
     */
    public Double getInfulence()
    {
        return infulence;
    }
    
    /**
     * @param infulence the infulence to set
     */
    public void setInfulence(Double infulence)
    {
        this.infulence = infulence;
    }
    
    /**
     * @return the dataCount
     */
    public Integer getDataCount()
    {
        return dataCount;
    }
    
    /**
     * @param dataCount the dataCount to set
     */
    public void setDataCount(Integer dataCount)
    {
        this.dataCount = dataCount;
    }
    
    /**
     * @return the source
     */
    public Map<String, List> getSource()
    {
        return source;
    }
    
    /**
     * @param source the source to set
     */
    public void setSource(Map<String, List> source)
    {
        this.source = source;
    }
    
    /**
     * @return the lastRsid
     */
    public long getLastRsid()
    {
        return lastRsid;
    }
    
    /**
     * @param lastRsid the lastRsid to set
     */
    public void setLastRsid(long lastRsid)
    {
        this.lastRsid = lastRsid;
    }
    
}
