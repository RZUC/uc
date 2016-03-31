package com.zhiwei.wjgg.model;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @ClassName: Event
 * @Description: TODO(事件实体类)
 * @author chenweitao
 * @date 2016年2月27日 上午10:27:19
 */
@Document
public class Event
{

    private String id;

    private String name;

    private String user;// 事件所属用户

    private String eventLevel;// 事件危机等级

    private String eventType;// 事件类型

    private boolean isCorrelation; // 是否相关

    /*
     * 相关性分值
     */
    private Double correlationNum;// 相关性分值
    
    private Double channelNum;//传播渠道数
    
    private Double spread;//传播数
    
    private Double impact;//影响力

    private String state;// 事件状态：1，正在分析；0，完成

    private String saveTime;// 事件登记时间

    private String startTime;// 事件开始时间

    private String endTime;// 事件结束时间

    private String word;// 事件关键词
    
    private Map<String,Integer> topWord;//热议词

    private String content;// 事件内容
    
    private String lastUpateTime;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEventLevel()
    {
        return eventLevel;
    }

    public void setEventLevel(String eventLevel)
    {
        this.eventLevel = eventLevel;
    }

    public String getEventType()
    {
        return eventType;
    }

    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    public boolean isCorrelation()
    {
        return isCorrelation;
    }

    public void setCorrelation(boolean isCorrelation)
    {
        this.isCorrelation = isCorrelation;
    }

    public Double getCorrelationNum()
    {
        return correlationNum;
    }

    public void setCorrelationNum(Double correlationNum)
    {
        this.correlationNum = correlationNum;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getSaveTime()
    {
        return saveTime;
    }

    public void setSaveTime(String saveTime)
    {
        this.saveTime = saveTime;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public String getWord()
    {
        return word;
    }

    public void setWord(String word)
    {
        this.word = word;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
    
    public Map<String, Integer> getTopWord()
    {
        return topWord;
    }

    public void setTopWord(Map<String, Integer> topWord)
    {
        this.topWord = topWord;
    }

    public Double getChannelNum()
    {
        return channelNum;
    }

    public void setChannelNum(Double channelNum)
    {
        this.channelNum = channelNum;
    }

    public Double getSpread()
    {
        return spread;
    }

    public void setSpread(Double spread)
    {
        this.spread = spread;
    }

    public Double getImpact()
    {
        return impact;
    }

    public void setImpact(Double impact)
    {
        this.impact = impact;
    }

    @Override
    public String toString()
    {
        return "Event [id=" + id + ", name=" + name + ", user=" + user
                + ", eventLevel=" + eventLevel + ", eventType=" + eventType
                + ", isCorrelation=" + isCorrelation + ", correlationNum="
                + correlationNum + ", channelNum=" + channelNum + ", spread="
                + spread + ", impact=" + impact + ", state=" + state
                + ", saveTime=" + saveTime + ", startTime=" + startTime
                + ", endTime=" + endTime + ", word=" + word + ", topWord="
                + topWord + ", content=" + content + "]";
    }

    /**
     * @return the lastUpateTime
     */
    public String getLastUpateTime()
    {
        return lastUpateTime;
    }

    /**
     * @param lastUpateTime the lastUpateTime to set
     */
    public void setLastUpateTime(String lastUpateTime)
    {
        this.lastUpateTime = lastUpateTime;
    }


}
