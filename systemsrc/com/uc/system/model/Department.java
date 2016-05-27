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

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description: 部门用于政策信息
 * @ClassName: Department
 * @author 落花流水
 * @date 2016年4月7日 下午4:00:07
 */
@Document(collection="department")
public class Department
{
    private int id;
    
    private String name;// 部门名称
    
    /**
     * @Title: Department
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param id
     * @param @param name 设定文件
     * @return
     */
    public Department(int id, String name)
    {
        super();
        this.id = id;
        this.name = name;
    }
    
    /**
     * @Title: Department
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param 设定文件
     * @return
     */
    public Department()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @return the id
     */
    public int getId()
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
     * @param id the id to set
     */
    public void setId(int id)
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
}
