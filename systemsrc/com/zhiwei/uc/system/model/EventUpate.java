/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月14日
    * @version 1.00 
*/
package com.zhiwei.uc.system.model;

import java.util.Map;

/**
 * @Description: 获取事件更新的数据
 * @ClassName: EventUpata
 * @author 落花流水
 * @date 2016年3月14日 上午9:02:52
 */
public class EventUpate
{
    private double infulence;// 影响力
    
    private double dataCount;// 数据总量
    
    private Map<String, Integer> wordMap;// 分词
    
    /**
     * @return the infulence
     */
    public double getInfulence()
    {
        return infulence;
    }
    
    /**
     * @return the dataCount
     */
    public double getDataCount()
    {
        return dataCount;
    }
    
    /**
     * @return the wordMap
     */
    public Map<String, Integer> getWordMap()
    {
        return wordMap;
    }
    
    /**
     * @param infulence the infulence to set
     */
    public void setInfulence(double infulence)
    {
        this.infulence = infulence;
    }
    
    /**
     * @param dataCount the dataCount to set
     */
    public void setDataCount(double dataCount)
    {
        this.dataCount = dataCount;
    }
    
    /**
     * @param wordMap the wordMap to set
     */
    public void setWordMap(Map<String, Integer> wordMap)
    {
        this.wordMap = wordMap;
    }
    
    @Override
    public String toString()
    {
        return "EventUpate [infulence=" + infulence + ", dataCount=" + dataCount + ", wordMap=" + wordMap + "]";
    }
    
}
