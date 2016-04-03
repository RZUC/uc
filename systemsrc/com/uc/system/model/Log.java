/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年2月23日
    * @version 1.00 
*/
package com.uc.system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.uc.system.util.TimeUtil;

/**
 * @Description: 系统Log
 * @ClassName: Login
 * @author 落花流水
 * @date 2016年2月23日 上午9:53:29
 */
@Document(collection = "systemLog")
public class Log
{
    @Id
    private String id;
    
    private String type;
    
    private String addTime;
    
    private String content;
    
    /**
     * @Title: Log
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param id
     * @param @param type
     * @param @param addTime
     * @param @param content 设定文件
     * @return
     */
    public Log(String type, String content)
    {
        this.type = type;
        this.addTime = TimeUtil.getCurrentTimeStr();
        this.content = content;
    }
    
    /**
     * @Title: Log
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param 设定文件
     * @return
     */
    public Log()
    {
        super();
    }
    
    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * @return the addTime
     */
    public String getAddTime()
    {
        return addTime;
    }
    
    /**
     * @return the content
     */
    public String getContent()
    {
        return content;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }
    
    /**
     * @param addTime the addTime to set
     */
    public void setAddTime(String addTime)
    {
        this.addTime = addTime;
    }
    
    /**
     * @param content the content to set
     */
    public void setContent(String content)
    {
        this.content = content;
    }
}
