package com.uc.system.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName: Favorite
 * @Description: 个人收藏
 * @author Administrator
 * @date 2016年6月19日 下午8:18:20
 */
@Document
public class Favorite
{
    @Id
    private int id;
    
    @Indexed
    private int uid;
    
    @Indexed
    private int policyinfoId;
    
    private String summary;// 简介
    
    public int getUid()
    {
        return uid;
    }
    
    public void setUid(int uid)
    {
        this.uid = uid;
    }
    
    public int getPolicyinfoId()
    {
        return policyinfoId;
    }
    
    public void setPolicyinfoId(int policyinfoId)
    {
        this.policyinfoId = policyinfoId;
    }
    
    public Favorite(int uid, int policyinfoId, String summary)
    {
        super();
        this.uid = uid;
        this.policyinfoId = policyinfoId;
    }
    
    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * @return the summary
     */
    public String getSummary()
    {
        return summary;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary)
    {
        this.summary = summary;
    }
    
    /*
     * (非 Javadoc) <p>Title:toString</p> <p>Description: </p>
     * 
     * @return
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Favorite [id=" + id + ", uid=" + uid + ", policyinfoId=" + policyinfoId + ", summary=" + summary + "]";
    }
    
}
