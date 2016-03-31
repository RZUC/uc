package com.zhiwei.uc.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description: 用户权限
 * @ClassName: User
 * @author 落花流水
 * @date 2016年1月15日 上午10:06:06
 */
@Document(collection = "permission")
public class Permission
{
    @Id
    private String id;
    
    private String description;
    
    private String code;
    
}
