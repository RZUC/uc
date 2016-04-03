package com.uc.system.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.uc.system.util.MapUtil;

/**
 * 
 * @ClassName: NatureKeyPoint
 * @Description: TODO(性质关键转折点)
 * @author chenweitao
 * @date 2016年2月29日 下午2:13:49
 */
@Document
public class NatureKeyPoint
{

    private String id;

    private String eventId;// 事件id

    private String type;// 整体

    private Map<String, List<String>> time_content;

    // private String time;//时间点
    //
    // private String content;//内容

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

    public Map<String, List<String>> getTime_content()
    {
        return MapUtil.treatOrderByKeyDescInStr(time_content);
    }

    public void setTime_content(Map<String, List<String>> time_content)
    {
        this.time_content = time_content;
    }

    @Override
    public String toString()
    {
        return "NatureKeyPoint [id=" + id + ", eventId=" + eventId + ", type="
                + type + ", time_content=" + time_content + "]";
    }

}
