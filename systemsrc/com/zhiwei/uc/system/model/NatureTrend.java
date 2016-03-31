package com.zhiwei.uc.system.model;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.zhiwei.uc.system.util.MapUtil;

/**
 * 
 * @ClassName: NatureTrend
 * @Description: TODO(性质趋势)
 * @author chenweitao
 * @date 2016年2月29日 下午2:13:49
 */
@Document
public class NatureTrend
{
    
    private String id;
    
    private String eventId;// 事件id
    
    private String type;// 中性
    
    private Map<String, Double> time_num;
    
    // private String time;//时间点
    //
    // private String num;//时间点的数据量
    
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
    
    public Map<String, Double> getTime_num()
    {
        return MapUtil.treatOrderByKeyDescInStr(time_num);
    }
    
    public void setTime_num(Map<String, Double> time_num)
    {
        this.time_num = time_num;
    }
    
    @Override
    public String toString()
    {
        return "NatureTrend [id=" + id + ", eventId=" + eventId + ", type=" + type + ", time_num=" + time_num + "]";
    }
    
}
