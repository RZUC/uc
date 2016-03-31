/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年1月15日
    * @version 1.00 
*/
package com.zhiwei.wjgg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description: 用户实体
 * @ClassName: User
 * @author 落花流水
 * @date 2016年1月15日 上午10:06:06
 */
@Document(collection = "user")
public class User
{
    @Id
    private String id;
    
    private String name;
    
    private String password;
    
    /**
     * @Fields permission : 只用于显示
     */
    @Transient
    private Permission permission;
    
    private String permissionid;
    
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
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     * @return the permission
     */
    public Permission getPermission()
    {
        return permission;
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
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    /**
     * @param permission the permission to set
     */
    public void setPermission(Permission permission)
    {
        this.permission = permission;
    }
    
    /**
     * @return the permissionid
     */
    public String getPermissionid()
    {
        return permissionid;
    }
    
    /**
     * @param permissionid the permissionid to set
     */
    public void setPermissionid(String permissionid)
    {
        this.permissionid = permissionid;
    }

    @Override
    public String toString()
    {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", permission=" + permission
            + ", permissionid=" + permissionid + "]";
    }
    
}
