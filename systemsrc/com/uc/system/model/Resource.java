/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年4月7日
    * @version 1.00 
*/
package com.uc.system.model;

/**
 * @Description: 政策资源文件
 * @ClassName: Resource
 * @author 落花流水
 * @date 2016年4月7日 下午4:08:54
 */
public class Resource
{
    /** 
     * @Title: Resource
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param id
     * @param @param name
     * @param @param path 设定文件 
     * @return  
     */ 
    public Resource(String id, String name, String path)
    {
        super();
        this.id = id;
        this.name = name;
        this.path = path;
    }

    /** 
     * @Title: Resource
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param  设定文件 
     * @return  
     */ 
    public Resource()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    private String id;
    
    private String name;// 资源名称
    
    private String path;// 资源的位置

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the path
     */
    public String getPath()
    {
        return path;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path)
    {
        this.path = path;
    }

    /* (非 Javadoc) 
     * <p>Title:toString</p> 
     * <p>Description: </p> 
     * @return 
     * @see java.lang.Object#toString() 
     */ 
    @Override
    public String toString()
    {
        return "Resource [id=" + id + ", name=" + name + ", path=" + path + "]";
    }
}
