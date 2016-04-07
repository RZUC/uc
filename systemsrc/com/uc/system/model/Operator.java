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

import com.uc.user.model.Role;

/**
 * @Description: 维护人员
 * @ClassName: Operator
 * @author 落花流水
 * @date 2016年4月7日 下午4:19:25
 */
@Document(collection = "operator")
public class Operator extends User
{
    private Role role;
}
