/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月31日
    * @version 1.00 
*/
package com.zhiwei.uc.user.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description: 角色实体
 * @ClassName: Role
 * @author 落花流水
 * @date 2016年3月31日 下午3:18:12
 */
@Document(collection = "role")
public class Role
{
    @Id
    private String id;
    
    private String roleName;
    
    private List<Permission> permissionList;
}
