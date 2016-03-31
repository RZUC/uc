/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月9日
    * @version 1.00 
*/
package com.zhiwei.uc.system.model;

/**
 * @Description: 相似标题
 * @ClassName: SimilarTitle
 * @author 落花流水
 * @date 2016年3月9日 下午4:35:00
 */
public class SimilarTitle
{
    /** 
     * @Title: SimilarTitle
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param  设定文件 
     * @return  
     */ 
    public SimilarTitle()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /** 
     * @Title: SimilarTitle
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param title
     * @param @param count 设定文件 
     * @return  
     */ 
    public SimilarTitle(String title, Integer count)
    {
        super();
        this.title = title;
        this.count = count;
    }

    private String title;
    
    private Integer count;
    
    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * @return the count
     */
    public Integer getCount()
    {
        return count;
    }
    
    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * @param count the count to set
     */
    public void setCount(Integer count)
    {
        this.count = count;
    }
}
