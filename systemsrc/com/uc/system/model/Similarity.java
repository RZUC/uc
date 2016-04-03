package com.uc.system.model;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @ClassName: Similarity
 * @Description: TODO(相似传播)
 * @author chenweitao
 * @date 2016年3月8日 上午11:43:24
 */
@Document
public class Similarity
{
    private String id;

    private Map<String, Integer> weixin;

    private Map<String, Integer> media;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Map<String, Integer> getWeixin()
    {
        return weixin;
    }

    public void setWeixin(Map<String, Integer> weixin)
    {
        this.weixin = weixin;
    }

    public Map<String, Integer> getMedia()
    {
        return media;
    }

    public void setMedia(Map<String, Integer> media)
    {
        this.media = media;
    }
    
    
}
