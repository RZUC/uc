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
package com.uc.system.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO(权限)
 * @ClassName: Permission
 * @author 落花流水
 * @date 2016年1月15日 上午10:09:14
 */
@Component
@Document(collection = "permission")
public class Permission
{
    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * @return the permission
     */
    public String getPermission()
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
     * @param permission the permission to set
     */
    public void setPermission(String permission)
    {
        this.permission = permission;
    }
    
    private String id;
    
    private String permission;// 权限ID
}
