package com.uc.system.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @ClassName: seq 
 * @Description: TODO(微信网媒数据自增键实体类) 
 * @author chenweitao 
 * @date 2016年3月3日 下午4:10:53
 */
@Document
public class Seq
{
    private String id;
    
    private long seq;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public long getSeq()
    {
        return seq;
    }

    public void setSeq(long seq)
    {
        this.seq = seq;
    }
    
    
}
