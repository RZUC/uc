package com.uc.system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "HuserInfoMedia")
public class HUserInfoBD extends HUserInfo
{
    @Id
    private String id;// 网站主域名
    
    private Short pr;
    
    private Integer aleax; // 指数
    
    private Integer monthAVGAleax;
    
    private Long monthPV;
    
    private Integer H;
    
    private String source;
    
    private Float pv;
    
    private Float normalization; // 标准化
    
    private Short mediaType;// A类媒体，B类媒体
    
//    private Float channelIndex;// 渠道指数

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @return the pr
     */
    public Short getPr()
    {
        return pr;
    }

    /**
     * @return the aleax
     */
    public Integer getAleax()
    {
        return aleax;
    }

    /**
     * @return the monthAVGAleax
     */
    public Integer getMonthAVGAleax()
    {
        return monthAVGAleax;
    }

    /**
     * @return the monthPV
     */
    public Long getMonthPV()
    {
        return monthPV;
    }

    /**
     * @return the h
     */
    public Integer getH()
    {
        return H;
    }

    /**
     * @return the source
     */
    public String getSource()
    {
        return source;
    }

    /**
     * @return the pv
     */
    public Float getPv()
    {
        return pv;
    }

    /**
     * @return the normalization
     */
    public Float getNormalization()
    {
        return normalization;
    }

    /**
     * @return the mediaType
     */
    public Short getMediaType()
    {
        return mediaType;
    }

    /**
     * @return the channelIndex
     */
//    public Float getChannelIndex()
//    {
//        return channelIndex;
//    }

    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @param pr the pr to set
     */
    public void setPr(Short pr)
    {
        this.pr = pr;
    }

    /**
     * @param aleax the aleax to set
     */
    public void setAleax(Integer aleax)
    {
        this.aleax = aleax;
    }

    /**
     * @param monthAVGAleax the monthAVGAleax to set
     */
    public void setMonthAVGAleax(Integer monthAVGAleax)
    {
        this.monthAVGAleax = monthAVGAleax;
    }

    /**
     * @param monthPV the monthPV to set
     */
    public void setMonthPV(Long monthPV)
    {
        this.monthPV = monthPV;
    }

    /**
     * @param h the h to set
     */
    public void setH(Integer h)
    {
        H = h;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source)
    {
        this.source = source;
    }

    /**
     * @param pv the pv to set
     */
    public void setPv(Float pv)
    {
        this.pv = pv;
    }

    /**
     * @param normalization the normalization to set
     */
    public void setNormalization(Float normalization)
    {
        this.normalization = normalization;
    }

    /**
     * @param mediaType the mediaType to set
     */
    public void setMediaType(Short mediaType)
    {
        this.mediaType = mediaType;
    }

    /**
     * @param channelIndex the channelIndex to set
     */
//    public void setChannelIndex(Float channelIndex)
//    {
//        this.channelIndex = channelIndex;
//    }

    /* (非 Javadoc) 
     * <p>Title:toString</p> 
     * <p>Description: </p> 
     * @return 
     * @see java.lang.Object#toString() 
     */ 
    @Override
    public String toString()
    {
        return "HUserInfoBD [id=" + id + ", pr=" + pr + ", aleax=" + aleax + ", monthAVGAleax=" + monthAVGAleax
            + ", monthPV=" + monthPV + ", H=" + H + ", source=" + source + ", pv=" + pv + ", normalization="
            + normalization + ", mediaType=" + mediaType + ", channelIndex=" + channelIndex + "]";
    }
}
